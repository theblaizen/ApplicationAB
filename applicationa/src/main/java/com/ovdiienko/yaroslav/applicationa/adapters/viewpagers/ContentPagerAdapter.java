package com.ovdiienko.yaroslav.applicationa.adapters.viewpagers;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ovdiienko.yaroslav.applicationa.R;
import com.ovdiienko.yaroslav.applicationa.ui.fragments.HistoryFragment;
import com.ovdiienko.yaroslav.applicationa.ui.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class ContentPagerAdapter extends FragmentStatePagerAdapter {
    private static final int FRAGMENT_TEST = 0;
    private static final int FRAGMENT_HISTORY = 1;
    private final Context mContext;
    private List<Fragment> mFragments;

    public ContentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
        mFragments = new ArrayList<>();

        addFragmentsToList();
    }

    private void addFragmentsToList() {
        mFragments.add(TestFragment.newInstance());
        mFragments.add(HistoryFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case FRAGMENT_TEST:
                return mFragments.get(FRAGMENT_TEST);
            case FRAGMENT_HISTORY:
                return mFragments.get(FRAGMENT_HISTORY);
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case FRAGMENT_TEST:
                return mContext.getString(R.string.tab_test);
            case FRAGMENT_HISTORY:
                return mContext.getString(R.string.tab_history);

        }
    }
}
