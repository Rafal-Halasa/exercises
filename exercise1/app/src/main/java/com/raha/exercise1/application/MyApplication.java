package com.raha.exercise1.application;

import android.app.Application;

import com.raha.exercise1.BuildConfig;

import timber.log.Timber;

import static timber.log.Timber.DebugTree;

/**
 * Created by raha on 2015-01-29.
 */
public class MyApplication extends Application {

    private static MyApplication singleton;


    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        singleton = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
        super.onCreate();
    }


}
