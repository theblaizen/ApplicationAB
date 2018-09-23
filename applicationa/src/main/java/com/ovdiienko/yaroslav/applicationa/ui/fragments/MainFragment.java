package com.ovdiienko.yaroslav.applicationa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ovdiienko.yaroslav.applicationa.R;
import com.ovdiienko.yaroslav.applicationa.adapters.viewpagers.ContentPagerAdapter;
import com.ovdiienko.yaroslav.applicationa.ui.activities.BaseActivity;

public class MainFragment extends BaseFragment {

    private ViewPager mPages;
    private TabLayout mTabs;
    private ContentPagerAdapter mPagesAdapter;

    public static MainFragment newInstance() {
        int container = R.layout.fragment_main;

        Bundle bundle = new Bundle();
        bundle.putInt(CONTAINER_LAYOUT, container);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected View initItems(View view) {
        mPages = view.findViewById(R.id.vp_main);
        mTabs = ((BaseActivity) getActivity()).getTabs();

        return view;
    }

    @Override
    protected void setupItems() {
        mPagesAdapter = new ContentPagerAdapter(getFragmentManager(), getActivity());
        mPages.setAdapter(mPagesAdapter);

        mTabs.setupWithViewPager(mPages);
    }
}
