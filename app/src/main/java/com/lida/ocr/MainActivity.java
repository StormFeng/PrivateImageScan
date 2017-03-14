package com.lida.ocr;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.ocr.retrofit.HttpMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Subscriber;

import static com.lida.ocr.util.Base64.encode;

public class MainActivity extends Activity implements View.OnClickListener {

    String imgFilePath = Environment.getExternalStorageDirectory().toString()
            +"/camera.jpg";
    EditText editText;
    String path = Environment.getExternalStorageDirectory().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.et);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},2001);
            }
        }
    }

    private void getResult(){
        File file = new File(imgFilePath);
        LogUtils.e(imgFilePath);
        if(file.exists()){
            LogUtils.e("exists");
        }else{
            LogUtils.e("not exists");
        }
        Subscriber<ResultBean> subscriber=new Subscriber<ResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
            }

            @Override
            public void onNext(ResultBean resultBean) {
                LogUtils.e(resultBean);
                Toast.makeText(MainActivity.this,resultBean.getRetData().get(0).getWord(),Toast.LENGTH_SHORT).show();
                editText.setText(resultBean.getRetData().get(0).getWord().trim());
            }
        };
        HttpMethods.getInstance().getResult(subscriber,getImgStr(imgFilePath));
    }


    public static String getImgStr(String imgFile){
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encode(data));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1001);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
        }else{
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            LogUtils.e("RESULT_OK");
            String name = "/camera.jpg";
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            FileOutputStream b = null;
            String fileName = path + name;
            LogUtils.e(fileName);
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    b.flush();
                    b.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            getResult();
        }
    }
}
