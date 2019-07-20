package com.example.placeholder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint;
import com.scichart.charting.visuals.annotations.TextAnnotation;
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class StockChartActivity extends AppCompatActivity {

    private TextView buyPrce;
    private TextView sellPrice;
    private Button findStock;
    private EditText stockNum;
    private TextView currStock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_chart);

        buyPrce = (TextView) findViewById(R.id.currBuyPrice);
        sellPrice = (TextView) findViewById(R.id.currSellPrice);
        currStock = (TextView) findViewById(R.id.stockName);
        findStock = (Button) findViewById(R.id.findStock);
        stockNum = (EditText) findViewById(R.id.stockNum);


        findStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStock(stockNum.getText().toString());
            }
        });



    }


    public void updateStock(String stockNum) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            }
        };


        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1000 * 5;

        timer.scheduleAtFixedRate(task, delay, intervalPeriod);
    }
}
