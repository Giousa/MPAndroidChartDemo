package com.zmm.mpandroidchartdemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zmm.mpandroidchartdemo.R;
import com.zmm.mpandroidchartdemo.view.CustomLineView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:51
 */

public class CustomOneActivity extends AppCompatActivity {

    @InjectView(R.id.custom_line_view)
    CustomLineView mCustomLineView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_one);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        mCustomLineView.setTitle("一个标题");
        mCustomLineView.setUnit("角度");
        mCustomLineView.setLeftPic(R.drawable.bg_view_left_pic);
        mCustomLineView.setxAxisCount(6);
        mCustomLineView.setxAxisMin(0);
        mCustomLineView.setxAxisMax(70);
        mCustomLineView.setyAxisCount(5);
        mCustomLineView.setyAxisMin(-40);
        mCustomLineView.setyAxisMax(60);
        mCustomLineView.setData();
        mCustomLineView.initChartData();
    }
}
