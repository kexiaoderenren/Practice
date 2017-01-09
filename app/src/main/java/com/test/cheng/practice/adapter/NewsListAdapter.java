package com.test.cheng.practice.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.view.main.NewsDetailActivity;

import java.util.List;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class NewsListAdapter extends BaseQuickAdapter<LastestNews.StoriesEntity, BaseViewHolder> {

    public NewsListAdapter(List<LastestNews.StoriesEntity> data) {
        super(R.layout.activity_list_item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final LastestNews.StoriesEntity s) {
        ImageView imgNewsThumbnail = (ImageView) baseViewHolder.getView(R.id.img_news_thumbnail);
        ImageLoaderUtils.loadImg(mContext, s.getImages().get(0), imgNewsThumbnail);
        baseViewHolder.setText(R.id.tv_news_title, s.getTitle());
        baseViewHolder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.start(mContext, s.getId(), s.getTitle());
            }
        });
    }
}
