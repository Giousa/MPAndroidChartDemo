package com.zmm.mpandroidchartdemo.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.zmm.mpandroidchartdemo.view.CustomLineView2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:51
 */

public class CustomThreeActivity extends AppCompatActivity {

    private static final String TAG = CustomThreeActivity.class.getSimpleName();
    ArrayList<Entry> yVals1;
    ArrayList<Entry> yVals2;

    ArrayList<Entry> yVals3;
    ArrayList<Entry> yVals4;

    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private static int count = 0;
    private boolean isPause = false;
    private static int delay = 200;  //1s
    private static int period = 200;  //1s
    private static int sleep = 1000;
    private static final int UPDATE_TEXTVIEW = 0;

    @InjectView(R.id.custom_line_view)
    CustomLineView2 mCustomLineView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXTVIEW:
                    updateTextView();
                    break;
                default:
                    break;
            }
        }
    };
    private Random mRandom;

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

        mCustomLineView.setTitle("三个标题");
        mCustomLineView.setUnit("配角");
        mCustomLineView.setLeftPic(R.drawable.chart_left_pic);
        mCustomLineView.setxAxisCount(6);
        mCustomLineView.setxAxisMin(0);
        mCustomLineView.setxAxisMax(60);
        mCustomLineView.setyAxisCount(5);
        mCustomLineView.setyAxisMin(-30);
        mCustomLineView.setyAxisMax(50);

        count = 0;
        yVals3  = new ArrayList<>();
        yVals4 = new ArrayList<>();
        mRandom = new Random();
        startTimer();
    }

    private void updateTextView(){
        Log.d(TAG,"updateTextView count: "+count);
        int y1 = mRandom.nextInt(50);
        int y2 = mRandom.nextInt(50);
        count++;
        if(count >= 50){
            return;
        }

        Entry entry = new Entry(count, y1);
        Entry entry2 = new Entry(count, y2);
        yVals3.add(entry);
        yVals4.add(entry2);


        mCustomLineView.setChartData(yVals3,yVals4);
        mCustomLineView.initChartData();

        for (int i = 0; i < yVals3.size(); i++) {
            Entry entry1 = yVals3.get(i);
            Log.d(TAG,"entry1 "+entry1.getY());
        }

        Log.d(TAG,"-------------");
    }
    private void startTimer(){
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i(TAG, "count: "+String.valueOf(count));
                    Log.d(TAG,"timer start");
                    sendMessage(UPDATE_TEXTVIEW);
                    do {
                        try {
                            Log.i(TAG, "sleep("+sleep+")...");
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                        }
                    } while (isPause);
//                    count ++;
                }
            };
        }
        if(mTimer != null && mTimerTask != null )
            mTimer.schedule(mTimerTask, delay, period);
    }


    private void stopTimer(){
        Log.d(TAG,"timer end");
        cancelTime();
        count = 0;
    }
    public void sendMessage(int id){
        if (mHandler != null) {
            Message message = Message.obtain(mHandler, id);
            mHandler.sendMessage(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTime();
    }
    private void cancelTime(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;
        }
        if(mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }
}
