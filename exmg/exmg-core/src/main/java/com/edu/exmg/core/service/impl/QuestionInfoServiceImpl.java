package com.edu.exmg.core.service.impl;

import java.util.*;

import com.edu.exmg.common.util.ConverterUtils;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.core.bean.*;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.dto.OptionInfoDTO;
import com.edu.exmg.core.dto.UserAnswerDTO;
import com.edu.exmg.core.mapper.*;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.vo.ConvergeAnswerVO;
import com.edu.exmg.core.vo.ExamQuestionVO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.QuestionInfoDTO;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.QuestionInfoService;
import com.edu.exmg.core.vo.QuestionInfoVO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionInfoServiceImpl extends CommonService<QuestionInfo, QuestionInfoDTO, QuestionInfoVO> implements QuestionInfoService {


    private QuestionInfoMapper questionInfoMapper;

    private OptionInfoMapper optionInfoMapper;

    private QuestionInfoExtMapper questionInfoExtMapper;

    private ExamQuestionMapper examQuestionMapper;

	private ExamInfoMapper examInfoMapper;

	@Autowired
	private UserAnswerMapper userAnswerMapper;

	public QuestionInfoServiceImpl(QuestionInfoMapper questionInfoMapper, OptionInfoMapper optionInfoMapper, QuestionInfoExtMapper questionInfoExtMapper, ExamQuestionMapper examQuestionMapper, ExamInfoMapper examInfoMapper) {
		super(questionInfoMapper, QuestionInfo.class, QuestionInfoDTO.class, QuestionInfoVO.class);
		this.questionInfoMapper = questionInfoMapper;
		this.optionInfoMapper = optionInfoMapper;
		this.questionInfoExtMapper = questionInfoExtMapper;
		this.examQuestionMapper = examQuestionMapper;
		this.examInfoMapper = examInfoMapper;
	}

	/**
	 * 新建完整题目
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public IResult createQuestion(QuestionInfoDTO dto) {
		dto.setId(null);
		QuestionInfo questionInfo = ConverterUtils.copyBean(dto, QuestionInfo.class);
		questionInfo.setStatus((byte) 0);
		Integer questionType = questionInfo.getQuestionType();
		questionInfoMapper.insertSelective(questionInfo);
		Integer questionId = questionInfo.getId();
		List<OptionInfoDTO> options = dto.getOptions();
		for (OptionInfoDTO option : options) {
			option.setId(null);
			option.setQuestionId(questionId);
			OptionInfo optionInfo = ConverterUtils.copyBean(option, OptionInfo.class);
			if (questionType == 3) {
				optionInfo.setRight(true);
			}
			optionInfo.setStatus(false);
			optionInfoMapper.insertSelective(optionInfo);
			option.setId(optionInfo.getId());
		}
		return Result.success(questionInfo);
	}

	/**
	 * 编辑完整题目
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public IResult editQuestion(QuestionInfoDTO dto) {
		QuestionInfo questionInfo = ConverterUtils.copyBean(dto, QuestionInfo.class);
		questionInfoMapper.updateByPrimaryKeySelective(questionInfo);

		Integer questionId = questionInfo.getId();
		OptionInfoExample opExample = new OptionInfoExample();
		opExample.createCriteria().andQuestionIdEqualTo(questionId);
		optionInfoMapper.deleteByExample(opExample);

		List<OptionInfoDTO> options = dto.getOptions();
		for (OptionInfoDTO option : options) {
			option.setId(null);
			option.setQuestionId(questionId);
			OptionInfo optionInfo = ConverterUtils.copyBean(option, OptionInfo.class);
			optionInfoMapper.insertSelective(optionInfo);
			option.setId(optionInfo.getId());
		}
		return Result.success(questionInfo);
	}

	/**
	 * 删除完整题目
	 * @param questionId
	 * @return
	 */
	@Override
	@Transactional
	public IResult removeQuestion(Integer questionId) {
		questionInfoMapper.deleteByPrimaryKey(questionId);

		OptionInfoExample opExample = new OptionInfoExample();
		opExample.createCriteria().andQuestionIdEqualTo(questionId);
		optionInfoMapper.deleteByExample(opExample);
		return Result.success(questionId);
	}

	@Override
	public PageVO<ExamQuestionVO> pageExamQuestion(ExamQuestionQuery query) {

		super.handlePageOrder(query, false);

		List<ExamQuestionVO> examQuestions = questionInfoExtMapper.examUnionQuestion(query);

		PageInfo<ExamQuestionVO> pageInfo = new PageInfo<>(examQuestions);

		return new PageVO<>(pageInfo);
	}

	@Override
	public List<ExamQuestionVO> queryAllQuestionWithUnknownOption(ExamQuestionQuery query) {
		return queryAllQuestionWithOption(query, false);
	}

	@Override
	public List<ExamQuestionVO> queryUserAnswerDetail(ExamQuestionQuery query) {
		return queryAllQuestionWithOption(query, true);
	}

	public List<ExamQuestionVO> queryAllQuestionWithOption(ExamQuestionQuery query, boolean showRight) {
		List<Integer> questionIds = queryQuestionIds(query);
		query.setQuestionIds(questionIds);
		List<ExamQuestionVO> result;
		Map<Integer, Integer> orderMap = orderQuestionMap(questionIds);
		if (showRight) {
			result = questionInfoExtMapper.questionUnionUserOption(query);
		} else {
			result = questionInfoExtMapper.questionUnionOption(query);
		}
		for (ExamQuestionVO questionVO : result) {
			questionVO.setSorted(orderMap.get(questionVO.getQuestionId()));
			questionVO.setExamId(query.getExamId());
		}
		result.sort(Comparator.comparingInt(ExamQuestionVO::getSorted));
		return result;
	}

	private List<Integer> queryQuestionIds(ExamQuestionQuery query) {
		List<Integer> questionIds = questionInfoExtMapper.examQuestionIds(query.getExamId());
		return questionIds;
	}

	@Override
	public List<ExamQuestionVO> randomQuestion(QuestionInfoQuery query) {
		List<Integer> questionIds = questionInfoExtMapper.questionInfoIds(query);
		Integer limit = query.getPageSize();
		List<Integer> randomIds = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		while (randomIds.size() < limit) {
			int index = RandomUtils.nextInt(0, questionIds.size());
			if (!visited.contains(index)) {
				visited.add(index);
				randomIds.add(questionIds.get(index));
			}
		}
		Map<Integer, Integer> orderMap = orderQuestionMap(randomIds);
		ExamQuestionQuery eqQuery = query.toExamQuestionQuery();
		eqQuery.setQuestionIds(randomIds);
		List<ExamQuestionVO> result = questionInfoExtMapper.questionUnionOption(eqQuery);
		for (ExamQuestionVO questionVO : result) {
			questionVO.setSorted(orderMap.get(questionVO.getQuestionId()));
		}
		result.sort(Comparator.comparingInt(ExamQuestionVO::getSorted));

		return result;
	}

	private Map<Integer, Integer> orderQuestionMap(List<Integer> questionIds) {
		Map<Integer, Integer> result = new HashMap<>();
		for (int index = 0; index < questionIds.size(); index++) {
			Integer qid = questionIds.get(index);
			result.put(qid, index);
		}
		return result;
	}

	@Override
	@Transactional
	public IResult submitUserAnswer(Integer examId, String userId, List<UserAnswerDTO> userAnswers) {
		UserAnswerExample example = new UserAnswerExample();
		example.createCriteria().andUserIdEqualTo(userId).andExamIdEqualTo(examId);
		userAnswerMapper.deleteByExample(example);

		for (UserAnswerDTO userAnswer : userAnswers) {
			Integer optionId = userAnswer.getOptionId();
			UserAnswer bean = ConverterUtils.copyBean(userAnswer, UserAnswer.class);
			OptionInfo optionInfo = optionInfoMapper.selectByPrimaryKey(optionId);
			bean.setRight(userAnswer.selectedRight(optionInfo.getRight()));
			userAnswerMapper.insertSelective(bean);
		}
		return Result.success("");
	}

	@Override
	public ConvergeAnswerVO simpleConverge(Integer examId, String userId) {
		ConvergeAnswerVO convergeAnswer = new ConvergeAnswerVO();
		ExamQuestionQuery query = new ExamQuestionQuery();
		query.setUserId(userId);
		query.setExamId(examId);

		List<ConvergeAnswerVO> convergeList = questionInfoExtMapper.convergeAnswerResult(query);
		Integer rightNum = 0;
		Integer totalNum = convergeList.size();
		for (ConvergeAnswerVO convergeResult : convergeList) {
			if (Objects.equals(convergeResult.getRightNum(), convergeResult.getTotalNum())) {
				rightNum++;
			}
		}

		convergeAnswer.setRightNum(rightNum);
		convergeAnswer.setWrongNum(totalNum - rightNum);
		convergeAnswer.setTotalNum(totalNum);
		convergeAnswer.setTotalScore((int) ((float)rightNum / (float) totalNum * 100));
		return convergeAnswer;
	}


	@Override
	public IResult resignExamQuestion(Integer examId, List<ExamQuestionDTO> exqtList) {
		ExamQuestionExample example = new ExamQuestionExample();
		example.createCriteria().andExamIdEqualTo(examId);
		examQuestionMapper.deleteByExample(example);
		ExamInfo examInfo = examInfoMapper.selectByPrimaryKey(examId);
		Integer totalScore = examInfo.getTotalScore();
		Integer subScore = totalScore / exqtList.size();
		Integer remainScore = totalScore;

		for (ExamQuestionDTO examQuestionDTO : exqtList) {
			ExamQuestion examQuestion = ConverterUtils.copyBean(examQuestionDTO, ExamQuestion.class);
			examQuestion.setStatus(false);
			examQuestion.setVersion(0);
			if (remainScore > subScore) {
				examQuestion.setScore(subScore);
			} else {
				examQuestion.setScore(remainScore);
			}
			remainScore = totalScore - subScore;
			examQuestionMapper.insertSelective(examQuestion);
		}
		return Result.success("success");
	}

	@Override
	public PageVO<QuestionInfoVO> queryPage(QuestionInfoQuery query) {
		
		QuestionInfoExample example = new QuestionInfoExample();

		super.handlePageOrder(query, true, example);

		example.createCriteria().andUserIdEqualTo(query.getUserId())
				.andQuestionPreffixLike(query.getQuestion())
				.andQuestionTypeEqualTo(query.getQuestionType())
				.andTopCategoryEqualTo(query.getTopCategory())
				.andCategoryIdEqualTo(query.getCategoryId())
				.andCommonEqualTo(query.getCommon())
				.andCreateTimeBetween(query.getBeginDate(), query.getEndDate());

		List<QuestionInfo> questionInfoBean = questionInfoMapper.selectByExample(example);
		PageInfo<QuestionInfo> pageInfo = new PageInfo<>(questionInfoBean);
		List<QuestionInfoVO> resultList = ConverterUtils.copyList(questionInfoBean, QuestionInfoVO.class);
		for (QuestionInfoVO result : resultList) {
			OptionInfoExample optionInfoExample = new OptionInfoExample();
			optionInfoExample.createCriteria().andQuestionIdEqualTo(result.getId());
			List<OptionInfo> options = optionInfoMapper.selectByExample(optionInfoExample);
			result.setOptions(options);
		}
		return new PageVO<>(pageInfo, resultList);
	}
	

	@Override
	public List<QuestionInfoVO> queryList(QuestionInfoQuery query) {
		query.setPageFlag(false);
		PageVO<QuestionInfoVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
