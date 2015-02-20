package com.raha.exercise1.url_test;

import java.net.URL;

import timber.log.Timber;

public class ConnectionManager implements ConnectionResponse {
    private boolean isRequestSend = false;
    private String sendLog = "request send";
    private ConnectionResponse conResponse;
    private static ConnectionManager connectionManager;

    private ConnectionManager(ConnectionResponse conResponse) {
        this.conResponse = conResponse;
    }

    public static ConnectionManager getInstance(ConnectionResponse conResponse) {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager(conResponse);
        }
        return connectionManager;
    }

    public void setConResponse(ConnectionResponse conResponse) {
        this.conResponse = conResponse;
    }

    public void sendRequest(URL url) {
        isRequestSend = true;
        Timber.d(sendLog);
        PageConnect pageConnect = new PageConnect(this);
        pageConnect.execute(url);
    }


    public boolean isRequestSend() {
        return isRequestSend;
    }

    @Override
    public void getStatusAndUrl(Boolean status, String url) {
        conResponse.getStatusAndUrl(status, url);
        isRequestSend = false;
    }
}
