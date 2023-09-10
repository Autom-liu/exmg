package com.edu.exmg.core.enums;

import com.edu.exmg.common.base.BaseIntegerBizEnum;
import com.edu.exmg.common.util.EnumUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 答题模式
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AnswerModeEnums implements BaseIntegerBizEnum {

    /**
     * 标准模式
     */
    STANDARD_MODE(1, "标准模式"),

    /**
     * 随机模式
     */
    RANDOM_MODE(2, "随机模式"),

    /**
     * 随机出题
     */
    SHUFFLE_QUESTIONS(21, "随机出题"),

    /**
     * 选项乱序
     */
    SHUFFLE_OPTIONS(22, "选项乱序"),

    /**
     * 问卷调查
     */
    SURVEY_MODE(3, "问卷调查")

    ;

    private Integer code;

    private String msg;

    AnswerModeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
