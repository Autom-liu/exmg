package com.edu.exmg.core.enums;

import com.edu.exmg.common.enums.IErrorEnum;

public enum ErrorEnum implements IErrorEnum {

	
	ERRCODE_0000("0000", "success"),

	/**
	 * 数据不存在
	 */
	ERRCODE_0004("0004", "数据不存在"),
	
	
	;

	private String code;
	
	private String msg;
	
	private ErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
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
