package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLookLive.PLVideoViewActivity;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryRoomMoreList;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by 92573 on 2018/1/13.
 */

/**
 * 更多直播adapter
 */
public class MoreLiveAdapter extends ListBaseAdapter<RespQueryRoomMoreList.ResultBean.SourceBean> {
    public MoreLiveAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_hot_live_gv_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
    final     RespQueryRoomMoreList.ResultBean.SourceBean data = getDataList().get(position);
        ImageView mRoomIcon = holder.getView(R.id.mRoomIcon);
        TextView mRoomTitle = holder.getView(R.id.mRoomTitle);
        TextView mUserCount = holder.getView(R.id.mUserCount);
        if (data.getRoomImage() != null) {
            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getRoomImage()), mRoomIcon);
        }


        mRoomIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PLVideoViewActivity.class);//跳转到观看直播页面
                i.putExtra("roomId",data.getRoomId());
                mContext. startActivity(i);
            }
        });


//        if (!TextUtils.isEmpty(data.getRoomImage())) {
//            Glide.with(get)
//                    .load(BaseUriConfig.makeImageUrl(data.getRoomImage()))
//                    .placeholder(R.mipmap.zye_10)
//                    .into(head_img_item);
//        }
        if (!TextUtils.isEmpty(data.getRoomName())) {
            mRoomTitle.setText(data.getRoomName());
        }
        if (!TextUtils.isEmpty(data.getRoomWatchNum())) {
            mUserCount.setText(data.getRoomWatchNum());
        }

    }

}
