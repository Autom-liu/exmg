package com.edu.exmg.core.service.impl;

import java.util.List;

import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.core.bean.ExamQuestion;
import com.edu.exmg.core.bean.ExamQuestionExample;
import com.edu.exmg.core.dto.ExamInfoDTO;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.mapper.ExamQuestionMapper;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.service.ExamQuestionService;
import com.edu.exmg.core.vo.ExamQuestionVO;

/**
 * @author autom
 */
@Service
public class ExamQuestionServiceImpl extends CommonService<ExamQuestion, ExamQuestionDTO, ExamQuestionVO> implements ExamQuestionService {


    private ExamQuestionMapper examQuestionMapper;

	public ExamQuestionServiceImpl(ExamQuestionMapper examQuestionMapper) {
		super(examQuestionMapper, ExamQuestion.class, ExamQuestionDTO.class, ExamQuestionVO.class);
		this.examQuestionMapper = examQuestionMapper;
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
