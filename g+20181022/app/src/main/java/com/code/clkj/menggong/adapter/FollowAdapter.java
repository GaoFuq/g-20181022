package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespMyAttention;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2017/9/8.
 */

public class FollowAdapter extends ListBaseAdapter<RespMyAttention.ResultBean.SourceBean> {

    private OnCallBackListenr mOnCallBackListenr;

    private Context mContext;
    public FollowAdapter(Context context) {
        super(context);
        mContext = context;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_follow;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespMyAttention.ResultBean.SourceBean resultbean = getDataList().get(position);
        SimpleDraweeView mHeadimg = holder.getView(R.id.mHeadimg);
        ImageView state = holder.getView(R.id.state_img);
        ImageView Abolish_concern = holder.getView(R.id.Abolish_concern);
        TextView museNickName = holder.getView(R.id.museNickName);
        TextView distance = holder.getView(R.id.distance);
        if (resultbean.getMuseImage()!=null){
//            Glide.with(mContext).load(BaseUriConfig.makeImageUrl(resultbean.getMuseImage())).into(mHeadimg);
            mHeadimg.setImageURI(BaseUriConfig.makeImageUrl(resultbean.getMuseImage()));
        }
        if (resultbean.getRoomStatus().equals("0")){
            state.setVisibility(View.GONE);
        }else{
            state.setVisibility(View.VISIBLE);
        }
        museNickName.setText(resultbean.getMuseNickName());
        distance.setText(resultbean.getDistance()+"以内");
        Abolish_concern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCallBackListenr!=null){
                    mOnCallBackListenr.delPoint(resultbean);
                }
            }
        });

    }

    public void setOnCallBackListenr(OnCallBackListenr onCallBackListenr) {
        mOnCallBackListenr = onCallBackListenr;
    }
    public interface OnCallBackListenr{
        void delPoint(RespMyAttention.ResultBean.SourceBean resultbean);
    }
}
