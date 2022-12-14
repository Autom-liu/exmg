package com.edu.exmg.common.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String toString(Object str) {
        return str == null ? null : str.toString();
    }

    public static String trimToEmpty(Object str) {
        return trimToEmpty(toString(str));
    }

}
