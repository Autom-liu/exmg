package com.edu.exmg.admin.vo;


import com.edu.exmg.admin.bean.Department;
import com.edu.exmg.admin.bean.Role;
import com.edu.exmg.admin.bean.User;
import lombok.Data;

import java.util.List;

@Data
public class UserEntity {

    private User user;

    private Department currentDept;

    private Role currentRole;

    private List<DeptRoleEntity> deptRoleList;

}

