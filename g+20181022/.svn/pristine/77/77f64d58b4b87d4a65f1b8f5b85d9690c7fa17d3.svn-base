package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.response.RespMyFriendsList;

/**
 * Created by Administrator on 2017/9/14.
 */

public class FriendsAdapter extends ListBaseAdapter<RespMyFriendsList.ResultBean.SourceBean> {

    public FriendsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.friends_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespMyFriendsList.ResultBean.SourceBean resultbean = getDataList().get(position);
        ImageView headImage = holder.getView(R.id.friends_hend_img);
        ImageView image_state = holder.getView(R.id.image_state);
        TextView title = holder.getView(R.id.title);
        TextView content = holder.getView(R.id.content);


    }
}
