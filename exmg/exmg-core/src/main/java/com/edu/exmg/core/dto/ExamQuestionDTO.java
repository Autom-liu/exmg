package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ExamQuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "op不能为空")
    private String op;

    @NotNull(message = "examId不能为空")
    private Integer examId;
    
    private Integer questionId;
    
    private Integer score;
    
    private Integer sorted;
    
}
