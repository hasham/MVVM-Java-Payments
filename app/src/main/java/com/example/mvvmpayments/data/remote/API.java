package com.example.mvvmpayments.data.remote;

import com.example.mvvmpayments.data.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String ACTION_PAYMENT_TYPES = "optile/checkout-android/develop/shared-test/lists/listresult.json";

    @GET(ACTION_PAYMENT_TYPES)
    Call<ApiResponse> getPaymentTypes();
}
