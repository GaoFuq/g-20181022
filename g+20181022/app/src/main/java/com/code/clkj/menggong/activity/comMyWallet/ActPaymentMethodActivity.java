package com.code.clkj.menggong.activity.comMyWallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.PreActPaymentMethodActivityI;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.PreActPaymentMethodActivityImpl;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.ViewActPaymentMethodActivityI;
import com.code.clkj.menggong.response.RespGetAlipayInfo;
import com.code.clkj.menggong.response.RespGetWxpayInfo;
import com.code.clkj.menggong.response.RespPayOrder;
import com.code.clkj.menggong.response.RespQueryAppOrderPayMentType;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import butterknife.Bind;
import butterknife.OnClick;

/**支付方式
 * Created by Administrator on 2017/9/13.
 */

public class ActPaymentMethodActivity extends TempActivity implements ViewActPaymentMethodActivityI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.Payment_method_confirm_btn)
    Button Payment_method_confirm_btn;//确认支付

    @Bind(R.id.act_pay_dianzi)
    CheckBox act_pay_dianzi;//电子钱包
    @Bind(R.id.act_pay_alipay)
    CheckBox act_pay_alipay;//支付宝
    @Bind(R.id.act_pay_wechat)
    CheckBox act_pay_wechat;//微信


    @Bind(R.id.act_pay_dianzi_ly)
    LinearLayout act_pay_dianzi_ly;
    @Bind(R.id.act_pay_alipay_ly)
    LinearLayout act_pay_alipay_ly;
    @Bind(R.id.act_pay_wechat_ly)
    LinearLayout act_pay_wechat_ly;


    private String mordNo;//订单号
    private PreActPaymentMethodActivityI mPreI;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.payment_method_layout);
    }

    @Override
    protected void findViews() {
        initData();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        mordNo = getIntent().getStringExtra("mordNo");
        mPreI = new PreActPaymentMethodActivityImpl(this);
//        mPreI.payOrder();

    }
    @OnClick({R.id.act_pay_dianzi_ly, R.id.act_pay_alipay_ly, R.id.act_pay_wechat_ly,
    R.id.Payment_method_confirm_btn})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.act_pay_dianzi_ly:
                upCheckBox(1);
                break;
            case R.id.act_pay_alipay_ly:
                upCheckBox(2);
                break;
            case R.id.act_pay_wechat_ly:
                upCheckBox(3);
                break;
            case R.id.Payment_method_confirm_btn:
                    if (!TempLoginConfig.sf_getLoginState()) {
                        showToast("您未登录");
                    } else {

                    }
                break;
            default:
                break;
        }
    }
    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("请选择支付方式");
    }


    /**
     * 更改支付状态
     */
    private int type=0;

    private void upCheckBox(int i) {
        act_pay_alipay.setChecked(false);
        act_pay_wechat.setChecked(false);

        type = i;
        switch (i) {
            case 1:
                act_pay_dianzi.setChecked(true);
            case 2:
                act_pay_alipay.setChecked(true);
                break;
            case 3:
                act_pay_wechat.setChecked(true);
                break;
        }
    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {

    }

    @Override
    public void onLoadDataSuccess() {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void payOrderSuccess(RespPayOrder data) {

    }

    @Override
    public void queryAppOrderPayMentTypeSuccess(RespQueryAppOrderPayMentType data) {

    }

    @Override
    public void getAlipayInfoSuccess(RespGetAlipayInfo data) {

    }

    @Override
    public void getWxpayInfoSuccess(RespGetWxpayInfo data) {

    }
}
