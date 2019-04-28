package com.code.clkj.menggong.activity.comHomepassword;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.activity.comHomepassword.comForgetPayPassWord.ActForgetPayPassWord;
import com.code.clkj.menggong.activity.comHomepassword.comPayPassWord.ActSetPayPassWord;
import com.code.clkj.menggong.activity.comHomepassword.comPayPassWord.ActtuijianUser;
import com.code.clkj.menggong.activity.comHomepassword.comResetLoginPassword.ActResetLoginPW;
import com.code.clkj.menggong.activity.comHomepassword.comResetPayPassword.ActResetPayPassword;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespActPassWord;
import com.code.clkj.menggong.response.RespActmyDisplayCode;
import com.code.clkj.menggong.response.RespActmySpreadPng;
import com.code.clkj.menggong.response.RespTuguannumber;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.sumimakito.awesomeqr.AwesomeQRCode;
import com.lf.PayAndShare.tempShare.TempShareVSCustomHelper;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ActTuiguang extends TempActivity implements ViewActPassWordI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;

/*   @Bind(R.id.go_tuiguang)
    TextView go_tuiguang;*/
    @Bind(R.id.mtuiguang)
    LinearLayout mtuiguang;

    @Bind(R.id.tuiguan_numnber)
    TextView tuiguan_numnber;

    @Bind(R.id.user_numnber)
    TextView user_numnber;

    @Bind(R.id.head_img)
    ImageView head_img;

    @Bind(R.id.yaoqingma)
    TextView yaoqingma;



    private PreActPassWordI mPreActPassWordI;
    private AlertDialog mCommitDialog;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_home_tuiguang);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("我的推广");
        toolbar_title.setTextSize(18);
        toolbar_menu_tv.setVisibility(View.GONE);
        toolbar_menu_tv.setText("分享");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setTextColor(Color.parseColor("#000000"));

        setClick();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).mySpread(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag()), new TempRemoteApiFactory.OnCallBack<RespTuguannumber>() {
            @Override
            public void onSucceed(RespTuguannumber data) {
                if (data.getFlag()==1){

                    if(!TextUtils.isEmpty(data.getResult().getTotal_user())){
                        user_numnber.setText(data.getResult().getTotal_user());
                    }
                    else{
                        user_numnber.setText("0");
                    }

                    if(!TextUtils.isEmpty(data.getResult().getTotal_integral())){
                        tuiguan_numnber.setText(data.getResult().getTotal_integral());
                    }
                    else{
                        tuiguan_numnber.setText("0");
                    }

                   /* if (mView!=null){
                        mView.isSetPayPwdSuccee(data);
                    }*/
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void setListeners() {




    }

    @Override
    protected void bindValues() {

    }
    private void setClick(){



        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).myDisplayCode(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag()), new TempRemoteApiFactory.OnCallBack<RespActmyDisplayCode>() {
            @Override
            public void onSucceed(RespActmyDisplayCode data) {
                if (data.getFlag()==1){

                   yaoqingma.setText(data.getResult());

                   /* if (mView!=null){
                        mView.isSetPayPwdSuccee(data);
                    }*/
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        se();
    }
    private  void se(){
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).mySpreadPng(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),TempLoginConfig.sf_getOnlineTag()), new TempRemoteApiFactory.OnCallBack<RespActmySpreadPng>() {
        @Override
        public void onSucceed(RespActmySpreadPng data) {
            if (data.getFlag()==1){
                generateCode(data.getResult(),head_img);
              //   head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult()));

            }
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            generateCode(e.toString(),head_img);
         //   head_img.setImageURI(BaseUriConfig.makeImageUrl(e.toString()));
        }
    });

    }
    //二维码
    private void generateCode(String code,final ImageView view) {
        Resources res = ActTuiguang.this.getResources();
     //   Bitmap mBitmap = BitmapFactory.decodeResource(res, R.mipmap.icon_card);
        new AwesomeQRCode.Renderer()
                .contents(code)
                /* .size((int) (0.58 * screenWidth))//二维码的大小*/
//                                    .margin(getResources().getDimensionPixelSize(R.dimen.qrcode_size_margin))//二维码边距即留白大小
                .dotScale(1)//点的伸缩比例
               /* .logoRadius(getResources().getDimensionPixelOffset(R.dimen.qrcode_size_logo_radius))//中心logo圆角大小
                .logo(mBitmap)//中心logo*/
                .logoScale(0.25F)//logo占二维码的比例
                .renderAsync(new AwesomeQRCode.Callback() {
                    @Override
                    public void onRendered(AwesomeQRCode.Renderer renderer, final Bitmap bitmap) {
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              view.setImageBitmap(bitmap);
                                          }
                                      }
                        );
                    }

                    @Override
                    public void onError(AwesomeQRCode.Renderer renderer, Exception e) {
                        e.printStackTrace();
                    }
                });
    }
/*,R.id.go_tuiguang*/
    @Override
    @OnClick({R.id.mtuiguang,R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        TempShareVSCustomHelper mHelper;
        switch (v.getId()) {
           case R.id.mtuiguang:
                startActivity(new Intent(this, ActtuijianUser.class));
                break;
            case R.id.toolbar_menu_tv:
                 mHelper = new TempShareVSCustomHelper(getTempContext(), BaseUriConfig.BASE_URL+"app/public/memberSpread/getSpread?museId="+TempLoginConfig.sf_getSueid(), "G+", "分享", new UMImage(this, R.mipmap.ic_launcher));
                mHelper.showShare();
                break;
           /* case R.id.go_tuiguang:
                 mHelper = new TempShareVSCustomHelper(getTempContext(), BaseUriConfig.BASE_URL+"app/public/memberSpread/getSpread?museId="+TempLoginConfig.sf_getSueid(), "G+", "分享", new UMImage(this, R.mipmap.ic_launcher));
                mHelper.showShare();
                break;*/
          /*   case R.id.Forget_pay_password:
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
                break;*/

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (TempLoginConfig.sf_getPayState()) {
           /* Payment_password_No.setVisibility(View.GONE);
            Payment_password_Yes.setVisibility(View.VISIBLE);*/
        } else {
          /*  Payment_password_Yes.setVisibility(View.GONE);
            Payment_password_No.setVisibility(View.VISIBLE);*/
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
           /* Payment_password_Yes.setVisibility(View.GONE);
            Payment_password_No.setVisibility(View.VISIBLE);*/
        } else {
           /* Payment_password_No.setVisibility(View.GONE);
            Payment_password_Yes.setVisibility(View.VISIBLE);*/
        }
    }

    @Override
    public void resetLoginPwdSuccee(TempResponse data) {
       /* showToast(data.getMsg());
        finish();*/
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
