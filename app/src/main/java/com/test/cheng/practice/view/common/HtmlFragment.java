package com.test.cheng.practice.view.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.test.cheng.practice.App;
import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2017/1/7.
 */
public class HtmlFragment extends BaseFragment {

    public static final String HTML_LOAD_TYPE = "htmlLoadType";
    public static final int URL_TYPE = 0;   //加载网页URL
    public static final int DATA_TYPE = 1;  //加载HTML代码片段

    @BindView(R.id.frame_container) FrameLayout frameContainer;

    private String url;
    private int htmlLoadType;   //Html加载类型
    private WebView webview;
    private HtmlInfoCallback htmlInfoCallback;

    /**
     * @param htmlLoadType Html加载类型
     * @param source htmlLoadType = 0, 加载网页URL
     *               htmlLoadType = 1, 加载HTML代码片段
     * @return
     */
    public static HtmlFragment newIntance(int htmlLoadType, String source){
        HtmlFragment fragment = new HtmlFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PARAM_URL, source);
        args.putInt(HTML_LOAD_TYPE, htmlLoadType);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param url 加载网页URL
     * @return
     */
    public static HtmlFragment newIntance(String url){
        return newIntance(URL_TYPE, url);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(Constants.PARAM_URL);
            htmlLoadType = getArguments().getInt(HTML_LOAD_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_framelayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview = new WebView(App.getInstance());
        frameContainer.addView(webview, 0);
        initWebview();
    }

    private void initWebview() {
        WebSettings webSettings = webview.getSettings();
        //webSettings.setUseWideViewPort(true);  //---自适应屏幕（也许没什么卵用）---
        webSettings.setJavaScriptEnabled(true); //---允许js运行---
        webSettings.setSupportZoom(true);  //---允许缩放---
        webSettings.setBuiltInZoomControls(false); //---允许缩放--
        webSettings.setSaveFormData(false);  //---禁止缓存数据----
        webSettings.setDomStorageEnabled(true); // 开启DOM storage API 功能
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        //解决html太大超出屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);

        if (htmlLoadType == URL_TYPE) {
            webview.loadUrl(url);
        } else {
            webview.loadDataWithBaseURL("x-data://base", url, "text/html", "UTF-8", null);
        }
        webview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view,String url){
                HtmlActivity.start(getMyActivity(), url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (htmlInfoCallback != null) { htmlInfoCallback.onReceivedTitle(title); }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webview.setVisibility(View.GONE);
        webview.removeAllViews();
        webview.destroy();
    }

    public void setHtmlInfoCallback(HtmlInfoCallback htmlInfoCallback){
        this.htmlInfoCallback = htmlInfoCallback;
    }

    public interface HtmlInfoCallback {
        public void onReceivedTitle(String title);
    }
}
