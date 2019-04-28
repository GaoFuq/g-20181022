package com.code.clkj.menggong.activity.comHomepassword;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHomepassword.comForgetPayPassWord.ActForgetPayPassWord;
import com.code.clkj.menggong.activity.comHomepassword.comPayPassWord.ActSetPayPassWord;
import com.code.clkj.menggong.activity.comHomepassword.comResetLoginPassword.ActResetLoginPW;
import com.code.clkj.menggong.activity.comHomepassword.comResetPayPassword.ActResetPayPassword;
import com.code.clkj.menggong.response.RespActPassWord;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ActPassWord extends TempActivity implements ViewActPassWordI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.Payment_password_Yes)
    LinearLayout Payment_password_Yes;
    @Bind(R.id.Payment_password_No)
    LinearLayout Payment_password_No;
    private PreActPassWordI mPreActPassWordI;
    private AlertDialog mCommitDialog;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_home_password);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("密码管理");
        toolbar_title.setTextSize(18);
    }

    @Override
    protected void setListeners() {
        mPreActPassWordI = new PrePreActPassWordImpl(this);
        mPreActPassWordI.isSetPayPwd(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag());
    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.Payment_password_No, R.id.Forget_pay_password, R.id.Reset_login_password,
            R.id.Reset_payment_password})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.Payment_password_No:
                startActivity(new Intent(this, ActSetPayPassWord.class));
                break;
            case R.id.Forget_pay_password:
                startActivity(new Intent(this, ActForgetPayPassWord.class));
                break;
            //重置登录密码
            case R.id.Reset_login_password:
//                startActivity(new Intent(this, ActResetLoginPW.class));
//                showLoginDialog();
                startActivity(new Intent(this, ActResetLoginPW.class));
                finish();
                break;
            //重置支付密码
            case R.id.Reset_payment_password:
//                showPayDialog();
                startActivity(new Intent(this, ActResetPayPassword.class));
                break;

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (TempLoginConfig.sf_getPayState()) {
            Payment_password_No.setVisibility(View.GONE);
            Payment_password_Yes.setVisibility(View.VISIBLE);
        } else {
            Payment_password_Yes.setVisibility(View.GONE);
            Payment_password_No.setVisibility(View.VISIBLE);
        }

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
    public void isSetPayPwdSuccee(RespActPassWord data) {
        if (data.getResult().equals("0")) {
            Payment_password_Yes.setVisibility(View.GONE);
            Payment_password_No.setVisibility(View.VISIBLE);
        } else {
            Payment_password_No.setVisibility(View.GONE);
            Payment_password_Yes.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void resetLoginPwdSuccee(TempResponse data) {
        showToast(data.getMsg());
        finish();
    }

    @Override
    public void resetPayPwdSuccee(TempResponse data) {
        showToast(data.getMsg());
    }

    //    //重置登录密码
//    private void showLoginDialog() {
//
//        View view = getLayoutInflater().inflate(R.layout.dialog_reset_login_password, null);
//        Button mQuit = (Button) view.findViewById(R.id.dialog_clear_cache_quit);
//        Button mCommit = (Button) view.findViewById(R.id.dialog_clear_cache_commit);
//        mCommitDialog = new AlertDialog.Builder(this).setView(view).create();
//        mCommitDialog.show();
//
//        mQuit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (QuickClickUtils.checkClick())return;
//                 mCommitDialog.dismiss();
//            }
//        });
//
//        mCommit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (QuickClickUtils.checkClick())return;
//                mCommitDialog.dismiss();
//                showProgressDialog(false);
//                mPreActPassWordI.resetLoginPwd();
//                dismissProgressDialog();
//            }
//        });
//    }
//    //重置支付密码
//    private void showPayDialog() {
//
//        View view = getLayoutInflater().inflate(R.layout.dialog_reset_login_password, null);
//        Button mQuit = (Button) view.findViewById(R.id.dialog_clear_cache_quit);
//        Button mCommit = (Button) view.findViewById(R.id.dialog_clear_cache_commit);
//        mCommitDialog = new AlertDialog.Builder(this).setView(view).create();
//        mCommitDialog.show();
//
//        mQuit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (QuickClickUtils.checkClick()) return;
//                mCommitDialog.dismiss();
//            }
//        });
//
//        mCommit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (QuickClickUtils.checkClick()) return;
//                mCommitDialog.dismiss();
//                showProgressDialog(false);
//                mPreActPassWordI.resetPayPwd();
//                dismissProgressDialog();
//            }
//        });
//    }
}
