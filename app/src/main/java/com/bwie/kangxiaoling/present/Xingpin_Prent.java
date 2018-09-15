package com.bwie.kangxiaoling.present;
import com.bwie.kangxiaoling.contact.Xinpin_Contact;
import com.bwie.kangxiaoling.entiti.XinpinEntity;
import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by ll on 2018/9/15.
 */

public class Xingpin_Prent extends Xinpin_Contact.Xinpin_Present {

    @Override
    public void Show(HashMap<String, String> prams) {
        mModel.Show(prams).subscribe(new Consumer<XinpinEntity>() {
            @Override
            public void accept(XinpinEntity xinpinEntity) throws Exception {
                mView.success(xinpinEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.fail("请求失败");
            }
        });
    }
}
