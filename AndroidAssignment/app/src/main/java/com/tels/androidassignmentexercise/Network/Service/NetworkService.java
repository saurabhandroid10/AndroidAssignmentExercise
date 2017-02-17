package com.tels.androidassignmentexercise.Network.Service;

import com.tels.androidassignmentexercise.Model.CountryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("746330/facts.json")
    Call<CountryResponse> getResponse();

}
