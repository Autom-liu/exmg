package com.edu.exmg.core.service.impl;

import java.util.List;

import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.core.bean.ExamQuestion;
import com.edu.exmg.core.bean.ExamQuestionExample;
import com.edu.exmg.core.enums.ErrorEnum;
import com.edu.exmg.core.mapper.ExamQuestionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.mapper.ExamQuestionMapper;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.service.ExamQuestionService;
import com.edu.exmg.core.vo.ExamQuestionVO;
import org.springframework.util.CollectionUtils;

/**
 * @author autom
 */
@Service
public class ExamQuestionServiceImpl extends CommonService<ExamQuestion, ExamQuestionDTO, ExamQuestionVO> implements ExamQuestionService {


    private ExamQuestionMapper examQuestionMapper;

	@Autowired
	private ExamQuestionExtMapper examQuestionExtMapper;

	public ExamQuestionServiceImpl(ExamQuestionMapper examQuestionMapper) {
		super(examQuestionMapper, ExamQuestion.class, ExamQuestionDTO.class, ExamQuestionVO.class);
		this.examQuestionMapper = examQuestionMapper;
	}


	/**
	 * ExamQuestionService 分配考试题目
	 *
	 * @param attrs
	 * @param questionIds
	 * @return
	 */
	@Override
	public IResult assignExamQuestion(ExamQuestionDTO attrs, List<Integer> questionIds) {
		if (CollectionUtils.isEmpty(questionIds)) {
			return IResult.error(ErrorEnum.ERRCODE_0004, "exqList");
		}

		for (Integer questionId : questionIds) {
			ExamQuestionExample example = new ExamQuestionExample();
			example.createCriteria().andExamIdEqualTo(attrs.getExamId()).andQuestionIdEqualTo(questionId);
			examQuestionMapper.deleteByExample(example);
		}

		if ("D".equals(attrs.getOp())) {
			return Result.success("success");
		}

		Integer sorted = attrs.getSorted();
		if (sorted > 0) {
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setSorted(attrs.getSorted());
			examQuestion.setVersion(questionIds.size());
			examQuestionExtMapper.insertUpdateSort(examQuestion);
		}

		int i = 0;
		for (Integer questionId : questionIds) {
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setQuestionId(questionId);
			examQuestion.setExamId(attrs.getExamId());
			examQuestion.setScore(attrs.getScore());
			if (sorted < 0) {
				// 按默认顺序新增题目
				examQuestionExtMapper.insertNextSort(examQuestion);
			} else {
				// 按指定顺序新增题目
				examQuestion.setSorted(attrs.getSorted() + i++);
				examQuestionMapper.insert(examQuestion);
			}
		}

		return Result.success("success");
	}
	
	@Override
	public PageVO<ExamQuestionVO> queryPage(ExamQuestionQuery query) {
		
		ExamQuestionExample example = new ExamQuestionExample();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<ExamQuestion> resultList = examQuestionMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<ExamQuestionVO> queryList(ExamQuestionQuery query) {
		query.setPageFlag(false);
		PageVO<ExamQuestionVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
