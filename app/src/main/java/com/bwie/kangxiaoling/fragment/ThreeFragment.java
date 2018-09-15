package com.bwie.kangxiaoling.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.kangxiaoling.R;
import com.bwie.kangxiaoling.adapter.CartAdapter;
import com.bwie.kangxiaoling.contact.CartContract;
import com.bwie.kangxiaoling.entiti.CartBean;
import com.bwie.kangxiaoling.present.CartPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpFragment;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;

public class ThreeFragment extends BaseMvpFragment<CartContract.CartModel, CartContract.CartPresenter> implements CartContract.CartView {
    @BindView(R.id.chexk)
    CheckBox check;
    @BindView(R.id.cart_jie)
    Button cart_jie;
    @BindView(R.id.cart_price)
    TextView cart_price;
    @BindView(R.id.cart_xrv)
    XRecyclerView cart_xrv;
    private List<CartBean.DataBean> data;
    private CartAdapter cartAdapter;

    @Override
    public BasePresenter initPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        cart_xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cart_xrv.setLoadingMoreEnabled(true);
        cart_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                cartAdapter.notifyDataSetChanged();
                cart_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                cartAdapter.notifyDataSetChanged();
                cart_xrv.loadMoreComplete();
            }
        });
        HashMap<String, String> prams = new HashMap<>();
        prams.put("uid", "71");
        presenter.cart(prams);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setInsheck(true);
                        for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                            data.get(i).getList().get(i1).setInsheck(true);
                        }
                    }
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setInsheck(false);
                        for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                            data.get(i).getList().get(i1).setInsheck(false);
                        }
                    }
                }
                cartAdapter.notifyDataSetChanged();
                jisuanzongjia();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void success(CartBean cartBean) {
        data = cartBean.getData();
        cartAdapter = new CartAdapter(getActivity(), data);
        cart_xrv.setAdapter(cartAdapter);
    }

    @Override
    public void fail(String msg) {

    }

    @Override
    protected int bindLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.threefragment_layout;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notify(String aa) {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartAdapter != null) {
            for (int i = 0; i < cartAdapter.getCartList().size(); i++) {

                stringBuilder.append(cartAdapter.getCartList().get(i).isInsheck());

                for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {

                    stringBuilder.append(cartAdapter.getCartList().get(i).getList().get(i1).isInsheck());
                }
            }
        }
        if (stringBuilder.toString().contains("false")) {
            check.setChecked(false);
        } else {
            check.setChecked(true);
        }
        jisuanzongjia();
    }

    private void jisuanzongjia() {
        double zongjia = 0;
        for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {
                if (cartAdapter.getCartList().get(i).getList().get(i1).isInsheck()) {
                    CartBean.DataBean.ListBean listBean = cartAdapter.getCartList().get(i).getList().get(i1);
                    zongjia += Double.parseDouble(listBean.getBargainPrice()) * listBean.getEdnum();
                }
            }
            cart_price.setText("总价:￥" + zongjia);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
