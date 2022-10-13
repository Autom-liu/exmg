package com.edu.exmg.core.vo;

import lombok.Data;

@Data
public class ConvergeAnswerVO {

    private String questionId;

    private Integer rightNum;

    private Integer wrongNum;

    private Integer totalNum;

    private Integer totalScore;

}
