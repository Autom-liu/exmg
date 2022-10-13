package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ExamInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "考试名称不能为空")
    private String examName;

    @NotBlank(message = "考试说明不能为空")
    private String examRemark;
    
    private Integer userFrom;
    
    private String userId;

    @Max(value = 300, message = "总分不得超过300分")
    @Min(value = 0, message = "总分不得低于0分")
    private Integer totalScore;
    
    private Integer answerMode;

    private Integer maxCount;
    
    private String picUrl;
    
    private Boolean banner;
    
    private Boolean autoGenerate;

    @NotNull(message = "请设置开始时间")
    private Date begintime;

    @NotNull(message = "请设置结束时间")
    private Date endtime;

    private Boolean status;

    private Integer version;
    
}
