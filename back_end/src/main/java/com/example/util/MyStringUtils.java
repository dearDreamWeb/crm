package com.example.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:字符串工具类
 */
public class MyStringUtils {
    public static List<Integer> stringToList(String str) {
        List<Integer> list = new ArrayList<>();
        if (!StringUtils.isEmpty(str)) {
            String[] splits = str.split(",");
            for (String split : splits) {
                list.add(Integer.parseInt(split));
            }
        }
        return list;
    }

    public static String listToString(List<Integer> list) {
        String pids = "";
        if (list != null && list.size() > 0) {
            Collections.reverse(list);
            int size = list.size();
            for (int i = 0;i < size;i++) {
                if (i == 0){
                    pids = list.get(i) + "";
                } else {
                    pids = list.get(i) + "," + pids;
                }
            }
        } else {
            pids = "0";
        }
        return pids;
    }

    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
