package com.wj.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wj on 2018/1/10.
 * Reference from Blankj
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/TimeUtils.java
 */

public class TimeUtils {

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将时间戳转换成字符串
     *
     * @param millis
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String millis2String(long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳转换成字符串
     *
     * @param millis 时间戳
     * @param format 时间格式
     * @return
     */
    public static String millis2String(long millis, DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 将字符串转换成时间戳
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将字符串转换成时间戳
     *
     * @param time   时间
     * @param format 时间格式
     * @return
     */
    public static long string2Millis(String time, DateFormat format) {
        try {
            return string2Date(time, format).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转换成Date型
     *
     * @param time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转换成Date型
     *
     * @param time
     * @param format
     * @return
     */
    public static Date string2Date(String time, DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期转换成时间字符串
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    /**
     * 将日期转换成时间字符串
     *
     * @param date   日期格式
     * @param format 日期格式
     * @return
     */
    public static String date2String(Date date, DateFormat format) {
        return format.format(date);
    }

    /**
     * 时间戳转日期
     *
     * @param millis
     * @return
     */
    public static Date millis2Date(long millis) {
        return millis2Date(millis);
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return 毫秒时间戳
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     * <p>格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getNowDateString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间字符串
     * <p>格式为 format</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getNowDateString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    /**
     * 获取当前 Date
     *
     * @return Date 类型时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取传入时间戳与当前时间的差值
     *
     * @param millis
     * @return 刚刚更新，1分钟前更新，1小时前更新，1天前更新，1个月前更新，1年前更新
     */
    public static String updateStr(long millis) {
        String[] resArray = {"刚刚更新", "分钟前更新", "小时前更新", "天前更新", "个月前更新", "年前更新"};
        long currentTimeMillis = System.currentTimeMillis();
        long value = currentTimeMillis - millis;
        long minute = 60 * 1000L;
        long hour = 60 * minute;
        long day = 24 * hour;
        long month = 30 * day;
        long year = 365 * day;
        if (value < minute) {//一分钟
            return resArray[0];
        } else if (value < hour) {//一小时
            return getTimeValue(value, minute) + resArray[1];
        } else if (value < day) {//一天
            return getTimeValue(value, hour) + resArray[2];
        } else if (value < month) {//一个月
            return getTimeValue(value, day) + resArray[3];
        } else if (value < year) {//一年
            return getTimeValue(value, month) + resArray[4];
        } else {
            return getTimeValue(value, year) + resArray[5];
        }
    }

    private static int getTimeValue(long value, long totalMillis) {
        int res = (int) (value / totalMillis);
        return res;
    }

    /**
     * 获取今天的日子
     * @return 10号11号 day
     */
    public static int getTodayDay(){
        Date nowDate = getNowDate();
        return nowDate.getDate();
    }

}
