package com.test.cheng.practice.view.main;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.ThemesListAdapter;
import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.ApiManager;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.view.base.BaseFragment;
import com.test.cheng.practice.widget.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kexiaoderenren on 2016/12/9.
 */
public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.img_topic) ImageView imgTopic;
    @BindView(R.id.tv_topic) TextView tvTopic;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @BindView(R.id.nestedScrollView) NestedScrollView nestedScrollView;
    @BindView(R.id.swipe_refresh) SuperSwipeRefreshLayout swipeRefresh;

    private List<ThemesListVo.StoriesBean> stories;
    private ThemesListAdapter adapter;
    private int id;

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
                getThemesDetail();
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
        getThemesDetail();
    }

    private void getThemesDetail() {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        ApiLoader.newApi().getThemesDetail(id).enqueue(new Callback<ThemesListVo>() {
            @Override
            public void onResponse(Call<ThemesListVo> call, Response<ThemesListVo> response) {
                swipeRefresh.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    ThemesListVo themesListVo = response.body();
                    ImageLoaderUtils.loadImg(getMyActivity(), themesListVo.getBackground(), imgTopic);
                    tvTopic.setText(themesListVo.getDescription());
                    if (themesListVo.getStories() != null && themesListVo.getStories().size() > 0) {
                        stories.addAll(themesListVo.getStories());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ThemesListVo> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
            }
        });
    }
}
