package com.edu.exmg.core.mapper;

import com.edu.exmg.core.bean.QuestionInfoExample;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.vo.ConvergeAnswerVO;
import com.edu.exmg.core.vo.ExamQuestionVO;
import com.edu.exmg.core.vo.QuestionInfoVO;

import java.util.List;

/**
 * @author autom
 * @date 2022-01-09
 **/
public interface QuestionInfoExtMapper {

    /**
     * 题目选项关联查询
     *
     * @param example
     * @return
     */
    List<QuestionInfoVO> questionUnionOptions(QuestionInfoExample example);

    /**
     * 考试题目关联查询
     *
     * @param query
     * @return
     */
    List<ExamQuestionVO> examUnionQuestion(ExamQuestionQuery query);

    /**
     * 根据考试编号查询题目列表
     * @param examId
     * @return
     */
    List<Integer> examQuestionIds(Integer examId);

    /**
     * 根据题目条件查询题目id
     * @param query
     * @return
     */
    List<Integer> questionInfoIds(QuestionInfoQuery query);



    /**
     * 题目关联选项查询
     * @param query
     * @return
     */
    List<ExamQuestionVO> questionUnionOption(ExamQuestionQuery query);

    /**
     * 题目关联选项查询,含用户正确选项
     * @param query
     * @return
     */
    List<ExamQuestionVO> questionUnionUserOption(ExamQuestionQuery query);

    /**
     * 统计用户题目正确数
     * @param query
     * @return
     */
    List<ConvergeAnswerVO> rightUserAnswerConverge(ExamQuestionQuery query);

    /**
     * 统计问题题目正确数
     * @param query
     * @return
     */
    List<ConvergeAnswerVO> rightQuestionAnswerConverge(ExamQuestionQuery query);

    /**
     * 统计用户错误选项数
     * @param query
     * @return
     */
    List<ConvergeAnswerVO> wrongUserAnswerConverge(ExamQuestionQuery query);

    /**
     * 用户答题结果汇总
     * @param query
     * @return
     */
    List<ConvergeAnswerVO> convergeAnswerResult(ExamQuestionQuery query);

    /**
     * 统计试题题目总数
     * @param examId
     * @return
     */
    int countExamQuestion(Integer examId);
}
