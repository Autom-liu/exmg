package com.edu.exmg.gateway.api;

import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.core.bean.AnswerRecord;
import com.edu.exmg.core.bean.AnswerRecordExample;
import com.edu.exmg.core.bean.QuestionInfoExample;
import com.edu.exmg.core.dto.QuestionInfoDTO;
import com.edu.exmg.core.enums.ExamCategoryEnums;
import com.edu.exmg.core.mapper.AnswerRecordMapper;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.ExamInfoService;
import com.edu.exmg.core.service.QuestionInfoService;
import com.edu.exmg.core.vo.*;
import com.edu.exmg.gateway.dto.UserAnswerSubmitDTO;
import com.edu.exmg.gateway.enums.ErrorEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author autom
 * @date 2022-01-02
 **/
@Slf4j
@RestController
@RequestMapping("question")
public class QuestionInfoAPI {

    @Autowired
    private ExamInfoService examInfoService;
    @Autowired
    private QuestionInfoService questionInfoService;

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @PostMapping("startExam")
    public IResult startExam(@RequestBody ExamQuestionQuery query) {
        Integer examId = query.getExamId();
        if (examId == null) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId");
        }
        List<ExamQuestionVO> examQuestionList = questionInfoService.queryAllQuestionWithUnknownOption(query);

        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setExamId(examId);
        answerRecord.setUserId(query.getUserId());
        answerRecord.setStartTime(new Date());
        answerRecordMapper.insert(answerRecord);
        AnswerRecordVO result = new AnswerRecordVO();
        result.setRecord(answerRecord);
        result.setQuestionList(examQuestionList);

        return Result.success(result);
    }

    @PostMapping("random")
    public IResult randomQuestion(@RequestBody QuestionInfoQuery query) {
        query.setCommon(true);
        query.setStatus(0);
        List<ExamQuestionVO> examQuestionList = questionInfoService.queryAllQuestionWithKnownOption(query);
        return ResultList.success(examQuestionList);
    }

    @PostMapping("category")
    public IResult category() {
        ExamCategoryEnums[] values = ExamCategoryEnums.values();
        List<ExamCategoryEnums> result = new ArrayList<>();
        for (ExamCategoryEnums examCategoryEnums : values) {
            if (!examCategoryEnums.isTop()) {
                QuestionInfoExample example = new QuestionInfoExample();
                example.createCriteria().andCategoryIdEqualTo(examCategoryEnums.getCode())
                        .andCommonEqualTo(true)
                        .andStatusEqualTo((byte) 0);
                long rows = questionInfoService.countByCriteria(example);
                if (rows > 0) {
                    result.add(examCategoryEnums);
                }
            }
        }

        return ResultList.success(result);
    }


    @PostMapping("submitAnswer")
    public IResult submitAnswer(@RequestBody UserAnswerSubmitDTO userAnswerSubmitDTO) {
        Integer examId = userAnswerSubmitDTO.getExamId();
        String userId = userAnswerSubmitDTO.getUserId();
        Integer recordId = userAnswerSubmitDTO.getRecordId();
        if (examId == null || StringUtils.isBlank(userId)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId,userId");
        }
        questionInfoService.submitUserAnswer(examId, userAnswerSubmitDTO.getUserId(), recordId, userAnswerSubmitDTO.getAnswerList());
        ConvergeAnswerVO convergeAnswer = questionInfoService.simpleConverge(examId, userId, recordId);
        convergeAnswer.setRecordId(recordId);
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setId(recordId);
        answerRecord.setExamId(examId);
        answerRecord.setUserId(userId);
        answerRecord.setEndTime(new Date());
        answerRecord.setTotalNum(convergeAnswer.getTotalNum());
        answerRecord.setRightNum(convergeAnswer.getRightNum());
        answerRecordMapper.updateByPrimaryKeySelective(answerRecord);
        return Result.success(convergeAnswer);
    }

    @PostMapping("user/answer/record")
    public IResult answerRecord(@RequestBody ExamQuestionQuery query) throws BizException {

        Integer examId = query.getExamId();
        String userId = query.getUserId();
        Integer recordId = query.getRecordId();
        if (examId == null && recordId == null && StringUtils.isBlank(userId)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId,userId,recordId");
        }

        AnswerRecordExample example = new AnswerRecordExample();
        example.createCriteria().andIdEqualTo(query.getRecordId())
                .andUserIdEqualTo(query.getUserId())
                .andExamIdEqualTo(query.getExamId())
                .andEndTimeIsNotNull();

        List<AnswerRecord> answerRecords = answerRecordMapper.selectByExample(example);
        List<AnswerRecordVO> resultList = new ArrayList<>();
        Map<Integer, ExamInfoVO> examMap = new HashMap<>();
        for (AnswerRecord answerRecord : answerRecords) {
            AnswerRecordVO answerRecordVO = new AnswerRecordVO();
            if (examMap.containsKey(answerRecord.getExamId())) {
                answerRecordVO.setExamInfo(examMap.get(answerRecord.getExamId()));
            } else {
                ExamInfoVO examInfo = examInfoService.findOne(answerRecord.getExamId());
                answerRecordVO.setExamInfo(examInfo);
                examMap.put(answerRecord.getExamId(), examInfo);
            }
            answerRecordVO.setRecord(answerRecord);
            resultList.add(answerRecordVO);
        }

        return ResultList.success(resultList);
    }

    @PostMapping("user/answer/detail")
    public IResult userAnswerDetail(@RequestBody ExamQuestionQuery query) {
        Integer examId = query.getExamId();
        String userId = query.getUserId();
        Integer recordId = query.getRecordId();
        if (examId == null || recordId == null || StringUtils.isBlank(userId)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId,userId,recordId");
        }
        List<ExamQuestionVO> result = questionInfoService.queryUserAnswerDetail(query);
        return ResultList.success(result);
    }

    @PostMapping("create")
    public IResult create(QuestionInfoDTO QuestionInfoDTO) throws BizException {
        questionInfoService.insert(QuestionInfoDTO);
        return Result.success("success");
    }

    @PostMapping("edit")
    public IResult edit(QuestionInfoDTO QuestionInfoDTO) throws BizException {
        questionInfoService.updateByIdSelective(QuestionInfoDTO);
        return Result.success("success");
    }

    @PostMapping("remove")
    public IResult remove(Integer id)  throws BizException {
        questionInfoService.deleteById(id);
        return Result.success("success");
    }

    @PostMapping("list")
    public IResult list(QuestionInfoQuery query) {
        List<QuestionInfoVO> examInfos = questionInfoService.queryList(query);
        return ResultList.success(examInfos);
    }

    @PostMapping("one")
    public IResult one(Integer id) throws BizException {
        QuestionInfoVO one = questionInfoService.findOne(id);
        return Result.success(one);
    }

}
