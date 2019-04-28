/**
 * NullUtils.java
 * <p/>
 * Create VersionInfo: 1.0
 * Create Date: 2012-4-24
 */
package com.code.clkj.menggong.util;

import java.util.Collection;
import java.util.Map;

/**
 * NullUtils 判断变量是否为空工具类
 */
public class NullUtils {
    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static Boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断对象是否不为空
     *
     * @param obj
     * @return
     */
    public static Boolean isNotNull(Object obj) {
        return null != obj;
    }

    /**
     * 判断是否为空字符串
     *
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * 判断是否不为空字符串
     *
     * @param str
     * @return
     */
    public static Boolean isNotEmpty(String str) {
        return isNotNull(str) && str.trim().length() > 0;
    }

    /**
     * 判断集合对象是否为空
     *
     * @param collection
     * @return
     */
    public static Boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * 判断集合对象是否不为空
     *
     * @param collection
     * @return
     */
    public static Boolean isNotEmpty(Collection<?> collection) {
        return isNotNull(collection) && collection.size() > 0;
    }

    /**
     * 判断数组对象是否为空
     *
     * @param arrary
     * @return
     */
    public static Boolean isEmpty(Object[] arrary) {
        return isNull(arrary) || arrary.length == 0;
    }

    /**
     * 判断数组对象是否不为空
     *
     * @param array
     * @return
     */
    public static Boolean isNotEmpty(Object[] array) {
        return isNotNull(array) && array.length > 0;
    }

    /**
     * 判断MAP对象是否为空
     *
     * @param map
     * @return
     */
    public static Boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断MAP对象是否不为空
     *
     * @param map
     * @return
     */
    public static Boolean isNotEmpty(Map<?, ?> map) {
        return isNotNull(map) && !map.isEmpty();
    }


}
