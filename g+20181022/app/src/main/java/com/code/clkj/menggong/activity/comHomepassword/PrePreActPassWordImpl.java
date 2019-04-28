package com.code.clkj.menggong.activity.comHomepassword;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespActPassWord;
import com.code.clkj.menggong.response.RespActmyDisplayCode;
import com.code.clkj.menggong.response.RespActmySpreadPng;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public class PrePreActPassWordImpl implements PreActPassWordI {
    private ViewActPassWordI mView;

    public PrePreActPassWordImpl(ViewActPassWordI mView){
        this.mView = mView;
    }

    @Override
    public void isSetPayPwd(String museId, String musePassword, String museOnlineTag) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).isSetPayPwd(museId, musePassword, museOnlineTag), new TempRemoteApiFactory.OnCallBack<RespActPassWord>() {
            @Override
            public void onSucceed(RespActPassWord data) {
                if (data.getFlag()==1){
                    if (mView!=null){
                        mView.isSetPayPwdSuccee(data);
                    }
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
    public void mySpreadPng(String museId, String musePassword, String museOnlineTag) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).mySpreadPng(museId,musePassword,museOnlineTag), new TempRemoteApiFactory.OnCallBack<RespActmySpreadPng>() {
            @Override
            public void onSucceed(RespActmySpreadPng data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        TempLoginConfig.sf_clearLoginInfo();
                        mView.resetLoginPwdSuccee(data);
                    }
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
    public void myDisplayCode(String museId, String musePassword, String museOnlineTag) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).myDisplayCode(museId, musePassword, museOnlineTag), new TempRemoteApiFactory.OnCallBack<RespActmyDisplayCode>() {
            @Override
            public void onSucceed(RespActmyDisplayCode data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        TempLoginConfig.sf_clearLoginInfo();
                        mView.resetLoginPwdSuccee(data);
                    }
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

    /*@Override
    public void resetLoginPwd(String oldPwd, String newPwd) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).resetLoginPwd(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), oldPwd, newPwd), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        TempLoginConfig.sf_clearLoginInfo();
                        mView.resetLoginPwdSuccee(data);
                    }
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
*/
  /*  @Override
    public void resetPayPwd() {

    }*/
}
