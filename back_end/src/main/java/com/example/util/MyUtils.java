package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static String record() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStamp = simpleDateFormat.format(new Date());
        System.out.println(timeStamp);
        return timeStamp;
    }
}
