package com.ovdiienko.yaroslav.applicationb.utils;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.Nullable;

public class TimerCloseApp {
    private final Handler mPoster;
    private final Handler mTimer;
    private HandlerThread mThread;
    private String mText;
    private final int mSecondsDelay;
    private int mSecondsToEnd;

    private OnTickListener mListener;

    public TimerCloseApp(@Nullable String text, int seconds, OnTickListener listener) {
        mThread = new HandlerThread("HandlerThread-1-1.0", Process.THREAD_PRIORITY_DEFAULT);
        mThread.start();
        mTimer = new Handler(mThread.getLooper());
        mPoster = new Handler(Looper.getMainLooper());
        mText = text;
        mSecondsToEnd = seconds;
        mListener = listener;
        mSecondsDelay = 1000;
    }

    public void start() {
        String text = String.format(mText, mSecondsToEnd);
        loop(text);
    }

    private void loop(String text) {
        mTimer.postDelayed(() -> {
            mPoster.post(() -> mListener.onTick(text, mSecondsToEnd));
            mSecondsToEnd--;
            start();
        }, mSecondsDelay);
    }

    public interface OnTickListener {
        void onTick(@Nullable String text, int secondsLeft);
    }
}
