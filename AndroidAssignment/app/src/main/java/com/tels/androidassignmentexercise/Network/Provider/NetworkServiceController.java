package com.tels.androidassignmentexercise.Network.Provider;

import com.tels.androidassignmentexercise.Utils.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saurabhgarg on 17/02/17.
 */
public class NetworkServiceController {

    protected final Retrofit retrofit;
    private OkHttpClient okHttpClient;


    public NetworkServiceController() {
        String baseUrl = Constant.BASE_URL;
        okHttpClient = buildOkhttpAdapter();
        retrofit = buildDefaultRestAdapter(baseUrl);
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


    private OkHttpClient buildOkhttpAdapter() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        return okClientBuilder.build();
    }


    private Retrofit buildDefaultRestAdapter(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}