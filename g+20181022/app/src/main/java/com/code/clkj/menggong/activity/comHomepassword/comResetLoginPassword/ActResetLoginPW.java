package com.code.clkj.menggong.activity.comHomepassword.comResetLoginPassword;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 重置登录密码
 * Created by Administrator on 2017-12-12.
 */

public class ActResetLoginPW extends TempActivity implements ViewActResetLoginPWI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.password_01)
    EditText password_01;
    @Bind(R.id.password_02)
    EditText password_02;
    private PreActResetLoginPWI mPre;


    String PassWord_01 = "";
    String PassWord_02 = "";

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_set_password_layout_again);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("重置登录密码");
        toolbar_title.setTextSize(18);
    }

    @Override
    protected void setListeners() {
        mPre = new PreActResetLoginPWImpl(this);
    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick(R.id.confirm_btn)
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
                PassWord_01 = password_01.getText().toString();
                PassWord_02 = password_02.getText().toString();
                if (TextUtils.isEmpty(PassWord_01)) {
                    showToast("请输入原密码");
                }
                if (TextUtils.isEmpty(PassWord_02)) {
                    showToast("请输入新密码");
                }
                mPre.resetLoginPwd(PassWord_01,PassWord_01,PassWord_02);
                break;
        }
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showProgressDialog(false);
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
    public void resetLoginPwdSuccee(TempResponse data) {
        showToast(data.getMsg());
        TempLoginConfig.sf_savePwd(PassWord_02);
        startActivity(new Intent(this, ActHome.class));
        finish();
    }
}
