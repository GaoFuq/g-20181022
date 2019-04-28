package com.code.clkj.menggong.activity.comUserLogin;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;

/**
 * Created by Administrator on 2017-12-09.
 */

public class PreActLoginImpl implements PreActLoginI {

    private ViewActLoginI mViewI;

    public PreActLoginImpl(ViewActLoginI mViewI){
        this.mViewI = mViewI;
    }

    @Override
    public void userLogin(String museUserName, String musePassword) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).userLogin(museUserName, musePassword), new TempRemoteApiFactory.OnCallBack<ResponseLoginInfo>() {
            @Override
            public void onSucceed(ResponseLoginInfo data) {
                if (data.getFlag() == 1){
                    if (mViewI!=null){
                        TempLoginConfig.sf_saveLoginInfo(data);
                        TempLoginConfig.sf_saveLoginState(true);
                        mViewI.userLoginSuccess(data);
                    }
                }else{
                    mViewI.toast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
