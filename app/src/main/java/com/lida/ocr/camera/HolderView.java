package com.lida.ocr.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.lida.ocr.R;

/**
 * Created by Administrator on 2017/3/15.
 */

public class HolderView extends LinearLayout {

    private LinearLayout llTarget;
    private View vTarget;

    public HolderView(Context context) {
        this(context, null);
    }

    public HolderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.holder,this,true);
        llTarget = (LinearLayout) findViewById(R.id.ll_target);
        vTarget = findViewById(R.id.v_target);
    }

    public void setSize(int width, int height){
        int w = llTarget.getLayoutParams().width;
        llTarget.setLayoutParams(new LinearLayout.LayoutParams(w,height));
        vTarget.setLayoutParams(new LinearLayout.LayoutParams(width,height));
    }

    public View getTarget(){
       return vTarget;
    }
}
