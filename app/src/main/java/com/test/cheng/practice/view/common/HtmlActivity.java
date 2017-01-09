package com.test.cheng.practice.view.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;

import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseActivity;
import com.test.cheng.practice.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by kexiaoderenren on 2016/12/30.
 */
public class HtmlActivity extends BaseActivity {


    @BindView(R.id.toolbar) Toolbar toolbar;

    private String url;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, HtmlActivity.class);
        intent.putExtra(Constants.PARAM_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        ButterKnife.bind(this);
        init();
        initToobar();
    }

    private void init() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra(Constants.PARAM_URL);
            FragmentManager fm = getSupportFragmentManager();
            HtmlFragment fragment = HtmlFragment.newIntance(url);
            fragment.setHtmlInfoCallback(new HtmlFragment.HtmlInfoCallback() {
                @Override
                public void onReceivedTitle(String title) {
                    if (!TextUtils.isEmpty(title)) {
                        toolbar.setTitle(title);
                    }
                }
            });
            fm.beginTransaction().replace(R.id.frame_container, fragment, HtmlFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
    }

    private void initToobar() {
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
