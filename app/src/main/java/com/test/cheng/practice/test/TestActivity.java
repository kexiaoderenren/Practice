package com.test.cheng.practice.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.cheng.practice.R;
import com.test.cheng.practice.view.common.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cheng on 2016/12/8.
 */
public class TestActivity extends BaseActivity  implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;

    private List<String> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        lists = new ArrayList<>();
        String[] test = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21",
                "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21",
                "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
        lists.addAll(Arrays.asList(test));

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(new TestAdapter(lists));

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }


}
