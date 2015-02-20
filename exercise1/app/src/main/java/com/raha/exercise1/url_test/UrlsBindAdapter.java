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

import butterknife.ButterKnife;
import butterknife.InjectView;

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
        View view = inflater.inflate(R.layout.url_list_inflater, container, false);
        UrlViewHolder viewHolder = new UrlViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(UrlsViewModel urlsViewModels, int position, View view) {
        TextView urlText = (TextView) view.findViewById(R.id.tv_url);
        urlText.setText(urlsViewModels.getUrl());
        RelativeLayout circle = (RelativeLayout) view.findViewById(R.id.rl_circle_container);
        circle.setBackgroundResource(urlsViewModels.getCircleResource());

    }

    static class UrlViewHolder {
        @InjectView(R.id.tv_url)
        TextView urlText;
        @InjectView(R.id.rl_circle_container)
        RelativeLayout circle;
        public UrlViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
