package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WxuserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer userFrom;
    
    private String wxOpenid;
    
    private String nickName;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
    private Integer version;
    
}
