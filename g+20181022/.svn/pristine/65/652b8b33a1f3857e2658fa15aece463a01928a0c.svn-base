package com.code.clkj.menggong.activity.comSetUp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comSetUp.comActFeedBackActivity.PreActFeedBackActivityI;
import com.code.clkj.menggong.activity.comSetUp.comActFeedBackActivity.PreActFeedBackActivityImpl;
import com.code.clkj.menggong.activity.comSetUp.comActFeedBackActivity.ViewActFeedBackActivityI;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ActFeedBackActivity extends TempActivity implements ViewActFeedBackActivityI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.phone_num)
    EditText phone_num;
    private String mPhone;//电话
    private String mContent;//内容
    private PreActFeedBackActivityI mPreI;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_feedback_layout);

    }

    @Override
    protected void findViews() {
        initData();
        mPreI = new PreActFeedBackActivityImpl(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick(R.id.save_feedback)
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.save_feedback:
                mPhone = phone_num.getText().toString();
                mContent = content.getText().toString();

                if (TextUtils.isEmpty(mPhone)) {
                    toast("请输入内容");
                }
                if (TextUtils.isEmpty(mContent)) {
                    toast("请输入电话");
                }
                mPreI.saveFeedBack(mPhone, mContent);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("意见反馈");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void saveFeedBackSuccess(TempResponse data) {
        if (data!=null) {
            toast(data.getMsg());
            finish();
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
}
