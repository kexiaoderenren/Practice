package com.test.cheng.practice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.NewsListAdapter;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.holder.TopNewsBannerHolder;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.widget.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

/**
 * 实现抽屉效果Activity
 * Created by keixaoderenren on 2017/1/3.
 */
public class HomeActivity extends BaseActivity implements NestedScrollView.OnScrollChangeListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.swipe_refresh) SuperSwipeRefreshLayout swipeRefresh;
    @BindView(R.id.convenientBanner) ConvenientBanner convenientBanner;
    @BindView(R.id.nestedScrollView) NestedScrollView nestedScrollView;

    private List<LastestNews.StoriesEntity> storiesEntityList;  //消息列表
    private List<LastestNews.TopStoriesEntity> topStorieList;   //顶部展示列表
    private NewsListAdapter newsListAdapter;
    private String lastday;  //标记上一天日期


    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initToolbar();
        init();
    }

    private void init() {
        storiesEntityList = new ArrayList<>();
        topStorieList = new ArrayList<>();

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.zhihu_report, R.string.zhihu_report);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final View refreshView  = getLayoutInflater().inflate(R.layout.loading, null);
        View footerView  = getLayoutInflater().inflate(R.layout.loading, null);
        swipeRefresh.setHeaderViewBackgroundColor(0xff888888);
        swipeRefresh.setHeaderView(refreshView);
        swipeRefresh.setFooterView(footerView);
        swipeRefresh.setTargetScrollWithLayout(true);
        //
        swipeRefresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {
                getNewsList();
            }

            @Override
            public void onPullDistance(int distance) {}

            @Override
            public void onPullEnable(boolean enable) {}
        });

        swipeRefresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (!TextUtils.isEmpty(lastday)) getBeforeNews(lastday);
            }

            @Override
            public void onPushDistance(int distance) {}

            @Override
            public void onPushEnable(boolean enable) {}
        });

        nestedScrollView.setOnScrollChangeListener(this);

        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);

        recyclerview.setItemAnimator(new DefaultItemAnimator());
        newsListAdapter = new NewsListAdapter(storiesEntityList);
        recyclerview.setAdapter(newsListAdapter);

        getNewsList();
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
       if (lastday != null && !lastday.equals(toolbar.getTitle().toString())) {
           toolbar.postDelayed(new Runnable() {
               @Override
               public void run() {
                   toolbar.setTitle(lastday);
               }
           }, 500);
       }
    }

    /*** 获取最新消息*/
    private void getNewsList() {
        swipeRefresh.setRefreshing(true);
        ApiLoader.newApi().getLastedNews().enqueue(new Callback<LastestNews>() {
            @Override
            public void onResponse(Call<LastestNews> call, Response<LastestNews> response) {
                LogUtils.i(response.body().toString());
                swipeRefresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    LastestNews lastestNews = response.body();
                    if (lastestNews != null && lastestNews.getStories() != null && lastestNews.getStories().size() > 0) {
                        lastday = lastestNews.getDate();
                        storiesEntityList.clear();
                        storiesEntityList.addAll(lastestNews.getStories());
                        newsListAdapter.notifyDataSetChanged();
                    }
                    if (lastestNews == null || lastestNews.getTop_stories() == null || lastestNews.getTop_stories().size() <= 0) {
                        convenientBanner.setVisibility(View.GONE);
                    } else {
                        topStorieList.clear();
                        topStorieList.addAll(lastestNews.getTop_stories());
                        convenientBanner.setVisibility(View.VISIBLE);
                        convenientBanner.setPages(new CBViewHolderCreator<TopNewsBannerHolder>() {
                            @Override
                            public TopNewsBannerHolder createHolder() {
                                return new TopNewsBannerHolder();
                            }
                        }, topStorieList)    //设置需要切换的View
                                .setPointViewVisible(true)    //设置指示器是否可见
                                .setPageIndicator(new int[]{R.mipmap.navigation_drop_normal, R.mipmap.navigation_drop_selected})   //设置指示器圆点
                                .startTurning(4000)     //设置自动切换（同时设置了切换时间间隔）
                                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器位置（左、中、右）
                    }
                }
            }

            @Override
            public void onFailure(Call<LastestNews> call, Throwable t) {}
        });
    }

    /**
     * 获取特定日期前一天消息
     * @param date 特定日期
     */
    private void getBeforeNews(String date) {

        ApiLoader.newApi().getBeforeNews(date).enqueue(new Callback<LastestNews>() {
            @Override
            public void onResponse(Call<LastestNews> call, Response<LastestNews> response) {
                swipeRefresh.setLoadMore(false);
                if (response == null || !response.isSuccessful()) { return; }
                LastestNews lastestNews = response.body();
                if (lastestNews != null && lastestNews.getStories() != null && lastestNews.getStories().size() > 0) {
                    lastday = lastestNews.getDate();
                    storiesEntityList.addAll(lastestNews.getStories());
                    newsListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<LastestNews> call, Throwable t) {}
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
