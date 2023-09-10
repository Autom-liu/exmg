package com.edu.exmg.core.service;

import java.util.List;

import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.service.IService;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.core.bean.ExamInfo;
import com.edu.exmg.core.dto.ExamInfoDTO;
import com.edu.exmg.core.query.ExamInfoQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.vo.ExamInfoVO;

/**
 * if your service don't need this method  you can remove it manually
 * @author autom
 */
public interface ExamInfoService extends IService<ExamInfo, ExamInfoDTO, ExamInfoVO> {
	
	PageVO<ExamInfoVO> queryPage(ExamInfoQuery query);
	
	List<ExamInfoVO> queryList(ExamInfoQuery query);

	/**
	 * 为用户随机生成考试试题
	 * @param query
	 * @return
	 */
	ExamInfoVO userRandomExam(QuestionInfoQuery query) throws BizException;

	/**
	 * 查询用户考试列表
	 * @param query
	 * @return
	 */
	List<ExamInfoVO> userExamList(ExamInfoQuery query);

}
