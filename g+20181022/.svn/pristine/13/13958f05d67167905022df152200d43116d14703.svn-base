package com.code.clkj.menggong.activity.comHomepassword.comResetLoginPassword;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public class PreActResetLoginPWImpl implements PreActResetLoginPWI {
    private ViewActResetLoginPWI mView;

    public PreActResetLoginPWImpl(ViewActResetLoginPWI mView){
        this.mView = mView;
    }


    @Override
    public void resetLoginPwd(String musePassword, String oldPwd, String newPwd) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).resetLoginPwd(TempLoginConfig.sf_getSueid(), musePassword, oldPwd, newPwd), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mView!=null){
                        mView.resetLoginPwdSuccee(data);
                    }
                }else{
                    if (mView!=null){
                        mView.showPro();
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mView!=null){
                    mView.disPro();
                    mView.showConntectError(ExceptionEngine.handleException(e));
                }

            }
        });
    }
}
