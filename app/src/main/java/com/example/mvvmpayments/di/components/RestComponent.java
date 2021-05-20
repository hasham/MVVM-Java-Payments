package com.example.mvvmpayments.di.components;

import com.example.mvvmpayments.ApplicationMain;
import com.example.mvvmpayments.di.modules.AppModule;
import com.example.mvvmpayments.di.modules.NetModule;
import com.example.mvvmpayments.ui.BaseActivity;
import com.example.mvvmpayments.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, NetModule.class })

public interface RestComponent {

    void inject(ApplicationMain application);

    void inject(BaseActivity activity);

    void inject(MainViewModel viewModel);
}
