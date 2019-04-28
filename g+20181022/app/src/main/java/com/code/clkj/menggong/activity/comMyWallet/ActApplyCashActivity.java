package com.code.clkj.menggong.activity.comMyWallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comShowBankCard.PreShowBankCardI;
import com.code.clkj.menggong.activity.comMyWallet.comShowBankCard.PreShowBankCardImpl;
import com.code.clkj.menggong.activity.comMyWallet.comShowBankCard.ViewShowBankCardI;
import com.code.clkj.menggong.adapter.ShowBankCardAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespShowBankCard;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.interfaces.OnItemClickListener;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ActApplyCashActivity extends TempActivity implements ViewShowBankCardI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.add_bank_card)
    ImageView add_bank_card;
    @Bind(R.id.show_bankcard)
    TempRecyclerView show_bankcard;
    @Bind(R.id.Cash_withdrawal)
    EditText Cash_withdrawal;

    private ShowBankCardAdapter mAdapter;
    private String mbanId;

    private PreShowBankCardI mPreI;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_apply_cash_layout);
    }

    @Override
    protected void findViews() {
        initData();
        mPreI = new PreShowBankCardImpl(this);
    }

    @Override
    protected void setListeners() {
        show_bankcard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ShowBankCardAdapter(this);
        show_bankcard.setAdapter(mAdapter);
        show_bankcard.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseBrank(currentPage + "");
            }
        });

        show_bankcard.setOnItemClickLinstener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.setSelectPosition(position);
                mbanId = mAdapter.getDataList().get(position).getMbanId();
                Log.i("mbanId", mbanId);
            }
        });

        show_bankcard.refreshing();
        show_bankcard.forceToRefresh();

    }

    @Override
    protected void bindValues() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        show_bankcard.refreshing();
        show_bankcard.forceToRefresh();
    }

    @OnClick({R.id.add_bank_card,R.id.confirm_btn})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.add_bank_card:
                Intent intentadd_bank_card = new Intent(this, ActAddBankCardActivity.class);
                startActivity(intentadd_bank_card);
                break;
            case R.id.confirm_btn:
                if (TextUtils.isEmpty(Cash_withdrawal.getText().toString())){
                    showToast("请输入提现金额");
                }
                if (mbanId==null){
                    showToast("请选择银行卡");
                }
                mPreI.saveWithdraw(mbanId,Cash_withdrawal.getText().toString().trim());
                break;
        }

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("申请提现");
    }

    @Override
    public void onLoadFinish() {
        show_bankcard.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        show_bankcard.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        show_bankcard.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getMuseBrankSuccee(RespShowBankCard data) {
        if (show_bankcard.isMore()){
            mAdapter.addAll(data.getResult().getSource());
        }else{
            mAdapter.setDataList(data.getResult().getSource());
        }
    }

    @Override
    public void saveWithdrawSuccee(TempResponse data) {
        showToast(data.getMsg());
        finish();
    }
}
