package com.raha.exercise1.url_test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.raha.exercise1.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity implements ConnectionResponse {
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.et_url_field)
    EditText urlEditText;
    @InjectView(R.id.lv_urls_list)
    ListView urlsList;

    public static final String TAG_MAIN_ACT = "Main Act";
    private int noValueInt = -1;
    private String sendLog = "send request";
    private UrlsListAdapter urlsAdapter;
    private List<UrlsViewModel> urlsViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setTagLog();
    }

    private void setTagLog() {
        Timber.tag(TAG_MAIN_ACT);
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

    @OnClick(R.id.bt_test)
    public void buttonTestClick(View view) {
        sendRequest();
        showProgressBar();
    }

    private void sendRequest() {
        Timber.d(sendLog);
        PageConnect pageConnect = new PageConnect(this);
        pageConnect.execute(urlEditText.getText().toString());
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void goneProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Integer getStatusAndUrl(Boolean status, String url) {
        goneProgressBar();
        createListView();
        addNewFileOrUpdate(status, url);
        return null;
    }

    private void addNewFileOrUpdate(Boolean status, String url) {
        int ringColorId = getRingColorId(status);
        int fieldPosition = findUrlInList(url);
        if (fieldPosition < 0) {
            addFieldTooList(ringColorId, url);
        } else {
            updateFieldOnList(ringColorId, fieldPosition);
        }
    }

    private int getRingColorId(Boolean status) {
        if (status) {
            return R.drawable.green_circle;
        } else {
            return R.drawable.red_circle;
        }
    }


    private void addFieldTooList(int ring, String url) {
        urlsViewModels.add(new UrlsViewModel(ring, url));
        updateUrlsAdapter();
    }

    private void updateFieldOnList(int ringColorId, int fieldPosition) {
        UrlsViewModel item = urlsAdapter.getItem(fieldPosition);
        if (item.isCircleResource(ringColorId)) {
            item.setCircleResource(ringColorId);
            updateUrlsAdapter();
        }

    }

    private void updateUrlsAdapter() {
        urlsAdapter.notifyDataSetChanged();
    }


    private int findUrlInList(String url) {
        UrlsViewModel viewModel;
        for (int i = 0; i < urlsViewModels.size(); i++) {
            viewModel = urlsViewModels.get(i);
            if (viewModel.getUrl().equals(url)) {
                return i;
            }
        }
        return noValueInt;
    }

    private void createListView() {
        if (urlsViewModels == null) {
            urlsViewModels = new ArrayList<>();
            urlsAdapter = new UrlsListAdapter(getApplicationContext(), urlsViewModels);
            urlsList.setAdapter(urlsAdapter);
        }
    }


}
