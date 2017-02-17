package com.tels.androidassignmentexercise.Application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tels.androidassignmentexercise.Network.Provider.NetworkProvider;


/**
 * Created by saurabhgarg on 17/02/17.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Returns the provider for all network requests
     *
     * @return NetworkProvider
     */
    public NetworkProvider getNetworkProvider() {
        return new NetworkProvider();
    }

    /**
     * This method is used for checking Network connectivity.
     *
     * @return boolean
     */
    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }
}
