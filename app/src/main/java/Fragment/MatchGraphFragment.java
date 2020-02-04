package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.placeholder.R;
import com.scichart.charting.model.dataSeries.IXyDataSeries;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.axes.AutoRange;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.data.model.DoubleRange;
import com.scichart.data.model.ISciList;
import com.scichart.extensions.builders.SciChartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchGraphFragment extends Fragment {
    private final static int FIFO_CAPACITY = 50;
    private final static long TIME_INTERVAL = 30;
    private final static double ONE_OVER_TIME_INTERVAL = 1.0 / TIME_INTERVAL;
    private final static double VISIBLE_RANGE_MAX = FIFO_CAPACITY * ONE_OVER_TIME_INTERVAL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.match_fragment, null);
        ButterKnife.bind(this, root);
        SciChartBuilder.init(getContext());
        SciChartBuilder sciChartBuilder = SciChartBuilder.instance();

        return root;
    }

    //    private final static double GROW_BY = VISIBLE_RANGE_MAX * 0.1;
//
//
////    protected final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
//
//    private Random random = new Random();
//
//    private final IXyDataSeries<Double, Double> ds1 = sciChartBuilder.newXyDataSeries(Double.class, Double.class).build();
//
////            sciChartBuilder.newXyDataSeries(Double.class, Double.class).withFifoCapacity(FIFO_CAPACITY).build();
//
//    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//    private ScheduledFuture<?> schedule;
//
//    @BindView(R.id.TempTradingChartHolder)
//    SciChartSurface surface;
//
//    private final DoubleRange xVisibleRange = new DoubleRange(-GROW_BY, VISIBLE_RANGE_MAX + GROW_BY);
//
//    private volatile boolean isRunning = true;
//
//    protected int getLayoutId() {
//        return R.layout.activity_match_detail;
//    }
//
//    protected void initExample() {
//        System.out.println("BUILDER IS" + sciChartBuilder);
//        final NumericAxis xAxis = sciChartBuilder.newNumericAxis().withVisibleRange(xVisibleRange).withAutoRangeMode(AutoRange.Never).build();
//        final NumericAxis yAxis = sciChartBuilder.newNumericAxis().withGrowBy(0.1d, 0.1d).withAutoRangeMode(AutoRange.Always).build();
//
//        final IRenderableSeries rs1 = sciChartBuilder.newLineSeries().withDataSeries(ds1).withStrokeStyle(0xFF4083B7, 2f, true).build();
//
//        UpdateSuspender.using(surface, new Runnable() {
//            @Override
//            public void run() {
//                Collections.addAll(surface.getXAxes(), xAxis);
//                Collections.addAll(surface.getYAxes(), yAxis);
//                Collections.addAll(surface.getRenderableSeries(), rs1);
//            }
//        });
//
//        schedule = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                if (!isRunning) {
//                    return;
//                }
//                UpdateSuspender.using(surface, insertRunnable);
//            }
//        }, 0, TIME_INTERVAL * 5, TimeUnit.MILLISECONDS);
//    }
//

//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        isRunning = false;
//
//        outState.putDouble("time", t);
//        outState.putParcelable("xValues1", ds1.getXValues());
//        outState.putParcelable("yValues1", ds1.getYValues());
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        System.out.println(sciChartBuilder+ "!!!");
//        if (savedInstanceState != null) {
//            t = savedInstanceState.getDouble("time");
//            final ISciList<Double> xValues1 = savedInstanceState.getParcelable("xValues1");
//            final ISciList<Double> yValues1 = savedInstanceState.getParcelable("yValues1");
//            ds1.append(xValues1, yValues1);
//        }
//    }
//
//    double t = 0;
//    private final Runnable insertRunnable = new Runnable() {
//        @Override
//        public void run() {
//            double y1 = 2;
//
//            ds1.append(t, y1);
//
//            t += ONE_OVER_TIME_INTERVAL;
//
//            if (t > VISIBLE_RANGE_MAX) {
//                xVisibleRange.setMinMax(xVisibleRange.getMin() + ONE_OVER_TIME_INTERVAL, xVisibleRange.getMax() + ONE_OVER_TIME_INTERVAL);
//            }
//        }
//    };


}
