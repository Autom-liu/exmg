package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserAnswerVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer userId;
    
    private Integer examId;
    
    private Integer questionId;
    
    private Integer score;
    
    private Integer sorted;
    
    private String answer;
    
    private Byte right;
    
}
