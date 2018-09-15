package com.bwie.kangxiaoling.model;

import com.bwie.kangxiaoling.api.XinPin_Api;
import com.bwie.kangxiaoling.commact.Commact;
import com.bwie.kangxiaoling.contact.Xinpin_Contact;
import com.bwie.kangxiaoling.entiti.XinpinEntity;
import com.bwie.kangxiaoling.utils.RetrofitUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ll on 2018/9/15.
 */

public class Xinpin_Model implements Xinpin_Contact.Xinpin_Model {
    @Override
    public Observable<XinpinEntity> Show(HashMap<String, String> prams) {
        return RetrofitUtils.getInstance().createApi(Commact.BASE_RELEASE_URL, XinPin_Api.class)
                .Show(prams).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }
}
