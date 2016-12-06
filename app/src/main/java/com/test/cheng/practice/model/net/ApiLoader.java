package com.test.cheng.practice.model.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * from: http://www.jianshu.com/p/0f97f94b171f
 * Created by gaokuncheng on 2016/12/5.  http://www.dianshengsheng.com
 */
public class ApiLoader {

    private static Retrofit retrofit;
    //public static final String BASE_URL = "https://api.github.com";
    public static final String BASE_URL = "http://114.215.169.50";

    private static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
                )
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
