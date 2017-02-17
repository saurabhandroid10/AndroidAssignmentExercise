package com.tels.androidassignmentexercise.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.tels.androidassignmentexercise.Adapter.CustomAdapter;
import com.tels.androidassignmentexercise.Application.MyApplication;
import com.tels.androidassignmentexercise.Model.CountryResponse;
import com.tels.androidassignmentexercise.Model.Row;
import com.tels.androidassignmentexercise.Network.Service.NetworkService;
import com.tels.androidassignmentexercise.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private NetworkService mNetworkService;
    private ProgressDialog pbDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        MyApplication myApplication = (MyApplication) this.getApplicationContext();
        if (null == mNetworkService) {
            mNetworkService = myApplication.getNetworkProvider().getNetworkService();
        }

        if (myApplication.isNetworkConnectionAvailable()) {
            getServerResponse();
        } else {
            //Setting title of screen in case of Network failure
            actionBar.setTitle(getResources().getString(R.string.network_error));
            Toast.makeText(this, getResources().getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is used to initialize view.
     */
    private void initView() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.please_wait));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Calling web service and rendering service response
     */
    private void getServerResponse() {
        pbDialog = ProgressDialog.show(this, getResources().getString(R.string.loading), getResources().getString(R.string.please_wait));
        Call<CountryResponse> androidResponseCall = mNetworkService.getResponse();
        androidResponseCall.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, retrofit2.Response<CountryResponse> response) {
                pbDialog.dismiss();
                int statusCode = response.code();
                if (statusCode == 200) {
                    CountryResponse countryResponse = response.body();

                    actionBar.setTitle(countryResponse.getTitle());
                    List<Row> rowData = countryResponse.getRows();

                    recyclerView.setAdapter(new CustomAdapter(rowData, MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for action item click
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getServerResponse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
