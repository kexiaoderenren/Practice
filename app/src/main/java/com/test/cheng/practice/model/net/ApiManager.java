package com.test.cheng.practice.model.net;

import com.test.cheng.practice.model.TestModel;
import com.test.cheng.practice.model.bean.LastestNews;
import com.test.cheng.practice.model.bean.StartImgVo;
import com.test.cheng.practice.utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
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
    Observable<StartImgVo> getStartImg();

    @GET(Constants.URL_GET_START_IMAGES)
    Call<StartImgVo> getStartImg2(@Path("widthAndHeight") String widthAndHeight);

    @GET(Constants.URL_GET_LATEST_NEWS)
    Call<LastestNews> getLastedNews();
}
