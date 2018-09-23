package com.ovdiienko.yaroslav.applicationa.dto.db.reporsitory;


import com.ovdiienko.yaroslav.applicationa.dto.db.AppDatabase;
import com.ovdiienko.yaroslav.applicationa.utils.AppExecutors;

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private AppExecutors mExecutors;
    private ImageRepository mImageRepository;

    private DataRepository(final AppDatabase database, AppExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
        mImageRepository = new ImageRepository(database, executors);
    }

    public static DataRepository getInstance(final AppDatabase database, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database, executors);
                }
            }
        }
        return sInstance;
    }

    public ImageRepository provideImageRepository() {
        return mImageRepository;
    }


    public void clearAllTables() {
        mExecutors.diskIO().execute(mDatabase::clearAllTables);
    }

}
