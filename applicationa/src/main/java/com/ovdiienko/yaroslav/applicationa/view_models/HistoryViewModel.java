package com.ovdiienko.yaroslav.applicationa.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.ovdiienko.yaroslav.applicationa.BaseApp;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.ImageEntity;
import com.ovdiienko.yaroslav.applicationa.dto.db.reporsitory.DataRepository;

import java.util.List;


public class HistoryViewModel extends AndroidViewModel {
    private MediatorLiveData<List<ImageEntity>> mResults;
    private DataRepository mRepository;

    public HistoryViewModel(@NonNull Application application) {
        super(application);

        mResults = new MediatorLiveData<>();
        mRepository = ((BaseApp) application).getDataRepository();
    }

    public void getImages() {
        mResults.addSource(mRepository.provideImageRepository().loadImages(), value -> mResults.setValue(value));
    }

    public void getImagesSortedByDate() {
        mResults.addSource(mRepository.provideImageRepository().loadImagesByDate(), value -> mResults.setValue(value));
    }

    public void getImagesSortedByStatus() {
        mResults.addSource(mRepository.provideImageRepository().loadImagesByStatus(), value -> mResults.setValue(value));
    }

    public LiveData<List<ImageEntity>> getImagesLiveData() {
        return mResults;
    }
}
