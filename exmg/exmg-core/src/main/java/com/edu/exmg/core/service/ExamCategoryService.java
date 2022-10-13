package com.edu.exmg.core.service;

import java.util.List;

import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.ExamCategory;
import com.edu.exmg.core.dto.ExamCategoryDTO;
import com.edu.exmg.core.query.ExamCategoryQuery;
import com.edu.exmg.core.vo.ExamCategoryVO;

/**
 * if your service don't need this method  you can remove it manually
 * @author autom
 */
public interface ExamCategoryService extends IService<ExamCategory, ExamCategoryDTO, ExamCategoryVO> {
	
	PageVO<ExamCategoryVO> queryPage(ExamCategoryQuery query);
	
	List<ExamCategoryVO> queryList(ExamCategoryQuery query);
	
}
