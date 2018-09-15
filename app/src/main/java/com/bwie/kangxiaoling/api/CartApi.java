package com.bwie.kangxiaoling.api;


import com.bwie.kangxiaoling.entiti.CartBean;

import java.util.HashMap;

import io.reactivex.Observable;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ll on 2018/9/7.
 */

public interface CartApi {
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<CartBean> cart(@FieldMap HashMap<String, String> prams);
}
