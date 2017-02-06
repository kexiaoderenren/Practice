package com.test.cheng.practice.model.repository;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public interface OnLoadListener<T> {


    void loadSuccess(T response);

    void loadFailed(String errorMsg);

    void loadFinished();
}
