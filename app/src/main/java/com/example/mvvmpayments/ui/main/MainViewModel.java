package com.example.mvvmpayments.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.mvvmpayments.ApplicationMain;
import com.example.mvvmpayments.data.models.ApiResponse;
import com.example.mvvmpayments.data.remote.API;
import com.example.mvvmpayments.data.repository.BaseRepository;
import com.example.mvvmpayments.ui.BaseNavigator;
import com.example.mvvmpayments.ui.BaseNavigator.NotifyType;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel {

    @Inject
    API apiService;
    private BaseNavigator navigator;
    private LifecycleOwner owner;
    private BaseRepository baseRepository;
    public ObservableBoolean showLoadingView = new ObservableBoolean(false);

    public MainViewModel(@NonNull Application application, BaseNavigator navigator, LifecycleOwner owner) {
        super(application);
        this.navigator = navigator;
        this.owner = owner;

        ((ApplicationMain) application).restComponent.inject(this);
        baseRepository = new BaseRepository(apiService, ((ApplicationMain) application));
    }

    protected void getPaymentTypes() {
        showLoadingView.set(true);
        LiveData<ApiResponse> observable = baseRepository.getPaymentMethods();
        observable.observe(owner, response -> {
            if (response != null) {
                navigator.onAPIResponse(response);
            } else {
                navigator.notifyUser("Unable to get response", NotifyType.TYPE_TOAST);
            }

            showLoadingView.set(false);
        });
    }
}