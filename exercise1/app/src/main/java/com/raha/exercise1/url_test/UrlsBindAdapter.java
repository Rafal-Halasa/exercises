package com.raha.exercise1.url_test;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raha.exercise1.R;

import java.util.List;

public class UrlsBindAdapter extends BindableAdapter<UrlsViewModel> {
    private List<UrlsViewModel> urlsViewModels;

    public UrlsBindAdapter(Context context, List<UrlsViewModel> urlsViewModels) {
        super(context);
        this.urlsViewModels = urlsViewModels;
    }


    @Override
    public int getCount() {
        return urlsViewModels.size();
    }

    @Override
    public UrlsViewModel getItem(int position) {
        return urlsViewModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.url_list_inflater,null);
    }

    @Override
    public void bindView(UrlsViewModel urlsViewModels, int position, View view) {
        TextView urlText = (TextView) view.findViewById(R.id.tv_url);
        urlText.setText(urlsViewModels.getUrl());
        RelativeLayout circle = (RelativeLayout) view.findViewById(R.id.rl_circle_container);
        circle.setBackgroundResource(urlsViewModels.getCircleResource());
    }
}
