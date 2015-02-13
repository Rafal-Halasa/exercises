package com.raha.exercise1.url_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raha.exercise1.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by raha on 2015-01-29.
 */
public class UrlsListAdapter extends BaseAdapter {
    private final Context applicationContext;
    private final List<UrlsViewModel> urlsViewModels;
    private final LayoutInflater infalter;


    public UrlsListAdapter(Context applicationContext, List<UrlsViewModel> urlsViewModels) {

        this.applicationContext = applicationContext;
        this.urlsViewModels = urlsViewModels;
        infalter = LayoutInflater.from(this.applicationContext);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        UrlViewHolder urlHolder;
        if (convertView == null) {
            convertView = infalter.inflate(R.layout.url_list_inflater, null);
            urlHolder = new UrlViewHolder(convertView);
            convertView.setTag(urlHolder);
        } else {
            urlHolder = (UrlViewHolder) convertView.getTag();
        }
        setHolderViews(urlHolder, position);
        return convertView;
    }

    private void setHolderViews(UrlViewHolder urlHolder, int position) {
        setTextViewHolder(urlHolder.urlText, urlsViewModels.get(position).getUrl());
        setCircleViewHolder(urlHolder.circle, urlsViewModels.get(position).getCircleResource());
    }

    private void setTextViewHolder(TextView urlText, String url) {
        urlText.setText(url);
    }

    private void setCircleViewHolder(RelativeLayout circle, int resId) {
        circle.setBackgroundResource(resId);
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
