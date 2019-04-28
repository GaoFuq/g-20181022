package com.code.clkj.menggong.activity.comUserLogin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempModule.tempUtils.TempTimeUtil;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码界面
 */


public class ForgetPassWordActivity extends TempActivity implements ViewActForgetPasswordI {

    @Bind(R.id.mUserPhone)
    EditText mUserPhone;  //用户手机号
    @Bind(R.id.mVerificationCode)
    EditText mVerificationCode;  //验证码
    @Bind(R.id.mSendVerificationCode)
    Button mSendVerificationCode; //发送验证码按钮
    @Bind(R.id.mNewPassdWord)
    EditText mNewPassdWord;  //新密码
    @Bind(R.id.mConfirm)
    TextView mConfirm; //确认按钮
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbarMenuTv;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    private TempRegexUtil tempRegexUtil;
    private PreActForgetPasswordI mPreI;
    private TempTimeUtil tempTimeUtil;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget_pass_word);
    }

    @Override
    protected void findViews() {
        initdata();
    }

    @Override
    protected void setListeners() {
        tempRegexUtil = new TempRegexUtil();
        mPreI = new PreActForgetPasswordImpl(this);
        tempTimeUtil = new TempTimeUtil(60000, 1000, mSendVerificationCode);
        tempTimeUtil.setTickString(getString(R.string.Send));
        tempTimeUtil.setFinishString(getString(R.string.Retrieve_verification_code));

    }

    @Override
    protected void bindValues() {

    }
    String phone;
    String password;
    String phonecode="";
    @Override
    @OnClick({R.id.mSendVerificationCode, R.id.mConfirm})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mSendVerificationCode:
//                Toast.makeText(this, "发送验证码", Toast.LENGTH_SHORT).show();
                 phonecode = mUserPhone.getText().toString();
                if (!TextUtils.isEmpty(phonecode) && tempRegexUtil.checkMobile(phonecode)) {
                    mPreI.sendForgetCode(phonecode);
                } else {
                    showToast("请输入正确的电话号码");
                }
                break;
            case R.id.mConfirm:
//                Toast.makeText(this, "重置密码成功", Toast.LENGTH_SHORT).show();
                phone = mUserPhone.getText().toString();
                String registercheck = mVerificationCode.getText().toString();
                password = mNewPassdWord.getText().toString();

                if (TextUtils.isEmpty(phone)){
                    showToast("请输入电话号码");
                    return;
                }
                if (!tempRegexUtil.checkMobile(phone)){
                    showToast("请输入正确的电话号码");
                    return;
                }
                if (TextUtils.isEmpty(registercheck)){
                    showToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                mPreI.doForget(registercheck,phone,password);
                break;
        }
    }
    public void initdata() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("找回密码");
        toolbar_title.setTextSize(18);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
    public void doForgetSuccess(TempResponse data) {
        showToast(data.getMsg());
        finish();
    }

    @Override
    public void sendForgetCodeSuccess(TempResponse data) {
        tempTimeUtil.start();
    }
}
