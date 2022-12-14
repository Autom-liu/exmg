package com.edu.exmg.common.vo;

import com.edu.exmg.common.enums.IErrorEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 全局统一通用返回接口
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 */
public interface IResult {
	
	String getCode();
	
	String getMsg();
	
	void setCode(String code);
	
	void setMsg(String msg);
	
	void setT(Throwable t);
	
	Throwable getT();

	@JsonIgnore
	default boolean isSuccess() {
		return "0000".equals(getCode());
	}

	@JsonIgnore
	default boolean isNotSuccess() {
		return !"0000".equals(getCode());
	}
	
	public static IResult error(String code, String msg) {
		IResult result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static IResult error(String code, String msg, Throwable t) {
		IResult result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setT(t);
		return result;
	}
	
	public static IResult error(IErrorEnum errorEnum) {
		IResult result = new Result<>();
		result.setCode(errorEnum.getCode().toString());
		result.setMsg(errorEnum.getMsg());
		return result;
	}

	public static IResult error(IErrorEnum errorEnum, String info) {
		IResult result = new Result<>();
		result.setCode(errorEnum.getCode().toString());
		result.setMsg(errorEnum.getMsg() + info);
		return result;
	}
	
	public static IResult error(IErrorEnum errorEnum, Throwable t) {
		IResult result = new Result<>();
		result.setCode(errorEnum.getCode().toString());
		result.setMsg(errorEnum.getMsg());
		result.setT(t);
		return result;
	}
}
