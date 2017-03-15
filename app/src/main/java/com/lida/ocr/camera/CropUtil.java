package com.lida.ocr.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CropUtil {

    public static String saveImg(Activity activity, View v){
        Bitmap bitmap;
        String path =  Environment.getExternalStorageDirectory().toString()  + "/preview.png";
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        bitmap = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        LogUtils.e(location);
        try {
            bitmap = Bitmap.createBitmap(bitmap, location[0], location[1], v.getWidth(), v.getHeight());
            FileOutputStream fout = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.e("生成预览图片失败：" + e);
        } catch (IllegalArgumentException e) {
            LogUtils.e("width is <= 0, or height is <= 0");
        } finally {
            // 清理缓存
            view.destroyDrawingCache();
        }
        return null;
    }
}
