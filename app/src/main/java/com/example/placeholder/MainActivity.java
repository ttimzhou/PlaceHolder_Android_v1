package com.example.placeholder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scichart.charting.visuals.SciChartSurface;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.NewsAdapter;
import Model.NewsItem;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter newsAdapter;
    private List<NewsItem> newsItems;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        try {
            SciChartSurface.setRuntimeLicenseKey("<LicenseContract>\n" +
                    "  <Customer>tim.qianzhou@berkeley.edu</Customer>\n" +
                    "  <OrderId>Trial</OrderId>\n" +
                    "  <LicenseCount>1</LicenseCount>\n" +
                    "  <IsTrialLicense>true</IsTrialLicense>\n" +
                    "  <SupportExpires>08/17/2019 00:00:00</SupportExpires>\n" +
                    "  <ProductCode>SC-ANDROID-2D-ENTERPRISE-SRC</ProductCode>\n" +
                    "  <KeyCode>3eb14eb8f88a2ffc9f1e2a44844de24e0dc71c3fb4006fb08f49a3795be0d78f16602e35cbdd8b47abb9354ebb8161c2a6438c4928f690d77f215b38110adb566656e9c3401107fa262011ac76712ea0357b6295de48e5d2cae4586f668b180aae674740235652bcb2609f152d5d125c54b1d54290a18b409a9daba3eb0526d192c4a6a493292d5487d90d8189227fb97248e6c3a90efd793e174dfdca0dc882154ad5a401596bae455ff1bccbcf4c10333758</KeyCode>\n" +
                    "</LicenseContract>");
        } catch (Exception e) {

        }

        startActivity(new Intent(this, StockChartActivity.class));


        newsRecyclerView = (RecyclerView) findViewById(R.id.newsRecyclerView);
//        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        newsItems = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            NewsItem item = new NewsItem("http://i.imgur.com/DvpvklR.png",
                    "China" + i,
                    "Goal!!!");
            newsItems.add(item);
        }

        newsAdapter = new NewsAdapter(MainActivity.this, newsItems);
        newsRecyclerView.setAdapter(newsAdapter);

    }

}
