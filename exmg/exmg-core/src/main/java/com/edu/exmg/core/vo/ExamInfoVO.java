package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ExamInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String examName;
    
    private String examRemark;
    
    private Integer userFrom;
    
    private String userId;
    
    private Integer totalScore;
    
    private Integer answerMode;
    
    private Integer maxCount;
    
    private String picUrl;
    
    private Boolean banner;
    
    private Boolean autoGenerate;
    
    private Date begintime;
    
    private Date endtime;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
    private Integer version;
    
}
