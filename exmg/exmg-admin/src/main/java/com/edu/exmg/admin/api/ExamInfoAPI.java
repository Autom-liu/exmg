package com.edu.exmg.admin.api;

import com.edu.exmg.admin.config.CurrentUser;
import com.edu.exmg.admin.vo.UserEntity;
import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.core.dto.ExamInfoDTO;
import com.edu.exmg.core.query.ExamInfoQuery;
import com.edu.exmg.core.service.ExamInfoService;
import com.edu.exmg.core.vo.ExamInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @author autom
 * @date 2022-01-05
 **/
@Slf4j
@RestController
@RequestMapping("admin")
public class ExamInfoAPI {

    @Autowired
    private ExamInfoService examInfoService;

    @PostMapping("exam/create")
    public IResult create(@Valid ExamInfoDTO examInfoDTO, @CurrentUser UserEntity userEntity) throws BizException {
        examInfoDTO.setId(null);
        examInfoDTO.setUserFrom(0);
        String userId = userEntity.getUser().getUserId();
        examInfoDTO.setUserId(userId);
        examInfoDTO.setAutoGenerate(false);
        examInfoDTO.setStatus(true);
        examInfoDTO.setVersion(0);
        examInfoService.insert(examInfoDTO);
        return Result.success("success");
    }

    @PostMapping("exam/edit")
    public IResult edit(@Valid ExamInfoDTO examInfoDTO) throws BizException {
        examInfoService.updateByIdSelective(examInfoDTO);
        return Result.success("success");
    }

    @PostMapping("exam/remove")
    public IResult remove(Integer id)  throws BizException {
        examInfoService.deleteById(id);
        return Result.success("success");
    }

    @PostMapping("exam/list")
    public IResult list(ExamInfoQuery query) {
        List<ExamInfoVO> examInfos = examInfoService.queryList(query);
        return ResultList.success(examInfos);
    }

    @PostMapping("exam/one")
    public IResult one(Integer id) throws BizException {
        ExamInfoVO one = examInfoService.findOne(id);
        return Result.success(one);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StringUtils.isEmpty(text)) {
                    setValue(null);
                    return;
                }
                Long timestamp = Long.valueOf(text);
                setValue(new Date(timestamp));
            }

            @Override
            public String getAsText() {
                Date value = (Date) getValue();
                return (value != null ? String.valueOf(value.getTime()) : "");
            }
        });
    }

}
