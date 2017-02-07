package com.test.cheng.practice.presenter;

import android.graphics.Color;
import android.graphics.PorterDuff;

import com.test.cheng.practice.App;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.NewsVo;
import com.test.cheng.practice.model.repository.NewsDetailRepository;
import com.test.cheng.practice.model.repository.OnLoadListener;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.NetUtils;
import com.test.cheng.practice.view.main.INewsDetailView;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public class NewsDetailPresenter extends BasePresenter<INewsDetailView> {

    private NewsDetailRepository repository;

    @Override
    public void attachView(INewsDetailView mvpView) {
        super.attachView(mvpView);
        repository = new NewsDetailRepository();
    }

    @Override
    public void detachView() {
        repository.cancelLoader();
        super.detachView();
    }

    public void getNewsDetail(int newsId) {
        checkViewAttached();
        if (!NetUtils.isNetworkAvailable()) {
            getMvpView().showNetError(App.getInstance().getString(R.string.network_connect_failed));
            return;
        }
        getMvpView().showLoading("");
        repository.getNewsDetail(newsId, new OnLoadListener<NewsVo>() {
            @Override
            public void loadSuccess(NewsVo newsVo) {
                String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">";

                StringBuffer html = new StringBuffer().append("<html><head>")
                        .append(css)
                        .append("</head><body>")
                        .append(newsVo.getBody())
                        .append("</body></html>");
                LogUtils.i(html.toString());
                getMvpView().load(html.toString(), newsVo.getImage(), newsVo.getImage_source());
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
