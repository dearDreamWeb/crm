package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * 开始时间拼接时分秒，返回字符串
     * @param startDate
     * @return
     */
    public static String startDateToStr(String startDate) {
        return startDate + "00:00:00";
    }

    /**
     * 结束时间拼接时分秒，返回字符串
     * @param endDate
     * @return
     */
    public static String endDateToStr(String endDate) {
        return endDate + "23:59:59";
    }

    /**
     * 将时间格式转换为字符串  yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateToTimeStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 将时间格式转换为字符串  yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToDateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 将特定的字符串转为Date
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateStr);
    }

    /**
     * 活动页面专属转换方法
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date activityStrToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = format.parse(dateStr);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
