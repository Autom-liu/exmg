package com.edu.exmg.admin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    
    private String deptId;
    
    private String roleId;
    
    private String menuId;
    
}
