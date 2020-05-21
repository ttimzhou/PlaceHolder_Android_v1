package com.example.placeholder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.NewsAdapter;
import Model.NewsItem;

public class fragment_new_home_activity extends Fragment {
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter newsAdapter;
    private List<NewsItem> newsItems;
    private RequestQueue queue;
    private JsonArrayRequest request;
    private String URL;
    private ImageView image;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        login();
        System.out.println("NOW LOGGING IN");
        return inflater.inflate(R.layout.fragment_news_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRecyclerView = getView().findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button click = view.findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject input = new JSONObject();
                try {
                    input.put("usernmae", "timzhou");
                } catch (JSONException e) {
                    System.out.println(e);
                }

                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, "https://sportexchange.herokuapp.com/game/update/5ec4589ae75782001713a448", input, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response + "sucess update");

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error + "Failed update");
                    }
                });

                queue.add(request1);

            }
        });
        newsItems = new ArrayList<>();
        URL = "https://sportexchange.herokuapp.com/news/allnews";
        queue = Volley.newRequestQueue(getActivity());
        request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        NewsItem item = new NewsItem(obj.getString("img"), obj.getString("title"), obj.getString("_id"));
//                        Picasso.get().load("http://sportexchange.herokuapp.com/file/5d4521e2ba18020017ea2b94").resize(50,50).into(image);
                        newsItems.add(item);
                    } catch (JSONException e) {

                    }
                }
                setRecyclerView();
            }
        }, null);

//        Error with news activity
//        queue.add(request);
        newsAdapter = new NewsAdapter(getContext(), newsItems);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setRecyclerView() {
        newsAdapter = new NewsAdapter(getContext(), newsItems);
        newsRecyclerView.setAdapter(newsAdapter);
    }



    //    Temp login during development
    public void login() {
        System.out.println("LOGGING IN");
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject input = new JSONObject();
        try {
            input.put("username", "timzhou").put("password", "123");
        } catch (JSONException e) {
            System.out.println("FOUND");
            System.out.println(e);
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://sportexchange.herokuapp.com/mobile/login", input, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");
                System.out.println(error);
            }
        });
        queue.add(request);
    }
}
