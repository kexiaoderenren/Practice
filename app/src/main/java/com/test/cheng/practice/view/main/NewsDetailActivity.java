package com.test.cheng.practice.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.base.BaseFragment;
import com.test.cheng.practice.view.common.HtmlFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2017/1/7.
 */
public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.main_content) CoordinatorLayout mainContent;

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.PARAM_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initToolbar();
        init();
    }

    private void init() {

    }

    private void initViewLayout(String url) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = HtmlFragment.newIntance(url);
        fm.beginTransaction().replace(R.id.frame_container, fragment, HtmlFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
