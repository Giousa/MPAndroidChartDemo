package com.zmm.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午2:27
 */

public class MPOneActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private LineChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_one);
        initView();

    }

    private void initView() {

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);

        // 控制是否显示右下角文字描述
        mChart.getDescription().setEnabled(false);

        //禁止触摸滑动
        mChart.setTouchEnabled(false);

        //背景颜色
        mChart.setBackgroundColor(Color.WHITE);

        // 添加数据
        setData(60, 80);

        //控制绘制时长
        mChart.animateX(2500);

        //是否绘制图例
        Legend legend = mChart.getLegend();
//        legend.setEnabled(false);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);

        //设置X轴
        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        xAxis.setTextColor(Color.GREEN);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(60);
//        xAxis.setLabelCount(10);
        xAxis.setLabelCount(10,false);


        //设置Y轴
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(Color.RED);
        leftAxis.setAxisMinimum(-40);
        leftAxis.setAxisMaximum(60);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setLabelCount(6, true);
        //添加限制线
        LimitLine yLimitLine = new LimitLine(0,"");
        yLimitLine.setLineColor(Color.RED);
//        yLimitLine.setTextColor(Color.RED);
        leftAxis.addLimitLine(yLimitLine);

        //右侧Y轴是一直存在的,所以要通过这个方法隐去
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        //---------------------------
        List<ILineDataSet> sets = mChart.getData().getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setDrawValues(false);
            set.setDrawCircles(false);
//            set.setMode(LineDataSet.Mode.LINEAR);//直线
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//弧线
        }
        //---------------------------
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range)-30;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int i = 0; i < count-1; i++) {
            float val = (float) (Math.random() * range)-30;
            yVals2.add(new Entry(i, val));
        }

        LineDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);

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

            // set data
            mChart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
