package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExamQuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer examId;
    
    private Integer questionId;
    
    private Integer score;
    
    private Integer sorted;
    
}
