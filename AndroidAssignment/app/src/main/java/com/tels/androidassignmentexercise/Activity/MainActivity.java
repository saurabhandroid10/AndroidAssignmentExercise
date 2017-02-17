package com.tels.androidassignmentexercise.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tels.androidassignmentexercise.R;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * This method is used to initialized view.
     */
    private void initView() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.please_wait));
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
                // refresh
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
