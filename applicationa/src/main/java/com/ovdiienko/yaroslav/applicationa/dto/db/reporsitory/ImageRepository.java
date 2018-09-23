package com.ovdiienko.yaroslav.applicationa.dto.db.reporsitory;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.ovdiienko.yaroslav.applicationa.dto.db.AppDatabase;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.ImageEntity;
import com.ovdiienko.yaroslav.applicationa.utils.AppExecutors;

import java.util.List;

public class ImageRepository {
    private final AppDatabase mDatabase;
    private AppExecutors mExecutors;

    private MediatorLiveData<List<ImageEntity>> mObservableImages;

    ImageRepository(AppDatabase appDatabase, AppExecutors executors) {
        mDatabase = appDatabase;
        mExecutors = executors;

        mObservableImages = new MediatorLiveData<>();
    }

    public LiveData<List<ImageEntity>> loadImages() {
        mObservableImages.addSource(mDatabase.imageDao().selectAllLikeObservable(),
                entities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableImages.postValue(entities);
                    }
                });

        return mObservableImages;
    }

    public LiveData<List<ImageEntity>> loadImagesByDate() {
        mObservableImages.addSource(mDatabase.imageDao().selectAllByDate(),
                entities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableImages.postValue(entities);
                    }
                });

        return mObservableImages;
    }

    public LiveData<List<ImageEntity>> loadImagesByStatus() {
        mObservableImages.addSource(mDatabase.imageDao().selectAllByStatus(),
                entities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableImages.postValue(entities);
                    }
                });

        return mObservableImages;
    }

}
