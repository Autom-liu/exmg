package com.edu.exmg.core.dto;

import lombok.Data;

/**
 * @author autom
 * @date 2022-01-05
 **/
@Data
public class OptionInfoDTO {

    private Integer id;

    private String option;

    private Integer questionId;

    private Integer optionType;

    private Boolean right;

    private String interpretation;

}
