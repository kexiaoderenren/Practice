package com.test.cheng.practice.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.test.cheng.practice.R;
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
    private ContentPagerAdapter contentAdapter;

    @BindView(R.id.vp_pager) ViewPager vpPager;
    @BindView(R.id.tl_title) TabLayout tlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MessageFragment messageFragment = new MessageFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(messageFragment);
        fragments.add(discoverFragment);
        fragments.add(mineFragment);
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(contentAdapter);
        tlTitle.setupWithViewPager(vpPager);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }


}
