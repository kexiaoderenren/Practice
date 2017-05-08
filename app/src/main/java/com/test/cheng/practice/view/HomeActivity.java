package com.test.cheng.practice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;
import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.NewsListAdapter;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.holder.TopNewsBannerHolder;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.LoggingInterceptor;
import com.test.cheng.practice.presenter.HomePresenter;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.DateUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.SharePreferenceUtils;
import com.test.cheng.practice.utils.ToastUtils;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.common.HtmlActivity;
import com.test.cheng.practice.view.discover.NewReleaseActivity;
import com.test.cheng.practice.view.main.IHomeView;
import com.test.cheng.practice.view.main.NewsDetailActivity;
import com.test.cheng.practice.widget.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 实现抽屉效果Activity
 * Created by keixaoderenren on 2017/1/3.
 */
public class HomeActivity extends BaseActivity implements NestedScrollView.OnScrollChangeListener,
        NavigationView.OnNavigationItemSelectedListener, IHomeView {

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
    private String today;  //标记当天日期

    private HomePresenter presenter;

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
        navView.setNavigationItemSelectedListener(this);
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
                presenter.getLastedNews();
            }

            @Override
            public void onPullDistance(int distance) {}

            @Override
            public void onPullEnable(boolean enable) {}
        });

        swipeRefresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (!TextUtils.isEmpty(today)) presenter.getBeforeNews(today);
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

        presenter = new HomePresenter();
        presenter.attachView(this);
        presenter.getLastedNews();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.new_releases:
                NewReleaseActivity.start(this);
                return true;
            case R.id.setting:

                return true;
            case R.id.about:
                HtmlActivity.start(this, Constants.URL_PERSONAL_HOME);
                return true;
            case R.id.brightness:
                /**
                 MODE_NIGHT_NO 日间模式
                 MODE_NIGHT_YES 夜间模式
                 MODE_NIGHT_AUTO 根据时间自动切换日夜间模式
                 MODE_NIGHT_FOLLOW_SYSTEM 默认模式，就是跟随系统的设置，
                 据说有可能以后会在android系统设置中添加日夜间模式的设置，此时如果你的app是默认模式，会根据系统设置变化日夜间模式
                 */
                if (SharePreferenceUtils.getBoolean(this, SharePreferenceUtils.SP_KEY_BRIGHTNESS, true)) {
                    SharePreferenceUtils.putBoolean(this, SharePreferenceUtils.SP_KEY_BRIGHTNESS, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                } else {
                    SharePreferenceUtils.putBoolean(this, SharePreferenceUtils.SP_KEY_BRIGHTNESS, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
       if (today != null && !today.equals(toolbar.getTitle().toString())) {
           toolbar.postDelayed(new Runnable() {
               @Override
               public void run() {
                   toolbar.setTitle(today + " " + DateUtils.getWeek(DateUtils.dateFormatTransfer(today)));
               }
           }, 500);
       }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void getNews(String date, final List<LastestNews.TopStoriesEntity> topStorieList, List<LastestNews.StoriesEntity> storiesList) {

        today = date;
        //消息列表
        if (storiesList != null && storiesList.size() > 0) {
            storiesEntityList.clear();
            storiesEntityList.addAll(storiesList);
            newsListAdapter.notifyDataSetChanged();
        }

        //广告展位
        if (topStorieList != null && topStorieList.size() >0 ) {
            this.topStorieList.clear();
            this.topStorieList.addAll(topStorieList);
            convenientBanner.setVisibility(View.VISIBLE);
            convenientBanner.setPages(new CBViewHolderCreator<TopNewsBannerHolder>() {
                @Override
                public TopNewsBannerHolder createHolder() {
                    return new TopNewsBannerHolder();
                }
            }, this.topStorieList)    //设置需要切换的View
                    .setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.mipmap.navigation_drop_normal, R.mipmap.navigation_drop_selected})   //设置指示器圆点
                    .startTurning(4000)     //设置自动切换（同时设置了切换时间间隔）
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器位置（左、中、右）
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            LastestNews.TopStoriesEntity entity = topStorieList.get(position);
                            NewsDetailActivity.start(HomeActivity.this, entity.getId(), entity.getTitle());
                        }
                    })
                    .setManualPageable(true);
        } else {
            convenientBanner.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadMore(String date, List<LastestNews.StoriesEntity> storiesList) {
        today = date;
        storiesEntityList.addAll(storiesList);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(String msg) {
        if (!HomePresenter.LOAD_MORE.equals(msg)) {
            swipeRefresh.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefresh.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
        swipeRefresh.setLoadMore(false);
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void showNetError(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
