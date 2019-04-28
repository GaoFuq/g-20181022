package com.code.clkj.menggong.activity.comLiveApply.comAuthenticationState;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comCameraStreaming.ExtCapStreamingActivity;
import com.code.clkj.menggong.activity.comLiveApply.comLiveApplyAgain.ActLiveApplyAgainActivity;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主播认证结果（成功，失败，审核中）
 * Created by Administrator on 2017-12-19.
 */

public class ActAuthenticationState extends TempActivity implements ViewqueryCanLivingRoomI {

    @Bind(R.id.state_bg)
    RelativeLayout state_bg;//抬头背景
    @Bind(R.id.state_sign)
    ImageView state_sign;//状态图片
    @Bind(R.id.state_sign_txt)
    TextView state_sign_txt;//状态文字
    @Bind(R.id.result_txt)
    TextView result_txt;//结果01
    @Bind(R.id.result_txt_02)
    TextView result_txt_02;//结果02
    @Bind(R.id.state_result)
    TextView state_result;//按钮


    private int mState;
    private String usceId01;
    private String failureCause;//认证失败原因
    private PrequeryCanLivingRoomI mPreI;

    private String URL;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_authentication_state);
    }

    @Override
    protected void findViews() {
        mPreI = new PrequeryCanLivingRoomImpl(this);
        mState = getIntent().getIntExtra("state", 3);
        usceId01 = getIntent().getStringExtra("usceId");
        failureCause = getIntent().getStringExtra("failureCause");

    }

    @Override
    protected void setListeners() {
        if (mState == 1) {
            state_bg.setBackgroundResource(R.mipmap.state_bg_center);
            state_sign.setBackgroundResource(R.mipmap.state_sign_center);
            state_sign_txt.setText("审核中...");
            result_txt.setText("提交成功，请等待管理员审核!");
            result_txt_02.setText("请耐心等待吧");
        } else if (mState == 2) {
            state_bg.setBackgroundResource(R.mipmap.state_bg_success);
            state_sign.setBackgroundResource(R.mipmap.state_sign_success);
            state_sign_txt.setText("审核成功");
            result_txt.setText("恭喜你的主播认证成功");
            state_result.setVisibility(View.VISIBLE);
            state_result.setText("进入直播");
        } else if (mState == 3) {
            state_bg.setBackgroundResource(R.mipmap.state_bg_faile);
            state_sign.setBackgroundResource(R.mipmap.state_sign_faile);
            state_sign_txt.setText("审核失败");
//            result_txt.setText("抱歉你的主播认证未通过");
            result_txt.setText(failureCause);//显示认证失败的原因
            state_result.setVisibility(View.VISIBLE);
            state_result.setText("重新认证");

        } else {

        }
    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.state_result, R.id.back_llt})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.state_result:
                if (state_result.getText().toString().equals("重新认证")) {
                    Intent intent = new Intent(this, ActLiveApplyAgainActivity.class);
                    intent.putExtra("usceId", usceId01);
                    startActivity(intent);
                    finish();
                }
                if (state_result.getText().toString().equals("进入直播")) {
                    mPreI.queryCanLivingRoom();
                }
                break;

            case R.id.back_llt:
                finish();
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
    public void queryCanLivingRoom(RespQueryCanLivingRoom data) {
        if (data.getResult().getRoomPushUrl()!=null) {
            Intent intent = new Intent(this, ExtCapStreamingActivity.class);
            intent.putExtra("URL", data.getResult().getRoomPushUrl());
            startActivity(intent);
            finish();
        }else{
            showToast("请重新点击确认按钮");
        }
    }
}
