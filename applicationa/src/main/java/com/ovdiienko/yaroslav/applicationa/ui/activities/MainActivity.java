package com.ovdiienko.yaroslav.applicationa.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.ovdiienko.yaroslav.applicationa.ui.fragments.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected Fragment addFragmentToActivity() {
        return MainFragment.newInstance();
    }

    @Override
    protected void addToolbarFeatures(Toolbar toolbar) {
    }
}
