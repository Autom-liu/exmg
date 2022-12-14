package com.edu.exmg.common.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import com.edu.exmg.common.base.BaseStringBizEnum;
import com.edu.exmg.common.enums.DefaultSysErrorEnum;
import com.edu.exmg.common.enums.BaseStatusEnum;
import com.edu.exmg.common.exception.SystemException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 通用枚举参数解析器  生成工厂
 * 所有实现{@link BaseStringBizEnum}的枚举在spring mvc参数解析阶段完成枚举类型注入
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class BaseEnumConverterDeserializerFactory extends JsonDeserializer<BaseStatusEnum> implements ConverterFactory<String, BaseStatusEnum> {
	
	private static final Map<Class<?>, Converter> CONVERTERS = new ConcurrentHashMap<>();

	@Override
	public <T extends BaseStatusEnum> Converter<String, T> getConverter(Class<T> targetType) {
		Converter<String, T> converter = CONVERTERS.get(targetType);
		if(converter == null) {
			converter = new CodeEnumConverter<>(targetType);
			CONVERTERS.put(targetType, converter);
		}
		return converter;
	}

	@Override
	public BaseStatusEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode jsonNode = (JsonNode) p.getCodec().readTree(p);
		String currentName = p.getCurrentName();
		Object currentDto = p.getCurrentValue();
		Class propertyType = BeanUtils.findPropertyType(currentName, currentDto.getClass());
		String text = jsonNode.asText();
		Converter converter = this.getConverter(propertyType);
		Object convertResult = converter.convert(text);
		if(convertResult == null) {
			throw new SystemException(DefaultSysErrorEnum.OUT_OF_ENUMS, currentName);
		}
		return convertResult instanceof BaseStatusEnum ? (BaseStatusEnum) convertResult : null;
	}

	public static class CodeEnumConverter<T extends BaseStatusEnum> implements Converter<String, T> {
		
		private Map<String, T> enumMap = new HashMap<>();

		public CodeEnumConverter(Class<T> targetType) {
			T[] enumConstants = targetType.getEnumConstants();
			for(T e : enumConstants) {
				enumMap.put(e.getCode().toString(), e);
			}
		}

		@Override
		public T convert(String source) {
			return enumMap.get(source);
		}

	}


}
