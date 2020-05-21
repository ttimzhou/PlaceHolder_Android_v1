package com.example.placeholder;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tradeActivity extends Fragment {
    EditText inputAmount;
    EditText inputPrice;
    Button sell;
    Button buy;
    String URL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.activity_trade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        inputAmount = view.findViewById(R.id.tradeAmount);
        inputPrice = view.findViewById(R.id.tradePrice);
        sell = view.findViewById(R.id.sellButton);
        buy = view.findViewById(R.id.buyButton);

        final String matchID = getArguments().getString("id");
        URL = "https://sportexchange.herokuapp.com/game/request";

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JSONObject input = new JSONObject();
                try {
                    input.put("username", "timzhou");
                    input.put("matchId", matchID);
                    input.put("buy", true);
                    input.put("amount", inputAmount.getText());
                    input.put("price", inputPrice.getText());
                } catch (JSONException e) {
                    System.out.println(e);
                }
                System.out.println(input);
                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, URL, input, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error + "Failed update"); }
                });
                queue.add(request1);
            }
        });


        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JSONObject input = new JSONObject();
                try {
                    input.put("username", "timzhou");
                    input.put("matchId", matchID);
                    input.put("buy", false);
                    input.put("amount", inputAmount.getText());
                    input.put("price", inputPrice.getText());
                } catch (JSONException e) {
                    System.out.println(e);
                }
                System.out.println(URL);
                System.out.println(input);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, input, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error + "Failed update"); }
                });
                queue.add(request);
            }
        });






    }


}
