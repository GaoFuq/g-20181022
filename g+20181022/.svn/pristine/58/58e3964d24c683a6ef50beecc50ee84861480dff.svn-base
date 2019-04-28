package com.code.clkj.menggong.activity.comLiveMyOrder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLiveMyOrder.myOrder.PreActMyOrderActivityI;
import com.code.clkj.menggong.activity.comLiveMyOrder.myOrder.PreActMyOrderActivityImpl;
import com.code.clkj.menggong.activity.comLiveMyOrder.myOrder.ViewActMyOrderActivityI;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity1;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespGetMuseOrderBuy;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/9/12.
 */

public class ActMyOrderActivity extends TempActivity implements ViewActMyOrderActivityI {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;


    @Bind(R.id.live_my_order)
    TempRecyclerView live_my_order;


    String tag = "";
    //    @Bind(R.id.order_commodity_picture)
//    ImageView order_commodity_picture;
    private PreActMyOrderActivityI mPreI;
    private ListBaseAdapter<RespGetMuseOrderBuy.ResultBean.SourceBean> myOrderAdapter;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_my_order_layout);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {
        initData();
        initRcy();
    }

    @Override
    protected void bindValues() {


    }
    private void initRcy() {
        mPreI = new PreActMyOrderActivityImpl(this);
        live_my_order.setLayoutManager(new LinearLayoutManager(this));
        myOrderAdapter = new ListBaseAdapter<RespGetMuseOrderBuy.ResultBean.SourceBean>(this) {

            @Override
            public int getLayoutId() {
                return R.layout.live_my_order_item1;
            }

            @Override
            public void onBindItemHolder(final SuperViewHolder holder, int position) {
                final RespGetMuseOrderBuy.ResultBean.SourceBean data = getDataList().get(position);
                ImageView order_commodity_picture = holder.getView(R.id.order_commodity_picture);
                TextView order_id = holder.getView(R.id.order_id);//订单号
                final TextView order_state = holder.getView(R.id.order_state);//订单状态
                TextView order_goods_name = holder.getView(R.id.order_goods_name);//商品名字
                TextView new_price = holder.getView(R.id.new_price);//现价
                TextView old_price = holder.getView(R.id.old_price);//原价
                TextView sale_num = holder.getView(R.id.sale_num);//销售总数
                TextView buy_num = holder.getView(R.id.buy_num);//购买数量


                final Button cancal_btn = holder.getView(R.id.cancal_btn);//取消订单
                final Button paymen_btn = holder.getView(R.id.paymen_btn);//订单状态按钮
                View view = holder.getView(R.id.view);
                final String mordId = data.getMordId();
                final String mordNo = data.getMordNo();
                if (data.getImage() != null) {
                    ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getImage()), order_commodity_picture);
                }
//&& data.getMordStatus().equals("5")
                if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("1")) {
                    order_state.setText("待付款");
                    cancal_btn.setVisibility(View.VISIBLE);//1111
                    paymen_btn.setText("付款");
                    tag = "1";

                } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("2")) {
                    order_state.setText("待发货");
                    cancal_btn.setVisibility(View.GONE);
                    paymen_btn.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("3")) {
                    order_state.setText("已发货");
                    cancal_btn.setVisibility(View.GONE);
                    paymen_btn.setText("确认收货");//111
                    tag = "2";
                } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("4")) {
                    order_state.setText("待评价");
                    cancal_btn.setVisibility(View.GONE);
                    paymen_btn.setText("评价");
                    tag = "3";
//                } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("6")) {
//                    order_state.setText("支付成功");
//                    cancal_btn.setVisibility(View.GONE);
//                    paymen_btn.setText("删除订单");
//                    tag = "4";
                } else if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("7")) {
                    order_state.setText("已评价");
                    cancal_btn.setVisibility(View.GONE);
                    paymen_btn.setText("删除订单");
                }

                holder.getView(R.id.cancal_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        live_my_order.setRefreshing(new TempRecyclerView.initDataListener() {
                            @Override
                            public void initDataData(int currentPage, int pageSize) {

                                mPreI.saveOrderStatus(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), mordId, "5", "2");
                                cancal_btn.setVisibility(View.GONE);
                                paymen_btn.setVisibility(View.GONE);
                                order_state.setText("该订单已取消");
                            }
                        });
                        live_my_order.forceToRefresh();
                        live_my_order.refreshing();
                    }
                });

                if (!TextUtils.isEmpty(data.getMordNo())) {
                    order_id.setText(data.getMordNo());
                }

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

                    buy_num.setText("×" + data.getGoodsNum());
                }

                if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("3")) {
                    holder.getView(R.id.paymen_btn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPreI.saveOrderStatus(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), mordId, "4", "2");//确认收货
                            order_state.setText("待评价");
                            cancal_btn.setVisibility(View.GONE);
                            paymen_btn.setText("评价");
                        }
                    });
                }
                if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("4")) {
                    holder.getView(R.id.paymen_btn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(ActMyOrderActivity.this, ActMyOrderEvaluateActivity.class);//跳转到评价页面
                            i.putExtra("order_image", data.getImage());
                            i.putExtra("mgcoOrderId", data.getMordId());
                            startActivity(i);
                        }
                    });

                }
                if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("1")) {
                    holder.getView(R.id.paymen_btn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (NullUtils.isNotEmpty(data.getMordNo())) {
                                Intent i = new Intent(ActMyOrderActivity.this, ActPaymentMethodActivity1.class);//跳转到支付页面
                                i.putExtra("OderMordNo", data.getMordNo());
                                i.putExtra("total_price_number", (TempDataUtils.string2Int(data.getDiscountPrice()) * TempDataUtils.string2Int(data.getGoodsNum())) + "");
                                i.putExtra("state", "2");
                                startActivity(i);
                            }
                        }
                    });

                }
                if (!TextUtils.isEmpty(data.getMordStatus()) && data.getMordStatus().equals("7")) {
                    paymen_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPreI.saveOrderStatus(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), mordId, "6", "2");//删除订单
                        }
                    });

                }
            }
        };
        live_my_order.setAdapter(myOrderAdapter);

        live_my_order.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseOrderBuy(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), currentPage + "", pageSize + "");
            }
        });
        live_my_order.forceToRefresh();
        live_my_order.refreshing();
    }


    @Override
    protected void onResume() {
        super.onResume();
      /* */
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        live_my_order.forceToRefresh();
        live_my_order.refreshing();
    }

    @Override
    protected void OnViewClicked(View v) {

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("我的订单");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void onLoadFinish() {
        live_my_order.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        live_my_order.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        live_my_order.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    //获取订单列表成功
    @Override
    public void getMuseOrderBuySuccess(RespGetMuseOrderBuy data) {
        if (live_my_order == null){
            live_my_order = new TempRecyclerView(this);
        }
        if (live_my_order.isMore()) {
            myOrderAdapter.addAll(data.getResult().getSource());
        } else {
            myOrderAdapter.setDataList(data.getResult().getSource());
            live_my_order.setTotalCount(TempDataUtils.string2Int(data.getResult().getSize()));
        }
    }

    //更改订单状态成功
    @Override
    public void saveOrderStatusSuccess(TempResponse data) {
        live_my_order.forceToRefresh();
        live_my_order.refreshing();
    }
}
