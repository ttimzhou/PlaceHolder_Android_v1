package com.example.placeholder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MatchDetailActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    final Fragment fragment1 = new tradeActivity();
    final Fragment mainFragment = new fragment_match_detail();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainFragment;


    BottomNavigationView.OnNavigationItemSelectedListener mSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//          Start fragment for buy sell or news
            switch (menuItem.getItemId()) {
                case R.id.navigation_trade:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_dashBoard:
                    fm.beginTransaction().hide(active).show(mainFragment).commit();
                    active = mainFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_detail);
        Bundle newBundle = new Bundle();
        newBundle.putString("id", getIntent().getStringExtra("id"));
        fragment1.setArguments(newBundle);
        mainFragment.setArguments(newBundle);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mSelectedListener);
        fm.beginTransaction().add(R.id.mainContainer, mainFragment, "0").commit();
        fm.beginTransaction().add(R.id.mainContainer, fragment1, "1").hide(fragment1).commit();
    }




}
