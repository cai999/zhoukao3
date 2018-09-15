package com.bwie.kangxiaoling.contact;

import com.bwie.kangxiaoling.entiti.XinpinEntity;
import com.bwie.kangxiaoling.model.Xinpin_Model;

import java.util.HashMap;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;
import lianxibase1.bwie.com.base.base.mvp.IBaseView;

/**
 * Created by ll on 2018/9/15.
 */

public interface Xinpin_Contact {
    abstract class Xinpin_Present extends BasePresenter<Xinpin_Model,Xinpin_View> {
        public abstract void Show(HashMap<String,String> prams);

        @Override
        public Xinpin_Model getmModel() {
            return new com.bwie.kangxiaoling.model.Xinpin_Model();
        }
    }

    interface Xinpin_View extends IBaseView{
        void fail(String msg);

        void success(XinpinEntity xinpinEntity);
    }



    interface Xinpin_Model extends IBaseModel {
        Observable<XinpinEntity> Show(HashMap<String, String> prams);
    }
}
