package com.raha.exercise1.activitis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raha.exercise1.R;
import com.raha.exercise1.interfaces.ConnectionResponse;
import com.raha.exercise1.url.PageConnect;
import com.raha.exercise1.utils.Consts;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAllVariable();
    }

    private void initializeAllVariable() {
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
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

    ConnectionResponse conResponse= new ConnectionResponse() {
        @Override
        public Integer getStatus(Boolean status) {
            goneProgressBar();
            showToast(status);
            return null;
        }
    };
    private void sendRequest() {
        PageConnect pageConnect = new PageConnect(conResponse);
        URL serverAddress = null;
        try {
            serverAddress = new URL(Consts.SERVER_ADDRESS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (serverAddress != null)
            pageConnect.execute(serverAddress);
    }
    private void showToast(Boolean responseStatus) {
        int stringTooToast;
        if (responseStatus) {
            stringTooToast = R.string.status_ok;
        } else {
            stringTooToast = R.string.status_fail;
        }
        Toast.makeText(this, stringTooToast, Toast.LENGTH_SHORT).show();
    }


}
