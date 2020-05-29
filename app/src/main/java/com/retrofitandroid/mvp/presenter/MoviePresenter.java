package com.retrofitandroid.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.retrofitandroid.api.ApiClient;
import com.retrofitandroid.mvp.model.MovieDataResponse;
import com.retrofitandroid.mvp.view.MovieView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter extends ApiClient {

    Context context;
    MovieView movieView;

    public MoviePresenter(Context context, MovieView movieView) {
        this.context = context;
        this.movieView = movieView;
    }

    public void GetMoviesList(String key, String language, String page) {
        API().getList(key, language, page).enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {
                try {
                    if (response.body().getResults() != null) {
                        movieView.sucessResponse(response.body().getResults());
                    } else {
                        movieView.failureResponse(response);
                    }
                } catch (Exception e) {
                    Log.e("TryCatchError", "" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {
                movieView.errorThrowable(t);
            }
        });


    }
}
