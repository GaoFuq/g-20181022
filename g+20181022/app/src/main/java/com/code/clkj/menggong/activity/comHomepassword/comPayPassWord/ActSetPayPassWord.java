package com.code.clkj.menggong.activity.comHomepassword.comPayPassWord;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置支付密码
 * Created by Administrator on 2017-12-12.
 */

public class ActSetPayPassWord extends TempActivity implements ViewSetPayPassWordI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.password_01)
    EditText password_01;
    @Bind(R.id.password_02)
    EditText password_02;


    String PassWord_01 = "";
    String PassWord_02 = "";


    private PreSetPayPassWordI mView;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_set_password_layout);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("设置支付密码");
        toolbar_title.setTextSize(18);
    }

    @Override
    protected void setListeners() {
        mView = new PreSetPayPassWordImpl(this);
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
                if (TextUtils.isEmpty(PassWord_01)){
                   showToast("请输入密码");
                }
                if (TextUtils.isEmpty(PassWord_02)){
                    showToast("请再次输入密码");
                }
                mView.setUserPayPwd(PassWord_01,PassWord_02);
                break;
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
    public void setUserPayPwdSuccee(TempResponse data) {
        showToast(data.getMsg());
        TempLoginConfig.sf_savePayState(true);
        finish();
    }
}
