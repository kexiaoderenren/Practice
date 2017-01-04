package com.test.cheng.practice.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.test.cheng.practice.App;

/**
 * 对Glide图片加载框架进行简单的封装
 * Created by kexiaoderenren on 2016/12/16.
 */
public class ImageLoaderUtils {

    public static void loadImg(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 暂停所有正在加载的图片
     */
    public static void pauseRequest(){
        if (Util.isOnMainThread()) {
            Glide.with(App.getInstance()).pauseRequests();
        }
    }

}
