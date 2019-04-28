package com.code.clkj.menggong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.bean.Room;

import java.util.List;


/**
 * 推荐直播间的适配器
 * Created by Administrator on 2017/9/5.
 */

public class NearLiveRoomAdapter extends RecyclerView.Adapter<NearLiveRoomAdapter.ViewHolder> {
    private List<Room> mRoom;  //存放直播间的集合

    public NearLiveRoomAdapter(List<Room> mRoom) {
        this.mRoom = mRoom;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_room_item,parent,false);//near_live_room_item
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mDistance.setText(mRoom.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return mRoom.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        private  ImageView mLiveRoomIcon;
        private  TextView mDistance;

        public ViewHolder(View itemView) {
            super(itemView);
            mLiveRoomIcon =  (ImageView) itemView.findViewById(R.id.mLiveRoomIcon);
            mDistance = (TextView) itemView.findViewById(R.id.mDistance);
        }
    }

}

