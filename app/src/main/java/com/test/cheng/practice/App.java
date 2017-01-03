package com.test.cheng.practice;

import android.app.Application;

/**
 * Created by kexiaoderenren on 2016/12/9.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return  instance;
    }
}
