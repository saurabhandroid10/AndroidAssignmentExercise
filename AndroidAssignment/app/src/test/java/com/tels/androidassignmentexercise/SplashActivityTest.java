package com.tels.androidassignmentexercise;

import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;

import com.tels.androidassignmentexercise.Activity.SplashActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by saurabhgarg on 20/02/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SplashActivityTest {

    private SplashActivity activity;
    private ProgressBar pbBar;

    @Before
    public void setUp() {
        // Call the "buildActivity" method so we get an ActivityController which we can use
        // to have more control over the activity lifecycle
        ActivityController<SplashActivity> ac = Robolectric.buildActivity(SplashActivity.class).create();
        activity = ac.get();
        pbBar = (ProgressBar) activity.findViewById(R.id.pgBar);
    }

    // checking Welcome To App text
    @Test
    public void checkWelcomeText() {
        String hello = new SplashActivity().getResources().getString(R.string.welcome_msg);
        assertThat(hello, equalTo("Welcome to App"));
    }

    // ProgressBar visibility
    @Test
    public void testButtonsVisible() {
        assertThat(pbBar.getVisibility(), equalTo(View.VISIBLE));
    }

    // Checking SplashActivity is not null
    @Test
    public void checkActivityNotNull() {
        assertTrue(activity != null);
    }
}
