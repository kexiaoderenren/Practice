package com.test.cheng.practice.model.repository;

import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.presenter.BasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public class DiscoverListRepository implements ILoader{

    private Subscriber<ThemesListVo> subscriber;

    public void getThemesDetail(int themesId, final OnLoadListener<ThemesListVo> onLoadListener) {
        subscriber = new Subscriber<ThemesListVo>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                onLoadListener.loadFinished();
                onLoadListener.loadFailed(e.getMessage());
            }

            @Override
            public void onNext(ThemesListVo themesListVo) {
                onLoadListener.loadFinished();
                onLoadListener.loadSuccess(themesListVo);
            }
        };
        ApiLoader.newApi().getThemesDetail2(themesId).
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancelLoader() {
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
