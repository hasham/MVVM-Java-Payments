package com.example.mvvmpayments.di.modules;

import android.app.Application;

import com.example.mvvmpayments.ApplicationMain;
import com.example.mvvmpayments.BuildConfig;
import com.example.mvvmpayments.data.remote.API;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private Application application;
    private final String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    API providesApiEndpoints(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        this.application = application;
        long cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.readTimeout(2, TimeUnit.MINUTES);
        httpClient.connectTimeout(2, TimeUnit.MINUTES);
        httpClient.writeTimeout(2, TimeUnit.MINUTES);
        httpClient.cache(cache);

        httpClient.addInterceptor(chain -> {

            Request request = chain.request()
                    .newBuilder()
//                    .header("User-Agent", "Android")
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            return chain.proceed(request);
        });

        httpClient.addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            String rawJson = Objects.requireNonNull(response.body()).string();
            try {
                // pre process response for error handling here
                // response success can also be handled in the Base Repository class and prompt the user in Activity
                int responseCode = response.code();
                if (responseCode == 404 || responseCode == 500) {
                    ((ApplicationMain) application).onApiResponseError(responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            // Re-create the response before returning it because body can be read only once
            return response.newBuilder()
                    .body(ResponseBody.create(Objects.requireNonNull(response.body()).contentType(), rawJson)).
                            build();
        });

        return httpClient.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }
}
