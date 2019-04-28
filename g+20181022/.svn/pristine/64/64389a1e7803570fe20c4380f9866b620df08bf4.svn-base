package com.code.clkj.menggong.activity.comMyWallet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.PreCheckBalanceI;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.PreCheckBalanceImpl;
import com.code.clkj.menggong.activity.comMyWallet.comCheckBalance.ViewCheckBalanceI;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.PreActPaymentMethodActivityI;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.PreActPaymentMethodActivityImpl;
import com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod.ViewActPaymentMethodActivityI;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespCheckLS;
import com.code.clkj.menggong.response.RespGetAlipayInfo;
import com.code.clkj.menggong.response.RespGetWxpayInfo;
import com.code.clkj.menggong.response.RespPayOrder;
import com.code.clkj.menggong.response.RespQueryAppOrderPayMentType;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.PayAndShare.tempALiPay.PayResult;
import com.lf.PayAndShare.tempALiPay.TempAliPayHelper;
import com.lf.PayAndShare.tempWXPay.TempWXPayHelper;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付
 */

public class ActPaymentMethodActivity1 extends TempActivity implements ViewActPaymentMethodActivityI, ViewCheckBalanceI, PayPwdView.InputCallBack {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.act_pay_wechat)
    CheckBox act_pay_wechat;//act_pay_wechat//act_pay_bank
    private static final int SDK_PAY_FLAG = 1;
    @Bind(R.id.act_pay_zhifubao_image)
    ImageView act_pay_zhifubao_image;//支付宝图片
    @Bind(R.id.act_pay_zhifubao_frame)
    LinearLayout act_pay_zhifubao_frame;//支付宝
    @Bind(R.id.act_pay_zhifubao_tx)
    TextView act_pay_zhifubao_tx;//支付宝 名字
    @Bind(R.id.act_pay_alipay)
    CheckBox act_pay_alipay;//支付宝复选框
    @Bind(R.id.act_pay_weixin_image)
    RelativeLayout act_pay_weixin_image;

    @Bind(R.id.act_pay_weixin_frame)
    LinearLayout mActPayWeixinFrame;
    @Bind(R.id.act_pay_yinlian_image)
    ImageView act_pay_yinlian_image;//微信图片
    @Bind(R.id.act_pay_yinlian_frame)
    LinearLayout act_pay_yinlian_frame;//微信
    @Bind(R.id.act_pay_yinlian_tx)
    TextView act_pay_yinlian_tx;//微信 名字
    @Bind(R.id.act_pay_bank)
    CheckBox act_pay_bank;//微信复选框


    @Bind(R.id.act_pay_dzWallet_image)
    ImageView act_pay_dzWallet_image;//电子钱包图片

    @Bind(R.id.act_pay_dianzi_ly)//电子钱包
            LinearLayout act_pay_dianzi_ly;

    @Bind(R.id.act_pay_dzWallet_tx)//电子钱包名字
            TextView act_pay_dzWallet_tx;
    @Bind(R.id.dianzi_banlance_tx)
    TextView dianzi_banlance_tx;//电子钱包余额

    @Bind(R.id.act_pay_dianzi)
    CheckBox act_pay_dianzi;//电子钱包复选框
    @Bind(R.id.act_pay_layout)
    LinearLayout act_pay_layout;
    @Bind(R.id.act_pay_fukuan_btn)
    Button act_pay_fukuan_btn;//付款按钮

    @Bind(R.id.act_pay_yue)
    CheckBox act_pay_yue;

    @Bind(R.id.act_pay_yue_frame)
    LinearLayout act_pay_yue_frame;

    @Bind(R.id.pay_shengyu)
    TextView pay_shengyu;

    @Bind(R.id.toolbar_top)
    Toolbar toolbarTop;
    @Bind(R.id.jine)
    LinearLayout jine;
    @Bind(R.id.dzqb)
    TextView dzqb;

    private PreActPaymentMethodActivityI mPreI;//支付类型
    private PreCheckBalanceI mBanlanceI;//电子钱包余额
    private TempAliPayHelper tempAliPayHelper;
    private TempWXPayHelper tempWXPayHelper;

    private String state;

    private static final String TAG = "ActPayment";
    private String mObject = "", mOrderNo = "1", mSuccess = "支付成功";
    String mPrice = "";
    //   private MyInputPwdUtil myInputPwdUtil;
    //  , name

    private String mordNo;//订单号
    private String OderMordNo;
    private String flag;
    private String total_price_number;//钱

    String dianziPsw;//获得电子钱包的密码
    List<RespQueryAppOrderPayMentType.ResultBean> mPayTypeList;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        super.setContentView(R.layout.act_payment_layout);
    }

    @Override
    protected void findViews() {
        initData();
        mPrice = getIntent().getStringExtra(Constance.KEY_GOODS_PRICE);
        mObject = getIntent().getStringExtra(Constance.KEY_GOODS_TITLE);
        if(TextUtils.isEmpty(mObject)){
            mObject="充值";
        }
        OderMordNo = getIntent().getStringExtra("OderMordNo");
//        mPrice = "0.01";
//        TempLoginConfig.sf_savePayWeixin("1");

    }

    private void initData() {
        Toolbar toolbar_top = (Toolbar) findViewById(R.id.toolbar_top);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(getResources().getColor(R.color.toolbar_top_color));
        TextView toolbar_title = (TextView) toolbar_top.findViewById(R.id.toolbar_title);
        toolbar_title.setText("请选择支付方式");
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        //  myInput();
        total_price_number = getIntent().getStringExtra("total_price_number");
        state = getIntent().getStringExtra("state");
        mordNo = getIntent().getStringExtra("mordNo");
        if (state != null) {
            if (state.equals("1")) {
                mordNo = getIntent().getStringExtra("tradeNo");
            }
            if (state.equals("2")) {
                mordNo = getIntent().getStringExtra("OderMordNo");
            }
            if (state.equals("3")) {
                mordNo = getIntent().getStringExtra("tradeNo");
                dzqb.setVisibility(View.GONE);
                act_pay_dianzi_ly.setVisibility(View.GONE);
            }
        }
        mPreI = new PreActPaymentMethodActivityImpl(this);
        mPreI.queryAppOrderPayMentType(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd());
        mBanlanceI = new PreCheckBalanceImpl(this);
        mBanlanceI.getMuseEpurse();
        if (TempLoginConfig.sf_getLoginState()) {
            mPreI.getAlipayInfo(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd());
            mPreI.getWxpayInfo(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd());
//            mPrestener.getAlipayInfo(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPassWord(), TempLoginConfig.sf_getOnLineKey());
//            mPrestener.getWxpayInfo(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPassWord(),"2");
        }

    }

    @Override
    @OnClick({R.id.act_pay_dianzi_ly,
            R.id.act_pay_zhifubao_frame,
            R.id.act_pay_weixin_frame,
            R.id.act_pay_yinlian_frame,
            R.id.act_pay_fukuan_btn,
            R.id.act_pay_yue_frame
    })
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.act_pay_dianzi_ly:
                upCheckBox(1);
                break;
            case R.id.act_pay_zhifubao_frame:
                upCheckBox(2);
                break;

            case R.id.act_pay_yinlian_frame:
                upCheckBox(3);
                break;
            case R.id.act_pay_weixin_frame:
                upCheckBox(4);
                break;
            case R.id.act_pay_yue_frame:
                upCheckBox(5);
                break;
            case R.id.act_pay_fukuan_btn:
                if (!TempLoginConfig.sf_getLoginState()) {
                    showToast("您未登录");
                } else {
                    if (type == 0) {
                        showToast("请选择支付方式");
                        return;
                    } else {
                        pay();
                    }
                }
                break;


            default:
                break;
        }

    }

    /**
     * 更改支付状态
     */
    private int type = 1;

    private void upCheckBox(int i) {
        act_pay_dianzi.setChecked(false);
        act_pay_alipay.setChecked(false);
        act_pay_bank.setChecked(false);
        act_pay_wechat.setChecked(false);
        act_pay_yue.setChecked(false);
        type = i;
        switch (i) {
            case 1:
                act_pay_dianzi.setChecked(true);
                break;
            case 2:
                act_pay_alipay.setChecked(true);
                break;
            case 3:
                act_pay_bank.setChecked(true);
                break;
            case 4:
                act_pay_wechat.setChecked(true);
                break;
            case 5:
                act_pay_yue.setChecked(true);
                break;
        }
    }


    /**
     * 支付
     */
    private void pay() {
        switch (type) {
            case 1:
                initpop();
                break;
            case 2:
                if (tempAliPayHelper != null)
                    tempAliPayHelper.pay();
                break;
            case 3:
                //    showToast(Constance.NONE);
                if (tempWXPayHelper != null) {
                    tempWXPayHelper.pay();
                }
                break;
            case 4:

                break;
            case 5:
                break;
            default:
                showToast("请选择支付方式");
                break;
        }

    }

    private void initpop() {

        Bundle bundle = new Bundle();
        bundle.putString(PayFragment.EXTRA_CONTENT, "¥ " + total_price_number);

        PayFragment fragment = new PayFragment();
        fragment.setArguments(bundle);
        fragment.setPaySuccessCallBack(this);
        fragment.show(getSupportFragmentManager(), "Pay");

    }

    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    String date = sDateFormat.format(new Date());

    //输入电子钱包 输入密码成功回调
    @Override
    public void onInputFinish(String result) {
        dianziPsw = result;//密码
        mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 3 + "", mordNo, date, mordNo, dianziPsw);//电子钱包支付

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

//        if (mPayTypeList.get(0).getOptyId().equals("1")) {
//            mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 1 + "", mordNo, date, mordNo, "");//支付宝支付
//        }
//        if (mPayTypeList.get(1).getOptyId().equals("2")) {
//            mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 2 + "", mordNo, date, mordNo, "");//微信支付
//        }
//        if (mPayTypeList.get(2).getOptyId().equals("3")) {
//            mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 3 + "", mordNo, date, mordNo, "123456");// TODO: 2018/1/12 先传一个死的密码
//        }

    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    Debug.error("payResult.toString()" + payResult.toString());
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    Debug.error(resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    Debug.error(resultStatus);
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Debug.error("支付成功");
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String date = sDateFormat.format(new Date());
                        Debug.error("payResult.toString()" + payResult.toString());
                        Debug.error("---------支付时间 date--------" + date);
                        showToast(mSuccess);
                        if (type==2) {
                            mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 1 + "", mordNo, date, mordNo, "");
                        }
                        if (type==3) {
                            mPreI.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), 2 + "", mordNo, date, mordNo, "");
                        }

//                        if(mOrderNo.indexOf("g")!=-1){
//                            mPrestener.memberpayOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPassWord(),1+"",mOrderNo,date,mOrderNo);
//                        }else{
//                            mPrestener.payOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPassWord(),1+"",mOrderNo,date,mOrderNo);
//                        }
                        //mPrestener.payOrder(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPassWord(),TempLoginConfig.sf_getOnLineKey(), Constance.KEY_ALIPAY_TYPE_ID+"","",date,mOrderNo);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(ActPaymentMethodActivity1.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(ActPaymentMethodActivity1.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
            }
            return false;
        }
    });


    /**
     * 支付成功
     *
     * @param data
     */
    @Override
    public void payOrderSuccess(RespPayOrder data) {
//            AppManager.getAppManager().finishAllActivity1();
        showToast(data.getMsg());
        finish();
//        return;
    }

    /**
     * 支付类型成功
     *
     * @param data
     */
    @Override
    public void queryAppOrderPayMentTypeSuccess(RespQueryAppOrderPayMentType data) {
        mPayTypeList = data.getResult();

        if (data.getResult().get(0).getOptyId().equals("1")) {
            Glide.with(this).load(BaseUriConfig.makeImageUrl(data.getResult().get(0).getOptyImage())).into(act_pay_zhifubao_image);//支付宝
            act_pay_zhifubao_tx.setText(data.getResult().get(0).getOptyName());
        }
        if (data.getResult().get(1).getOptyId().equals("2")) {
            Glide.with(this).load(BaseUriConfig.makeImageUrl(data.getResult().get(1).getOptyImage())).into(act_pay_yinlian_image);//微信
            act_pay_yinlian_tx.setText(data.getResult().get(1).getOptyName());
        }
        if (data.getResult().get(2).getOptyId().equals("3")) {
            Glide.with(this)
                    .load(BaseUriConfig.makeImageUrl(data.getResult().get(2).getOptyImage()))
                    .placeholder(R.mipmap.tb_19)
                    .into(act_pay_dzWallet_image);

            act_pay_dzWallet_tx.setText(data.getResult().get(2).getOptyName());
        }

//

    }

    String optyPublicKey = "";
    String optyNotifyUrl = "";
    String optyMchId = "";
    String optyAppKey = "";
    String optyPrivateKey = "";
    String optyAppId = "";

    /**
     * 获取支付宝成功
     *
     * @param data
     */
    @Override
    public void getAlipayInfoSuccess(RespGetAlipayInfo data) {
        //   url=aliPayIndoData.getResult().getOptyNotifyUrl();
        optyPublicKey = data.getResult().getOptyPublicKey();//公钥
        optyNotifyUrl = data.getResult().getOptyNotifyUrl();
        optyMchId = data.getResult().getOptyMchId();//商户id
        optyAppKey = data.getResult().getOptyAppKey();//支付app key
        optyPrivateKey = data.getResult().getOptyPrivateKey();//私钥
        optyAppId = data.getResult().getOptyAppId();//支付app id
        tempAliPayHelper = new TempAliPayHelper(this, mHandler, SDK_PAY_FLAG, mObject, mObject, total_price_number,
                data.getResult().getOptyMchId(), data.getResult().getOptyAppId(), mordNo, data.getResult().getOptyNotifyUrl(), data.getResult().getOptyPrivateKey());


    }


    String WeChatoptyNotifyUrl = "";//回调URL
    String WeChatoptyMchId = "";
    String WeChatoptyAppKey = "";//app key
    String WeChatoptyAppId = "";//app ID

    /**
     * 获取微信支付成功
     *
     * @param data
     */
    @Override
    public void getWxpayInfoSuccess(RespGetWxpayInfo data) {

//        ConstantConfig.APPID_weixin = wxPayInfoData.getResult().getOptyAppId();
//        ConstantConfig.SECRET_weixin = wxPayInfoData.getResult().getOptyAppKey();
//        Constance.WX_ORDER_NO = mOrderNo;
//        Constance.WX_PRICE = mPrice;

        Debug.error("----data.getResult().getOptyAppId()(--" + data.getResult().getOptyAppId());
        Debug.error("----data.getResult().getOptyAppKey()(--" + data.getResult().getOptyAppKey());
        Debug.error("----data.getResult().getOptyMchId()(--" + data.getResult().getOptyMchId());
        Debug.error("----data.getResult().getOptyNotifyUrl()(--" + data.getResult().getOptyNotifyUrl());
//
//        Debug.error("------payprice---------"+mOrderNo);
//        Debug.error("-----payorder----------"+mPrice);
//        Debug.error("------mObject---------"+mObject);

        WeChatoptyMchId = data.getResult().getOptyMchId();
        WeChatoptyAppKey = data.getResult().getOptyAppKey();
        WeChatoptyAppId = data.getResult().getOptyAppId();
        WeChatoptyNotifyUrl = data.getResult().getOptyNotifyUrl();

        tempWXPayHelper = new TempWXPayHelper(this, data.getResult().getOptyAppId(), data.getResult().getOptyAppKey(),
                data.getResult().getOptyMchId(), data.getResult().getOptyNotifyUrl(), total_price_number, mObject, mObject, mordNo);


    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
    }

    @Override
    public void onLoadDataSuccess() {

    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    //电子钱包余额
    @Override
    public void getMuseEpurseSuccee(RespCheckBalance data) {
        dianzi_banlance_tx.setText(data.getResult().getEpurse() + "元");
    }

    @Override
    public void getMuseEpurseLogSuccee(RespCheckLS data) {

    }

}
