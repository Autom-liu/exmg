package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.Menu;
import com.edu.exmg.admin.bean.Permission;
import com.edu.exmg.admin.bean.PermissionExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;


public interface PermissionMapper extends BaseMapper<Permission> {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    List<Permission> selectByExample(PermissionExample example);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    List<Menu> joinWithMenu(Permission query);

}