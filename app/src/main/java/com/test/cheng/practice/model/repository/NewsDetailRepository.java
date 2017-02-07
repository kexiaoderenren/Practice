package com.test.cheng.practice.model.repository;

import android.graphics.Color;
import android.graphics.PorterDuff;

import com.test.cheng.practice.model.bean.NewsVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.BaseSubscriber;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.main.INewsDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public class NewsDetailRepository implements ILoader {

    private Subscriber<NewsVo> subscriber;

    @Override
    public void cancelLoader() {
        if(subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }

    public void getNewsDetail(int newsId, final OnLoadListener<NewsVo> onLoadListener) {
        subscriber = new Subscriber<NewsVo>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                onLoadListener.loadFinished();
                onLoadListener.loadFailed(e.getMessage());
            }

            @Override
            public void onNext(NewsVo newsVo) {
                onLoadListener.loadFinished();
                onLoadListener.loadSuccess(newsVo);
            }
        };

        ApiLoader.newApi().getNewsDetail2(newsId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }
}
