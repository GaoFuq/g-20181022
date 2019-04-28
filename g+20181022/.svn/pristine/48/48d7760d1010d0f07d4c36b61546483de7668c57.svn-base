package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.util.TempDataUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Lzx on 2016/12/30.
 */

public class DataAdapter extends ListBaseAdapter<String> {

    public DataAdapter(Context context) {
        super(context);
    }

    public DataAdapter(Context context,String[] data) {
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
        String item = getDataList().get(position);

        TextView titleText = holder.getView(R.id.info_text);
        titleText.setText(TempDataUtils.string2NotNull(item));
    }

    @Override
    public int getItemCount() {
        return 4;
//        return super.getItemCount();
    }
}
