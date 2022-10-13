package com.edu.exmg.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author autom
 * @date 2021-12-28
 **/
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static<K, V> Map<K, V> singletonMap(K key, V value) {
        Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
