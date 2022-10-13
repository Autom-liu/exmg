package com.edu.exmg.admin.enums;


import com.edu.exmg.common.base.BaseIntegerBizEnum;
import lombok.Getter;

@Getter
public enum MenuTypeEnum implements BaseIntegerBizEnum {

    /**菜单**/
    MENU_TYPE_0(0, "菜单"),

    /**页面**/
    MENU_TYPE_1(1, "页面"),

    /**按钮**/
    MENU_TYPE_2(2, "按钮"),

    /**权限**/
    MENU_TYPE_3(3, "权限"),

    ;

    MenuTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

}
