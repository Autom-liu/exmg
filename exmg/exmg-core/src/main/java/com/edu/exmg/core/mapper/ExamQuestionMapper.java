package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.ExamQuestion;
import com.edu.exmg.core.bean.ExamQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionMapper extends Mapper<ExamQuestion> {
    long countByExample(ExamQuestionExample example);

    int deleteByExample(ExamQuestionExample example);

    List<ExamQuestion> selectByExample(ExamQuestionExample example);

    int updateByExampleSelective(@Param("record") ExamQuestion record, @Param("example") ExamQuestionExample example);

    int updateByExample(@Param("record") ExamQuestion record, @Param("example") ExamQuestionExample example);
}