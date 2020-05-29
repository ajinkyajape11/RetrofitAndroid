package com.retrofitandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrofitandroid.R;
import com.retrofitandroid.mvp.model.MovieData;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.Myholder> {

    List<MovieData> adapterList;

    public MoviesListAdapter(List<MovieData> dataList) {
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
