package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class WxuserVO implements Serializable {
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
