package com.test.cheng.practice.utils;

import com.orhanobut.logger.Logger;

/**
 * 日志输出工具
 * Created by gaokuncheng on 2016/12/5.
 */
public class LogUtils {

    public final static String LOG_NAME = "com.test.cheng.practice";
    public final static String LOG_NET = "TagNet";

    public static void i(String content) {
        i(LOG_NAME, content);
    }

    public static void i(String logName, String content) {
        Logger.i(logName, content);
    }

    public static void d(String content) {
        d(LOG_NAME, content);
    }

    public static void d(String logName,String content) {
        Logger.d(logName, content);
    }
}
