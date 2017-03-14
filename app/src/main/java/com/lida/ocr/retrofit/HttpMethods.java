package com.lida.ocr.retrofit;
import com.apkfuns.logutils.LogUtils;
import com.lida.ocr.ResultBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class HttpMethods {

    public static final String BASE_URL = "http://apis.baidu.com/idl_baidu/baiduocrpay/";
    private static int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private HttpService httpService;

    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new LogInterceptor());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    public void getResult(Subscriber<ResultBean> subscriber, String image){
        Map<String,String> map=new HashMap<>();
        map.put("fromdevice","android");
        map.put("clientip","10.10.10.0");
        map.put("detecttype","LocateRecognize");
        map.put("image",image);
        map.put("imagetype","1");
        LogUtils.e(map);
        httpService.getResult("4ed43314cdba4fab72122331273d9fcb",map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
