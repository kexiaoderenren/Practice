package com.test.cheng.practice.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.test.cheng.practice.R;
import com.test.cheng.practice.model.TestModel;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.ToastUtils;
import com.test.cheng.practice.view.common.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2016/12/8.
 */
public class TestActivity extends BaseActivity {


    @BindView(R.id.btn) Button btn;
    @BindView(R.id.btn2) Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }




    private void testRxJavaHttpGet() {
        ApiLoader.newApi().testRxJavaHttpGet("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<TestModel>() {
                    @Override
                    public void call(TestModel testModel) {
                        Logger.d(testModel.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TestModel testModel) {

                    }
                });
    }

    private void testRetrofitGet() {
        ApiLoader.newApi().testHttpGet("1").enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                Logger.i(response.body().toString());
                TestModel.ResultEntity entity = response.body().getResult().get(0);
                Logger.i("entity---:" + entity.getSubmit());
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
            }
        });
    }

    private void testRetrofitPost() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("submit", "1");

        Call<TestModel> test = ApiLoader.newApi().testPost(map);
        test.enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                Log.i("test", "response:" + response.code() + response.body().toString());
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
                Logger.i("Throwable:" + t.getMessage());
            }
        });

    }


    /**
     *
     * @OnClick({R.id.tv_add,R.id.tv_delete})

    public void clickView(View view){

         switch (view.getId()){

                  case R.id.tv_add:

                       Toast.makeText(this,"tv_add", Toast.LENGTH_SHORT).show();

                       break;

                 case R.id.tv_delete:

                       Toast.makeText(this,"tv_delete", Toast.LENGTH_SHORT).show();

                       break;

             }

     */

}
