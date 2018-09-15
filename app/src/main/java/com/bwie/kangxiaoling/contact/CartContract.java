package com.bwie.kangxiaoling.contact;


import com.bwie.kangxiaoling.entiti.CartBean;


import java.util.HashMap;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;
import lianxibase1.bwie.com.base.base.mvp.IBaseView;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public interface CartContract {
     abstract class CartPresenter extends BasePresenter<CartModel,CartView> {
         public abstract void cart(HashMap<String,String> prams);

         @Override
         public CartModel getmModel() {
             return new com.bwie.kangxiaoling.model.CartModel();
         }
     }

    /**
     * model层接口
     */
    interface CartModel extends IBaseModel {
        Observable<CartBean> cart(HashMap<String, String> prams);
    }

    /**
     * view层接口
     */
    interface CartView extends IBaseView {
        void success(CartBean cartBean);
        void fail(String msg);
    }
}
