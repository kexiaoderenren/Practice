package com.test.cheng.practice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.NewsListAdapter;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.test.TestAdapter;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;

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
public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    private List<LastestNews.StoriesEntity> storiesEntityList;  //消息列表
    private NewsListAdapter newsListAdapter;

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.mine, R.string.mine);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        newsListAdapter = new NewsListAdapter(storiesEntityList);
        recyclerview.setAdapter(newsListAdapter);
        getNewsList();

    }

    private void getNewsList() {
        showHoldLoading();
        ApiLoader.newApi().getLastedNews().enqueue(new Callback<LastestNews>() {
            @Override
            public void onResponse(Call<LastestNews> call, Response<LastestNews> response) {
                hideHoldLoading();
                if (response.isSuccessful()) {
                    LastestNews lastestNews = response.body();
                    if (lastestNews != null && lastestNews.getStories() != null && lastestNews.getStories().size()>0) {
                        storiesEntityList.addAll(lastestNews.getStories());
                        newsListAdapter.notifyDataSetChanged();
                    }
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
