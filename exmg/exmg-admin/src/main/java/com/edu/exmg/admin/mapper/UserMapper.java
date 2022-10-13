package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.User;
import com.edu.exmg.admin.bean.UserExample;
import com.edu.exmg.admin.query.UserQuery;
import com.edu.exmg.common.service.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    List<User> selectDeptUser(UserQuery query);

}