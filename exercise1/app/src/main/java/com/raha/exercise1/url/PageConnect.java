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
import java.net.URL;


/**
 * Created by raha on 2015-01-23.
 */
public class PageConnect extends AsyncTask<URL, Integer, Boolean> {


    private ConnectionResponse conResponse;

    public PageConnect(ConnectionResponse conResponse) {

        this.conResponse = conResponse;
    }

    @Override
    protected Boolean doInBackground(URL... urls) {
        HttpURLConnection urlConnection = null;
        Boolean responseStatus = false;
        try {
            urlConnection = getResponse(urls[0]);
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
        conResponse.getStatus(responseStatus);

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
