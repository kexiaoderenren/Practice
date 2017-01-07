package com.test.cheng.practice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期格式工具类
 * Created by kexiaoderenren on 2017/1/4.
 */
public class DateUtils {

    public static final String DATA_FORMAT1 = "yyyy-MM-dd";

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

    /**
     * 根据日期得到周几
     * @param pTime 日期（"yyyy-MM-dd）格式
     * @return
     */
    public static String getWeek(String pTime) {

        String Week = "星期";
        SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT1);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }
        return Week;
    }




}
