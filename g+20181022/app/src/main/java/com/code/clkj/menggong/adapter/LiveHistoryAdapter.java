package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespShowHistory;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 历史直播适配器
 * Created by Administrator on 3017/9/11.
 */

public class LiveHistoryAdapter extends ListBaseAdapter<RespShowHistory.ResultBean.SourceBean> {

    private OnCallBackListenr mOnCallBackListenr;

    public boolean isableDel() {
        return isableDel;
    }

    private boolean isableDel;

    public void setIsableDel(boolean isableDel) {
        this.isableDel = isableDel;
        notifyDataSetChanged();
    }

    public LiveHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.live_history_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespShowHistory.ResultBean.SourceBean resultEntity = getDataList().get(position);
        if (resultEntity != null) {
            ImageView mLiveHistoryIcon = holder.getView(R.id.mLiveHistoryIcon);
            TextView history_name = holder.getView(R.id.history_name);
            TextView live_num_tv = holder.getView(R.id.live_num_tv);
            ImageView del_history = holder.getView(R.id.del_history);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin_horizontal = mContext.getResources().getDimensionPixelSize(R.dimen.padding_size_default);
            if(position % 3 == 0) {
                lp.setMargins(margin_horizontal, margin_horizontal/3, margin_horizontal/3, margin_horizontal/3);
            } else {
                lp.setMargins(margin_horizontal/3, margin_horizontal/3, margin_horizontal, margin_horizontal/3);
            }
            holder.itemView.setLayoutParams(lp);
            
            if (resultEntity.getImage() != null) {
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(resultEntity.getImage()), mLiveHistoryIcon);
            }
            history_name.setText(resultEntity.getRoomName());
            live_num_tv.setText(resultEntity.getWatchNum());

            if (isableDel) {
                del_history.setVisibility(View.VISIBLE);
            } else {
                del_history.setVisibility(View.GONE);
            }

            del_history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCallBackListenr!=null){
                        mOnCallBackListenr.delPoint(resultEntity);
                    }
                }
            });
        }
    }
    public void setOnCallBackListenr(OnCallBackListenr onCallBackListenr) {
        mOnCallBackListenr = onCallBackListenr;
    }
    public interface OnCallBackListenr {
        void delPoint(RespShowHistory.ResultBean.SourceBean resultEntity);
    }
}
