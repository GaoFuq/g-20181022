package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;

/**
 * Created by Administrator on 2017/9/13.
 */

public class PersonalAvatarAdapter extends RecyclerView.Adapter<PersonalAvatarAdapter.ViewHolder> {

    public PersonalAvatarAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_avatar_item,parent,false);
        PersonalAvatarAdapter.ViewHolder viewHolder = new PersonalAvatarAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView personal_name;
        private TextView personal_title;
        private ImageView personal_image_01;
        private ImageView personal_image_02;
        private ImageView personal_image_03;
        private TextView personal_time;

        public ViewHolder(View itemView) {
            super(itemView);
            personal_name = (TextView) itemView.findViewById(R.id.personal_name);
            personal_title = (TextView) itemView.findViewById(R.id.personal_title);
            personal_time = (TextView) itemView.findViewById(R.id.personal_time);
            personal_image_01 = (ImageView) itemView.findViewById(R.id.personal_image_01);
            personal_image_02 = (ImageView) itemView.findViewById(R.id.personal_image_02);
            personal_image_03 = (ImageView) itemView.findViewById(R.id.personal_image_03);

        }
    }
}
