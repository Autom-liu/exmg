package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.Menu;
import com.edu.exmg.admin.bean.MenuExample;
import com.edu.exmg.common.service.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    List<Menu> selectByExample(MenuExample example);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);
}