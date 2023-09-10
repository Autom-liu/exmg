package com.edu.exmg.core.service;

import java.util.List;

import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.ExamQuestion;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.vo.ExamQuestionVO;

/**
 * if your service don't need this method  you can remove it manually
 * @author autom
 */
public interface ExamQuestionService extends IService<ExamQuestion, ExamQuestionDTO, ExamQuestionVO> {
	
	PageVO<ExamQuestionVO> queryPage(ExamQuestionQuery query);
	
	List<ExamQuestionVO> queryList(ExamQuestionQuery query);

	/**
	 * 分配考试题目
	 *
	 * @param attrs
	 * @param questionIds
	 * @return
	 */
	IResult assignExamQuestion(ExamQuestionDTO attrs, List<Integer> questionIds);

}
