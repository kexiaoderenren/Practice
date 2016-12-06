package com.test.cheng.practice;

import android.content.res.ObbInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.test.cheng.practice.model.TestModel;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvHelloworld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHelloworld = (TextView) findViewById(R.id.tv_helloworld);
        tvHelloworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("submit","1");

                Call<Object> test = ApiLoader.newApi().testPost(map);
                test.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.i("test", "response:" + response.code() + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.i("test", "Throwable:" + t.getMessage());
                    }
                });
            }
        });
    }
}
