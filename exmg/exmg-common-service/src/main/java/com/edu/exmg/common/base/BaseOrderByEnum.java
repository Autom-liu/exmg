package com.edu.exmg.common.base;

import com.edu.exmg.common.enums.BaseStatusEnum;
import com.edu.exmg.common.web.BaseEnumConverterDeserializerFactory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 通用排序字段枚举类接口，从前端传来的排序字段参数不能直接传字段名，而是要在枚举规定的code范围内
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
@JsonDeserialize(using = BaseEnumConverterDeserializerFactory.class)
public interface BaseOrderByEnum extends BaseStatusEnum {

	String getOrderField();
	
	@Override
	default String getMsg() {
		return null;
	}
	
}
