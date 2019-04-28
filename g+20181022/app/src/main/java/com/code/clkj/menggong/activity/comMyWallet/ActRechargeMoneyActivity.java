package com.code.clkj.menggong.activity.comMyWallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comRechargeMoney.PreRechargeMoneyI;
import com.code.clkj.menggong.activity.comMyWallet.comRechargeMoney.PreRechargeMoneyImpl;
import com.code.clkj.menggong.activity.comMyWallet.comRechargeMoney.ViewRechargeMoneyI;
import com.code.clkj.menggong.response.RespSaveEpurseOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ActRechargeMoneyActivity extends TempActivity implements ViewRechargeMoneyI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.recharge_price)
    EditText recharge_price;

    private PreRechargeMoneyI mPreI;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_recharge_layout);
    }

    @Override
    protected void findViews() {
        initData();
        mPreI = new PreRechargeMoneyImpl(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick(R.id.confirm_btn)
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
                if (TextUtils.isEmpty(recharge_price.getText().toString())) {
                    showToast("请输入充值金额");
                }else{
                    mPreI.saveEpurseOrder(recharge_price.getText().toString());
                }
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("充值");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(ExceptionEngine.handleException(e).message);
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
    public void saveEpurseOrderSuccess(RespSaveEpurseOrder data) {
        String meorOrderNumber;
        if (data.getResult().getMeorOrderNumber()!=null){
            meorOrderNumber = data.getResult().getMeorOrderNumber();
            Intent orderIntent = new Intent(this,ActPaymentMethodActivity1.class);
            orderIntent.putExtra("tradeNo",meorOrderNumber);
            orderIntent.putExtra("state","3");
            orderIntent.putExtra("total_price_number",recharge_price.getText().toString());
            startActivity(orderIntent);
        }

    }
}
