package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.holder.MenuAllOrderHolder;
import com.code.clkj.menggong.response.RespgetCheckList;
import com.lf.tempcore.tempAdapter.TempListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017-12-14.
 */

public class checkIdAdapter extends TempListAdapter<RespgetCheckList.ResultBean,MenuAllOrderHolder> {

    public checkIdAdapter(List<RespgetCheckList.ResultBean> data, Context context, int layoutId) {
        super(data, context, layoutId);
    }

    @Override
    protected MenuAllOrderHolder createHolder() {
        return new MenuAllOrderHolder();
    }

    @Override
    protected void initHolder(int position, View v, MenuAllOrderHolder holder) {
        holder.setmName((TextView) v.findViewById(R.id.item_menu_all_order_name));
        holder.setImageView((ImageView) v.findViewById(R.id.item_menu_all_order_name_icon));
    }

    @Override
    public void bunldHolderValue(int position, MenuAllOrderHolder holder, RespgetCheckList.ResultBean item) {
        holder.getmName().setText(item.getCkName());
    }
}
