package com.test.cheng.practice.view.main;

import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.view.IMvpView;

import java.util.List;

/**
 * Created by kexiaoderenren on 2017/2/7.
 */
public interface IHomeView extends IMvpView {

    /**
     * 获取当天消息列表和热门消息
     * @param date  当前请求日期
     * @param topStorieList 顶部展示列表
     * @param storiesList  消息列表
     */
    void getNews(String date,List<LastestNews.TopStoriesEntity> topStorieList, List<LastestNews.StoriesEntity> storiesList);

    /**
     * 记载更多历史消息列表
     * @param date  当前请求日期
     * @param storiesList  历史消息列表
     */
    void loadMore(String date, List<LastestNews.StoriesEntity> storiesList);
}
