package com.bwie.kangxiaoling.api;


import com.bwie.kangxiaoling.entiti.XinpinEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by ll on 2018/9/15.
 */

public interface XinPin_Api {
    @POST("product/searchProducts")
    @FormUrlEncoded
    Observable<XinpinEntity> Show(@FieldMap HashMap<String,String> prams);

}
