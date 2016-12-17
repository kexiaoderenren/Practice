package com.test.cheng.practice.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.cheng.practice.R;

import java.util.List;

/**
 * Created by gaokuncheng on 2016/12/17.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

    private Context context;
    private List<String> lists;

    public TestAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public TestAdapter.TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(TestAdapter.TestHolder holder, int position) {
        //ImageLoaderUtils.loadImg(context, "http://image13.wine9.com/activity/2016/12/app/lc/6_02.jpg", holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    class TestHolder extends RecyclerView.ViewHolder {


        public TestHolder(View itemView) {
            super(itemView);
        }
    }
}
