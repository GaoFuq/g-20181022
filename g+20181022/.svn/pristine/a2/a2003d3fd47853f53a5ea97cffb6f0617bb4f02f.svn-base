package com.code.clkj.menggong.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespGetSearchByKey;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by 92573 on 2018/1/13.
 */

/**
 * 更多直播adapter
 */
public class SearchLiveAdapter extends ListBaseAdapter<RespGetSearchByKey.ResultBean.SourseBean> {
    public SearchLiveAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_hot_live_gv_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        RespGetSearchByKey.ResultBean.SourseBean data = getDataList().get(position);
        ImageView mRoomIcon = holder.getView(R.id.mRoomIcon);
        TextView mRoomTitle = holder.getView(R.id.mRoomTitle);
        TextView mUserCount = holder.getView(R.id.mUserCount);
        if (data.getRoomImage() != null) {
            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getRoomImage()), mRoomIcon);
        }
//        if (!TextUtils.isEmpty(data.getRoomImage())) {
//            Glide.with(get)
//                    .load(BaseUriConfig.makeImageUrl(data.getRoomImage()))
//                    .placeholder(R.mipmap.zye_10)
//                    .into(head_img_item);
//        }
        if (!TextUtils.isEmpty(data.getRoomName())) {
            mRoomTitle.setText(data.getRoomName());
        }
//        if (!TextUtils.isEmpty(data.getRoomWatchNum())) {
//            mUserCount.setText(data.getRoomWatchNum());
//        }

    }

}
