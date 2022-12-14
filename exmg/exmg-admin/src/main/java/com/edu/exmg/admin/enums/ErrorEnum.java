package com.edu.exmg.admin.enums;


import com.edu.exmg.common.enums.IErrorEnum;
import lombok.Getter;

@Getter
public enum ErrorEnum implements IErrorEnum {

    /**success**/
    ERRCODE_0000("0000", "success"),

    /**请分配用户机构**/
    ERRCODE_0001("0001", "请分配用户机构"),

    /**用户信息不存在**/
    ERRCODE_0002("0002", "用户信息不存在"),

    /**机构信息不存在**/
    ERRCODE_0003("0003", "机构信息不存在"),

    /**角色信息不存在**/
    ERRCODE_0004("0004", "角色信息不存在"),

    /**存在用户关系**/
    ERRCODE_0005("0005", "存在用户关系"),

    /**密码认证失败**/
    ERRCODE_0006("0006", "密码认证失败"),

    /**帐号密码错误**/
    ERRCODE_0007("0007", "帐号密码错误"),

    /**用户未登陆或超时**/
    ERRCODE_0008("0008", "用户未登陆或超时，请重新登陆"),

    /**未分配机构角色**/
    ERRCODE_0009("0009", "未分配机构角色，请联系管理员"),

    /**非本人禁止操作**/
    ERRCODE_0010("0010", "非本人禁止操作"),

    /**菜单信息不存在**/
    ERRCODE_0011("0011", "菜单信息不存在"),

    /**无权访问**/
    ERRCODE_0012("0012", "无权访问"),

    /** 菜单信息不完整 **/
    ERRCODE_0013("0013", "菜单信息不完整"),

    /** 菜单不存在 **/
    ERRCODE_0014("0014", "菜单不存在"),

    /** 只能删除最底层菜单 **/
    ERRCODE_0015("0015", "只能删除最底层菜单"),

    /** 不存在的用户关系 **/
    ERRCODE_0016("0016", "不存在的用户关系"),

    /** 不允许解除默认关系 **/
    ERRCODE_0017("0017", "不允许解除默认关系"),

    /**参数错误**/
    ERRCODE_0403("0403", "参数错误"),

    ;

    private String code;

    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isNotSuccess() {
        return false;
    }

}
