package com.test.cheng.practice.model.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;

/**
 * 首页顶部广告录播图holder
 * Created by kexiaoderenren on 2017/1/4.
 */
public class TopNewsBannerHolder implements Holder<LastestNews.TopStoriesEntity> {

    private ImageView imgTopNews;
    private TextView tvTopNews;

    @Override
    public View createView(Context context) {
        View bannerView = LayoutInflater.from(context).inflate(R.layout.activity_top_banner_news, null);
        imgTopNews = (ImageView) bannerView.findViewById(R.id.imgbtn_top);
        tvTopNews = (TextView) bannerView.findViewById(R.id.tv_top_title);
        return bannerView;
    }

    @Override
    public void UpdateUI(Context context, int position, LastestNews.TopStoriesEntity data) {
        tvTopNews.setText(data.getTitle());
        ImageLoaderUtils.loadImg(context, data.getImage(), imgTopNews);
    }
}
