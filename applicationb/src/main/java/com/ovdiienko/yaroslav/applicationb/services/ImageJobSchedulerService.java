package com.ovdiienko.yaroslav.applicationb.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import com.ovdiienko.yaroslav.applicationb.utils.Constants;


public class ImageJobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        String imageUrl = params.getExtras().getString(Constants.IMAGE_EXTRAS_NAME);

        Intent saveService = new Intent(this, ImageSaveService.class);
        saveService.putExtra(Constants.IMAGE_EXTRAS_NAME, imageUrl);
        startService(saveService);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
