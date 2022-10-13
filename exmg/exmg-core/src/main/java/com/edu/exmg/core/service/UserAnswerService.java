package com.edu.exmg.core.service;

import java.util.List;


import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.UserAnswer;
import com.edu.exmg.core.dto.UserAnswerDTO;
import com.edu.exmg.core.query.UserAnswerQuery;
import com.edu.exmg.core.vo.UserAnswerVO;

/**
 * if your service don't need this method  you can remove it manually
 * @author autom
 */
public interface UserAnswerService extends IService<UserAnswer, UserAnswerDTO, UserAnswerVO> {
	
	PageVO<UserAnswerVO> queryPage(UserAnswerQuery query);
	
	List<UserAnswerVO> queryList(UserAnswerQuery query);
	
}
