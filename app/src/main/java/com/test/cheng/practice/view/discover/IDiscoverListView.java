package com.test.cheng.practice.view.discover;

import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.view.IMvpView;

import java.util.List;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public interface IDiscoverListView extends IMvpView {

    void refresh(List<ThemesListVo.StoriesBean> data, String description, String background);

    void loadMore(List<ThemesListVo.StoriesBean> data);

}
