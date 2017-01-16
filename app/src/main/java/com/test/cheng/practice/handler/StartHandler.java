package com.test.cheng.practice.handler;

import android.os.Handler;
import android.os.Message;

import com.test.cheng.practice.view.StartActivity;

import java.lang.ref.WeakReference;

/**
 * Created by kexiaoderenren on 2017/1/12.
 */
public class StartHandler extends Handler {

    /*** 跳转到首页*/
    public static final int INTENT_MAIN = 1;

    private WeakReference<StartActivity> mActivity;


    public StartHandler(StartActivity activity){
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        StartActivity startActivity = mActivity.get();
        if (startActivity == null) { return; }
        switch (msg.what) {
            case INTENT_MAIN:
                startActivity.intentMain();
                break;
        }
    }
}
