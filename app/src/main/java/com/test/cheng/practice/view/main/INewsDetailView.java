package com.test.cheng.practice.view.main;

import com.test.cheng.practice.view.IMvpView;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public interface INewsDetailView extends IMvpView {

    void load(String content, String imageUrl, String authorName);
}
