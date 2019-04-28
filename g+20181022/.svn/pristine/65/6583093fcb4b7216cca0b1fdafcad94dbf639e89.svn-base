package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.response.RoomComment;


public class LiveListAdapter extends ListBaseAdapter<RoomComment.ResultEntity> {
    private Context mContext;
    private   RoomComment.ResultEntity item;
    private   TextView collect_number;
    //    LikeButton home_collect;
    public LiveListAdapter(Context context) {
        super(context);
        this.mContext=context;
    }
    @Override
    public int getLayoutId() {
        return R.layout.layout_list_live_zhibo_comment;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        item = mDataList.get(position);
        TextView titleText = holder.getView(R.id.live_comment_name);
        collect_number = holder.getView(R.id.live_comment_context);
//         home_collect = holder.getView(R.id.home_collect);
        titleText.setText(item.getMuseNickName()+"");
        collect_number.setText(item.getRcomContent()+"");
    }
}
