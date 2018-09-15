package com.bwie.kangxiaoling.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.kangxiaoling.R;
import com.bwie.kangxiaoling.entiti.CartBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by ll on 2018/9/8.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<CartBean.DataBean> cartlist;

    public CartAdapter(Context context, List<CartBean.DataBean> data) {
        this.context = context;
        this.cartlist = data;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
        final CartBean.DataBean bean = cartlist.get(position);
        holder.cart_check.setChecked(bean.isInsheck());
        for (int i = 0; i < bean.getList().size(); i++) {
            if (!bean.getList().get(i).isInsheck()) {
                holder.cart_check.setChecked(false);
                break;
            } else {
                holder.cart_check.setChecked(true);
            }
        }
        holder.cart_text.setText(bean.getSellerName());
        Cart_product_Adapter cart_product_adapter = new Cart_product_Adapter(context, bean.getList());
        holder.cart_produck_rev.setAdapter(cart_product_adapter);
        holder.cart_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cart_check.isChecked()) {
                    bean.setInsheck(true);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setInsheck(true);
                    }
                } else {
                    bean.setInsheck(false);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setInsheck(false);
                    }
                }
                notifyDataSetChanged();
                EventBus.getDefault().post("");
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public List<CartBean.DataBean> getCartList() {
        return cartlist;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cart_check;
        private TextView cart_text;
        private RecyclerView cart_produck_rev;

        public CartViewHolder(View itemView) {
            super(itemView);
            cart_check = (CheckBox) itemView.findViewById(R.id.cart_check);
            cart_text = (TextView) itemView.findViewById(R.id.cart_text);
            cart_produck_rev = (RecyclerView) itemView.findViewById(R.id.cart_produck_rev);
            cart_produck_rev.setLayoutManager(new LinearLayoutManager(context));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notify(String aa) {
        notifyDataSetChanged();
    }
}
