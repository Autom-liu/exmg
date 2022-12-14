package com.edu.exmg.admin.dto;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    @NotNull(message = "用户名不能为空")
    private String userName;

    private String mobile;
    
    private String officePhone;
    
    private String email;
    
    private String remark;

    private Integer status;

    private String deptId;

    private String roleId;
    
}
