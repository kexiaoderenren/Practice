package com.test.cheng.practice.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.test.cheng.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2016/12/14.
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    /** 是否显示toolbar **/
    protected boolean hasShowToolbar = true;

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());
        if (hasShowToolbar) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            settingToolbar();
            setSupportActionBar(toolbar);
        }
        initViews();
        init();
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
