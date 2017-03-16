package com.lida.ocr;

import android.content.Context;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.lida.ocr.retrofit.HttpMethods;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import rx.Subscriber;
import static com.lida.ocr.util.Base64.encode;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class GetWordUtil {

    public static void getCarNumber(final Context context, String imgFilePath){
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
                Toast.makeText(context,resultBean.getRetData().get(0).getWord(),Toast.LENGTH_SHORT).show();
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
}
