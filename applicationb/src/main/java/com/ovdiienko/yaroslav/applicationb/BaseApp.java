package com.ovdiienko.yaroslav.applicationb;

import android.app.Application;

import com.ovdiienko.yaroslav.applicationb.utils.AppExecutors;

public class BaseApp extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppExecutors getExecutors() {
        return mAppExecutors;
    }
}