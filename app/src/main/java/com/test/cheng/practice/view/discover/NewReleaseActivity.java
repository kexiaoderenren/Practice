package com.test.cheng.practice.view.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.MainPagerAdapter;
import com.test.cheng.practice.model.bean.ThemesCategoryVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.BaseCallback;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.widget.NoSrcollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kexiaoderenren on 2017/1/9.
 */
public class NewReleaseActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.vp_pager) NoSrcollViewPager vpPager;
    @BindView(R.id.tl_title) TabLayout tlTitle;

    private ThemesCategoryVo themesVo;
    private List<String> tabTitles;
    private List<Fragment> fragments;
    private MainPagerAdapter pageAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, NewReleaseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_release);
        ButterKnife.bind(this);
        initToobar();
        init();
    }

    private void init() {
        tabTitles = new ArrayList<>();
        fragments = new ArrayList<>();
        getThemes();
    }

    private void getThemes() {
        showHoldLoading();
        ApiLoader.newApi().getThemes().enqueue(new BaseCallback<ThemesCategoryVo>() {
            @Override
            protected void success(ThemesCategoryVo result) {
                themesVo = result;
                for (ThemesCategoryVo.OthersBean bean : themesVo.getOthers()) {
                    tabTitles.add(bean.getName());
                }
                if (tabTitles.size() > 0) {
                    initFragments(tabTitles.size());
                    pageAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabTitles, fragments);
                    vpPager.setAdapter(pageAdapter);
                    tlTitle.setupWithViewPager(vpPager);
                    tlTitle.setTabsFromPagerAdapter(pageAdapter);
                }
            }
        });
    }

    private void initFragments(int count) {
        for (int i=0; i<count; i++) {
            fragments.add(DiscoverFragment.newIntance(themesVo.getOthers().get(i).getId()));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {}

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    private void initToobar() {
        toolbar.setTitle(getString(R.string.find));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
