package com.ovdiienko.yaroslav.applicationa.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ovdiienko.yaroslav.applicationa.R;
import com.ovdiienko.yaroslav.applicationa.utils.ActivityUtils;
import com.ovdiienko.yaroslav.applicationa.utils.AppException;
import com.ovdiienko.yaroslav.applicationa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends BaseFragment {
    private Button mLaunchBtn;
    private EditText mImageAddressHolder;

    public static TestFragment newInstance() {
        int container = R.layout.fragment_test;

        Bundle bundle = new Bundle();
        bundle.putInt(CONTAINER_LAYOUT, container);

        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    protected View initItems(View view) {
        mLaunchBtn = view.findViewById(R.id.btn_process_image);
        mImageAddressHolder = view.findViewById(R.id.et_image_link);
        return view;
    }

    @Override
    protected void setupItems() {
        mLaunchBtn.setOnClickListener(this::onClick);
    }

    private void openAppB() {
        try {
            ActivityUtils.openOtherApp(getActivity(), Constants.PACKAGE_PATH, Constants.ACTIVITY_PATH, new ActivityUtils.ExtrasSetter() {
                @Override
                public List<String> putExtrasString() {
                    List<String> stringExtras = new ArrayList<>();
                    stringExtras.add(mImageAddressHolder.getText().toString());
                    return stringExtras;
                }

                @Override
                public List<String> putExtrasName() {
                    List<String> nameExtras = new ArrayList<>();
                    nameExtras.add(Constants.IMAGE_EXTRAS_NAME);
                    return nameExtras;
                }
            });
        } catch (AppException extrasException) {
            extrasException.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (R.id.btn_process_image == view.getId()) {
            String url = mImageAddressHolder.getText().toString();
            if (!TextUtils.isEmpty(url)) {
                openAppB();
            }
        }
    }
}
