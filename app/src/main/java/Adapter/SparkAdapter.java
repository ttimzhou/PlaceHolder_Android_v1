package Adapter;

import android.graphics.RectF;

import java.util.ArrayList;

public class SparkAdapter extends com.robinhood.spark.SparkAdapter {
    private ArrayList<Float> yData;

    public SparkAdapter(ArrayList<Float> yData) {
        this.yData = yData;
    }

    @Override
    public int getCount() {
        return yData.size();
    }

    @Override
    public Object getItem(int index) {
        return yData.get(index);
    }



    @Override
    public float getY(int index) {
        return yData.get(index);
    }
}
