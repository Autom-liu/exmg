package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.Role;
import com.edu.exmg.admin.bean.RoleExample;
import com.edu.exmg.common.service.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    List<Role> selectByExample(RoleExample example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);
}