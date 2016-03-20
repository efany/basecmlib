package com.efan.basecmlib.application;

import android.app.Application;

/**
 * Created by efan on 2016/2/25.
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;

    public BaseApplication() {
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
