package com.retrofitandroid.mvp.view;

import com.retrofitandroid.mvp.model.MovieData;
import com.retrofitandroid.mvp.model.MovieDataResponse;

import java.util.List;

import retrofit2.Response;

public interface MovieView {

    void showLoading();

    void hideLoading();

    void sucessResponse(List<MovieData> data);

    void failureResponse(Response<MovieDataResponse> response);

    void errorThrowable (Throwable throwable);
}
