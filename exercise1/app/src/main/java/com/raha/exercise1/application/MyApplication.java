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
        } else {
            Timber.plant(new CrashReportingTree());
        }
        super.onCreate();
    }

    private class CrashReportingTree implements Timber.Tree {
        @Override
        public void v(String message, Object... args) {
            v(message, args);
        }

        @Override
        public void v(Throwable t, String message, Object... args) {

        }

        @Override
        public void d(String message, Object... args) {
            d(message, args);
        }

        @Override
        public void d(Throwable t, String message, Object... args) {

        }

        @Override
        public void i(String message, Object... args) {

        }

        @Override
        public void i(Throwable t, String message, Object... args) {

        }

        @Override
        public void w(String message, Object... args) {

        }

        @Override
        public void w(Throwable t, String message, Object... args) {

        }

        @Override
        public void e(String message, Object... args) {

        }

        @Override
        public void e(Throwable t, String message, Object... args) {

        }
    }
}
