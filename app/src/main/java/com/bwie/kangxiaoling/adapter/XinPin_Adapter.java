package com.bwie.kangxiaoling.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.kangxiaoling.R;
import com.bwie.kangxiaoling.entiti.XinpinEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by ll on 2018/9/15.
 */

public class XinPin_Adapter extends RecyclerView.Adapter<XinPin_Adapter.XinPin_HolderView> {
    private Context context;
    private XinpinEntity xinpinEntity;
    private final List<XinpinEntity.DataBean> data;

    public XinPin_Adapter(Context context, XinpinEntity xinpinEntity) {
        this.context = context;
        this.xinpinEntity = xinpinEntity;
        data = xinpinEntity.getData();
        for (int i = 0; i < data.size(); i++) {

        }

    }

    @Override
    public XinPin_Adapter.XinPin_HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.xinpin_adapter_layout, parent, false);
        return new XinPin_HolderView(view);
    }

    @Override
    public void onBindViewHolder(XinPin_Adapter.XinPin_HolderView holder, int position) {
        holder.xinpin_text.setText(data.get(position).getTitle());
        Uri uri = Uri.parse(data.get(position).getImages().split("\\|")[0]);
        holder.xinpin_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class XinPin_HolderView extends RecyclerView.ViewHolder {
        private SimpleDraweeView xinpin_img;
        private TextView xinpin_text;

        public XinPin_HolderView(View itemView) {
            super(itemView);
            xinpin_img = (SimpleDraweeView) itemView.findViewById(R.id.xinpin_img);
            xinpin_text = (TextView) itemView.findViewById(R.id.xinpin_text);
        }
    }
}
