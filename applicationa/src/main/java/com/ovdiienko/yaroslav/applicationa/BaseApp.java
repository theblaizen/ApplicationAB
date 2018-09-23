package com.ovdiienko.yaroslav.applicationa;

import android.app.Application;

import com.ovdiienko.yaroslav.applicationa.dto.db.AppDatabase;
import com.ovdiienko.yaroslav.applicationa.dto.db.reporsitory.DataRepository;
import com.ovdiienko.yaroslav.applicationa.utils.AppExecutors;

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

    private AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getDataRepository() {
        return DataRepository.getInstance(getDatabase(), mAppExecutors);
    }
}