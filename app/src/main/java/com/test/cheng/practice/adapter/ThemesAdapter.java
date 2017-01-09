package com.test.cheng.practice.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.bean.ThemesVo;

import java.util.List;

/**
 * Created by kexiaoderenren on 2017/1/9.
 */
public class ThemesAdapter extends BaseQuickAdapter<ThemesVo.OthersBean, BaseViewHolder> {


    public ThemesAdapter(int layoutResId, List<ThemesVo.OthersBean> data) {
        super(R.layout.nav_header_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ThemesVo.OthersBean othersBean) {
        baseViewHolder.setText(R.id.tv_txt, othersBean.getName());
    }
}
