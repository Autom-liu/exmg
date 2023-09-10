package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.ExamQuestion;
import com.edu.exmg.core.bean.ExamQuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamQuestionExtMapper {

    /**
     * 插入顺序
     * @param examQuestion
     * @return
     */
    int insertUpdateSort(ExamQuestion examQuestion);

    /**
     * 新增一条记录按默认往下排序
     * @param examQuestion
     * @return
     */
    int insertNextSort(ExamQuestion examQuestion);

}