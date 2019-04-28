package com.code.clkj.menggong.adapter;

/**
 * Created by 92573 on 2018/1/15.
 */

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLookLive.PLVideoViewActivity;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryRoomIndex;
import com.nostra13.universalimageloader.core.ImageLoader;



/**
 * 推荐直播adapter
 */
public class FragHomeRecommendLiveAdapter extends ListBaseAdapter<RespQueryRoomIndex.ResultBean.ListRecommendBean> {
    public FragHomeRecommendLiveAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_hot_live_gv_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
      final   RespQueryRoomIndex.ResultBean.ListRecommendBean data = getDataList().get(position);
        ImageView roomImage = holder.getView(R.id.mRoomIcon);
        TextView liveNickName = holder.getView(R.id.mRoomTitle);
        TextView lookNumber = holder.getView(R.id.mUserCount);
        ImageView img_state = holder.getView(R.id.img_state);

        roomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PLVideoViewActivity.class);//跳转到观看直播页面
                i.putExtra("roomId",data.getRoomId());
                mContext. startActivity(i);
            }
        });



        if (data.getRoomImage()!= null) {
//            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(resultEntity.getImage()), mLiveHistoryIcon);
            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getRoomImage()),roomImage);
        }
        if (!TextUtils.isEmpty(data.getRoomName())) {
            liveNickName.setText(data.getRoomName());
        }
        if (!TextUtils.isEmpty(data.getRoomWatchNum())) {
            lookNumber.setText(data.getRoomWatchNum());
        }
        img_state.setVisibility(View.VISIBLE);
    }
}
