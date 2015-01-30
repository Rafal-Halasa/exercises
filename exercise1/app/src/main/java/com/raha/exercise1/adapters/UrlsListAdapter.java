package com.raha.exercise1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raha.exercise1.R;
import com.raha.exercise1.models.appModels.UrlsViewModel;

import java.util.List;
import java.util.zip.Inflater;

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
        infalter= LayoutInflater.from(this.applicationContext);
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
        if (convertView==null){
            convertView =infalter.inflate(R.layout.url_list_inflater,null);
            urlHolder=new UrlViewHolder();
            convertView.setTag(urlHolder);
        }else{
            urlHolder=(UrlViewHolder)convertView.getTag();
        }
        urlHolder.urlText=setView(convertView,R.id.tv_url,urlsViewModels.get(position).getUrl());
        urlHolder.circle=setView(convertView,R.id.rl_circle_container,urlsViewModels.get(position).getCircleResource());

        return convertView;
    }

    private TextView setView(View convertView, int id, String url) {
        TextView textView = (TextView) convertView.findViewById(id);
        textView.setText(url);
        return textView;
    }
    private RelativeLayout setView(View convertView, int id, int resId) {
        RelativeLayout relLay = (RelativeLayout) convertView.findViewById(id);
        relLay.setBackgroundResource(resId);
        return relLay;
    }

    private class UrlViewHolder{
            TextView urlText;
            RelativeLayout circle;
    }
}
