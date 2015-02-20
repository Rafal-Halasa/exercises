package com.raha.exercise1.utils;

import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;

public class ParseUtils {
    public static boolean checkURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
