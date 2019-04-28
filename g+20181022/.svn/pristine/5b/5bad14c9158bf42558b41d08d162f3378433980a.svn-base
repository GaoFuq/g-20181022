package com.code.clkj.menggong.wxapi;

import com.umeng.weixin.callback.WXCallbackActivity;


/*import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;*/

/**
 * Created by longf on 2016/12/15.
 */  //implements IWXAPIEventHandler
public class WXEntryActivity extends WXCallbackActivity {
  //  private String TAG = "WXEntryActivity";

          //  wx6f65e789b8c8d043
  /*  private String APP_ID = "wxde94f70fb73973da";//optyAppId=wxf0af3c25410ee37d
   // private String APP_SECRT= "207ca3e7c9cad40e9f157b43e7234e11";//与服务器返回的返回无关
    private boolean isFirst = true;
    private String access_token,openid,code;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Debug.error("LfWXEntryActivity","微信分享onCreate");
//        CommonUtil.print(TAG, "onCreate()", 2);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, false);

        // 将该app注册到微信
        api.registerApp(APP_ID);

        api.handleIntent(getIntent(), this);

    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
       *//* String result;
        Debug.error("-------微信回调-----LfWXEntryActivity----------", "微信分享返回" + resp.errCode + resp.errStr);
        Debug.error("-------微信回调-----LfWXEntryActivity------openId----", "微信分享返回" + resp.openId);
        Debug.error("-------微信回调-----LfWXEntryActivity------transaction----", "微信分享返回" + resp.transaction);
        Debug.error("-------微信回调====type", "微信分享返回" + resp.getType());
        if (resp.getType() == 1) {

            code = ((SendAuth.Resp) resp).code;
            Debug.error("------微信回调------code----------" + code);
            if (isFirst) {
                Debug.error("------微信回调------BaseResp.ErrCode.ERR_OK----------" + BaseResp.ErrCode.ERR_OK);
                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        isFirst = false;
                        Debug.error("------微信回调------isFirst----------" + isFirst);
//                        getWxToken();
                        break;
                }


            }


        } else if (resp.getType() == 2) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = "微信分享成功";
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = "微信分享被取消";
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    result = "微信分享失败";
                    break;
                default:
                    result = "微信分享未知";
                    break;
            }

            Debug.error("--------微信回调--result---------" + result);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            finish();
        }*//*


    }*/
}


//        new AlertDialog.Builder(this).setMessage(result).setPositiveButton("好的", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        }).create().show();

//    }
//
//    private void getWxToken(){
//
//        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).accesToken(APP_ID, APP_SECRT.trim(), code, "authorization_code"), new TempRemoteApiFactory.OnCallBack<ResponseTooken>() {
//            @Override
//            public void onSucceed(ResponseTooken data) {
//                Debug.error("------1------"+data.getOpenid());
//                Debug.error("------2------"+data.getAccess_token());
//                if (!TextUtils.isEmpty(data.getOpenid())&&!TextUtils.isEmpty(data.getAccess_token())){
//                    TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).userinfo(data.getAccess_token(), data.getOpenid()), new TempRemoteApiFactory.OnCallBack<ResponseWxInfo>() {
//                        @Override
//                        public void onSucceed(ResponseWxInfo data) {
//                            Debug.error("------3------"+data.getUnionid());
//                            Debug.error("------4-----"+data.getHeadimgurl());
//                            Debug.error("------5------"+data.getNickname());
////                            if (!TextUtils.isEmpty(data.getNickname()) && !TextUtils.isEmpty(data.getHeadimgurl()) && !TextUtils.isEmpty(data.getOpenid())){
////                               }
//                            userThirdLogin(data.getNickname(),data.getHeadimgurl(),data.getUnionid());
//
//
//                        }
//
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//                    });
//                } else {
//                    getWxToken();
//
//                }
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//
//    }


//    private void userThirdLogin(final String museUserName, final String museImage, final String museWechatOpenid){
//
//        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).userThirdLogin(TempLoginConfig.sf_getSueid(), museWechatOpenid, TempLoginConfig.sf_getOnLineKey(), museUserName, museImage, museWechatOpenid), new TempRemoteApiFactory.OnCallBack<ResponseLoginInfo>() {
//            @Override
//            public void onSucceed(ResponseLoginInfo data) {
//
//                if (data.getFlag()==1){
////                    Toast.makeText(WXEntryActivity.this,"登录成功",Toast.LENGTH_LONG).show();
//                    TempLoginConfig.sf_saveLoginState(true);
//                    TempLoginConfig.sf_saveIsWXLogin(true);
//                    TempLoginConfig.sf_savePassword(museWechatOpenid);
//                    TempLoginConfig.sf_saveWXUser(museUserName, museImage, museWechatOpenid);
//                    TempLoginConfig.sf_saveLoginInfo(data);
//
//                    onBackPressed();
//                }
//
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//
//
//
//    }


