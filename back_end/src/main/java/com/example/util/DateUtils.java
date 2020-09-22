package com.example.util;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:
 */
public class DateUtils {
    /**
     * 获取当前时间，返回时间格式
     * @return
     */
    public static Date getDate(){
        return new Date();
    }

    /**
     * 时间戳转换时间
     * @param timeStamp
     * @return
     */
    public static Date timeStampToDate(Long timeStamp){
        return new Date(timeStamp);
    }

    /**
     * 获取时间字符串
     * @return
     */
    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }
}
