package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.response.RespShowBankCard;

/**
 * Created by clkj on 2017/12/22.
 */

public class ShowBankCardAdapter extends ListBaseAdapter<RespShowBankCard.ResultBean.SourceBean> {
    private int selectPosition = -1;
    private String mCard;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public ShowBankCardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_show_bank_card;
    }

    //tb_18
    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespShowBankCard.ResultBean.SourceBean resultbean = getDataList().get(position);
        TextView mbanName = holder.getView(R.id.mbanName);
        TextView mbanCard = holder.getView(R.id.mbanCard);

        CheckBox act_toolbar_menu_tixian_item_check = holder.getView(R.id.act_toolbar_menu_tixian_item_check);
        mbanName.setText(resultbean.getMbanName());

        mCard = resultbean.getMbanCard();
        if (mCard!=null&&mCard!="") {
            if (mCard.length()>4){
                mbanCard.setText("****  ****  **** " + mCard.substring(mCard.length() - 4, mCard.length()));
            }else{
                mbanCard.setText("****  ****  **** " + mCard);
            }

        }

        act_toolbar_menu_tixian_item_check.setChecked(selectPosition == position);

    }
}
