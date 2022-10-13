package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExamCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String categoryName;
    
    private Integer parentId;
    
    private Integer level;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
    private Integer version;
    
}
