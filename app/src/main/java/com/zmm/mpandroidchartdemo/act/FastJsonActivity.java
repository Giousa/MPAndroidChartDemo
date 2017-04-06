package com.zmm.mpandroidchartdemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zmm.mpandroidchartdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/6
 * Time:上午10:40
 */

public class FastJsonActivity extends AppCompatActivity {

    private static final String TAG = FastJsonActivity.class.getSimpleName();

    String jsonUrl = "{\n" +
            "    \"line1\": [\n" +
            "        20,\n" +
            "        23,\n" +
            "        56,\n" +
            "        8,\n" +
            "        56,\n" +
            "        23,\n" +
            "        89,\n" +
            "        90,\n" +
            "        12,\n" +
            "        45,\n" +
            "        89,\n" +
            "        1,\n" +
            "        4,\n" +
            "        8,\n" +
            "        90,\n" +
            "        45,\n" +
            "        23,\n" +
            "        67,\n" +
            "        90,\n" +
            "        23,\n" +
            "        45,\n" +
            "        67,\n" +
            "        89,\n" +
            "        34,\n" +
            "        67,\n" +
            "        89,\n" +
            "        23,\n" +
            "        56,\n" +
            "        89,\n" +
            "        12,\n" +
            "        34,\n" +
            "        45,\n" +
            "        67,\n" +
            "        89,\n" +
            "        34,\n" +
            "        23,\n" +
            "        56,\n" +
            "        78,\n" +
            "        23,\n" +
            "        56,\n" +
            "        34,\n" +
            "        45,\n" +
            "        67,\n" +
            "        78,\n" +
            "        13,\n" +
            "        45,\n" +
            "        75,\n" +
            "        23,\n" +
            "        12,\n" +
            "        45,\n" +
            "        89,\n" +
            "        45,\n" +
            "        23,\n" +
            "        56,\n" +
            "        78,\n" +
            "        9\n" +
            "    ],\n" +
            "    \"line2\": [\n" +
            "        12,\n" +
            "        34,\n" +
            "        45,\n" +
            "        67,\n" +
            "        86,\n" +
            "        45,\n" +
            "        23,\n" +
            "        12,\n" +
            "        34,\n" +
            "        23,\n" +
            "        56,\n" +
            "        78,\n" +
            "        45,\n" +
            "        34,\n" +
            "        12,\n" +
            "        34,\n" +
            "        7,\n" +
            "        4,\n" +
            "        12,\n" +
            "        9,\n" +
            "        2,\n" +
            "        45,\n" +
            "        67,\n" +
            "        23,\n" +
            "        12,\n" +
            "        77,\n" +
            "        23,\n" +
            "        2,\n" +
            "        34,\n" +
            "        56,\n" +
            "        34,\n" +
            "        12,\n" +
            "        34,\n" +
            "        45,\n" +
            "        67,\n" +
            "        21,\n" +
            "        34,\n" +
            "        45,\n" +
            "        56,\n" +
            "        34,\n" +
            "        45,\n" +
            "        12,\n" +
            "        34,\n" +
            "        8,\n" +
            "        3,\n" +
            "        23,\n" +
            "        45\n" +
            "    ]\n" +
            "}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                jsonParse();
                break;
            case R.id.btn_02:
                break;
            case R.id.btn_03:
                break;
        }
    }

    private void jsonParse() {
        JSONObject jsonObject = JSON.parseObject(jsonUrl);
        JSONArray line1 = jsonObject.getJSONArray("line1");
        Log.d(TAG,line1.toString());
        JSONArray line2 = jsonObject.getJSONArray("line2");
        Log.d(TAG,line2.toString());

        for (int i = 0; i < line1.size(); i++) {
            Log.d(TAG,line1.get(i)+"");
        }
    }
}
