package com.tels.androidassignmentexercise.Network.Provider;


import com.tels.androidassignmentexercise.Network.Service.NetworkService;

/**
 * Created by saurabhgarg on 17/02/17.
 */
public class NetworkProvider {

    private NetworkService mNetworkService;
    private NetworkServiceController mServiceController;

    public NetworkProvider() {
        mServiceController = new NetworkServiceController();
        mNetworkService = mServiceController.create(NetworkService.class);
    }

    /**
     * This method is used to get instance of NetworkService
     * @return NetworkService
     */
    public NetworkService getNetworkService() {
        return mNetworkService;
    }

}
