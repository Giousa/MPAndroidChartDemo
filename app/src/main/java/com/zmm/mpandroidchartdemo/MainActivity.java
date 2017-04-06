package com.zmm.mpandroidchartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zmm.mpandroidchartdemo.act.CustomOneActivity;
import com.zmm.mpandroidchartdemo.act.CustomTwoActivity;
import com.zmm.mpandroidchartdemo.act.FastJsonActivity;
import com.zmm.mpandroidchartdemo.act.MPOneActivity;
import com.zmm.mpandroidchartdemo.act.MPTwoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04,R.id.btn_05})
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.btn_01:
                intent = new Intent(MainActivity.this,MPOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_02:
                intent = new Intent(MainActivity.this,MPTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_03:
                intent = new Intent(MainActivity.this,CustomOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_04:
                intent = new Intent(MainActivity.this, CustomTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_05:
                intent = new Intent(MainActivity.this,FastJsonActivity.class);
                startActivity(intent);
                break;
        }
    }

}
