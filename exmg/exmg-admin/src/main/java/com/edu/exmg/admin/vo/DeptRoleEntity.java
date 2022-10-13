package com.edu.exmg.admin.vo;


import com.edu.exmg.admin.bean.Department;
import com.edu.exmg.admin.bean.Role;
import lombok.Data;

@Data
public class DeptRoleEntity {

    private Department department;

    private Role role;

    private String userId;

    private String deptId;

    private String roleId;

    private Boolean isDefault;
}
