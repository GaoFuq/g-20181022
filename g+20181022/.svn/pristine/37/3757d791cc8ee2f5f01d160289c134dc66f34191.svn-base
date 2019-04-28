package com.code.clkj.menggong.activity.comSetUp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.activity.comWeb.ActWeb;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespActPhone;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ActSetUpActivity extends TempActivity implements ViewActSetUpActivityI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.phone)
    LinearLayout phone;
    @Bind(R.id.exit_login)
    TextView exit_login;

    private PreActSetUpActivityI mPreI;
    private AlertDialog mCommitDialog;
//    @Bind(R.id.Common_problem)
//    ImageView Common_problem;//常见问题
//    @Bind(R.id.Feedback)
//    ImageView Feedback;//意见反馈
//    @Bind(R.id.Aboutus)
//    ImageView Aboutus;//关于我们


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_live_set_up_layout);
    }

    @Override
    protected void findViews() {
        initData();

    }

    @Override
    protected void setListeners() {
        mPreI = new PreActSetUpActivityImpl(this);
        mPreI.contactInformation();
    }

    @Override
    protected void bindValues() {
        if (TempLoginConfig.sf_getLoginState()){
            exit_login.setVisibility(View.VISIBLE);
        }else{
            exit_login.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.Common_problem, R.id.Feedback, R.id.Aboutus, R.id.exit_login, R.id.phone, R.id.hcql})
    @Override
    protected void OnViewClicked(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.Common_problem:
                Intent intentCommon_problem = new Intent(this, ActCommonProblemActivity.class);
                startActivity(intentCommon_problem);
                break;
            case R.id.Feedback:
                Intent intentFeedBack = new Intent(this, ActFeedBackActivity.class);
                startActivity(intentFeedBack);
                break;
            case R.id.Aboutus:
//                Intent intentAboutus = new Intent(this, ActAboutUsActivity.class);
//                startActivity(intentAboutus);
                intent = new Intent(this, ActWeb.class);
                intent.putExtra(Constance.KEY_WEB_TITLE, "关于我们");
                intent.putExtra(Constance.KEY_WEB_URL, TempURIConfig.PLARMINFO);
                startActivity(intent);
                break;
            case R.id.phone:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + textView2.getText().toString())));
                break;
            case R.id.exit_login://退出登录
                mPreI.updateLoginById(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag());
                break;
            case R.id.hcql:
                showDialog();
                break;

        }

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("设置");
        toolbar_title.setTextSize(18);
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
    public void updateLoginByIdSuccess(TempResponse data) {
        TempLoginConfig.sf_saveLoginState(false);
        TempLoginConfig.sf_clearLoginInfo();
        startActivity(new Intent(this, LoginActivity.class).putExtra("isExitLogin", true));
        finish();
    }

    @Override
    public void contactInformationSuccee(RespActPhone data) {
        textView2.setText(data.getResult());
    }

    //清除缓存确认框
    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.pop_common_dialog, null);
        TextView context_tv = (TextView) view.findViewById(R.id.context_tv01);
        TextView title_tv01 = (TextView) view.findViewById(R.id.title_tv01);
        TextView wallet_sure = (TextView) view.findViewById(R.id.wallet_sure01);//确定
        TextView wallet_back = (TextView) view.findViewById(R.id.wallet_back01);//取消
        title_tv01.setText("清理缓存");
        context_tv.setText("尊敬的用户，您确定要清除缓存吗？");
//        View view = getLayoutInflater().inflate(R.layout.dialog_clear_cache_layout, null);
//        Button mQuit = (Button) view.findViewById(R.id.dialog_clear_cache_quit);
//        Button mCommit = (Button) view.findViewById(R.id.dialog_clear_cache_commit);
        mCommitDialog = new AlertDialog.Builder(this).setView(view).create();
        mCommitDialog.show();

        wallet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommitDialog.dismiss();
            }
        });

        wallet_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                               new DataCleanManager().deleteDir(new File(Constants.SDCARD_CACHE_PATH));
//                                DataClearManger.cleanApplicationData(getApplicationContext(), null);
                Toast.makeText(getTempContext(), "缓存清理成功！", Toast.LENGTH_SHORT).show();
                mCommitDialog.dismiss();
            }
        });
    }
}
