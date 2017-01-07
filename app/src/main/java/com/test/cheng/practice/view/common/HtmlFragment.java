package com.test.cheng.practice.view.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2017/1/7.
 */
public class HtmlFragment extends BaseFragment {

    @BindView(R.id.webview) WebView webview;

    private String url;

    public static BaseFragment newIntance(String url){
        HtmlFragment fragment = new HtmlFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PARAM_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(Constants.PARAM_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_html, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebview();
    }

    private void initWebview() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setUseWideViewPort(true);  //---自适应屏幕（也许没什么卵用）---
        webSettings.setJavaScriptEnabled(true); //---允许js运行---
        webSettings.setSupportZoom(true);  //---允许缩放---
        webSettings.setBuiltInZoomControls(false); //---允许缩放--
        webSettings.setSaveFormData(false);  //---禁止缓存数据----

        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        //解决html太大超出屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);

        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //当h5代码加载完成后,通过webview调用 js
                view.loadUrl("javascript:loadShareInfo()");
                super.onPageFinished(view, url);
                hideHoldLoading();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showHoldLoading();
            }
        });
    }
}
