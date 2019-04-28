package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.TextView;

import com.code.clkj.menggong.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Lzx on 2016/12/30.
 * 首页 adapter
 */

public class FragHomeOnlineAdapter extends ListBaseAdapter<String> {
    /**
     */
    public FragHomeOnlineAdapter(Context context) {
        super(context);
    }

    public FragHomeOnlineAdapter(Context context, String[] data) {
        super(context);
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings,data);
        this.setDataList(strings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_item_text;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        String item = mDataList.get(position);

        TextView titleText = holder.getView(R.id.info_text);
        titleText.setText(item);
    }
}
