package com.edu.exmg.core.service;

import java.util.List;


import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.QuestionInfo;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.dto.QuestionInfoDTO;
import com.edu.exmg.core.dto.UserAnswerDTO;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.vo.ConvergeAnswerVO;
import com.edu.exmg.core.vo.ExamQuestionVO;
import com.edu.exmg.core.vo.QuestionInfoVO;

/**
 * if your service don't need this method  you can remove it manually
 * @author autom
 */
public interface QuestionInfoService extends IService<QuestionInfo, QuestionInfoDTO, QuestionInfoVO> {
	
	PageVO<QuestionInfoVO> queryPage(QuestionInfoQuery query);
	
	List<QuestionInfoVO> queryList(QuestionInfoQuery query);

	IResult createQuestion(QuestionInfoDTO dto);

	IResult removeQuestion(Integer questionId);

	IResult editQuestion(QuestionInfoDTO dto);

	/**
	 * 分页考试题目列表查询
	 * @param query
	 * @return
	 */
	PageVO<ExamQuestionVO> pageExamQuestion(ExamQuestionQuery query);

	/**
	 * 小程序端查询试题下的所有题目及其选项，开始答题
	 * @param query
	 * @return
	 */
	List<ExamQuestionVO> queryAllQuestionWithUnknownOption(ExamQuestionQuery query);

	/**
	 * 查询用户答题详细情况
	 * @param query
	 * @return
	 */
	List<ExamQuestionVO> queryUserAnswerDetail(ExamQuestionQuery query);


    /**
     * 随机选取题目
     * @param query
     * @return
     */
    List<ExamQuestionVO> randomQuestion(QuestionInfoQuery query);


	/**
	 * 用户提交的答案
	 * @param examId
	 * @param userAnswers
	 * @return
	 */
	IResult submitUserAnswer(Integer examId, String userId, List<UserAnswerDTO> userAnswers);

	/**
	 * 简单统计用户答题数据
	 * @param examId
	 * @param userId
	 * @return
	 */
	ConvergeAnswerVO simpleConverge(Integer examId, String userId);


	/**
	 * 重新分配考试题目
	 * @param examId
	 * @param exqtList
	 * @return
	 */
	IResult resignExamQuestion(Integer examId, List<ExamQuestionDTO> exqtList);
	
}
