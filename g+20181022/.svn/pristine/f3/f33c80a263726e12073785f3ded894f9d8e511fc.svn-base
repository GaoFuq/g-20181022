package com.code.clkj.menggong.activity.comUserLogin;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-11.
 */

public class PreActForgetPasswordImpl implements PreActForgetPasswordI {

    private ViewActForgetPasswordI mViewI;

    public PreActForgetPasswordImpl(ViewActForgetPasswordI viewI) {
        mViewI = viewI;
    }
    @Override
    public void doForget(String code, String userName, String password) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).doForget(code, userName, password), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.doForgetSuccess(data);
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
    public void sendForgetCode(String phone) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).sendForgetCode(phone), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.sendForgetCodeSuccess(data);
                    }
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
