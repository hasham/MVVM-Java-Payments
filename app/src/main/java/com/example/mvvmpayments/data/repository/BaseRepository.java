package com.example.mvvmpayments.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmpayments.ApplicationMain;
import com.example.mvvmpayments.data.models.ApiResponse;
import com.example.mvvmpayments.data.remote.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRepository {

    private API apiService;
    private ApplicationMain application;

    public BaseRepository(API apiService, ApplicationMain application) {
        this.apiService = apiService;
        this.application = application;
    }

    public LiveData<ApiResponse> getPaymentMethods() {

        MutableLiveData<ApiResponse> data = new MutableLiveData<>();

        apiService.getPaymentTypes().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;

    }
}
