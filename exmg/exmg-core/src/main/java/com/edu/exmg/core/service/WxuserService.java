package com.edu.exmg.core.service;

import java.util.List;

import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.Wxuser;
import com.edu.exmg.core.dto.WxuserDTO;
import com.edu.exmg.core.query.WxuserQuery;
import com.edu.exmg.core.vo.WxuserVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface WxuserService extends IService<Wxuser, WxuserDTO, WxuserVO> {
	
	PageVO<WxuserVO> queryPage(WxuserQuery query);
	
	List<WxuserVO> queryList(WxuserQuery query);
	
}
