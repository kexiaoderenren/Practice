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
public class DiscoverListRepository implements IDiscoverListLoader<ThemesListVo>{

    @Override
    public void getThemesDetail(int themesId, final OnLoadListener<ThemesListVo> onLoadListener) {
        ApiLoader.newApi().getThemesDetail2(themesId).
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemesListVo>() {
                    @Override
                    public void onCompleted() {
                        onLoadListener.loadFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoadListener.loadFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ThemesListVo themesListVo) {
                        onLoadListener.loadSuccess(themesListVo);
                    }
                });
    }
}
