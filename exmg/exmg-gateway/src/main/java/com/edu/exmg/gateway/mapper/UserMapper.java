package com.edu.exmg.gateway.mapper;

import com.edu.exmg.gateway.bean.User;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    User selectByOpenid(String wxopenid);

}
