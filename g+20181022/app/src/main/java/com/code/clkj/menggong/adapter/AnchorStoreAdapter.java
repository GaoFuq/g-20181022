package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class AnchorStoreAdapter extends RecyclerView.Adapter<AnchorStoreAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anchor_store_item,parent,false);
        AnchorStoreAdapter.ViewHolder viewHolder = new AnchorStoreAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 5;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView store_goods_picture;
        private TextView store_goods_name;
        private TextView store_goods_price;
        private TextView store_goods_num;

        public ViewHolder(View itemView) {
            super(itemView);
            store_goods_picture = (ImageView) itemView.findViewById(R.id.store_goods_picture);
            store_goods_name = (TextView) itemView.findViewById(R.id.store_goods_name);
            store_goods_price = (TextView) itemView.findViewById(R.id.store_goods_price);
            store_goods_num = (TextView) itemView.findViewById(R.id.store_goods_num);

        }
    }
}
