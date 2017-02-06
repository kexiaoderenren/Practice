package com.test.cheng.practice.view.discover;

import android.view.View;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public interface IMvpView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);

    void showNetError(String msg);
}
