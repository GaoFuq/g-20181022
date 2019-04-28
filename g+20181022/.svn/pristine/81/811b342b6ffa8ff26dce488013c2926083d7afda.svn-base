package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MessageCenterAdapter extends RecyclerView.Adapter<MessageCenterAdapter.ViewHolder> {

    public MessageCenterAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_center_item,parent,false);
        MessageCenterAdapter.ViewHolder viewHolder = new MessageCenterAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{


        private TextView message_text;
        private ImageView message_image;

        public ViewHolder(View itemView) {
            super(itemView);
            message_text= (TextView) itemView.findViewById(R.id.message_text);
            message_image = (ImageView) itemView.findViewById(R.id.message_image);
        }
    }
}
