package com.test.cheng.practice.presenter;

import com.test.cheng.practice.App;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.model.repository.DiscoverListRepository;
import com.test.cheng.practice.model.repository.OnLoadListener;
import com.test.cheng.practice.utils.NetUtils;
import com.test.cheng.practice.view.discover.IDiscoverListView;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public class DiscoverPresenter extends BasePresenter<IDiscoverListView> {

    private DiscoverListRepository repository;

    public DiscoverPresenter() {
        repository = new DiscoverListRepository();
    }

    @Override
    public void attachView(IDiscoverListView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        repository.cancelLoader();
        super.detachView();
    }

    public void getThemesDetail(int id) {
        checkViewAttached();
        if (!NetUtils.isNetworkAvailable()) {
            getMvpView().showNetError(App.getInstance().getString(R.string.network_connect_failed));
            return;
        }
        repository.getThemesDetail(id, new OnLoadListener<ThemesListVo>() {

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
