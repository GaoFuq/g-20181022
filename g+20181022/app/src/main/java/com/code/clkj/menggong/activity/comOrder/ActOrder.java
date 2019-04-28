package com.code.clkj.menggong.activity.comOrder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAddress.ActAddress;
import com.code.clkj.menggong.activity.comAddress.ActEditAddressActivity;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity1;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespSaveMallOrder;
import com.code.clkj.menggong.response.RespToConfirmOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempUtil.QuickClickUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by clkj on 2018/1/8.
 */

public class ActOrder extends TempActivity implements ViewOrderI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.act_firm_address_none)
    View act_firm_address_none;//无收货地址显示
    @Bind(R.id.act_firm_address)
    View act_firm_address;//显示收货地址
    @Bind(R.id.Consignee)
    TextView Consignee;//收货人
    @Bind(R.id.edit_img_01)
    ImageView edit_img_01;//收货地址默认状态图标
    @Bind(R.id.phone_number_01)
    TextView phone_number_01;//收货人电话
    @Bind(R.id.address_01)
    TextView address_01;//收货人地址
    @Bind(R.id.default_tv)
    TextView default_tv;//地址默认状态

    @Bind(R.id.order_good_image)
    ImageView order_good_image;//订单商品图片
    @Bind(R.id.store_goods_name)
    TextView store_goods_name;//订单商品名称
    @Bind(R.id.store_goods_price)
    TextView store_goods_price;//订单商品现价
    @Bind(R.id.store_goods_old_price)
    TextView store_goods_old_price;//订单商品原价
    @Bind(R.id.store_goods_num)
    TextView store_goods_num;//订单商品销量
    @Bind(R.id.num)
    TextView num;//订单商品数量
    @Bind(R.id.xiaoji)
    TextView xiaoji;//小计
    @Bind(R.id.frag_shop_detail_goods_settle_accounts)
    TextView frag_shop_detail_goods_settle_accounts;//结算
    @Bind(R.id.total_price)
    TextView total_price;//总计
    String total_price_number;//共计多少钱
    @Bind(R.id.bg_btn)
    Button bg_btn;

    @Bind(R.id.act_firm_order_bz_text)
    EditText act_firm_order_bz_text;//订单备注
    String goods_number;

    private boolean ischoose = true;
    private String msadId;
    private String msadIsDefault;


    private PreOrderI mPreOrderI;
    private String GoodId;
    private String GoodCotent;
    private String select_address_id;
    private boolean isSelect;
    private String mordNo;//订单号

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_confirm_order_layout);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("确认订单");
        toolbar_title.setTextSize(18);

        GoodId = getIntent().getStringExtra(Constance.GOODID);
        GoodCotent = getIntent().getStringExtra(Constance.GOODCOUNT);

        mPreOrderI = new PreOrderImpl(this);
        mPreOrderI.toConfirmOrder(GoodId, GoodCotent);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPreOrderI!=null)
        mPreOrderI.toConfirmOrder(GoodId, GoodCotent);
    }

    @Override
    @OnClick({R.id.act_firm_address_none, R.id.edit_img_01, R.id.bg_btn, R.id.frag_shop_detail_goods_settle_accounts})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.act_firm_address_none:
                Intent edit_address_intent = new Intent(this, ActAddress.class);
                edit_address_intent.putExtra("isSelect",true);
                startActivityForResult(edit_address_intent, Constance.ACT_RESPEST_MAP_CODE);
                startActivity(edit_address_intent);
                break;
            case R.id.edit_img_01:
                if (QuickClickUtils.checkClick()) return;
                Intent intent = new Intent(this, ActEditAddressActivity.class);
                intent.putExtra("msadId", msadId);
                intent.putExtra("msadIsDefault", msadIsDefault);
                startActivity(intent);
                break;
            case R.id.bg_btn:
                Intent show_address_intent = new Intent(this, ActAddress.class);
                show_address_intent.putExtra("isSelect", true);
                startActivityForResult(show_address_intent, Constance.ACT_PERSONAL_DATA_CODE);
                break;
            case R.id.frag_shop_detail_goods_settle_accounts:
                String remark = act_firm_order_bz_text.getText().toString();//备注
                mPreOrderI.saveMallOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), GoodId, goods_number, msadId, remark);
//                Intent intent1 = new Intent(this, ActPaymentMethodActivity1.class);
//                intent1.putExtra("mordNo", mordNo);
//                intent1.putExtra("total_price_number", total_price_number);
//                startActivity(intent1);
                break;
            default:
                Intent intent1 = new Intent(this, ActPaymentMethodActivity.class);
                intent1.putExtra("mordNo", mordNo);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showConntectError(ExceptionEngine.handleException(e));
    }

    @Override
    public void showPro() {
        showProgressDialog(false);
    }

    @Override
    public void disPro() {
        dismissProgressDialog();
    }

    @Override
    public void toConfirmOrderSuccess(RespToConfirmOrder data) {
        if (data.getResult().getMallGoods() != null) {
            if (data.getResult().getShipingAddress() != null) {
                ischoose = false;
                bg_btn.setVisibility(View.VISIBLE);
                act_firm_address_none.setVisibility(View.GONE);
                act_firm_address.setVisibility(View.VISIBLE);
                Consignee.setText(data.getResult().getShipingAddress().getMsadReceiverName());
                phone_number_01.setText(data.getResult().getShipingAddress().getMsadMobileNo());
                address_01.setText(data.getResult().getShipingAddress().getAddress());
                msadId = data.getResult().getShipingAddress().getMsadId();
                msadIsDefault = data.getResult().getShipingAddress().getMsadIsDefault();

                if (data.getResult().getShipingAddress().getMsadIsDefault().equals("0")) {
                    default_tv.setVisibility(View.GONE);
                    edit_img_01.setBackgroundResource(R.mipmap.tb_2);

                } else {
                    default_tv.setVisibility(View.VISIBLE);
                    edit_img_01.setBackgroundResource(R.mipmap.tb_3);
                }
            } else {
                ischoose = true;
                bg_btn.setVisibility(View.GONE);
                act_firm_address_none.setVisibility(View.VISIBLE);
                act_firm_address.setVisibility(View.GONE);
            }
            Glide.with(this).load(BaseUriConfig.makeImageUrl(data.getResult().getMallGoods().getMgooImage()))
                    .into(order_good_image);
            store_goods_name.setText(data.getResult().getMallGoods().getMgooName());
            store_goods_price.setText("￥" + data.getResult().getMallGoods().getDiscountPrice());
            store_goods_old_price.setText("￥" + data.getResult().getMallGoods().getMgooPrice());
            store_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            store_goods_num.setText("已售" + data.getResult().getMallGoods().getMgooStockNum());
            goods_number = data.getResult().getModeNum();
            num.setText("x" + data.getResult().getModeNum());
            xiaoji.setText("￥" + data.getResult().getPrice());
            frag_shop_detail_goods_settle_accounts.setText("结算" + "(" + data.getResult().getModeNum() + ")");
            total_price.setText("￥" + data.getResult().getPrice());
            total_price_number = data.getResult().getPrice();//总价

        }
    }

    @Override
    public void saveMallOrderSuccess(RespSaveMallOrder data) {
        showToast(data.getMsg());
        mordNo = data.getResult().getMordNo();//订单号
        Intent intent1 = new Intent(this, ActPaymentMethodActivity1.class);
        intent1.putExtra("mordNo", mordNo);
        intent1.putExtra("total_price_number", total_price_number);
        startActivity(intent1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constance.KEY_RESULT_CODE_ADDRESS_1:
                String mSelectId = data.getStringExtra("select_address_id");
                String msadMobileNo = TempDataUtils.string2NotNull(data.getStringExtra("select_address_phone"));
                String receiverName = TempDataUtils.string2NotNull(data.getStringExtra("select_address_name"));
                String msadAddrName = TempDataUtils.string2NotNull(data.getStringExtra("select_address"));
                String address_state = TempDataUtils.string2NotNull(data.getStringExtra("address_state"));
                if (mSelectId != null) {
                    Consignee.setText(receiverName);
                    phone_number_01.setText(msadMobileNo);
                    address_01.setText(msadAddrName);
                    msadId = mSelectId;
                    if (address_state.equals("1")) {
                        edit_img_01.setBackgroundResource(R.mipmap.tb_3);
                        default_tv.setVisibility(View.VISIBLE);
                    } else {
                        edit_img_01.setBackgroundResource(R.mipmap.tb_2);
                        default_tv.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }
}
