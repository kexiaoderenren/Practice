package com.test.cheng.practice.test;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.utils.Constants;
import com.test.cheng.practice.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by kexiaoderenren on 2016/12/17.
 */
public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    public TestAdapter(List<String> data) {
        super(R.layout.activity_list_item_test, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        if (baseViewHolder.getAdapterPosition() % 2 == 0) {
            ImageLoaderUtils.loadImg(mContext, Constants.TEST_URL, (ImageView) baseViewHolder.getView(R.id.img_test));
        } else {
            ImageLoaderUtils.loadImg(mContext, Constants.TEST_URL2, (ImageView) baseViewHolder.getView(R.id.img_test));
        }
    }
}
