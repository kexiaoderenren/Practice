package com.test.cheng.practice.model.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * from: http://www.jianshu.com/p/0f97f94b171f
 * Created by kexiaoderenren on 2016/12/5.  http://www.dianshengsheng.com
 */
public class ApiLoader {

    //public static final String BASE_URL = "https://api.github.com";
    public static final String BASE_URL = "http://news-at.zhihu.com";

    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor().setLevel(LoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
