package com.example.placeholder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.highsoft.highcharts.Common.HIChartsClasses.HISeries;
import com.scichart.charting.model.dataSeries.IXyDataSeries;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.data.model.DoubleRange;
import com.scichart.extensions.builders.SciChartBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import Adapter.MatchesAdapter;
import Adapter.MessageAdapter;
import Adapter.TransactionHistoryAdapter;
import Model.MatchItem;
import Model.MessageItem;
import Model.TransactionModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_match_detail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_match_detail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_match_detail extends Fragment {
    private final static long TIME_INTERVAL = 5000;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> schedule;
    private boolean isRunning = true;
    private RecyclerView messageRecyclerView;
    private ArrayList<MessageItem> messageItems;
    private MessageAdapter messageAdapter;

    final double XaxisStart = -1;
    final double XaxisEnd = 1500;
    final DoubleRange xVisibleRange = new DoubleRange(XaxisStart, XaxisEnd);

    final double YaxisStart = -1;
    final double YaxisEnd = 100;
    final DoubleRange yVisibleRange = new DoubleRange(YaxisStart, YaxisEnd);

    String sell5 = "51";
    String sell4 = "51";
    String sell3 = "51";
    String sell2 = "51";
    String sell1 = "51";
    String buy1 = "50";
    String buy2 = "50";
    String buy3 = "50";
    String buy4 = "50";
    String buy5 = "50";

    TextView pendingB1;
    TextView pendingB2;
    TextView pendingB3;
    TextView pendingB4;
    TextView pendingB5;
    TextView pendingS1;
    TextView pendingS2;
    TextView pendingS3;
    TextView pendingS4;
    TextView pendingS5;
    ArrayList<TransactionModel> transactionLists;
    RecyclerView transactionRecycler;

    //      Could specify the range and mode
    NumericAxis xAxis;
    NumericAxis yAxis;
    IXyDataSeries<Integer, Double> ds;
    IRenderableSeries rs;
    //    SparkAdapter sparkAdapter;
    //    SparkView sparkView;
    RequestQueue queue;
    String URL;
    TextView currPrice;

    JsonObjectRequest request;
    ArrayList<HISeries> data;


    @NonNull
    @Override
    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        return super.getViewLifecycleOwnerLiveData();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_match_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(getContext());
        URL = "https://sportexchange.herokuapp.com/game/update/" + getArguments().getString("id");
        data = new ArrayList<>();

        pendingB1 = getView().findViewById(R.id.B1detail);
        pendingB2 = getView().findViewById(R.id.B2detail);
        pendingB3 = getView().findViewById(R.id.B3detail);
        pendingB4 = getView().findViewById(R.id.B4detail);
        pendingB5 = getView().findViewById(R.id.B5detail);
        pendingS1 = getView().findViewById(R.id.S1detail);
        pendingS2 = getView().findViewById(R.id.S2detail);
        pendingS3 = getView().findViewById(R.id.S3detail);
        pendingS4 = getView().findViewById(R.id.S4detail);
        pendingS5 = getView().findViewById(R.id.S5detail);
        transactionRecycler = getView().findViewById(R.id.transactionRecycler);
        transactionRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        initiateGraph();
        JSONObject input = new JSONObject();
        try {
            input.put("username", "timzhou");
        } catch (JSONException e) {
            System.out.println(e);
        }

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, URL, input, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                processUpdate(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error + "Failed update"); }
        });
        queue.add(request1);
        messageRecyclerView = getView().findViewById(R.id.liveGameRecyclerView);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        messageItems = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            MessageItem item = new MessageItem("5/20/20", "Hello World", "timzhou");
            messageItems.add(item);
        }
        messageAdapter = new MessageAdapter(getContext(), messageItems);
        messageRecyclerView.setAdapter(messageAdapter);
    }

    private void initiateGraph() {

        TextView matchTitle = getView().findViewById(R.id.matchTitle);
        matchTitle.setText("Manunited vs Madrid");
        SciChartBuilder.init(getContext());
        final Random random = new Random();
        SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
        final SciChartSurface chart = getView().findViewById(R.id.TempTradingChartHolder);

        //      Could specify the range and mode
        xAxis = sciChartBuilder.newNumericAxis().withVisibleRange(xVisibleRange).build();
        yAxis = sciChartBuilder.newNumericAxis().withVisibleRange(yVisibleRange).build();
        ds = sciChartBuilder.newXyDataSeries(Integer.class, Double.class).build();
        rs = sciChartBuilder.newLineSeries().withDataSeries(ds).withStrokeStyle(0xFF4083B7, 2f, true).build();

        UpdateSuspender.using(chart, new Runnable() {
            @Override
            public void run() {
                Collections.addAll(chart.getXAxes(), xAxis);
                Collections.addAll(chart.getYAxes(), yAxis);
                Collections.addAll(chart.getRenderableSeries(), rs);
            }
        });
        final Runnable insertRunnable = new Runnable() {

            //          Include Request Calls to get price updates.
            @Override
            public void run() {
                JSONObject input = new JSONObject();
                try {
                    input.put("username", "timzhou");
                } catch (JSONException e) {
                    System.out.println(e);
                }
                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, URL, input, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        processUpdate(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error + "Failed update");
                    }
                });
                queue.add(request1);
            }
        };
        schedule = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (!isRunning) {
                    return;
                }
                UpdateSuspender.using(chart, insertRunnable);
            }
        }, 0, TIME_INTERVAL, TimeUnit.MILLISECONDS);
    }

    //  append latest price to price history array
    public void processUpdate(JSONObject res) {
        try {
            JSONArray priceLog = res.getJSONArray("priceHistory");
//          Update Price log

            for (int i = ds.getCount(); i < priceLog.length(); i++) {
                double price = priceLog.getDouble(i);
                ds.append(i, price);
            }

//          Update pending requests;
            JSONArray buyQueue = res.getJSONArray("buyQueue");
            JSONArray sellQueue = res.getJSONArray("sellQueue");
            JSONArray transactions = res.getJSONArray("log");
            while (buyQueue.length() < 5) {
                buyQueue.put("--");
            }
            while (sellQueue.length() < 5) {
                sellQueue.put("--");
            }
            System.out.println(buyQueue);
            System.out.println(sellQueue);

            buy1 = buyQueue.getString(0);
            buy2 = buyQueue.getString(1);
            buy3 = buyQueue.getString(2);
            buy4 = buyQueue.getString(3);
            buy5 = buyQueue.getString(4);
            sell1 = sellQueue.getString(0);
            sell2 = sellQueue.getString(1);
            sell3 = sellQueue.getString(2);
            sell4 = sellQueue.getString(3);
            sell5 = sellQueue.getString(4);
            System.out.println("BUY 2 is " + buy2);

            pendingB1.setText("B " + buy1);
            pendingB2.setText("B " + buy2);
            pendingB3.setText("B " + buy3);
            pendingB4.setText("B " + buy4);
            pendingB5.setText("B " + buy5);
            pendingS1.setText("S " + sell1);
            pendingS2.setText("S " + sell2);
            pendingS3.setText("S " + sell3);
            pendingS4.setText("S " + sell4);
            pendingS5.setText("S " + sell5);

            transactionLists = new ArrayList<>();
            for (int i = 0; i < transactions.length(); i++) {
                JSONObject curr = transactions.getJSONObject(i);
                if (curr.getBoolean("buy")) {
                    transactionLists.add(new TransactionModel("B", curr.getString("amount"), curr.getString("createdAt").substring(11,16)));
                } else {
                    transactionLists.add(new TransactionModel("S", curr.getString("amount"), curr.getString("createdAt").substring(11,16)));
                }
            }
            TransactionHistoryAdapter mAdapter = new TransactionHistoryAdapter(transactionLists, getContext());
            transactionRecycler.setAdapter(mAdapter);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
}
