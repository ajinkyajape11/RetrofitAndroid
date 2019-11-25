package com.retrofitandroid.mvp.presenter;

import android.content.Context;

import com.retrofitandroid.ApiInterface;
import com.retrofitandroid.api.ApiClient;
import com.retrofitandroid.mvp.model.MovieDataResponse;
import com.retrofitandroid.mvp.view.MovieView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    Context context;
    MovieView movieView;

    ApiClient apiClient;

    public MoviePresenter(Context context, MovieView movieView) {
        this.context = context;
        this.movieView = movieView;
    }

    public void getApiCall() {

        new ApiClient().API().getList("", "", "").enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {

            }

            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {

            }
        });


    }
}
