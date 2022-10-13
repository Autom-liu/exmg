package com.edu.exmg.admin.query;


import com.edu.exmg.common.query.PageQuery;
import lombok.Data;

@Data
public class UserQuery extends PageQuery {

    private String userId;

    private String username;

    private String deptId;

}
