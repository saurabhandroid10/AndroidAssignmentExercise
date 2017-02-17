package com.tels.androidassignmentexercise.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.tels.androidassignmentexercise.R;
import com.tels.androidassignmentexercise.Utils.Constant;

/**
 * Created by saurabhgarg on 17/02/17.
 */
public class SplashActivity extends Activity {

    // Splash screen timer
    private int SPLASH_TIME_OUT = Constant.SPLASH_TIME_OUT;
    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initializeView();

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void initializeView() {
        pgBar = (ProgressBar) findViewById(R.id.pgBar);
        pgBar.setVisibility(View.VISIBLE);
    }
}
