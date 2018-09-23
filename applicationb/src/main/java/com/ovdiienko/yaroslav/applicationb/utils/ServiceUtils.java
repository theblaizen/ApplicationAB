package com.ovdiienko.yaroslav.applicationb.utils;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;

import com.ovdiienko.yaroslav.applicationb.services.ImageJobSchedulerService;

public class ServiceUtils {
    public static void startImageSaveService(Context context, int jobId, PersistableBundle bundle) {
        ComponentName serviceName = new ComponentName(context, ImageJobSchedulerService.class);
        JobInfo jobInfo = new JobInfo.Builder(jobId, serviceName)
                .setMinimumLatency(15_000L)
                .setOverrideDeadline(20_000L)
                .setExtras(bundle)
                .build();

        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (scheduler != null) {
            scheduler.schedule(jobInfo);
        }

    }

    public static int generateId() {
        return 156 + (int) System.currentTimeMillis() / 1000;
    }
}
