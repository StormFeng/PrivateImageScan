package com.lida.ocr.retrofit;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.net.URL;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        String str = request.url().toString();
        String method=request.method();
        URL url=new URL(str);
        LogUtils.e(url);
        LogUtils.e("请求方式:"+method);
        LogUtils.e("请求地址:"+url);
        LogUtils.e("请求参数:"+url.getQuery());
        Response response=chain.proceed(request);
        return response;
    }
}
