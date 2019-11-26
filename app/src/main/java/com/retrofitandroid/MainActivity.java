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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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

    Lapdter lapdter;

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
        moviePresenter.getApiCall(
                "db879a16f7c7bb79031c9b37eae73e65",
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
            lapdter = new Lapdter(movieDataList);
            rcList.setAdapter(lapdter);
        } else {
            movieDataList.addAll(data);
            lapdter.notifyDataSetChanged();
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
        moviePresenter.getApiCall(
                "db879a16f7c7bb79031c9b37eae73e65",
                "en-US", String.valueOf(pageNumber)
        );
    }

    public class Lapdter extends RecyclerView.Adapter<Lapdter.Myholder> {
        List<MovieData> adapterList;


        public Lapdter(List<MovieData> dataList) {
            this.adapterList = dataList;

        }

        public class Myholder extends RecyclerView.ViewHolder {

            TextView textView;
            TextView textView2;

            public Myholder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                textView2 = itemView.findViewById(R.id.textView2);
            }
        }


        @NonNull
        @Override
        public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new Myholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Myholder holder, int position) {
            holder.textView.setText(adapterList.get(position).getTitle());
            holder.textView2.setText(adapterList.get(position).getOverview());
        }


        @Override
        public int getItemCount() {
            return adapterList.size();
        }


    }


}
