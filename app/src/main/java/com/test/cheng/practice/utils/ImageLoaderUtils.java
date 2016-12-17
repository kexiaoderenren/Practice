package com.test.cheng.practice.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 对Glide图片加载框架进行简单的封装
 * Created by kexiaoderenren on 2016/12/16.
 */
public class ImageLoaderUtils {

    public static void loadImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImg(Fragment fragment, String url, ImageView imageView) {
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .into(imageView);
    }
}
