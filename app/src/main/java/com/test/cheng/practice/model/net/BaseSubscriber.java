package com.test.cheng.practice.model.net;

import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.NetUtils;
import com.test.cheng.practice.utils.ToastUtils;
import com.test.cheng.practice.view.base.BaseActivity;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by kexiaoderenren on 2017/1/18.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private WeakReference<BaseActivity> mActivity;

    public BaseSubscriber(BaseActivity activity) {
        this.mActivity = new WeakReference<BaseActivity>(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        BaseActivity baseActivity = mActivity.get();
        if (baseActivity == null) { return; }
        if (!NetUtils.isNetworkAvailable()) {
            ToastUtils.show(baseActivity, baseActivity.getString(R.string.network_connect_failed));
            // **一定要主动调用下面这一句**
            onCompleted();
            return;
        }
        // 显示进度条
        baseActivity.showHoldLoading();
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e.getLocalizedMessage());
        BaseActivity baseActivity = mActivity.get();
        if (baseActivity == null) { return; }
        baseActivity.hideHoldLoading();
    }

    @Override
    public void onCompleted() {
        //关闭等待进度条
        BaseActivity baseActivity = mActivity.get();
        if (baseActivity == null) { return; }
        baseActivity.hideHoldLoading();
    }


}
