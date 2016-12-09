package com.test.cheng.practice.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.cheng.practice.R;
import com.test.cheng.practice.view.common.BaseFragment;

/**
 * Created by gaokuncheng on 2016/12/9.
 */
public class DiscoverFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
}
