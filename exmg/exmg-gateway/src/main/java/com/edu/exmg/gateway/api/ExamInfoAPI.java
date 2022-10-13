package com.edu.exmg.gateway.api;

import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.core.dto.ExamInfoDTO;
import com.edu.exmg.core.query.ExamInfoQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.ExamInfoService;
import com.edu.exmg.core.vo.ExamInfoVO;
import com.edu.exmg.gateway.enums.ErrorEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 试题信息接口
 * @author autom
 * @date 2022-01-02
 **/
@Slf4j
@RestController
@RequestMapping("exam")
public class ExamInfoAPI {

    @Autowired
    private ExamInfoService examInfoService;

    /**
     * 试题首页展示
     * @return
     */
    @PostMapping("banner")
    public IResult fetchBannerExam() {
        ExamInfoQuery examInfoQuery = new ExamInfoQuery();
        examInfoQuery.setBanner(true);
        examInfoQuery.setAutoGenerate(false);
        examInfoQuery.setStatus(true);
        examInfoQuery.setQueryDate(new Date());
        List<ExamInfoVO> examInfos = examInfoService.queryList(examInfoQuery);
        return ResultList.success(examInfos);
    }

    /**
     * 随机生成试题
     * @return
     */
    @PostMapping("random")
    public IResult randomExam(@RequestBody QuestionInfoQuery query) {
        if (StringUtils.isBlank(query.getUserId())) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "userId");
        }
        ExamInfoVO examInfo = examInfoService.userRandomExam(query);
        return Result.success(examInfo);
    }

    /**
     * 用户试题列表
     * @param query
     * @return
     */
    @PostMapping("user")
    public IResult userExam(@RequestBody ExamInfoQuery query) {
        if (StringUtils.isBlank(query.getUserId())) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "userId");
        }
        List<ExamInfoVO> resultList = examInfoService.userExamList(query);
        return ResultList.success(resultList);
    }

    @PostMapping("create")
    public IResult create(ExamInfoDTO examInfoDTO) throws BizException {
        examInfoService.insert(examInfoDTO);
        return Result.success("success");
    }

    @PostMapping("edit")
    public IResult edit(ExamInfoDTO examInfoDTO) throws BizException {
        examInfoService.updateByIdSelective(examInfoDTO);
        return Result.success("success");
    }

    @PostMapping("remove")
    public IResult remove(Integer id)  throws BizException {
        examInfoService.deleteById(id);
        return Result.success("success");
    }

    @PostMapping("list")
    public IResult list(ExamInfoQuery query) {
        List<ExamInfoVO> examInfos = examInfoService.queryList(query);
        return ResultList.success(examInfos);
    }

    @PostMapping("one")
    public IResult one(Integer id) throws BizException {
        ExamInfoVO one = examInfoService.findOne(id);
        return Result.success(one);
    }


}
