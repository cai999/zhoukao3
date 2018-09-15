package com.bwie.kangxiaoling.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.kangxiaoling.MyAdder;
import com.bwie.kangxiaoling.R;
import com.bwie.kangxiaoling.entiti.CartBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by ll on 2018/9/8.
 */

class Cart_product_Adapter extends RecyclerView.Adapter<Cart_product_Adapter.Cart_productViewHolder>{
    private Context context;
    private List<CartBean.DataBean.ListBean> list;

    public Cart_product_Adapter(Context context, List<CartBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Cart_product_Adapter.Cart_productViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_product_layout, parent, false);
        return new Cart_productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Cart_product_Adapter.Cart_productViewHolder holder, int position) {
        final CartBean.DataBean.ListBean bean = list.get(position);
        holder.cart_produck_check.setChecked(bean.isInsheck());
        holder.cart_produck_text.setText(bean.getTitle());
        holder.cart_produck_price.setText("优惠价:¥"+bean.getBargainPrice());
        Uri uri = Uri.parse(bean.getImages().split("\\|")[0]);
        holder.cart_produck_img.setImageURI(uri);
        holder.myadder.getnumed(bean.getEdnum());
        holder.myadder.setNumLisener(new MyAdder.NumLisener() {
            @Override
            public void getNum(int num) {
                bean.setEdnum(num);
                EventBus.getDefault().post("");
            }
        });
        holder.cart_produck_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(holder.cart_produck_check.isChecked()){
                   bean.setInsheck(true);
               }else{
                   bean.setInsheck(false);
               }
                EventBus.getDefault().post("");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Cart_productViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cart_produck_check;
        private SimpleDraweeView cart_produck_img;
        private TextView cart_produck_text;
        private TextView cart_produck_price;
        private MyAdder myadder;

        public Cart_productViewHolder(View itemView) {
            super(itemView);
            cart_produck_check = (CheckBox) itemView.findViewById(R.id.cart_produck_check);
            cart_produck_img = (SimpleDraweeView) itemView.findViewById(R.id.cart_produck_img);
            cart_produck_text = (TextView) itemView.findViewById(R.id.cart_produck_text);
            cart_produck_price = (TextView) itemView.findViewById(R.id.cart_produck_price);
           myadder = (MyAdder) itemView.findViewById(R.id.myadder);
        }
    }
}