package com.example.placeholder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import Adapter.MatchesAdapter;
import Adapter.NewsAdapter;
import Model.MatchItem;
import Model.NewsItem;

public class MatchPreviewActivity extends AppCompatActivity {


    private RecyclerView matchRecyclerView;
    private ArrayList<MatchItem> matchItems;
    private RecyclerView.Adapter matchesAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_matches:
                    return true;
                case R.id.navigation_news:
                    startActivity(new Intent(MatchPreviewActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashBoard:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_preview);
        BottomNavigationView navView = findViewById(R.id.nav_view_match);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.nav_view_match);


        matchRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMatch);
//        newsRecyclerView.setHasFixedSize(true);
        matchRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        matchItems = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            MatchItem item = new MatchItem("United",
                    "Spurs",
                    "7576", "101", "10");
            matchItems.add(item);
        }

        matchesAdapter = new MatchesAdapter(this, matchItems);
        matchRecyclerView.setAdapter(matchesAdapter);

    }
}
