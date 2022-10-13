package com.edu.exmg.common.enums;

import com.edu.exmg.common.base.BaseOrderByEnum;
import lombok.Getter;
import lombok.ToString;

/**
 * @author autom
 * @date 2022-01-09
 **/
@Getter
@ToString
public enum CreateTimeOrderEnum implements BaseOrderByEnum {

    CREATE_TIME("1001", "create_time"),

    CREATE_TIME_DESC("1002", "create_time desc")
    ;


    private String code;

    private String orderField;

    CreateTimeOrderEnum(String code, String orderField) {
        this.code = code;
        this.orderField = orderField;
    }
}
