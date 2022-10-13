package com.edu.exmg.core.service.impl;

import java.util.List;

import com.edu.exmg.core.bean.ExamCategory;
import com.edu.exmg.core.bean.ExamCategoryExample;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.ExamCategoryDTO;
import com.edu.exmg.core.mapper.ExamCategoryMapper;
import com.edu.exmg.core.query.ExamCategoryQuery;
import com.edu.exmg.core.service.ExamCategoryService;
import com.edu.exmg.core.vo.ExamCategoryVO;

@Service
public class ExamCategoryServiceImpl extends CommonService<ExamCategory, ExamCategoryDTO, ExamCategoryVO> implements ExamCategoryService {


    private ExamCategoryMapper examCategoryMapper;

	public ExamCategoryServiceImpl(ExamCategoryMapper examCategoryMapper) {
		super(examCategoryMapper, ExamCategory.class, ExamCategoryDTO.class, ExamCategoryVO.class);
		this.examCategoryMapper = examCategoryMapper;
	}
	
	@Override
	public PageVO<ExamCategoryVO> queryPage(ExamCategoryQuery query) {
		
		ExamCategoryExample example = new ExamCategoryExample();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<ExamCategory> resultList = examCategoryMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<ExamCategoryVO> queryList(ExamCategoryQuery query) {
		query.setPageFlag(false);
		PageVO<ExamCategoryVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
