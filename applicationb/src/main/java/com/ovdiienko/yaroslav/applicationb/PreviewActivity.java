package com.ovdiienko.yaroslav.applicationb;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ovdiienko.yaroslav.applicationb.utils.Constants;
import com.ovdiienko.yaroslav.applicationb.utils.ServiceUtils;

public class PreviewActivity extends AppCompatActivity {

    private ImageView mImageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mImageContainer = findViewById(R.id.iv_container);
        findViewById(R.id.tv_err_msg).setVisibility(View.GONE);

        setupViews();
    }

    private void setupViews() {
        Intent intent = getIntent();
        if (intent != null) {
            String imageUrl = intent.getStringExtra(Constants.IMAGE_EXTRAS_NAME);
            Glide.with(this).load(imageUrl).into(mImageContainer);

            if (intent.hasExtra(Constants.OPENED_FROM_HISTORY_EXTRAS_NAME)) {
                PersistableBundle bundle = new PersistableBundle();
                bundle.putString(Constants.IMAGE_EXTRAS_NAME, imageUrl);
                int id = ServiceUtils.generateId();

                ServiceUtils.startImageSaveService(this, id, bundle);
            }
        }
    }

}
