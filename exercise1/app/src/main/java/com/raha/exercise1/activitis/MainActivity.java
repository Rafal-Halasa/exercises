package com.raha.exercise1.activitis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raha.exercise1.R;
import com.raha.exercise1.adapters.UrlsListAdapter;
import com.raha.exercise1.interfaces.ConnectionResponse;
import com.raha.exercise1.models.appModels.UrlsViewModel;
import com.raha.exercise1.url.PageConnect;
import com.raha.exercise1.utils.Consts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import timber.log.Timber;


public class MainActivity extends ActionBarActivity implements ConnectionResponse {
    public static final String TAG_MAIN_ACT = "Main Act";
    private String sendLog = "send request";
    private ProgressBar progressBar;
    private EditText urlEditText;
    private ListView urlsList;
    private UrlsListAdapter urlsAdapter;
    private List<UrlsViewModel> urlsViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTagLog();
        initializeAllVariable();

    }


    private void setTagLog() {
        Timber.tag(TAG_MAIN_ACT);
    }

    private void initializeAllVariable() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        urlEditText = (EditText) findViewById(R.id.et_url_field);
        urlsList = (ListView) findViewById(R.id.lv_urls_list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonSendRequest(View view) {
        showProgressBar();
        sendRequest();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void goneProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void sendRequest() {
        Timber.d(sendLog);
        PageConnect pageConnect = new PageConnect(this);
        pageConnect.execute(urlEditText.getText().toString());
    }


    @Override
    public Integer getStatusAndUrl(Boolean status, String url) {
        goneProgressBar();
        createListView();
        addFieldTooList(status, url);
        return null;
    }

    private void addFieldTooList(Boolean status, String url) {
        int ring;
        if (status) {
            ring = R.drawable.green_circle;
        } else {
            ring = R.drawable.red_circle;
        }
        urlsViewModels.add(new UrlsViewModel(ring, url));
        urlsAdapter.notifyDataSetChanged();
    }

    private void createListView() {
        if (urlsViewModels == null) {
            urlsViewModels = new ArrayList<>();
            urlsAdapter = new UrlsListAdapter(getApplicationContext(), urlsViewModels);
            urlsList.setAdapter(urlsAdapter);
        }
    }
}
