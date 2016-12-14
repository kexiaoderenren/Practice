package com.test.cheng.practice.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.test.cheng.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaokuncheng on 2016/12/14.
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    /**页面布局文件**/
    protected int layoutResId;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutResId();
        setContentView(layoutResId);
        initViews();
        setSupportActionBar(toolbar);
        init();
    }

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 设置布局文件
     */
    protected abstract void setLayoutResId();

    /**
     * 初始化布局控件
     */
    protected abstract void initViews();
}
