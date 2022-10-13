package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.OptionInfo;
import com.edu.exmg.core.bean.OptionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionInfoMapper extends Mapper<OptionInfo> {
    long countByExample(OptionInfoExample example);

    int deleteByExample(OptionInfoExample example);

    List<OptionInfo> selectByExample(OptionInfoExample example);

    int updateByExampleSelective(@Param("record") OptionInfo record, @Param("example") OptionInfoExample example);

    int updateByExample(@Param("record") OptionInfo record, @Param("example") OptionInfoExample example);
}