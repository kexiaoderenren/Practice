package com.test.cheng.practice.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.test.cheng.practice.R;
import com.test.cheng.practice.adapter.MainPagerAdapter;
import com.test.cheng.practice.view.common.BaseActivity;
import com.test.cheng.practice.view.main.DiscoverFragment;
import com.test.cheng.practice.view.main.HomeFragment;
import com.test.cheng.practice.view.main.MessageFragment;
import com.test.cheng.practice.view.main.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String[] tabs = {"首页", "消息", "发现", "我得"};
    private List<Fragment> fragments;
    private MainPagerAdapter mainPagerAdapter;

    @BindView(R.id.vp_pager) ViewPager vpPager;
    @BindView(R.id.tl_title) TabLayout tlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        vpPager.setAdapter(mainPagerAdapter);
        tlTitle.setupWithViewPager(vpPager);
    }

    private void init() {
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MessageFragment messageFragment = new MessageFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(messageFragment);
        fragments.add(discoverFragment);
        fragments.add(mineFragment);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabs, fragments);
    }


}
