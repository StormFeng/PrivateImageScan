package com.lida.ocr.retrofit;

import com.lida.ocr.ResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public interface HttpService {


    @FormUrlEncoded
    @POST("idlocrpaid")
    Observable<ResultBean> getResult(@Header("apikey") String apikey, @FieldMap Map<String, String> options);
}
