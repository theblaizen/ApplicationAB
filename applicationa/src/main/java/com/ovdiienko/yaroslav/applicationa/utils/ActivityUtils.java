package com.ovdiienko.yaroslav.applicationa.utils;


import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.List;

public class ActivityUtils {

    /**
     * Serves to open other apps by package and classname. Extras are optional. If no need to use it, just pass null.
     *
     * @param extrasSetter provide interface to put extras into intent.
     */
    public static void openOtherApp(FragmentActivity activity, String packagePath, String classPath, @Nullable ExtrasSetter extrasSetter) throws AppException {
        Intent launchIntent = new Intent();
        launchIntent.setComponent(new ComponentName(packagePath, classPath));

        if (extrasSetter != null) {
            List<String> stringExtras = extrasSetter.putExtrasString();
            List<String> nameExtras = extrasSetter.putExtrasName();

            if (stringExtras == null || nameExtras == null)
                throw new AppException("Extras can not be null!");
            if (stringExtras.size() != nameExtras.size())
                throw new AppException("Extras containers must contain the same number of elements!");

            int size = nameExtras.size();
            for (int id = 0; id < size; id++) {
                launchIntent.putExtra(nameExtras.get(id), stringExtras.get(id));
            }
        }

        if (activity.getPackageManager().resolveActivity(launchIntent, 0) != null) {
            activity.startActivity(launchIntent);
        }
    }

    public interface ExtrasSetter {
        List<String> putExtrasString();

        List<String> putExtrasName();
    }
}
