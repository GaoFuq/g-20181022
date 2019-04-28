package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLookLive.PLVideoViewActivity;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryRoomIndex;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 92573 on 2018/1/6.
 */

/**
 * 首页--直播新星adapter
 */
public class FragHomeListNewAdapter extends ListBaseAdapter<RespQueryRoomIndex.ResultBean.ListNewBean> {
    private Context mContext;
    public FragHomeListNewAdapter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_new_star_live_gv_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
      final   RespQueryRoomIndex.ResultBean.ListNewBean data = getDataList().get(position);
        SimpleDraweeView head_img_item = holder.getView(R.id.head_img_item);
        TextView nickname_tv_item = holder.getView(R.id.nickname_tv_item);
        TextView watch_num_tv_item = holder.getView(R.id.watch_num_tv_item);
        LinearLayout ly = holder.getView(R.id.ly);


        ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent    i = new Intent(mContext, PLVideoViewActivity.class);//跳转到观看直播页面
                i.putExtra("roomId",data.getRoomId());
                mContext. startActivity(i);
            }
        });

        if (data.getRoomImage() != null) {
//            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getRoomImage()), head_img_item);
            head_img_item.setImageURI(BaseUriConfig.makeImageUrl(data.getRoomImage()));
        }
//        if (!TextUtils.isEmpty(data.getRoomImage())) {
//            Glide.with(get)
//                    .load(BaseUriConfig.makeImageUrl(data.getRoomImage()))
//                    .placeholder(R.mipmap.zye_10)
//                    .into(head_img_item);
//        }
        if (!TextUtils.isEmpty(data.getRoomName())) {
            nickname_tv_item.setText(data.getRoomName());
        }
        if (!TextUtils.isEmpty(data.getRoomWatchNum())) {
            watch_num_tv_item.setText(data.getRoomWatchNum());
        }

    }
}
