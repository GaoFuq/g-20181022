package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.util.TempDataUtils;


/**
 * 附近直播间的适配器
 * Created by Administrator on 2017/9/5.
 */

public class TuiguangAdapter extends ListBaseAdapter<RespNearLive.ResultBean.SourceBean> {

    private Context mContext;

    public TuiguangAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.live_room_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespNearLive.ResultBean.SourceBean resultbean = getDataList().get(position);
        ImageView mRoomIcon = holder.getView(R.id.mRoomIcon);
        ImageView img_line = holder.getView(R.id.img_line);
        TextView mUserCount = holder.getView(R.id.mUserCount);

        if (resultbean.getRoomStatus().equals("0")) {
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
        if (resultbean.getMuseimage() != null) {
            Glide.with(mContext).load(BaseUriConfig.makeImageUrl(resultbean.getMuseimage()))
                    .into(mRoomIcon);
        }

    }
}

