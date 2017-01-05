package com.test.cheng.practice.utils;

/**
 * 日期格式工具类
 * Created by kexiaoderenren on 2017/1/4.
 */
public class DateUtils {

    /**
     * 将 YYYYMMDD格式日期 转为 YYYY-MM-DD格式日期
     * @param data YYYYMMDD格式日期
     * @return YYYY-MM-DD格式日期
     */
    public static String dateFormatTransfer(String data) {
        StringBuffer sb = new StringBuffer(data);
        sb.insert(4, Constants.MINUS_SIGN);
        sb.insert(7, Constants.MINUS_SIGN);
        return sb.toString();
    }

    public static String getWeekInfo(){
        return "";
    }




}
