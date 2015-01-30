package com.raha.exercise1.url;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raha.exercise1.R;
import com.raha.exercise1.interfaces.ConnectionResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by raha on 2015-01-23.
 */
public class PageConnect extends AsyncTask<String, Integer, Boolean> {


    private ConnectionResponse conResponse;
    private URL url;
    private String urlStr;
    private String httpProt="http://";
    public PageConnect(ConnectionResponse conResponse) {

        this.conResponse = conResponse;
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        HttpURLConnection urlConnection = null;
        Boolean responseStatus = false;
        urlStr=urls[0];
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            try {
                url = new URL(httpProt+urlStr);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            urlConnection = getResponse(url);
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
        conResponse.getStatusAndUrl(responseStatus, urlStr);

    }

    private Boolean checkResponseCode(int responseCode) {
        if (responseCode == 200) {
            return true;
        } else {
            return false;
        }
    }

    private HttpURLConnection getResponse(URL url) throws IOException {
        HttpURLConnection urlConnected = null;
        urlConnected = (HttpURLConnection) url.openConnection();
        return urlConnected;
    }

}
