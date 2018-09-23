package com.ovdiienko.yaroslav.applicationb.services;


import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ovdiienko.yaroslav.applicationb.BaseApp;
import com.ovdiienko.yaroslav.applicationb.utils.AppExecutors;
import com.ovdiienko.yaroslav.applicationb.utils.Constants;
import com.ovdiienko.yaroslav.applicationb.utils.FileUtils;

import java.io.InputStream;
import java.net.URL;


public class ImageSaveService extends IntentService {

    public ImageSaveService() {
        super("ImageSaveServiceThread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String imageUrl = intent.getStringExtra(Constants.IMAGE_EXTRAS_NAME);

        try (InputStream inputStream = (InputStream) new URL(Uri.parse(imageUrl).toString()).getContent()) {
            saveImage(BitmapFactory.decodeStream(inputStream), imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImage(Bitmap bitmap, String imageUrl) {
//        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
        String path = FileUtils.getDefinedExternalPath();
        saveToFile(path, bitmap, imageUrl);
    }

    private void saveToFile(String path, Bitmap bitmap, String imageUrl) {
        AppExecutors executors = ((BaseApp) getApplication()).getExecutors();
        FileUtils.saveToFile(executors, path, bitmap, imageUrl);
    }
}
