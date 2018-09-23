package com.ovdiienko.yaroslav.applicationb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ovdiienko.yaroslav.applicationb.utils.TimerCloseApp;


public class MainActivity extends AppCompatActivity implements TimerCloseApp.OnTickListener {
    private TextView mErrorMsg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mErrorMsg = findViewById(R.id.tv_err_msg);
        findViewById(R.id.iv_container).setVisibility(View.GONE);

        setupViews();
    }

    private void setupViews() {
        Intent intent = getIntent();
        if (intent.hasCategory("android.intent.category.LAUNCHER")) {
            new TimerCloseApp(getString(R.string.err_msg), 10, this).start();
        }
    }

    @Override
    public void onTick(@Nullable String text, int secondsLeft) {
        mErrorMsg.setText(text);
        if (secondsLeft == -1) {
            finish();
        }
    }
}
