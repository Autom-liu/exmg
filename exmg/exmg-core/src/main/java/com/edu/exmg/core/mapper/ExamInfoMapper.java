package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.ExamInfo;
import com.edu.exmg.core.bean.ExamInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamInfoMapper extends Mapper<ExamInfo> {
    long countByExample(ExamInfoExample example);

    int deleteByExample(ExamInfoExample example);

    List<ExamInfo> selectByExample(ExamInfoExample example);

    int updateByExampleSelective(@Param("record") ExamInfo record, @Param("example") ExamInfoExample example);

    int updateByExample(@Param("record") ExamInfo record, @Param("example") ExamInfoExample example);
}