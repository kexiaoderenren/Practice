package com.test.cheng.practice.model.net;

import com.test.cheng.practice.model.TestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gaokuncheng on 2016/12/5.
 */
public interface ApiManager {

    @GET("/users/{user}")
    Call<TestModel> repo(@Path("user") String user);
}
