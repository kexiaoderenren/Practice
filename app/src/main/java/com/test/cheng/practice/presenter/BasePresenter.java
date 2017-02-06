package com.test.cheng.practice.presenter;

import com.test.cheng.practice.view.discover.IMvpView;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public class BasePresenter<V extends IMvpView> implements IPresenter<V> {

    private V mMvpView;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
