package com.test.cheng.practice.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.utils.ImageLoaderUtils;
import com.test.cheng.practice.view.main.NewsDetailActivity;

import java.util.List;

/**
 * Created by gaokuncheng on 2017/5/8.
 */
public class ThemesListAdapter extends BaseQuickAdapter<ThemesListVo.StoriesBean, BaseViewHolder> {


    public ThemesListAdapter(List<ThemesListVo.StoriesBean> data) {
        super(R.layout.activity_list_item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final ThemesListVo.StoriesBean storiesBean) {
        ImageView imgNewsThumbnail = (ImageView) baseViewHolder.getView(R.id.img_news_thumbnail);
        if (storiesBean.getImages() != null && storiesBean.getImages().size() > 0) {
            ImageLoaderUtils.loadImg(mContext, storiesBean.getImages().get(0), imgNewsThumbnail);
        }
        baseViewHolder.setText(R.id.tv_news_title, storiesBean.getTitle());
        baseViewHolder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.start(mContext, storiesBean.getId(), storiesBean.getTitle());
            }
        });
    }
}
