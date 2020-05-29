package com.retrofitandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retrofitandroid.adapters.MoviesListAdapter;
import com.retrofitandroid.mvp.model.MovieData;
import com.retrofitandroid.mvp.model.MovieDataResponse;
import com.retrofitandroid.mvp.presenter.MoviePresenter;
import com.retrofitandroid.mvp.view.MovieView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieView, OnLoadMoreListener {

    int pageNumber = 0;

    List<MovieData> movieDataList;
    MoviePresenter moviePresenter;

    @BindView(R.id.rcList)
    RecyclerView rcList;

    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    MoviesListAdapter moviesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSmartRefreshLayout = findViewById(R.id.mSmartRefreshLayout);
        mSmartRefreshLayout.setOnLoadMoreListener(this);
        mSmartRefreshLayout.setEnableRefresh(false);

        rcList.setLayoutManager(new LinearLayoutManager(this));

        moviePresenter = new MoviePresenter(this, this);

        pageNumber++;
        moviePresenter.GetMoviesList(
                "<ENTER-YOUR-API-KEY>",
                "en-US", String.valueOf(pageNumber)
        );
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void sucessResponse(List<MovieData> data) {
        Log.e("Data", "array = >" + data.size());
        if (pageNumber == 1) {
            movieDataList = new ArrayList<MovieData>();
            movieDataList = data;
            moviesListAdapter = new MoviesListAdapter(movieDataList);
            rcList.setAdapter(moviesListAdapter);
        } else {
            movieDataList.addAll(data);
            moviesListAdapter.notifyDataSetChanged();
        }

        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void failureResponse(Response<MovieDataResponse> response) {
        Log.e("Data", " response = >" + response);
        pageNumber--;

    }

    @Override
    public void errorThrowable(Throwable throwable) {
        Log.e("Data", " throwable = >" + throwable);
        pageNumber--;

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNumber++;
        moviePresenter.GetMoviesList(
                "<ENTER-YOUR-API-KEY>",
                "en-US", String.valueOf(pageNumber));

    }

}
