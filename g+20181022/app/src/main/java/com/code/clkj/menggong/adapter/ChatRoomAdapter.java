package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespCahtRoom;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.util.TempDataUtils;


/**
 * 附近直播间的适配器
 * Created by Administrator on 2017/9/5.
 */

public class ChatRoomAdapter extends ListBaseAdapter<RespCahtRoom.ResultEntity.SourceEntity> {

    private Context mContext;

    public ChatRoomAdapter(Context context) {
        super(context);
        mContext = context;
    }
/*    chat_room_item*/
    @Override
    public int getLayoutId() {
        return R.layout.friends_item_one;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespCahtRoom.ResultEntity.SourceEntity resultbean = getDataList().get(position);
        TextView mRoomTitle = holder.getView(R.id.mRoomTitle);
        TextView title = holder.getView(R.id.title);
        TextView liaotian = holder.getView(R.id.liaotian);
        TextView friends_item_address_tx = holder.getView(R.id.friends_item_address_tx);
        mRoomTitle.setText(resultbean.getRoomName());
        title.setText(resultbean.getJuli()+"m");
        liaotian.setText(resultbean.getTotal());
        friends_item_address_tx.setText(resultbean.getComment());
      //  mRoomTitle.setText(resultbean.get);
        /*if (resultbean.getImg() != null) {
            Glide.with(mContext).load(BaseUriConfig.makeImageUrl(resultbean.getImg()))
                    .into(mRoomIcon);
        }*/
       /* if (resultbean.getRoomStatus().equals("0")) {
            img_line.setVisibility(View.GONE);
        } else {
            img_line.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(resultbean.getDistance())) {
            if (TempDataUtils.string2Double(resultbean.getDistance())<1000){
                mUserCount.setText(TempDataUtils.string2Double(resultbean.getDistance())+"米以内");
            }else{
                mUserCount.setText(TempDataUtils.string2Double(resultbean.getDistance())/1000+"KM");
            }
        }
       */

    }
}

