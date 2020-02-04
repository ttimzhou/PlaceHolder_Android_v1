package com.example.placeholder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;
import com.scichart.charting.visuals.SciChartSurface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import Model.NewsItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter newsAdapter;
    private List<NewsItem> newsItems;
    private RequestQueue queue;
    private JsonArrayRequest request;
    private String URL;
    private ImageView image;
    private Button click;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_matches:
                    if (getSupportFragmentManager().findFragmentByTag("FRAGMENT_MATCHES") != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, getSupportFragmentManager().findFragmentByTag("FRAGMENT_MATCHES")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_match_home_activity()).commit();
                    }
                    break;
                case R.id.navigation_news:
                    if (getSupportFragmentManager().findFragmentByTag("FRAGMENT_NEWS") != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, getSupportFragmentManager().findFragmentByTag("FRAGMENT_NEWS")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_new_home_activity()).commit();
                    }
                    break;
                case R.id.navigation_dashBoard:
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view_match);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_news);
        String license = "<LicenseContract>\n" +
                "  <Customer>zhouqian1998@gmail.com</Customer>\n" +
                "  <OrderId>Trial</OrderId>\n" +
                "  <LicenseCount>1</LicenseCount>\n" +
                "  <IsTrialLicense>true</IsTrialLicense>\n" +
                "  <SupportExpires>09/17/2019 00:00:00</SupportExpires>\n" +
                "  <ProductCode>SC-ANDROID-2D-ENTERPRISE-SRC</ProductCode>\n" +
                "  <KeyCode>b980a6b50bc7bff0a7a5eef4c0c74d4f07abee5f6f5e63b39bf7af364b32612ea3029a806b86477e5652f59e1e4054ef107815015f08282accbad51307e534e183d7887787b2e602a1c8f5f0818ea78ac60c47d8d4387e54cc51fa6f1d481462dae5ceb85f01bef0e48a3d3c98755a3589a5d761abc79e72388dd51b29719a63777ddabc36681b05fae6cc37e47bb8b4cb98931f8041d048d073471fbb591cb1b556a52b68d38969a05be5abe18832f0</KeyCode>\n" +
                "</LicenseContract>";
        try {
            SciChartSurface.setRuntimeLicenseKey(license);
        } catch (Exception e) {
            System.out.println(e);
        }



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_new_home_activity());

    }



}
