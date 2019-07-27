package com.example.placeholder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import Adapter.NewsAdapter;
import Model.NewsItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter newsAdapter;
    private List<NewsItem> newsItems;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_matches:
                    startActivity(new Intent(MainActivity.this, MatchPreviewActivity.class));
                    return true;
                case R.id.navigation_news:
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
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view_match);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_news);


//        startActivity(new Intent(this, StockChartActivity.class));



        newsRecyclerView = (RecyclerView) findViewById(R.id.newsRecyclerView);
//        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        newsItems = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            NewsItem item = new NewsItem("http://i.imgur.com/DvpvklR.png",
                    "China" + i,
                    "Goal!!!", "101");
            newsItems.add(item);
        }

        newsAdapter = new NewsAdapter(MainActivity.this, newsItems);
        newsRecyclerView.setAdapter(newsAdapter);

    }

}
