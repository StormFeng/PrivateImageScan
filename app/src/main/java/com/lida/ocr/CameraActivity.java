package com.lida.ocr;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.apkfuns.logutils.LogUtils;
import com.lida.ocr.camera.CropUtil;
import com.lida.ocr.camera.HolderView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CameraActivity extends Activity {

    HolderView holderView;
    View target;
    Activity activity;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String[]{Manifest.permission.CAMERA},1001);
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},1002);
        setContentView(R.layout.camera);
        activity = this;
        initView();
    }

    private void initView() {
        holderView = (HolderView) findViewById(R.id.holderView);
        holderView.setSize(500,200);
        target = holderView.getTarget();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LogUtils.e(CropUtil.saveImg(activity,target));
            }
        },2000);
    }
}
