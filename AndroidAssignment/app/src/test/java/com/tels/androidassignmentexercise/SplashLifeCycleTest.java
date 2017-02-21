package com.tels.androidassignmentexercise;

import android.os.Build;

import com.tels.androidassignmentexercise.Activity.SplashActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;

/**
 * Created by saurabhgarg on 20/02/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SplashLifeCycleTest {

    private ActivityController<SplashActivity> controller;
    private SplashActivity activity;

    @Before
    public void setUp() {
        // Call the "buildActivity" method so we get an ActivityController which we can use
        // to have more control over the activity lifecycle
        controller = Robolectric.buildActivity(SplashActivity.class);
    }

    @Test
    public void checkLifeCycle() {
        activity = controller.create().start().resume().visible().get();
        assertTrue(activity != null);
    }

    @After
    public void tearDown() {
        // Destroy activity after every test
        controller.pause().stop().destroy();
    }
}
