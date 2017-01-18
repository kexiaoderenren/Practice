package com.test.cheng.practice.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.test.cheng.practice.App;

/**
 * 网络处理工具类
 * Created by kexiaoderenren on 2017/1/18.
 */
public class NetUtils {

    /**
     * 检测网络是否连接
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    /**
     * 判断当前网络是否处于WiFi环境下
     * @return
     */
    public static boolean isWifiNetwork() {
        ConnectivityManager manager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }
}
