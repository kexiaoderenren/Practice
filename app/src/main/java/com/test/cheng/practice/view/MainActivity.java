package com.test.cheng.practice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.orhanobut.logger.Logger;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.test.cheng.practice.R;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.main.DiscoverFragment;
import com.test.cheng.practice.view.main.HomeFragment;
import com.test.cheng.practice.view.main.MessageFragment;
import com.test.cheng.practice.view.main.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 底部Tab效果切换Activity
 */
public class MainActivity extends BaseActivity implements OnTabSelectListener {


    @BindView(R.id.bottomBar) BottomBar bottomBar;

    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;

    public static void start(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        bottomBar.setOnTabSelectListener(this);
    }

    /**
     * 切换tab页fragment
     * @param fragmentName
     */
    private void switchMenu(String fragmentName) {

        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);

        if (fragment != null) {
            if (fragment == mCurrentFragment) return;
            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            if (fragmentName.equals(HomeFragment.class.getSimpleName())) {
                fragment = new HomeFragment();
            } else if (fragmentName.equals(MessageFragment.class.getSimpleName())) {
                fragment = new MessageFragment();
            } else if (fragmentName.equals(DiscoverFragment.class.getSimpleName())) {
                fragment = new DiscoverFragment();
            } else if (fragmentName.equals(MineFragment.class.getSimpleName())) {
                fragment = new MineFragment();
            }
            mFragmentManager.beginTransaction().add(R.id.contentContainer, fragment, fragmentName).commit();
        }
        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }
        mCurrentFragment = fragment;
    }


    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_one:
                switchMenu(HomeFragment.class.getSimpleName());
                break;
            case R.id.tab_two:
                switchMenu(MessageFragment.class.getSimpleName());
                break;
            case R.id.tab_three:
                switchMenu(DiscoverFragment.class.getSimpleName());
                break;
            case R.id.tab_four:
                switchMenu(MineFragment.class.getSimpleName());
                break;
        }
    }
}
