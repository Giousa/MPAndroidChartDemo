package com.zmm.mpandroidchartdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zmm.mpandroidchartdemo.R;

import java.util.ArrayList;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:41a
 */

public class CustomLineView2 extends RelativeLayout {

    private static final String TAG = CustomLineView2.class.getSimpleName();

    private TextView mChartTitle;
    private TextView mChartUnit;
    private ImageView mChartLeft;
    private LineChart mChart;
    private int xAxisMin = 0;
    private int xAxisMax = 60;
    private int yAxisMin = 0;
    private int yAxisMax = 60;
    private int xAxisCount = 6;
    private int yAxisCount = 6;
    private boolean isInteger = false;

    public CustomLineView2(Context context) {
        this(context, null);
    }

    public CustomLineView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLineView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.item_chart_custom, this);
        mChartTitle = (TextView) view.findViewById(R.id.chart_tv_title);
        mChartUnit = (TextView) view.findViewById(R.id.chart_tv_unit);
        mChartLeft = (ImageView) view.findViewById(R.id.chart_iv_left_pic);
        mChart = (LineChart) view.findViewById(R.id.chart_line_custom);
    }

    public void initChartData() {

        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
//        mChart.animateX(2500);
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(20f);
        xAxis.setTextColor(Color.GREEN);
        xAxis.setAxisMinimum(xAxisMin);
        xAxis.setAxisMaximum(xAxisMax);
        xAxis.setLabelCount(xAxisCount, isInteger);
        xAxis.setAxisLineWidth(2f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextSize(20f);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMinimum(yAxisMin);
        leftAxis.setAxisMaximum(yAxisMax);
        leftAxis.setLabelCount(yAxisCount, isInteger);
        leftAxis.setAxisLineWidth(2f);

        LimitLine yLimitLine = new LimitLine(0, "");
        yLimitLine.setLineColor(Color.GREEN);
        leftAxis.addLimitLine(yLimitLine);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

//        List<ILineDataSet> sets = mChart.getData().getDataSets();
//
//        for (ILineDataSet iSet : sets) {
//
//            LineDataSet set = (LineDataSet) iSet;
//            set.setDrawValues(false);
//            set.setDrawCircles(false);
//            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        }
    }

    public void setData() {

        int count = xAxisMax;
        int range = 80;

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) - 30;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count - 1; i++) {
            float val = (float) (Math.random() * range) - 30;
            yVals2.add(new Entry(i, val));
        }

        LineDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);

//            set1.setDrawValues(false);
//            set1.setDrawCircles(false);
//            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//
//            set2.setDrawValues(false);
//            set2.setDrawCircles(false);
//            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(yVals1, "左");
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);

            set2 = new LineDataSet(yVals2, "右");
            set2.setColor(Color.RED);
            set2.setLineWidth(2f);

            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            mChart.setData(data);
        }
    }

    public void setChartData(ArrayList<Entry> yVals1, ArrayList<Entry> yVals2) {

        LineDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.invalidate();
        } else {
            set1 = new LineDataSet(yVals1, "左");
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);

            set2 = new LineDataSet(yVals2, "右");
            set2.setColor(Color.RED);
            set2.setLineWidth(2f);

            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            set2.setDrawValues(false);
            set2.setDrawCircles(false);
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            mChart.setData(data);
            mChart.notifyDataSetChanged(); // let the chart know it's data changed
            mChart.invalidate();
        }
    }

    public void setTitle(String title) {
        mChartTitle.setText(title);
    }

    public void setUnit(String unit) {
        mChartUnit.setText(unit);
    }

    public void setLeftPic(int leftPic) {
        mChartLeft.setImageDrawable(getResources().getDrawable(leftPic));
    }

    public void setxAxisMin(int xAxisMin) {
        this.xAxisMin = xAxisMin;
    }

    public void setxAxisMax(int xAxisMax) {
        this.xAxisMax = xAxisMax;
    }

    public void setyAxisMin(int yAxisMin) {
        this.yAxisMin = yAxisMin;
    }

    public void setyAxisMax(int yAxisMax) {
        this.yAxisMax = yAxisMax;
    }

    public void setxAxisCount(int xAxisCount) {
        this.xAxisCount = xAxisCount;
    }

    public void setyAxisCount(int yAxisCount) {
        this.yAxisCount = yAxisCount;

    }

}
