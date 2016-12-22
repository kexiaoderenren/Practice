package com.test.cheng.practice.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.common.BaseActivity;
import com.test.cheng.practice.view.common.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2016/12/8.
 */
public class TestActivity extends BaseToolbarActivity implements BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;

    private List<String> lists;
    private TestAdapter testAdapter;


    @Override
    protected void init() {
        lists = new ArrayList<>();
        String[] test = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        lists.addAll(Arrays.asList(test));

        refreshLayout.setOnRefreshListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        testAdapter = new TestAdapter(lists);
        testAdapter.openLoadAnimation(2);
        testAdapter.setOnLoadMoreListener(this);
        recyclerview.setAdapter(testAdapter);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void settingToolbar() {
        super.settingToolbar();
        toolbar.setTitle("test");
    }

    @Override
    public void onLoadMoreRequested() {

        recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                lists.add("1");
                testAdapter.loadMoreComplete();
                if (lists.size() > 15) {
                    testAdapter.loadMoreEnd();
                }
            }
        }, 1000);
    }


    @Override
    public void onRefresh() {
        recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 500);
    }
}
