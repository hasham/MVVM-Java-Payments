package com.example.mvvmpayments;

import android.app.Application;
import android.util.Log;

import com.example.mvvmpayments.di.components.DaggerRestComponent;
import com.example.mvvmpayments.di.components.RestComponent;
import com.example.mvvmpayments.di.modules.AppModule;
import com.example.mvvmpayments.di.modules.NetModule;

public class ApplicationMain extends Application {

    public RestComponent restComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        restComponent = DaggerRestComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.API_HOST))
                .build();

        restComponent.inject(this);
    }

    public void onApiResponseError(int responseCode) {
        //handle response code here
        Log.e("onApiResponseError", String.format("API Response error code %s", responseCode));
    }
}
