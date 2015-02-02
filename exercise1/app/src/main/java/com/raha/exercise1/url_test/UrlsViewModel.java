package com.raha.exercise1.url_test;

/**
 * Created by raha on 2015-01-30.
 */
public class UrlsViewModel {
    private int circleResource;
    private String url;

    public UrlsViewModel(int circleResource, String url) {
        this.circleResource = circleResource;
        this.url = url;
    }

    public int getCircleResource() {
        return circleResource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCircleResource(int circleResource) {
        this.circleResource = circleResource;
    }
}
