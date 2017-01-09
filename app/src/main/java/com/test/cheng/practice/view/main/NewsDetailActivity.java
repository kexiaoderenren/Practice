package com.test.cheng.practice.view.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.NewsVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.ApiManager;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.base.BaseFragment;
import com.test.cheng.practice.view.common.HtmlFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kexiaoderenren on 2017/1/7.
 */
public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.main_content) CoordinatorLayout mainContent;
    @BindView(R.id.tv_img_author) TextView tvImgAuthor;

    private int id;
    private String title = "";
    private NewsVo newsVo;

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
            getNewsDetail();
        }
        collapsingToolbar.setTitle(title);
    }

    private void getNewsDetail() {
        showHoldLoading();
        ApiLoader.newApi().getNewsDetail(id).enqueue(new Callback<NewsVo>() {

            @Override
            public void onResponse(Call<NewsVo> call, Response<NewsVo> response) {
                hideHoldLoading();
                if (response.code() == 200) {
                    LogUtils.i(response.body().toString());

                    newsVo = response.body();
                    ImageLoaderUtils.loadImg(NewsDetailActivity.this, newsVo.getImage(), imgBackdrop);
                    tvImgAuthor.setText(newsVo.getImage_source());

                    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                    String html = "<html><head>" + css + "</head><body>" + newsVo.getBody() + "</body></html>";
                    initViewLayout(html);
                }
            }

            @Override
            public void onFailure(Call<NewsVo> call, Throwable t) {
                hideHoldLoading();
            }
        });
    }

    private void initViewLayout(String source) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = HtmlFragment.newIntance(HtmlFragment.DATA_TYPE, source);
        fm.beginTransaction().replace(R.id.frame_container, fragment, HtmlFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderUtils.pauseRequest();
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
