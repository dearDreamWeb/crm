package com.example.util;

import java.util.UUID;

/**
 * @author: pengjia
 * @date: 2020/10/28
 * @description:
 */
public class MyUtils {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().
                replaceAll("-", "");
        System.out.println(uuid);
        return uuid;
    }
}
