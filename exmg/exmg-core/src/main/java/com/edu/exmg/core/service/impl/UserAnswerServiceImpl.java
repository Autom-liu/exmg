package com.edu.exmg.core.service.impl;

import java.util.List;

import com.edu.exmg.core.bean.UserAnswer;
import com.edu.exmg.core.bean.UserAnswerExample;
import org.springframework.stereotype.Service;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.UserAnswerDTO;
import com.edu.exmg.core.mapper.UserAnswerMapper;
import com.edu.exmg.core.query.UserAnswerQuery;
import com.edu.exmg.core.service.UserAnswerService;
import com.edu.exmg.core.vo.UserAnswerVO;

@Service
public class UserAnswerServiceImpl extends CommonService<UserAnswer, UserAnswerDTO, UserAnswerVO> implements UserAnswerService {


    private UserAnswerMapper userAnswerMapper;

	public UserAnswerServiceImpl(UserAnswerMapper userAnswerMapper) {
		super(userAnswerMapper, UserAnswer.class, UserAnswerDTO.class, UserAnswerVO.class);
		this.userAnswerMapper = userAnswerMapper;
	}
	
	@Override
	public PageVO<UserAnswerVO> queryPage(UserAnswerQuery query) {
		
		UserAnswerExample example = new UserAnswerExample();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<UserAnswer> resultList = userAnswerMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<UserAnswerVO> queryList(UserAnswerQuery query) {
		query.setPageFlag(false);
		PageVO<UserAnswerVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
