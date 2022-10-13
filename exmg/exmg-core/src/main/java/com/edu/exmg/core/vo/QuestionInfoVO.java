package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.edu.exmg.core.bean.OptionInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 *
 * @author autom
 */
@Data
@JsonInclude(Include.NON_NULL)
public class QuestionInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String question;
    
    private Integer questionType;

    private String userId;

    private String nickName;

    private Integer difficulty;
    
    private Integer categoryId;

    private Integer topCategory;
    
    private String interpretation;
    
    private String picsUrl;
    
    private Date createTime;
    
    private Boolean status;

    private Boolean common;

    private List<OptionInfo> options;

    
}
