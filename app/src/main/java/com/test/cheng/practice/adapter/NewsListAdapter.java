package com.test.cheng.practice.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class NewsListAdapter extends BaseQuickAdapter<LastestNews.StoriesEntity, BaseViewHolder> {

    public NewsListAdapter(List<LastestNews.StoriesEntity> data) {
        super(R.layout.activity_list_item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LastestNews.StoriesEntity s) {
        ImageView imgNewsThumbnail = (ImageView) baseViewHolder.getView(R.id.img_news_thumbnail);
        ImageLoaderUtils.loadImg(mContext, s.getImages().get(0), imgNewsThumbnail);
        baseViewHolder.setText(R.id.tv_news_title, s.getTitle());
    }
}
