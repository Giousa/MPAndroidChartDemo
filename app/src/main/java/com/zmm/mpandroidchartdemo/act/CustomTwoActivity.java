package com.zmm.mpandroidchartdemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.data.Entry;
import com.zmm.mpandroidchartdemo.R;
import com.zmm.mpandroidchartdemo.utils.ChartJson;
import com.zmm.mpandroidchartdemo.view.CustomLineView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:51
 */

public class CustomTwoActivity extends AppCompatActivity {

    private static final String TAG = CustomTwoActivity.class.getSimpleName();
    ArrayList<Entry> yVals1;
    ArrayList<Entry> yVals2;

    @InjectView(R.id.custom_line_view)
    CustomLineView mCustomLineView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_one);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initData() {
        JSONObject jsonObject = JSON.parseObject(ChartJson.CHARTJSON02);
        JSONArray line1 = jsonObject.getJSONArray("line1");
        Log.d(TAG,line1.toString());
        JSONArray line2 = jsonObject.getJSONArray("line2");
        Log.d(TAG,line2.toString());

        yVals1  = new ArrayList<>();
        yVals2 = new ArrayList<>();

        for (int i = 0; i < line1.size(); i++) {
            Log.d(TAG,line1.get(i)+"");
            yVals1.add(new Entry(i, (int) line1.get(i)));
        }

        for (int i = 0; i < line2.size(); i++) {
            Log.d(TAG,line1.get(i)+"");
            yVals2.add(new Entry(i, (int) (line2.get(i))));
        }
    }

    private void initView() {
        mCustomLineView.setTitle("二个标题");
        mCustomLineView.setUnit("翻角");
        mCustomLineView.setLeftPic(R.drawable.chart_left_pic);
        mCustomLineView.setxAxisCount(6);
        mCustomLineView.setxAxisMin(0);
        mCustomLineView.setxAxisMax(60);
        mCustomLineView.setyAxisCount(5);
        mCustomLineView.setyAxisMin(-30);
        mCustomLineView.setyAxisMax(50);
        mCustomLineView.setChartData(yVals1,yVals2);
        mCustomLineView.initChartData();
    }
}
