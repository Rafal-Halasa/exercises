package com.raha.exercise1.activitis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raha.exercise1.R;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void sendRequest() {
        PageConnect pageConnect = new PageConnect(this,progressBar);
        URL serverAddress = null;
        try {
            serverAddress = new URL(Consts.SERVER_ADDRESS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (serverAddress != null)
            pageConnect.execute(serverAddress);
    }

}
