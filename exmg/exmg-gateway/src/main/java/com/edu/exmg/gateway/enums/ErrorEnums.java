package com.edu.exmg.gateway.enums;


import com.edu.exmg.common.enums.IErrorEnum;

/**
 * @author autom
 * @date 2021-04-20
 **/
public enum ErrorEnums implements IErrorEnum {
    /**
     * success
     */
    ERRCODE_0000("0000", "success"),

    /**
     * 不能为空
     */
    ERRCODE_0001("0001", "不能为空"),

    /**
     * 微信请求失败
     */
    ERRCODE_0002("0002", "微信请求失败"),

    /**
     * 请求参数非法
     */
    ERRCODE_0003("0003", "请求参数非法"),

    /**
     * 用户未登陆
     */
    ERRCODE_0004("0004", "用户未登陆"),

    /**
     * 参数长度不足
     */
    ERRCODE_0005("0005", "参数长度不足")

    ;
    private String code;

    private String msg;

    private ErrorEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public boolean isSuccess() {
        return this == ERRCODE_0000;
    }

    @Override
    public boolean isNotSuccess() {
        return this != ERRCODE_0000;
    }
}
