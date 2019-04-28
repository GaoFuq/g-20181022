package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.response.RespCheckLS;

/**
 * Created by Administrator on 2017/9/13.
 */

public class PayHistoryAdapter extends ListBaseAdapter<RespCheckLS.ResultBean.SourceBean> {

    private String state;

    public PayHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pay_money_history_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespCheckLS.ResultBean.SourceBean resultEntity = getDataList().get(position);
        TextView anchor_title = holder.getView(R.id.anchor_title);//描述
        TextView consumption = holder.getView(R.id.consumption);//金额
        TextView anchor_time = holder.getView(R.id.anchor_time);//时间
        if (resultEntity != null) {
            state = resultEntity.getMeloDirection();
            if (state.equals("1")) {
                consumption.setText("+" + resultEntity.getMeloPrice());
            } else {
                consumption.setText("-" + resultEntity.getMeloPrice());
            }
            anchor_title.setText(resultEntity.getMeloContent());
            anchor_time.setText(resultEntity.getMeloTime());
        }
    }
}
