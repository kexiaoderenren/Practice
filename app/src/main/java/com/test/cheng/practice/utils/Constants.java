package com.test.cheng.practice.utils;

/**
 * 常量类
 * Created by kexiaoderenren on 2016/12/7.
 */
public class Constants {

    public static final String TEST_URL = "http://images.wine9.com/activity/goodspic/cp/10517/lrxm_06.jpg";
    public static final String TEST_URL2 = "http://image.dianshengsheng.com/goods/store_10/goods_145/201608291815458834.jpg";

    public static final String URL_PERSONAL_HOME = "https://github.com/kexiaoderenren";

    public static final int Constant_4000 = 3000;
    public static final int Constant_200 = 200;
    public static final int SUCCESS = Constant_200;


    /**
     * **********************************
     * 知乎日报接口URL 均来自https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
     *
     * -------start
     ************************************
     */

    /**
     * 最新消息 获取首页图片 http://news-at.zhihu.com/api/4/start-image/1080*1776
     */
    public static final String URL_GET_START_IMAGES = "/api/4/start-image/{widthAndHeight}";

    /**
     * http://news-at.zhihu.com/api/4/news/before/20131119
     */
    public static final String URL_GET_BEFORE_NEWS = "/api/4/news/before/{date}";

    /**
     * http://news-at.zhihu.com/api/4/news/latest
     */
    public static final String URL_GET_LATEST_NEWS = "/api/4/news/latest";

    /**
     * http://news-at.zhihu.com/api/4/news/3892357
     */
    public static final String URL_GET_NEWS_DETAIL = "/api/4/news/{id}";

    /**
     * 主题日报列表查看
     * http://news-at.zhihu.com/api/4/themes
     */
    public static final String URL_GET_THEMES = "/api/4/themes";

    /**
     * 主题日报内容查看
     * http://news-at.zhihu.com/api/4/theme/11
     */
    public static final String URL_GET_THEMES_DETAIL = "/api/4/theme/{id}";

    /**
     * **********************************
     * 知乎日报接口URL-------end
     ************************************
     */


    public static final String RESULT = "result";

    public static final String MINUS_SIGN = "-";  //减号
    public static final String PARAM_ID = "id";
    public static final String PARAM_URL = "url";
    public static final String PARAM_TITLE = "title";

}
