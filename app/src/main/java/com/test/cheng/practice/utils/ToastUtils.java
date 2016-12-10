package com.test.cheng.practice.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kexiaoderenren on 2016/12/8.
 */
public class ToastUtils {

    public static void show(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
