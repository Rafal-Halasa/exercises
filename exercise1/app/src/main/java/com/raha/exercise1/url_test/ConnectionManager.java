package com.raha.exercise1.url_test;

import timber.log.Timber;

public class ConnectionManager implements ConnectionResponse{
    private boolean isRequestSend = false;
    private String sendLog="request send";
    private ConnectionResponse conResponse;

    public ConnectionManager(ConnectionResponse conResponse) {
        this.conResponse = conResponse;
    }
    public void sendRequest(String url) {
        isRequestSend=true;
        Timber.d(sendLog);
        PageConnect pageConnect = new PageConnect(this);
        pageConnect.execute(url);
    }


    public boolean isRequestSend() {
        return isRequestSend;
    }

    @Override
    public Integer getStatusAndUrl(Boolean status, String url) {
        conResponse.getStatusAndUrl(status,url);
        isRequestSend=false;
        return null;
    }
}
