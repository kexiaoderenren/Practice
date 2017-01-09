package com.test.cheng.practice.view.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.MainPagerAdapter;
import com.test.cheng.practice.model.bean.ThemesVo;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.main.MessageFragment;
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
public class NewReleaseActivity extends BaseActivity {

    @BindView(R.id.vp_pager) NoSrcollViewPager vpPager;
    @BindView(R.id.tl_title) TabLayout tlTitle;

    private ThemesVo themesVo;
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
        init();
    }

    private void init() {
        tabTitles = new ArrayList<>();
        fragments = new ArrayList<>();
        getThemes();
    }

    private void getThemes() {
        ApiLoader.newApi().getThemes().enqueue(new Callback<ThemesVo>() {
            @Override
            public void onResponse(Call<ThemesVo> call, Response<ThemesVo> response) {
                if (response.code() == Constants.SUCCESS) {
                    themesVo = response.body();
                    getTabTitles(themesVo);
                    if (tabTitles.size() > 0) {
                        initFragments(tabTitles.size());
                        pageAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabTitles, fragments);
                        vpPager.setAdapter(pageAdapter);
                        tlTitle.setupWithViewPager(vpPager);
                        tlTitle.setTabsFromPagerAdapter(pageAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ThemesVo> call, Throwable t) {}
        });
    }

    private void initFragments(int count) {
        for (int i=0; i<count; i++) {
            fragments.add(new MessageFragment());
        }
    }

    /**
     * get tab's tile
     * @param themesVo
     */
    private void getTabTitles(ThemesVo themesVo) {
        if (themesVo == null || themesVo.getOthers()==null || themesVo.getOthers().size() <= 0) {
            return;
        }
        for (ThemesVo.OthersBean bean : themesVo.getOthers()) {
            tabTitles.add(bean.getName());
        }
    }
}
