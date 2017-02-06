package com.test.cheng.practice.model.net;

import com.test.cheng.practice.model.TestModel;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.bean.NewsVo;
import com.test.cheng.practice.model.bean.StartImgVo;
import com.test.cheng.practice.model.bean.ThemesCategoryVo;
import com.test.cheng.practice.model.bean.ThemesListVo;
import com.test.cheng.practice.utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kexiaoderenren on 2016/12/5.
 */
public interface ApiManager {

    public final static String PATH = "/index.php?m=&c=Index&a=sunyang_test";

    @GET(PATH)
    Call<TestModel> testHttpGet(@Query("submit") String submit);


    @FormUrlEncoded
    @POST(PATH)
    Call<TestModel> testPost(@FieldMap Map<String,Object> maps);

    @GET(PATH)
    Observable<TestModel> testRxJavaHttpGet(@Query("submit") String submit);


    @GET(Constants.URL_GET_START_IMAGES)
    Call<StartImgVo> getStartImg2(@Path("widthAndHeight") String widthAndHeight);

    @GET(Constants.URL_GET_START_IMAGES)
    Observable<StartImgVo> getStartImg(@Path("widthAndHeight") String widthAndHeight);

    @GET(Constants.URL_GET_LATEST_NEWS)
    Call<LastestNews> getLastedNews();



    /**
     * 过往消息
     * @param date 20131119格式日期
     * @return
     */
    @GET(Constants.URL_GET_BEFORE_NEWS)
    Call<LastestNews> getBeforeNews(@Path("date") String date);


    @GET(Constants.URL_GET_NEWS_DETAIL)
    Call<NewsVo> getNewsDetail(@Path(Constants.PARAM_ID) int id);

    @GET(Constants.URL_GET_NEWS_DETAIL)
    Observable<NewsVo> getNewsDetail2(@Path(Constants.PARAM_ID) int id);

    @GET(Constants.URL_GET_THEMES)
    Call<ThemesCategoryVo> getThemes();

    @GET(Constants.URL_GET_THEMES_DETAIL)
    Call<ThemesListVo> getThemesDetail(@Path(Constants.PARAM_ID) int id);

    @GET(Constants.URL_GET_THEMES_DETAIL)
    Observable<ThemesListVo> getThemesDetail2(@Path(Constants.PARAM_ID) int id);
}
