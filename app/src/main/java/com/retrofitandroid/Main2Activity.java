package com.retrofitandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.retrofitandroid.mvp.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(getData()));
        mRecyclerView.addItemDecoration(sectionItemDecoration);

    }

    public ArrayList<String> getData() {
        String[] strings = getResources().getStringArray(R.array.animals);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(strings));
        return list;
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<String> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position)
                        .charAt(0) != people.get(position - 1)
                        .charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position)
                        .subSequence(0,
                                1);
            }
        };
    }
}
