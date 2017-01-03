package com.test.cheng.practice.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.test.cheng.practice.App;

/**
 * 设备信息工具类
 * Created by kexiaoderenren on 2017/1/3.
 */
public class DeviceUtils {


    /**
     * 获取屏幕宽度
     * @return int
     */
    public static int getSreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return int
     */
    public static int getSreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
