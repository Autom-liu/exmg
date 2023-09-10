package com.edu.exmg.common.util;

import com.edu.exmg.common.enums.BaseStatusEnum;

import java.util.*;

/**
 * 枚举工具类
 */
public class EnumUtils {


    /**
     * 根据code查枚举
     * @param key
     * @param enumValues
     * @return 不存在返回 Optional.empty()
     * @param <T>
     */
    public static<T extends BaseStatusEnum> Optional<T> getEnumByCode(Object key, T[] enumValues) {
        for (T enumValue : enumValues) {
            if (Objects.equals(enumValue.getCode(), key)) {
                return Optional.of(enumValue);
            }
        }
        return Optional.empty();
    }

}
