package com.test.cheng.practice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by kexiaoderenren on 2016/12/10.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<String> tabs;
    private List<Fragment> fragments;

    public MainPagerAdapter(FragmentManager fm, List<String> tabs, List<Fragment> fragments) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
    }

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return (tabs == null) ? 0 :tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
