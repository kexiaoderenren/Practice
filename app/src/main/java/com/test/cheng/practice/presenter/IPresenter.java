package com.test.cheng.practice.presenter;

import com.test.cheng.practice.view.discover.IMvpView;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public interface IPresenter<V extends IMvpView> {

    void attachView(V mvpView);

    void detachView();
}
