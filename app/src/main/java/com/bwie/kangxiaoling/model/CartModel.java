package com.bwie.kangxiaoling.model;

import android.content.Context;

import com.bwie.kangxiaoling.api.CartApi;
import com.bwie.kangxiaoling.commact.Commact;
import com.bwie.kangxiaoling.contact.CartContract;
import com.bwie.kangxiaoling.entiti.CartBean;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public class CartModel implements CartContract.CartModel {


    @Override
    public Observable<CartBean> cart(HashMap<String, String> prams) {
        return RetrofitUtils.getInstance().createApi(Commact.BASE_RELEASE_URL, CartApi.class)
                .cart(prams).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
