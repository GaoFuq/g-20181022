package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.code.clkj.menggong.R;

/**
 * Created by Administrator on 2017/9/13.
 */

public class NearbyMessageAdapter extends RecyclerView.Adapter<NearbyMessageAdapter.ViewHolder> {

    public NearbyMessageAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_message_item,parent,false);
        NearbyMessageAdapter.ViewHolder viewHolder = new NearbyMessageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mmessage_hend_img;

        public ViewHolder(View itemView) {
            super(itemView);
            mmessage_hend_img = (ImageView) itemView.findViewById(R.id.mmessage_hend_img);

        }
    }
}
