package com.edu.exmg.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserAnswerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String userId;
    
    private Integer examId;
    
    private Integer questionId;

    private Integer optionId;

    private Integer sorted;
    
    private String answer;

    private Boolean selected;

    public boolean selectedRight(Boolean rightAnswer) {
        boolean var1 = selected != null ? selected : false;
        boolean var2 = rightAnswer != null ? rightAnswer : false;
        return var1 == var2;
    }
    
}
