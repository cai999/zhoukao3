package com.bwie.kangxiaoling.present;


import com.bwie.kangxiaoling.contact.CartContract;
import com.bwie.kangxiaoling.entiti.CartBean;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public class CartPresenter extends CartContract.CartPresenter {

    @Override
    public void cart(HashMap<String, String> prams) {
        mModel.cart(prams).subscribe(new Consumer<CartBean>() {
            @Override
            public void accept(CartBean cartBean) throws Exception {
             mView.success(cartBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.fail("请求失败");
            }
        });
    }
}
