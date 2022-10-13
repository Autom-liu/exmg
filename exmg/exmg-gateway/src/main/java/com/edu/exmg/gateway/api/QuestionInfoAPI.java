package com.edu.exmg.gateway.api;

import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.core.dto.QuestionInfoDTO;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.QuestionInfoService;
import com.edu.exmg.core.vo.ConvergeAnswerVO;
import com.edu.exmg.core.vo.ExamQuestionVO;
import com.edu.exmg.core.vo.QuestionInfoVO;
import com.edu.exmg.gateway.dto.UserAnswerSubmitDTO;
import com.edu.exmg.gateway.enums.ErrorEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author autom
 * @date 2022-01-02
 **/
@Slf4j
@RestController
@RequestMapping("question")
public class QuestionInfoAPI {

    @Autowired
    private QuestionInfoService questionInfoService;

    @PostMapping("startExam")
    public IResult startExam(@RequestBody ExamQuestionQuery query) {
        Integer examId = query.getExamId();
        if (examId == null) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId");
        }
        List<ExamQuestionVO> examQuestionList = questionInfoService.queryAllQuestionWithUnknownOption(query);
        return ResultList.success(examQuestionList);
    }

    @PostMapping("random")
    public IResult randomQuestion(@RequestBody ExamQuestionQuery query) {
        query.setCommon(true);
        query.setStatus(0);
        List<ExamQuestionVO> examQuestionList = questionInfoService.queryAllQuestionWithUnknownOption(query);
        return ResultList.success(examQuestionList);
    }

    @PostMapping("submitAnswer")
    public IResult submitAnswer(@RequestBody UserAnswerSubmitDTO userAnswerSubmitDTO) {
        Integer examId = userAnswerSubmitDTO.getExamId();
        String userId = userAnswerSubmitDTO.getUserId();
        if (examId == null || StringUtils.isBlank(userId)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId,userId");
        }
        questionInfoService.submitUserAnswer(examId, userAnswerSubmitDTO.getUserId(), userAnswerSubmitDTO.getAnswerList());
        ConvergeAnswerVO convergeAnswer = questionInfoService.simpleConverge(examId, userId);
        return Result.success(convergeAnswer);
    }

    @PostMapping("user/answer/detail")
    public IResult userAnswerDetail(@RequestBody ExamQuestionQuery query) {
        Integer examId = query.getExamId();
        String userId = query.getUserId();
        if (examId == null || StringUtils.isBlank(userId)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "examId,userId");
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
