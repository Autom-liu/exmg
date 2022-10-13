package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.Wxuser;
import com.edu.exmg.core.bean.WxuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxuserMapper extends Mapper<Wxuser> {
    long countByExample(WxuserExample example);

    int deleteByExample(WxuserExample example);

    List<Wxuser> selectByExample(WxuserExample example);

    int updateByExampleSelective(@Param("record") Wxuser record, @Param("example") WxuserExample example);

    int updateByExample(@Param("record") Wxuser record, @Param("example") WxuserExample example);
}