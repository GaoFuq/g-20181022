package com.code.clkj.menggong.activity.comLiveMyGift;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.ActApplyCashActivity;
import com.code.clkj.menggong.response.RespSaveEpurse;
import com.code.clkj.menggong.response.RespgetGiftBalance;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.CustomDialog;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/12.
 */

/**
 * 礼物结算
 */
public class ActMyGiftExchangeActivity extends TempActivity implements ViewMyGiftExchangeActivityI{

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title

    @Bind(R.id.exchange_btn)
    Button exchange_btn;

    @Bind(R.id.my_gift_exchange_crash)
    TextView my_gift_exchange_crash;//兑换现金

    private PreMyGiftExchangeActivityI mPreI;
    private String balance = "0";
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_my_gift_settlement_layout);
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
        mPreI = new PreMyGiftExchangeActivityImpl(this);
        if (TempLoginConfig.sf_getLoginState()) {
            mPreI.getGiftBalance(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag());
        }
        if (balance.equals("0")) {
            my_gift_exchange_crash.setText("礼物一共可兑换" + "0"+ "现金");
        }


    }

    @OnClick({R.id.exchange_btn,R.id.Withdrawals_btn})
    @Override
    protected void OnViewClicked(View v) {

        switch (v.getId()) {
            case R.id.exchange_btn:
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                if (balance.equals("0")) {
                    builder.setMessage("是否兑换成" + "0" + "余额");

                } else {
                    builder.setMessage("是否兑换成" + balance+ "余额");

                }
//                builder.setMessage("是否兑换成1000余额");
//                builder.setTitle("提示");

                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (balance.equals("0")) {
                            showToast("暂时没有礼物可以统计礼物金额");
                        } else {
                            mPreI.saveEpurse(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), balance);
                            initPopNotice(true, "兑换成功");
                        }
                    }
                });

                builder.create().show();

                break;
            case R.id.Withdrawals_btn:
                startActivity(new Intent(this, ActApplyCashActivity.class));
                break;
        }
    }
    //弹出的提示框
    private void initPopNotice(boolean isSelect, String info) {

        View v = getLayoutInflater().inflate(R.layout.pop_notice_exchange_layout, null);
        TextView tv = (TextView) v.findViewById(R.id.pop_notice_text);
        if (isSelect) {

            tv.setText(info);
        } else {
            tv.setText(info);
        }

        final AlertDialog mDailog = new AlertDialog.Builder(this).setView(v).create();
        v.findViewById(R.id.pop_notice_btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDailog != null && mDailog.isShowing()) {
                    mDailog.dismiss();
                }
            }
        });

        mDailog.show();

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("礼物结算");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void getGiftBalanceSuccess(RespgetGiftBalance data) {
         balance = data.getResult().getBalance();
        if (data.getFlag() == 0) {
            showToast(data.getMsg());
        } else {
            my_gift_exchange_crash.setText("礼物一共可兑换" + balance + "现金");
        }



    }

    @Override
    public void saveEpurseSuccess(RespSaveEpurse data) {
        data.getResult();
    }

    @Override
    public void onLoadFinish() {

    }


    @Override
    public void toast(String message) {

    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {

    }

    @Override
    public void showPro() {

    }

    @Override
    public void disPro() {

    }
}
