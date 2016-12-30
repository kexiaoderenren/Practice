package com.test.cheng.practice.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.test.cheng.practice.R;
import com.test.cheng.practice.view.base.BaseActivity;

/**
 * Created by kexiaoderenren on 2016/12/14.
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
        init();
        settingToolbar();
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 设置布局文件
     */
    protected abstract int setLayoutResId();

    /**
     * 初始化布局控件
     */
    protected abstract void initViews();

    /**
     * 设置toolbar属性
     */
    protected void settingToolbar() {

    }
}
