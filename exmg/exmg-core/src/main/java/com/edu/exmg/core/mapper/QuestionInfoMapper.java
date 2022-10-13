package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.QuestionInfo;
import com.edu.exmg.core.bean.QuestionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionInfoMapper extends Mapper<QuestionInfo> {
    long countByExample(QuestionInfoExample example);

    int deleteByExample(QuestionInfoExample example);

    List<QuestionInfo> selectByExample(QuestionInfoExample example);

    int updateByExampleSelective(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByExample(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);
}