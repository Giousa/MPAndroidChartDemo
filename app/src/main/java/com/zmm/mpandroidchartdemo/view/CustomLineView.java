package com.zmm.mpandroidchartdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/5
 * Time:下午5:41
 */

public class CustomLineView extends View {
    
    public CustomLineView(Context context) {
        this(context,null);
    }

    public CustomLineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }
}
