package com.test.cheng.practice.model.net;

import com.test.cheng.practice.App;
import com.test.cheng.practice.utils.LogUtils;
import com.test.cheng.practice.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by kexiaoderenren on 2017/2/6.
 */
public abstract class BaseCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        doResponse(response);
        if (response.isSuccessful() && response.body() != null) {
            success(response.body());
        } else {
            ToastUtils.show(App.getInstance(), response.message());
            failed();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        LogUtils.net(t.getMessage());
        ToastUtils.show(App.getInstance(), t.getMessage());
        failed();
    }

    /**
     * 与服务器连接失败时调用
     * <p/>
     * 是相对于 success方法未被调用时会走这个方法
     * 需要的时候复写
     */
    protected void failed() {}

    /**
     * 不管是故障还是所有网络响应必然会调用此方法
     * 需要的时候复写
     */
    protected void doResponse(Response<T> response) {}

    /**
     * 一切顺利,成功拿到需要的数据
     *
     * @param result   数据结果集
     */
    protected abstract void success(T result);
}
