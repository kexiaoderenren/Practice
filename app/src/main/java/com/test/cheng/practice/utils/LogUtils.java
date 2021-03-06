package com.test.cheng.practice.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by kexiaoderenren on 2016/12/21.
 */
public class LogUtils {

    public static void d (String content) {
        Logger.d(content);
    }

    public static void i(String content) {
        Logger.i(content);
    }

    public static void e(String content) {
        Logger.e(content);
    }

    public static void net(String content) {
        Logger.i("net:" + content);
    }
}
