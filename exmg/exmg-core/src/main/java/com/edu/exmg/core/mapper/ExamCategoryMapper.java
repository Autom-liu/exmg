package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.ExamCategory;
import com.edu.exmg.core.bean.ExamCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamCategoryMapper extends Mapper<ExamCategory> {
    long countByExample(ExamCategoryExample example);

    int deleteByExample(ExamCategoryExample example);

    List<ExamCategory> selectByExample(ExamCategoryExample example);

    int updateByExampleSelective(@Param("record") ExamCategory record, @Param("example") ExamCategoryExample example);

    int updateByExample(@Param("record") ExamCategory record, @Param("example") ExamCategoryExample example);
}