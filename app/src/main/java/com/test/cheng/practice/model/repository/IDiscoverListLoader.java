package com.test.cheng.practice.model.repository;

import com.test.cheng.practice.model.bean.ThemesListVo;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public interface IDiscoverListLoader<T>{

    void getThemesDetail(int themesId, OnLoadListener<T> onLoadListener);
}
