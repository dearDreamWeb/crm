package com.example.util;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:集合验证工具类
 */
public class MyListUtils {
    public static boolean isEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotEmpty(List list) {
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
