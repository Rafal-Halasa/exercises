package com.raha.exercise1.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URL;

public class ConnectionUtils {
    public static boolean isInternetConnection(Context context) {
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
//    public static boolean isGoodUrl(String url){
//        new URL(urlEditText.getText().toString())
//    }
}
