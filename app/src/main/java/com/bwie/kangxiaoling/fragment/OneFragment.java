package com.bwie.kangxiaoling.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.kangxiaoling.R;
import com.bwie.kangxiaoling.adapter.XinPin_Adapter;
import com.bwie.kangxiaoling.contact.Xinpin_Contact;
import com.bwie.kangxiaoling.entiti.XinpinEntity;
import com.bwie.kangxiaoling.present.Xingpin_Prent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpFragment;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;

/**
 * Created by ll on 2018/9/15.
 */

public class OneFragment extends BaseMvpFragment<Xinpin_Contact.Xinpin_Model,Xinpin_Contact.Xinpin_Present> implements Xinpin_Contact.Xinpin_View{
    @BindView(R.id.xinpin_xre)
    XRecyclerView xinpin_xre;
    private int page = 1;
    @Override
    protected int bindLayoutId() {
        return R.layout.onefragment_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        xinpin_xre.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HashMap<String,String> prams = new HashMap<>();
        prams.put("keywords","笔记本");
        prams.put("page",page+"");
        presenter.Show(prams);
    }
    @Override
    public void success(XinpinEntity xinpinEntity) {
        Log.i("ccc",xinpinEntity.toString());
        XinPin_Adapter xinPin_adapter = new XinPin_Adapter(getActivity(), xinpinEntity);
       xinpin_xre.setAdapter(xinPin_adapter);
    }

    @Override
    public BasePresenter initPresenter() {
        return new Xingpin_Prent();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void fail(String msg) {
        Log.i("ccc",msg);
    }
}
