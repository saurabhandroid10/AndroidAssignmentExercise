package com.tels.androidassignmentexercise.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.tels.androidassignmentexercise.Fragment.ListFragment;
import com.tels.androidassignmentexercise.R;

public class MainActivity extends AppCompatActivity implements ListFragment.TaskListener {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ListFragment listFragment = new ListFragment();
        ft.add(R.id.flMain, listFragment, getResources().getString(R.string.list_fragment));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    /**
     * This method is used to initialize view.
     */
    private void initView() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.please_wait));
    }


    @Override
    public void onTaskFinished(String title) {
        actionBar.setTitle(title);
    }
}
