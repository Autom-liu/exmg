package com.edu.exmg.admin.api;

import com.edu.exmg.admin.config.CurrentUser;
import com.edu.exmg.admin.vo.UserEntity;
import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.core.dto.ExamCategoryDTO;
import com.edu.exmg.core.service.ExamCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author autom
 * @date 2022-01-05
 **/
@Slf4j
@RestController
@RequestMapping("admin")
public class CategoryAPI {

    @Autowired
    private ExamCategoryService examCategoryService;

    @PostMapping("category/create")
    public IResult create(@Valid ExamCategoryDTO categoryDTO, @CurrentUser UserEntity userEntity) throws BizException {
        categoryDTO.setStatus(true);
        categoryDTO.setCreateTime(new Date());

        examCategoryService.insert(categoryDTO);
        return Result.success("success");
    }

}
