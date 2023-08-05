package com.sp.hiltdemo.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ApplicationActivity extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
