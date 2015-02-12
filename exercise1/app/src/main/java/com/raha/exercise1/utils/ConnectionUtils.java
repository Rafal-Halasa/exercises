package com.raha.exercise1.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtils {
    public static boolean isInternetConnection(Activity activity) {
        ConnectivityManager systemService = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
