package com.edu.exmg.admin.dto;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roleId;

    @NotNull(message = "角色名称不能为空")
    private String roleName;
    
    private String remark;
    
}
