package com.test.cheng.practice.model.net;

import com.test.cheng.practice.model.TestModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by gaokuncheng on 2016/12/5.
 */
public interface ApiManager {

    public final static String PATH = "/index.php?m=&c=Index&a=sunyang_test";

    @GET("/users/{user}")
    Call<TestModel> testHttpGet(@Path("user") String user);

    @FormUrlEncoded
    @POST("/user")
    Call<TestModel> testHttpPost(@FieldMap Map<String,Object> maps);

    @FormUrlEncoded
    @POST(PATH)
    Call<Object> testPost(@FieldMap Map<String,Object> maps);
}
