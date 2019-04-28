package com.code.clkj.menggong.activity.comUserLogin;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.bean.User;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.comApp.AppManager;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;


/**
 * 登录界面
 */
public class LoginActivity extends TempActivity implements ViewActLoginI {

    @Bind(R.id.mHeadIcon)
    ImageView mHeadIcon; //头像
    @Bind(R.id.mUserID)
    EditText mUserID; //用户ID
    @Bind(R.id.mPassWord)
    EditText mPassWord; //密码
//    @Bind(R.id.mInviteID)
//    EditText mInviteID; //邀请人ID
    @Bind(R.id.mForgetPassWord)
    TextView mForgetPassWord;  //忘记密码
    @Bind(R.id.mLoginButton)
    TextView mLoginButton;   //登录按钮
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbarMenuTv;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    private String password,phone;
    private TempRegexUtil tempRegexUtil;
    private PreActLoginI mPreI;
    private boolean isExitLogin;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_layout);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbarMenuTv.setVisibility(View.VISIBLE);
        toolbarMenuTv.setText("注册");
        toolbarMenuTv.setTextSize(16);
        toolbarMenuTv.setTextColor(Color.parseColor("#f70052"));
        toolbar_title.setText("");
        User user = new User();
        user.setId(mUserID.getText().toString());
        user.setPwd(mPassWord.getText().toString());
        isExitLogin=getIntent().getBooleanExtra("isExitLogin",false);
        toolbar_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                if (isExitLogin)//如果是退出登录后  返回直接退出app
                    exit();
                else
                    finish();*/
            }
        });

    }

    @Override
    protected void setListeners() {
        tempRegexUtil = new TempRegexUtil();
        mPreI = new PreActLoginImpl(this);
    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.mLoginButton, R.id.mForgetPassWord,R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mLoginButton:
                // TODO 登录按钮的点击事件
//                    Intent intent =new Intent(this, ActHome.class);
//                    startActivity(intent);
                phone = mUserID.getText().toString().trim();
                password = mPassWord.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入电话号码");
                    return;
                }
                if (!tempRegexUtil.checkMobile(phone)) {
                    showToast("请输入正确的电话号码");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                mPreI.userLogin(phone,password);
                break;
            case R.id.mForgetPassWord:
                //TODO 忘记密码的点击事件
                Intent intent = new Intent(this, ForgetPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_menu_tv:
                Intent intent_toolbar_menu_tv = new Intent(this,RegisterActivity.class);
                startActivity(intent_toolbar_menu_tv);
                break;
                default:
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
    public void userLoginSuccess(ResponseLoginInfo data) {
        TempLoginConfig.sf_savePwd(password);
        startActivity(new Intent(this, ActHome.class));

        io.rong.imlib.model.UserInfo userInfo;

        if (!TextUtils.isEmpty(TempLoginConfig.sf_getImage())){
            userInfo = new UserInfo(TempLoginConfig.sf_getSueid(),data.getResult().getMuseNickName(), Uri.parse(BaseUriConfig.makeImageUrl(data.getResult().getMuseImage())) );

        }else {
            Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.mipmap.ic_launcher) + "/" + getResources().getResourceTypeName(R.mipmap.ic_launcher) + "/" + getResources().getResourceEntryName(R.mipmap.ic_launcher));
            userInfo = new UserInfo(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getNickName(), uri);
        }
        RongIM.getInstance().setCurrentUserInfo(userInfo);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0&&isExitLogin) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    long mExitTime=0;
    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            try {
                AppManager.getAppManager().AppExit();
                finish();
                //   TempApplication.getInstance().onTerminate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
