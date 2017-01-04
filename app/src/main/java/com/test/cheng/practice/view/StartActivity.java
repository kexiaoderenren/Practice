package com.test.cheng.practice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.StartImgVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.DeviceUtils;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class StartActivity extends BaseActivity {

    @BindView(R.id.img_start) ImageView imgStart;
    @BindView(R.id.tv_txt) TextView tvTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        StringBuffer stringBuffer = new StringBuffer()
                .append(DeviceUtils.getSreenWidth(this))
                .append("*")
                .append(DeviceUtils.getSreenHeight(this));
        ApiLoader.newApi().getStartImg2(stringBuffer.toString()).enqueue(new Callback<StartImgVo>() {
            @Override
            public void onResponse(Call<StartImgVo> call, Response<StartImgVo> response) {
                if (response.isSuccessful()) {
                    LogUtils.d(response.body().toString());
                    StartImgVo vo = response.body();
                    if (vo != null) {
                        ImageLoaderUtils.loadImg(StartActivity.this, vo.getImg(), imgStart);
                        tvTxt.setText(vo.getText());
                    }
                }
            }

            @Override
            public void onFailure(Call<StartImgVo> call, Throwable t) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        imgStart.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.start(StartActivity.this);
                finish();
            }
        }, Constants.Constant_4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
    }
}
