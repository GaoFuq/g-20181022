package com.code.clkj.menggong.activity.comMyWallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comAddBankCard.PreAddBankCardI;
import com.code.clkj.menggong.activity.comMyWallet.comAddBankCard.PreAddBankCardImpl;
import com.code.clkj.menggong.activity.comMyWallet.comAddBankCard.ViewAddBankCardI;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ActAddBankCardActivity extends TempActivity implements ViewAddBankCardI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.Cardholder)
    EditText Cardholder;
    @Bind(R.id.Card_number)
    EditText Card_number;
    @Bind(R.id.Opening_bank)
    EditText Opening_bank;

    private PreAddBankCardI mPreI;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_add_bank_card_layout);
    }

    @Override
    protected void findViews() {
        mPreI = new PreAddBankCardImpl(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        initData();
    }

    @Override
    @OnClick(R.id.baocun_btn)
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.baocun_btn:
                if (TextUtils.isEmpty(Cardholder.getText().toString())){
                    showToast("请输入持卡人姓名");
                }
                if (TextUtils.isEmpty(Card_number.getText().toString())){
                    showToast("输入银行卡号");
                }
                if (TextUtils.isEmpty(Opening_bank.getText().toString())){
                    showToast("请输入开户行");
                }
                mPreI.saveMuseBrank(Opening_bank.getText().toString(),Card_number.getText().toString(),Cardholder.getText().toString());
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("添加银行卡");
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(e.message);
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
    public void saveMuseBrankSuccess(TempResponse data) {
        showToast(data.getMsg());
        finish();
    }
}
