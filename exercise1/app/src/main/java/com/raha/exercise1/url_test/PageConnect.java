package com.raha.exercise1.url_test;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;


/**
 * Created by raha on 2015-01-23.
 */
public class PageConnect extends AsyncTask<URL, Integer, Boolean> {


    private ConnectionResponse conResponse;
    private URL url;
    private String urlStr;
    private String httpProt = "http://";

    public PageConnect(ConnectionResponse conResponse) {

        this.conResponse = conResponse;
    }

    @Override
    protected Boolean doInBackground(URL... urls) {
        HttpURLConnection urlConnection = null;
        Boolean responseStatus = false;
        url = urls[0];
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            responseStatus = checkResponseCode(urlConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return responseStatus;
    }


    @Override
    protected void onPostExecute(Boolean responseStatus) {
        conResponse.getStatusAndUrl(responseStatus, url.toString());
        Timber.e("dasd", new Object());

    }

    private Boolean checkResponseCode(int responseCode) {
        return responseCode == 200;
    }


}
