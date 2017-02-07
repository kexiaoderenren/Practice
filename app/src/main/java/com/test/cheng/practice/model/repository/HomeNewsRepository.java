package com.test.cheng.practice.model.repository;

import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.BaseCallback;

import retrofit2.Call;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public class HomeNewsRepository implements ILoader {

    private Call lastestCall, beforeCall;

    @Override
    public void cancelLoader() {
        if (lastestCall != null && !lastestCall.isCanceled()) lastestCall.cancel();
        if (beforeCall != null && !beforeCall.isCanceled()) beforeCall.cancel();
    }

    /**
     * 获取最新消息
     * @param onLoadListener
     */
    public void getLastedNews(final OnLoadListener<LastestNews> onLoadListener) {

        lastestCall = ApiLoader.newApi().getLastedNews();
        lastestCall.enqueue(new BaseCallback<LastestNews>() {
            @Override
            protected void success(LastestNews result) {
                onLoadListener.loadFinished();
                onLoadListener.loadSuccess(result);
            }

            @Override
            protected void failed() {
                super.failed();
                onLoadListener.loadFinished();
            }
        });
    }

    /**
     * 获取特定日期前一天消息
     * @param date
     * @param onLoadListener
     */
    public void getBeforeNews(String date, final OnLoadListener<LastestNews> onLoadListener) {

        beforeCall = ApiLoader.newApi().getBeforeNews(date);
        beforeCall.enqueue(new BaseCallback<LastestNews>() {

            @Override
            protected void success(LastestNews result) {
                onLoadListener.loadFinished();
                onLoadListener.loadSuccess(result);
            }

            @Override
            protected void failed() {
                super.failed();
                onLoadListener.loadFinished();
                onLoadListener.loadFailed("");
            }
        });
    }
}
