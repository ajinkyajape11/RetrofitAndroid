package com.retrofitandroid.api;

import android.content.Context;

import com.retrofitandroid.ApiInterface;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit = null;

    String main_url_local = "https://api.themoviedb.org/3/";

    public ApiClient() {
    }

    public ApiInterface API() {

        retrofit = new Retrofit
                .Builder()
                .baseUrl(main_url_local)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build();
       return retrofit.create(ApiInterface.class);

    }

}

