package com.raha.exercise1.url_test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raha.exercise1.R;
import com.raha.exercise1.utils.ParseUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.raha.exercise1.utils.ConnectionUtils.*;


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
    private UrlsBindAdapter urlsAdapter;
    private List<UrlsViewModel> urlsViewModels = new ArrayList<>();
    private ConnectionManager connectionRequest = ConnectionManager.getInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initializeAdapter();

    }

    private void initializeAdapter() {
        urlsAdapter = new UrlsBindAdapter(getApplicationContext(), urlsViewModels);
        urlsList.setAdapter(urlsAdapter);
    }

    @Override
    public void getStatusAndUrl(Boolean status, String url) {
        Timber.d(sendLog);
        progressBar.setVisibility(View.GONE);
        addNewFileOrUpdate(status, url);
    }

    @OnClick(R.id.bt_test)
    public void buttonTestClick(View view) {
        if (isInternetConnection(getApplicationContext())) {
            if (trySendRequest())
                progressBar.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_LONG).show();
        }
    }

    private boolean trySendRequest() {
        if (!connectionRequest.isRequestSend()) {
            return checkUrlAndSendRequest();
        }
        return false;
    }

    private boolean checkUrlAndSendRequest() {
        String urlString = urlEditText.getText().toString();
        if (ParseUtils.checkURL(urlString)) {
            sendRequest(urlString);
            return true;
        } else {
            urlEditText.setError("malformed url");
            return false;
        }
    }

    private void sendRequest(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Timber.e("Bad URL", e);
        }
        connectionRequest.sendRequest(url);
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
        urlsAdapter.notifyDataSetChanged();
    }

    private void updateFieldOnList(int ringColorId, int fieldPosition) {
        UrlsViewModel item = urlsAdapter.getItem(fieldPosition);
        if (!item.isCircleResource(ringColorId)) {
            item.setCircleResource(ringColorId);
            urlsAdapter.notifyDataSetChanged();
        }

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

}
