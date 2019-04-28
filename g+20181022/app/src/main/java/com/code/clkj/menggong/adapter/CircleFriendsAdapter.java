package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.code.clkj.menggong.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class CircleFriendsAdapter extends RecyclerView.Adapter<CircleFriendsAdapter.ViewHolder> {

    public CircleFriendsAdapter(){

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_friends_item,parent,false);
        CircleFriendsAdapter.ViewHolder viewHolder = new CircleFriendsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 3;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView circle_friends_name;
        private TextView circle_friends_title;
//        private ImageView circle_friends_image_01;
//        private ImageView circle_friends_image_02;
//        private ImageView circle_friends_image_03;
        private TextView circle_friends_time;

        public ViewHolder(View itemView) {
            super(itemView);
            circle_friends_name = (TextView) itemView.findViewById(R.id.circle_friends_name);
            circle_friends_title = (TextView) itemView.findViewById(R.id.circle_friends_title);
            circle_friends_time = (TextView) itemView.findViewById(R.id.circle_friends_time);
//            circle_friends_image_01 = (ImageView) itemView.findViewById(R.id.circle_friends_image_01);
//            circle_friends_image_02 = (ImageView) itemView.findViewById(R.id.circle_friends_image_02);
//            circle_friends_image_03 = (ImageView) itemView.findViewById(R.id.circle_friends_image_03);

        }
    }
}
