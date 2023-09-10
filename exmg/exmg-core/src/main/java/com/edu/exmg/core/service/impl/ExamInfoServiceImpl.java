package com.edu.exmg.core.service.impl;

import java.util.Date;
import java.util.List;

import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.ConverterUtils;
import com.edu.exmg.common.util.DateUtils;
import com.edu.exmg.core.bean.ExamInfo;
import com.edu.exmg.core.bean.ExamInfoExample;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.mapper.ExamInfoExtMapper;
import com.edu.exmg.core.mapper.QuestionInfoMapper;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.ExamQuestionService;
import com.edu.exmg.core.service.QuestionInfoService;
import com.edu.exmg.core.vo.ExamQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.ExamInfoDTO;
import com.edu.exmg.core.mapper.ExamInfoMapper;
import com.edu.exmg.core.query.ExamInfoQuery;
import com.edu.exmg.core.service.ExamInfoService;
import com.edu.exmg.core.vo.ExamInfoVO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamInfoServiceImpl extends CommonService<ExamInfo, ExamInfoDTO, ExamInfoVO> implements ExamInfoService {


    private ExamInfoMapper examInfoMapper;

	private QuestionInfoService questionInfoService;

	@Autowired
	private ExamQuestionService examQuestionService;

	private ExamInfoExtMapper examInfoExtMapper;

	public ExamInfoServiceImpl(ExamInfoMapper examInfoMapper, QuestionInfoService questionInfoService, ExamInfoExtMapper examInfoExtMapper) {
		super(examInfoMapper, ExamInfo.class, ExamInfoDTO.class, ExamInfoVO.class);
		this.examInfoMapper = examInfoMapper;
		this.questionInfoService = questionInfoService;
		this.examInfoExtMapper = examInfoExtMapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ExamInfoVO userRandomExam(QuestionInfoQuery query) throws BizException {
		// 创建考试
		String userId = query.getUserId();
		ExamInfo examInfo = createExamInfo(userId);
		examInfoMapper.insert(examInfo);
		ExamInfoVO result = ConverterUtils.copyBean(examInfo, ExamInfoVO.class);

		// 随机选取题目
		List<ExamQuestionVO> randomQuestions = questionInfoService.randomQuestion(query);
		// 分配考试题目
		List<ExamQuestionDTO> examQuestions = ConverterUtils.copyList(randomQuestions, ExamQuestionDTO.class);
		for (ExamQuestionDTO examQuestion : examQuestions) {
			examQuestion.setExamId(examInfo.getId());
			examQuestion.setScore(examInfo.getTotalScore() / examQuestions.size());
			examQuestionService.insert(examQuestion);
		}



		return result;
	}

	@Override
	public List<ExamInfoVO> userExamList(ExamInfoQuery query) {
		List<ExamInfo> examInfos = examInfoExtMapper.selectUserExamList(query);
		List<ExamInfoVO> resultList = ConverterUtils.copyList(examInfos, ExamInfoVO.class);
		return resultList;
	}

	private ExamInfo createExamInfo(String userId) {
		ExamInfo examInfo = new ExamInfo();
		Date now = new Date();
		String datetimeStr = DateUtils.toString(now, DateUtils.DatePattern.SIMPLE_DATETIME_FORMATER);
		examInfo.setExamName(datetimeStr + "随机试题");
		examInfo.setExamRemark(datetimeStr + "随机试题");
		examInfo.setAnswerMode(1);
		examInfo.setBanner(false);
		examInfo.setBegintime(now);
		examInfo.setAutoGenerate(true);
		examInfo.setCreateTime(now);
		examInfo.setEndtime(now);
		examInfo.setMaxCount(0);
		examInfo.setPicUrl("");
		examInfo.setStatus(true);
		examInfo.setTotalScore(100);
		examInfo.setUserFrom(1);
		examInfo.setUserId(userId);
		examInfo.setVersion(0);
		return examInfo;
	}

	@Override
	public PageVO<ExamInfoVO> queryPage(ExamInfoQuery query) {
		
		ExamInfoExample example = new ExamInfoExample();
		
		super.handlePageOrder(query, false, example);
		
		example.createCriteria().andUserFromEqualTo(query.getUserFrom())
				.andUserIdEqualTo(query.getUserId())
				.andAnswerModeEqualTo(query.getAnswerMode())
				.andBannerEqualTo(query.getBanner())
				.andAutoGenerateEqualTo(query.getAutoGenerate())
				.andBegintimeLessThan(query.getQueryDate())
				.andEndtimeGreaterThan(query.getQueryDate())
				.andStatusEqualTo(query.getStatus());
		
		List<ExamInfo> resultList = examInfoMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<ExamInfoVO> queryList(ExamInfoQuery query) {
		query.setPageFlag(false);
		PageVO<ExamInfoVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}


}
