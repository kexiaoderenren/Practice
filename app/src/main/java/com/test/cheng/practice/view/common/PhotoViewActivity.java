package com.test.cheng.practice.view.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
 * Created by gaokuncheng on 2017/1/16.
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

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) photoview.getLayoutParams();
        params.width = DeviceUtils.getSreenWidth(this);
        photoview.setLayoutParams(params);

        if (savedInstanceState != null && !TextUtils.isEmpty(savedInstanceState.getString(Constants.PARAM_URL))) {
            Log.d("test","--------- not null");
            url = getIntent().getStringExtra(Constants.PARAM_URL);
        } else {
            Log.d("test","--------- null");
            url = getIntent().getStringExtra(Constants.PARAM_URL);
        }
        ImageLoaderUtils.loadImg(this, url, photoview);
    }

    @OnClick(R.id.img_download)
    void downLoadImg() {
        LogUtils.d("7777777777777777777777777777");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.PARAM_TITLE, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
    }
}
