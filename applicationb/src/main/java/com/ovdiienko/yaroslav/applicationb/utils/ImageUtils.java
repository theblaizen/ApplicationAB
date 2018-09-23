package com.ovdiienko.yaroslav.applicationb.utils;


import android.graphics.Bitmap;

import java.io.OutputStream;

public class ImageUtils {
    public static String getImageNameFromPath(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static void compressBitmap(Bitmap bitmap, OutputStream stream) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);
    }
}
