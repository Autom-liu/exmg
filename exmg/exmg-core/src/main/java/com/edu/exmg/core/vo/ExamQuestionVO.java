package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.edu.exmg.core.bean.OptionInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @author autom
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ExamQuestionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer examId;

    private Integer recordId;

    private Integer questionId;
    
    private Integer score;
    
    private Integer sorted;

    private String question;

    private String picsUrl;

    private Integer questionType;

    private Integer topCategory;

    private Integer categoryId;

    private Integer difficulty;

    private String interpretation;

    private Boolean common;

    private List<OptionInfo> options;
    
}
