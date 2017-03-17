package com.lida.ocr;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.ocr.camera.RectCameraActivity;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class ActivityHome extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        findViewById(R.id.button_car).setOnClickListener(this);
        findViewById(R.id.button_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_car:
                startActivity(new Intent(ActivityHome.this, RectCameraActivity.class));
                break;
            case R.id.button_card:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.e(requestCode);
        LogUtils.e(permissions);
        LogUtils.e(grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            initView();
        }else{
            Toast.makeText(ActivityHome.this,"请开启相机或文件权限",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
