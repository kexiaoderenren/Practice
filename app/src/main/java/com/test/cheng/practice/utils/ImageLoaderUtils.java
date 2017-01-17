package com.test.cheng.practice.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;
import com.test.cheng.practice.App;
import com.test.cheng.practice.R;

/**
 * 对Glide图片加载框架进行简单的封装
 * Created by kexiaoderenren on 2016/12/16.
 */
public class ImageLoaderUtils {

    /**
     * 默认加载网络图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(url)
                    .placeholder(R.drawable.ic_default_color)
                    .error(R.drawable.ic_default_color)
                     .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * Glide加载可伸缩图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadFlexibleImg(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(url)
                    .placeholder(R.drawable.ic_default_color)
                    .error(R.drawable.ic_default_color)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    public static void loadImgFitCenter(final Activity activity, final String url, final ImageView imageView, final int width) {
         Glide.with(activity)
                 .load(url)
                 .asBitmap()
                 .into(new SimpleTarget<Bitmap>() {
                     @Override
                     public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                         int imageWidth = resource.getWidth();
                         int imageHeight = resource.getHeight();
                         int height = width * imageHeight / imageWidth;
                         ViewGroup.LayoutParams para = imageView.getLayoutParams();
                         para.height = height;
                         imageView.setLayoutParams(para);
                         Glide.with(activity).load(url).asBitmap().into(imageView);
                     }
                 });
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
