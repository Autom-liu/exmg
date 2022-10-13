package com.edu.exmg.common.base;

import com.edu.exmg.common.enums.BaseStatusEnum;
import com.edu.exmg.common.web.BaseEnumConverterDeserializerFactory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *    业务类通用枚举，与业务相关的枚举定义都实现该接口
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
@JsonDeserialize(using = BaseEnumConverterDeserializerFactory.class)
public interface BaseStringBizEnum extends BaseStatusEnum {

    @Override
    String getCode();

}
