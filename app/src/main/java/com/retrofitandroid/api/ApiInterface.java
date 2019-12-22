package com.retrofitandroid.api;

import com.retrofitandroid.mvp.model.MovieDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated?")
    Call<MovieDataResponse> getList(@Query("api_key") String api_key,
                                    @Query("language") String language,
                                    @Query("page") String page);

}
