package com.test.cheng.practice.presenter;

import android.text.TextUtils;

import com.test.cheng.practice.App;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.repository.HomeNewsRepository;
import com.test.cheng.practice.model.repository.OnLoadListener;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.NetUtils;
import com.test.cheng.practice.view.main.IHomeView;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public class HomePresenter extends BasePresenter<IHomeView> {

    public final static String LOAD_MORE = "loadMore";
    public final static String REFRESH = "refresh";

    private HomeNewsRepository repository;

    @Override
    public void attachView(IHomeView mvpView) {
        super.attachView(mvpView);
        repository = new HomeNewsRepository();
    }

    @Override
    public void detachView() {
        repository.cancelLoader();
        super.detachView();
    }

    public void getLastedNews() {
        checkViewAttached();
        if (!NetUtils.isNetworkAvailable()) {
            getMvpView().showNetError(App.getInstance().getString(R.string.network_connect_failed));
            return;
        }
        getMvpView().showLoading(REFRESH);
        repository.getLastedNews(new OnLoadListener<LastestNews>() {
            @Override
            public void loadSuccess(LastestNews response) {
                getMvpView().getNews(response.getDate(), response.getTop_stories(), response.getStories());
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

    public void getBeforeNews(String date) {
        checkViewAttached();
        if (!NetUtils.isNetworkAvailable()) {
            getMvpView().showNetError(App.getInstance().getString(R.string.network_connect_failed));
            return;
        }
        getMvpView().showLoading(LOAD_MORE);
        repository.getBeforeNews(date, new OnLoadListener<LastestNews>() {
            @Override
            public void loadSuccess(LastestNews response) {

                if (TextUtils.isEmpty(response.getDate()) || response.getStories()== null || response.getStories().size() <= 0) {
                    getMvpView().showError("");
                    return;
                }
                getMvpView().loadMore(response.getDate(), response.getStories());
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
