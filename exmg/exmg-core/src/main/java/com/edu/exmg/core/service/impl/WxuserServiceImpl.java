package com.edu.exmg.core.service.impl;

import java.util.List;

import com.edu.exmg.common.service.CommonService;
import com.edu.exmg.core.bean.Wxuser;
import com.edu.exmg.core.bean.WxuserExample;
import org.springframework.stereotype.Service;


import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.dto.WxuserDTO;
import com.edu.exmg.core.mapper.WxuserMapper;
import com.edu.exmg.core.query.WxuserQuery;
import com.edu.exmg.core.service.WxuserService;
import com.edu.exmg.core.vo.WxuserVO;

@Service
public class WxuserServiceImpl extends CommonService<Wxuser, WxuserDTO, WxuserVO> implements WxuserService {


    private WxuserMapper wxuserMapper;

	public WxuserServiceImpl(WxuserMapper wxuserMapper) {
		super(wxuserMapper, Wxuser.class, WxuserDTO.class, WxuserVO.class);
		this.wxuserMapper = wxuserMapper;
	}
	
	@Override
	public PageVO<WxuserVO> queryPage(WxuserQuery query) {
		
		WxuserExample example = new WxuserExample();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Wxuser> resultList = wxuserMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<WxuserVO> queryList(WxuserQuery query) {
		query.setPageFlag(false);
		PageVO<WxuserVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
