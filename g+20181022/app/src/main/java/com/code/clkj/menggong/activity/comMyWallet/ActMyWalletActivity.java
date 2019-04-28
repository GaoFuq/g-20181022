package com.code.clkj.menggong.activity.comMyWallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.PreCheckBalanceI;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.PreCheckBalanceImpl;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.ViewCheckBalanceI;
import com.code.clkj.menggong.adapter.PayHistoryAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespCheckLS;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 钱包
 * Created by Administrator on 2017/9/13.
 */

public class ActMyWalletActivity extends TempActivity implements ViewCheckBalanceI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.pay_money_history)
    TempRecyclerView pay_money_history;
    @Bind(R.id.Recharge)
    ImageView Recharge;
    @Bind(R.id.Withdrawals)
    ImageView Withdrawals;
    @Bind(R.id.epurse)
    TextView epurse;

    private PreCheckBalanceI mPreI;
    private PayHistoryAdapter mAdapter;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_my_wallet_layout);
    }

    @Override
    protected void findViews() {
        initData();
        mPreI = new PreCheckBalanceImpl(this);
        mPreI.getMuseEpurse();
    }

    @Override
    protected void setListeners() {
        pay_money_history.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new PayHistoryAdapter(this);
        pay_money_history.setAdapter(mAdapter);
        pay_money_history.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseEpurseLog(currentPage + "");
            }
        });
        pay_money_history.refreshing();
        pay_money_history.forceToRefresh();
    }

    @Override
    protected void bindValues() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPreI != null)
            mPreI.getMuseEpurse();
    }

    @OnClick({R.id.Withdrawals, R.id.Recharge})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.Recharge:
                Intent intentRecharge = new Intent(this, ActRechargeMoneyActivity.class);
                startActivity(intentRecharge);
                break;
            case R.id.Withdrawals:
                Intent intentWithdrawals = new Intent(this, ActApplyCashActivity.class);
                startActivity(intentWithdrawals);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("");
    }

    @Override
    public void onLoadFinish() {
        pay_money_history.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        pay_money_history.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        pay_money_history.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getMuseEpurseSuccee(RespCheckBalance data) {
        epurse.setText(data.getResult().getEpurse());
    }

    @Override
    public void getMuseEpurseLogSuccee(RespCheckLS data) {
        if (pay_money_history.isMore()) {
            mAdapter.addAll(data.getResult().getSource());
        } else {
            mAdapter.setDataList(data.getResult().getSource());
        }
        pay_money_history.setTotalCount(data.getResult().getSize());
    }
}
