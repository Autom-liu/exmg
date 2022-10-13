package com.edu.exmg.admin.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author autom
 * @date 2022-01-10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportQuestion implements Serializable {

    @ExcelProperty(value = "题目编号",order = 0)
    private String question;

    @ExcelProperty(value = "题目解析",order = 1)
    private String interpretation;

    @ExcelProperty(value = "题目类型(1:单选,2:多选,3:填空简答)",order = 2)
    private Integer questionType;

    @ExcelProperty(value = "题目难度",order = 3)
    private Integer difficulty;

    @ExcelProperty(value = "一级分类(见知识分类表，填编号)",order = 4)
    private Integer topCategory;

    @ExcelProperty(value = "知识分类(见知识分类表，填编号)",order = 5)
    private Integer categoryId;

    @ExcelProperty(value = "公共题目",order = 6)
    private Boolean common;

    @ExcelProperty(value = "选项0",order = 7)
    private String option0;

    @ExcelProperty(value = "选项1",order = 8)
    private String option1;

    @ExcelProperty(value = "选项2",order = 9)
    private String option2;

    @ExcelProperty(value = "选项3",order = 10)
    private String option3;

    @ExcelProperty(value = "正确选项",order = 11)
    private String answer;

}
