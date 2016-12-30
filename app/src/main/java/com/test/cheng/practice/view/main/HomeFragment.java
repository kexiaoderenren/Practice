package com.test.cheng.practice.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.cheng.practice.R;
import com.test.cheng.practice.test.TestAdapter;
import com.test.cheng.practice.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kexiaoderenren on 2016/12/9.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;

    private List<String> lists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        lists = new ArrayList<>();
        String[] test = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21",
                "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21",
                "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
        lists.addAll(Arrays.asList(test));

        recyclerview.setLayoutManager(new LinearLayoutManager(getMyActivity()));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(new TestAdapter(lists));

    }
}
