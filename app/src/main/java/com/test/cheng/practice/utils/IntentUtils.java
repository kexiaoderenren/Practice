package com.test.cheng.practice.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by kexiaoderenren on 2017/1/16.
 */
public class IntentUtils {

    /**
     * 调用系统浏览器
     * @param context
     * @param url
     */
    public static void startSystemBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
