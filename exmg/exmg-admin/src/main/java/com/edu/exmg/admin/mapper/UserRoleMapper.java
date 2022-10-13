package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.UserRole;
import com.edu.exmg.admin.bean.UserRoleExample;
import com.edu.exmg.admin.vo.DeptRoleEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    List<DeptRoleEntity> joinWithDeptRole(String userId);

}