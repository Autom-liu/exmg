package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author autom
 */
@Data
public class QuestionInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String question;
    
    private Integer questionType;

    private String userId;

    private String nickName;
    
    private Integer difficulty;

    private Integer topCategory;
    
    private Integer categoryId;
    
    private String interpretation;
    
    private String picsUrl;

    private Boolean status;

    private Boolean common;
    
    private Integer version;

    List<OptionInfoDTO> options;
    
}
