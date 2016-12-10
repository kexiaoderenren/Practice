package com.test.cheng.practice.model.net;

import com.test.cheng.practice.model.TestModel;

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
}
