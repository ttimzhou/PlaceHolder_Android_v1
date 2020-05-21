package com.example.placeholder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.MatchesAdapter;
import Model.MatchItem;

public class fragment_match_home_activity extends Fragment {

    private RecyclerView matchRecyclerView;
    private ArrayList<MatchItem> matchItems;
    private RecyclerView.Adapter matchesAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matchRecyclerView = getView().findViewById(R.id.recyclerViewMatch);
        matchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        matchItems = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            MatchItem item = new MatchItem("United",
                    "Spurs",
                    "5ec4775ee75782001713a493", "101", "10");
            matchItems.add(item);
        }

        //?
        matchesAdapter = new MatchesAdapter(getContext(), matchItems);
        matchRecyclerView.setAdapter(matchesAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        newsRecyclerView.setHasFixedSize(true);

    }
}