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
import com.zmm.mpandroidchartdemo.utils.TestUrl;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
                jsonParseUrl();
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


    private void jsonParseUrl() {
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
    }
    private void getData() {
        //创建网络请求对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建请求参数
        Request request = new Request.Builder().url(TestUrl.CHARTURL01).build();
        //创建发起网络请求对象
        Call call = okHttpClient.newCall(request);
        try {
            //发起网络请求
            Response response = call.execute();
            if(response.isSuccessful()){
                Log.d("网络连接成功","successful");
                Log.d("网络连接成功","服务器消息："+response.message());
                Log.d("网络连接成功","服务器消息："+response.toString());
                Log.d("网络连接成功","服务器消息："+response.body());
                byte[] bytes = response.body().bytes();
                String s = new String(bytes);
                Log.d(TAG,s);
                JSONObject jsonObject = JSON.parseObject(s);
                JSONArray line1 = jsonObject.getJSONArray("line1");
                Log.d("网络连接成功",line1.toString());
                
            }else {
                Log.d("网络连接失败","失败");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
