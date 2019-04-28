package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLiveMyOrder.ActMyOrderEvaluateActivity;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity1;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespGetMuseOrderBuy;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 92573 on 2018/1/10.
 */

public class MyOrderAdapter1 extends ListBaseAdapter<RespGetMuseOrderBuy.ResultBean.SourceBean> implements View.OnClickListener {

    String tag = "";
    public MyOrderAdapter1(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.live_my_order_item1;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        RespGetMuseOrderBuy.ResultBean.SourceBean data = getDataList().get(position);
        ImageView order_commodity_picture = holder.getView(R.id.order_commodity_picture);
        TextView order_id = holder.getView(R.id.order_id);//订单号
        TextView order_state = holder.getView(R.id.order_state);//订单状态
        TextView order_goods_name = holder.getView(R.id.order_goods_name);//商品名字
        TextView new_price = holder.getView(R.id.new_price);//现价
        TextView old_price = holder.getView(R.id.old_price);//原价
        TextView sale_num = holder.getView(R.id.sale_num);//销售总数
        TextView buy_num = holder.getView(R.id.buy_num);//购买数量
        Button cancal_btn = holder.getView(R.id.cancal_btn);//取消订单
        Button paymen_btn = holder.getView(R.id.paymen_btn);//订单状态按钮
        View view = holder.getView(R.id.view);
        if (data.getImage() != null) {
            ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getImage()), order_commodity_picture);
        }
//        Button evaluate_btn;
//        TextView order_title;

        if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("1")) {
            order_state.setText("待付款");
            cancal_btn.setVisibility(View.VISIBLE);
            paymen_btn.setText("付款");

            tag = "1";

        } else if(!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("2")) {
            order_state.setText("待发货");
            cancal_btn.setVisibility(View.GONE);
            paymen_btn.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("3")) {
            order_state.setText("待收货");
            cancal_btn.setVisibility(View.GONE);
            paymen_btn.setText("确认收货");
            tag = "2";
        } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("4")) {
            order_state.setText("待评价");
            cancal_btn.setVisibility(View.GONE);
            paymen_btn.setText("评价");
            paymen_btn.setOnClickListener(this);
            tag = "3";
        }

        if (!TextUtils.isEmpty(data.getMordId())) {
            order_id.setText(data.getMordId());
        }
//        if (!TextUtils.isEmpty(data.getMordStatus())) {
//            order_state.setText(data.getMordStatus());
//
//        }
        if (!TextUtils.isEmpty(data.getMgooName())) {
            order_goods_name.setText(data.getMgooName());
        }
        if (!TextUtils.isEmpty(data.getDiscountPrice())) {
            new_price.setText("￥" + data.getDiscountPrice());
        }
        if (!TextUtils.isEmpty(data.getPeice())) {
            old_price.setText("￥" + data.getPeice());
        }
        if (!TextUtils.isEmpty(data.getStockNum())) {
            sale_num.setText("已售" + data.getStockNum());
        }
        if (!TextUtils.isEmpty(data.getGoodsNum())) {

            buy_num.setText("×" +data.getGoodsNum());
        }
    }
    Intent i;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.paymen_btn:
                if (tag.equals("1")) {
                    i = new Intent(view.getContext(),ActPaymentMethodActivity1.class);//跳转到支付页面
                    view.getContext().startActivity(i);


                } else if (tag.equals("3")) {
                    i = new Intent(view.getContext(),ActMyOrderEvaluateActivity.class);
                    view.getContext().startActivity(i);
                }

                break;
            case R.id.cancal_btn:

                break;
        }
    }
}
