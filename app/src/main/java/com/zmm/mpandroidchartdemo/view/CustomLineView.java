package com.zmm.mpandroidchartdemo.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.zmm.mpandroidchartdemo.R;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:41a
 */

public class CustomLineView extends RelativeLayout {

    private TextView mChartTitle;
    private TextView mChartUnit;
    private ImageView mChartLeft;
    private LineChart mChart;

    public CustomLineView(Context context) {
        this(context,null);
    }

    public CustomLineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initData();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.custom_lineview, this);
        mChartTitle = (TextView) view.findViewById(R.id.chart_tv_title);
        mChartUnit = (TextView) view.findViewById(R.id.chart_tv_unit);
        mChartLeft = (ImageView) view.findViewById(R.id.chart_iv_left_pic);
        mChart = (LineChart) view.findViewById(R.id.chart_line_custom);
    }

    private void initData() {

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
}
