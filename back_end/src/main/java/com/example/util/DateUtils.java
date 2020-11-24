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
    public static Date activityStrToDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(dateStr);
        System.out.println(parse);
        return parse;
    }
    /*获取当前系统时间的下个月*/
    public static String nextMonth(){
        Date date = new Date();
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));//取到年份值
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))+1;//取到鱼粉值
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));//取到天值
        if(month==0){
            year-=1;month=12;
        }
        else if(day>28){
            if(month==2){
                if(year%400==0||(year %4==0&&year%100!=0)){
                    day=29;
                }else day=28;
            }else if((month==4||month==6||month==9||month==11)&&day==31)
            {
                day=30;
            }
        }
        String y = year+"";String m ="";String d ="";
        if(month<10) m = "0"+month;
        else m=month+"";
        if(day<10) d = "0"+day;
        else d = day+"";
        System.out.println(y+"-"+m+"-"+d);
        return y+"-"+m+"-"+d;
    }
}
