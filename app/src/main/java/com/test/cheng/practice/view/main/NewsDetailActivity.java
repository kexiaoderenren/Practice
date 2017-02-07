package com.test.cheng.practice.view.main;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.NewsVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.BaseSubscriber;
import com.test.cheng.practice.presenter.NewsDetailPresenter;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.ToastUtils;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.base.BaseFragment;
import com.test.cheng.practice.view.common.HtmlFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kexiaoderenren on 2017/1/7.
 */
public class NewsDetailActivity extends BaseActivity implements INewsDetailView{

    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.main_content) CoordinatorLayout mainContent;
    @BindView(R.id.tv_img_author) TextView tvImgAuthor;

    private int id;
    private String title = "";
    private NewsDetailPresenter presenter;

    public static void start(Context context, int id, String title) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.PARAM_ID, id);
        intent.putExtra(Constants.PARAM_TITLE, title);
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
        if (getIntent() != null) {
            id = getIntent().getIntExtra(Constants.PARAM_ID, 0);
            title = getIntent().getStringExtra(Constants.PARAM_TITLE);
            presenter = new NewsDetailPresenter();
            presenter.attachView(this);
            presenter.getNewsDetail(id);
        }
        collapsingToolbar.setTitle(title);
    }


    private void initViewLayout(String source) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = HtmlFragment.newIntance(HtmlFragment.DATA_TYPE, source);
        fm.beginTransaction().replace(R.id.cardview_container, fragment, HtmlFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
        presenter.detachView();
    }

    @Override
    public void load(String content, String imageUrl, String authorName) {
        ImageLoaderUtils.loadImg(NewsDetailActivity.this, imageUrl, imgBackdrop);
        imgBackdrop.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        tvImgAuthor.setText(authorName);
        initViewLayout(content);
    }

    @Override
    public void showLoading(String msg) {
        showHoldLoading(msg);
    }

    @Override
    public void hideLoading() {
        hideHoldLoading();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
