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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class StartActivity extends BaseActivity {

    @BindView(R.id.img_start) ImageView imgStart;
    @BindView(R.id.tv_txt) TextView tvTxt;

    private Subscriber subscriber;

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
        if (subscriber == null) {
            subscriber = new Subscriber<StartImgVo>() {

                @Override
                public void onCompleted() {}

                @Override
                public void onError(Throwable e) {}

                @Override
                public void onNext(StartImgVo startImgVo) {
                    ImageLoaderUtils.loadImg(StartActivity.this, startImgVo.getImg(), imgStart);
                    tvTxt.setText(startImgVo.getText());
                }
            };
        }
        ApiLoader.newApi().getStartImg(stringBuffer.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
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
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            LogUtils.d("-----subscriber.unsubscribe()");
            subscriber.unsubscribe();
        }
    }
}
