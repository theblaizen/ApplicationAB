package com.ovdiienko.yaroslav.applicationb.utils;


import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {

    public static void saveToFile(AppExecutors executors, String path, Bitmap bitmap, String imageUrl) {
        executors.diskIO().execute(() -> {
            try {
                FileUtils.createPathDirs(path);
                File file = new File(path, ImageUtils.getImageNameFromPath(imageUrl) + ".jpg");
                FileUtils.createFileIfNotExist(file);

                OutputStream stream = new FileOutputStream(file);
                ImageUtils.compressBitmap(bitmap, stream);

                stream.flush();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean createPathDirs(String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            return filePath.mkdirs();
        }

        return false;
    }

    public static boolean createFileIfNotExist(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }

        return file.createNewFile();
    }

    public static String getDefinedExternalPath() {
        return Environment.getExternalStorageDirectory().toString() + Constants.FOLDER;
    }
}
