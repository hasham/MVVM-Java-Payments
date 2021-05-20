package com.example.mvvmpayments.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmpayments.data.remote.API;
import com.google.gson.Gson;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    API apiService;

    @Inject
    Gson gson;
}
