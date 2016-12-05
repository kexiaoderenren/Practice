package com.test.cheng.practice.model.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaokuncheng on 2016/12/5.
 */
public class ApiLoader {

    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.github.com";

    private static Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static ApiManager newApi() {
        if (retrofit == null) {
            retrofit = getRetrofit();
        }
        return retrofit.create(ApiManager.class);
    }
}
