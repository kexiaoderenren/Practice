package com.test.cheng.practice.presenter;

import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.repository.DiscoverListRepository;
import com.test.cheng.practice.model.repository.IDiscoverListLoader;
import com.test.cheng.practice.model.repository.OnLoadListener;
import com.test.cheng.practice.view.discover.IDiscoverListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public class DiscoverPresenter extends BasePresenter<IDiscoverListView> {

    private IDiscoverListLoader discoverListLoader;

    @Override
    public void attachView(IDiscoverListView mvpView) {
        super.attachView(mvpView);
        discoverListLoader = new DiscoverListRepository();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getThemesDetail(int id) {

        discoverListLoader.getThemesDetail(id, new OnLoadListener<ThemesListVo>() {

            @Override
            public void loadSuccess(ThemesListVo response) {
                getMvpView().refresh(response.getStories(), response.getDescription(), response.getBackground());
            }

            @Override
            public void loadFailed(String errorMsg) {
                getMvpView().showError(errorMsg);
            }

            @Override
            public void loadFinished() {
                getMvpView().hideLoading();
            }
        });
    }
}
