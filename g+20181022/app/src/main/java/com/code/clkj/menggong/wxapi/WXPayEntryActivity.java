package com.code.clkj.menggong.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WXPayEntryActivity extends TempActivity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	//appid
	//	public static  String APP_ID = "wx99c469ab4eb247b2";//TODO 要和app保持一致

	public static  String APP_ID = "wx6541bb7b6665c985";//TODO 要和app保持一致
	private IWXAPI api;
	private String PAY_ORDER_TYPE_KEY = "";
	private String orderNumber = "";
	private String goodsDetail = "";
	private String orderId = "";
    private String totoleprice="";
	private String canyuprice="";
	private String payMoney="";

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		api = WXAPIFactory.createWXAPI(this, APP_ID);
		api.handleIntent(getIntent(), this);

	}

	@Override
	protected void findViews() {

	}

	@Override
	protected void bindValues() {

	}

	@Nullable
	@Override
	protected void OnViewClicked(View v) {

	}


	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Debug.error(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (TextUtils.isEmpty(canyuprice)){
			canyuprice="0";
		}
	/*	totoleprice = getIntent().getStringExtra(Constants.PAY_ORDER_TYPE_VALUE_TOTLE);
		//参与金额
		canyuprice = getIntent().getStringExtra(Constants.PAY_ORDER_TYPE_VALUE_CANYU);*/



		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int code = resp.errCode;
			String msg = "";
			Intent intent;
			switch (code) {
				case 0:
					msg = "支付成功！";
					Debug.error("---------0---------------");
					SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
					String date = sDateFormat.format(new java.util.Date());
					TempLoginConfig.sf_savePayWeixin("2");
					finish();
					Debug.error("---------4---------------");
					break;
				case -1:
					TempLoginConfig.sf_savePayWeixin("1");
					msg = "支付失败！"+resp.errStr;
					finish();
					break;
				case -2:
					TempLoginConfig.sf_savePayWeixin("1");
					msg = "您取消了支付！";
					finish();
					break;
				default:
					TempLoginConfig.sf_savePayWeixin("1");
					msg = "支付失败！";
					finish();
					break;

			}

		}
	}

	@Override
	protected void setListeners() {

	}

}