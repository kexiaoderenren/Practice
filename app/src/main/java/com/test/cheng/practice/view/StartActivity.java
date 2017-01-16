package com.test.cheng.practice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.handler.StartHandler;
import com.test.cheng.practice.model.bean.StartImgVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.DeviceUtils;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class StartActivity extends BaseActivity {

    @BindView(R.id.img_start) ImageView imgStart;
    @BindView(R.id.tv_txt) TextView tvTxt;

    private StartHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        handler = new StartHandler(this);
        StringBuffer stringBuffer = new StringBuffer()
                .append(DeviceUtils.getSreenWidth(this))
                .append("*")
                .append(DeviceUtils.getSreenHeight(this));
        ApiLoader.newApi().getStartImg(stringBuffer.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StartImgVo>() {

                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartImgVo startImgVo) {
                        ImageLoaderUtils.loadImg(StartActivity.this, startImgVo.getImg(), imgStart);
                        tvTxt.setText(startImgVo.getText());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(StartHandler.INTENT_MAIN, Constants.Constant_4000);
    }

    public void intentMain() {
        HomeActivity.start(StartActivity.this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler.hasMessages(StartHandler.INTENT_MAIN)) {
            handler.removeMessages(StartHandler.INTENT_MAIN);
        }
        ImageLoaderUtils.pauseRequest();
    }

}
