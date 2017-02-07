package com.test.cheng.practice.view.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.ThemesListAdapter;
import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.presenter.DiscoverPresenter;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.ToastUtils;
import com.test.cheng.practice.view.base.BaseFragment;
import com.test.cheng.practice.widget.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现模块
 *     MVP架构（https://github.com/fangx/ZhiHuMVP）
 * Created by kexiaoderenren on 2017/2/6.
 */
public class DiscoverFragment extends BaseFragment implements IDiscoverListView{

    @BindView(R.id.img_topic) ImageView imgTopic;
    @BindView(R.id.tv_topic) TextView tvTopic;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @BindView(R.id.nestedScrollView) NestedScrollView nestedScrollView;
    @BindView(R.id.swipe_refresh) SuperSwipeRefreshLayout swipeRefresh;

    private List<ThemesListVo.StoriesBean> stories;
    private ThemesListAdapter adapter;
    private int id;
    private DiscoverPresenter presenter;

    public static DiscoverFragment newIntance(int id) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.PARAM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(Constants.PARAM_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stories = new ArrayList<>();
        final View refreshView  = LayoutInflater.from(getMyActivity()).inflate(R.layout.loading, null);

        swipeRefresh.setHeaderViewBackgroundColor(0xff888888);
        swipeRefresh.setHeaderView(refreshView);
        swipeRefresh.setTargetScrollWithLayout(true);
        swipeRefresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {
                presenter.getThemesDetail(id);
            }

            @Override
            public void onPullDistance(int distance) {}

            @Override
            public void onPullEnable(boolean enable) {}
        });
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMyActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);

        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new ThemesListAdapter(stories);
        recyclerview.setAdapter(adapter);

        presenter = new DiscoverPresenter();
        presenter.attachView(this);
        presenter.getThemesDetail(id);
    }

    @Override
    public void refresh(List<ThemesListVo.StoriesBean> data, String description, String background) {
        ImageLoaderUtils.loadImg(getMyActivity(), background, imgTopic);
        tvTopic.setText(description);
        if (data != null && data.size() > 0) {
            stories.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore(List<ThemesListVo.StoriesBean> data) {}

    @Override
    public void showLoading(String msg) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showError(String msg) {}

    @Override
    public void showNetError(String msg) {
        ToastUtils.show(getMyActivity(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
        presenter.detachView();
    }
}
