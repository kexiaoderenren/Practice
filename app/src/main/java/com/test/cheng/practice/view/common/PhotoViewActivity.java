package com.test.cheng.practice.view.common;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.DeviceUtils;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by kexiaoderenren on 2017/1/16.
 */
public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.photoview) PhotoView photoview;
    private String url;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(Constants.PARAM_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        ButterKnife.bind(this);
        if (savedInstanceState != null && !TextUtils.isEmpty(savedInstanceState.getString(Constants.PARAM_URL))) {
            url = savedInstanceState.getString(Constants.PARAM_URL);
        } else {
            url = getIntent().getStringExtra(Constants.PARAM_URL);
        }
        photoview.setZoomable(true);
        ImageLoaderUtils.loadFlexibleImg(this, url, photoview);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.PARAM_URL, url);
        Log.i("test","------onSaveInstanceState()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
    }
}
