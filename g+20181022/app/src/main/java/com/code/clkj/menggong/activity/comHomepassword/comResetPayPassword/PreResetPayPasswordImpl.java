package com.code.clkj.menggong.activity.comHomepassword.comResetPayPassword;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/16.
 */

public class PreResetPayPasswordImpl implements PreResetPayPasswordI {

    private ViewResetPayPasswordI mViewI;

    public PreResetPayPasswordImpl(ViewResetPayPasswordI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void resetPayPwd(String oldPwd,String newPwd) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).resetPayPwd(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                oldPwd, newPwd), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.ResetPayPassword(data);
                    }
                }else{
                    if (mViewI!=null){
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI!=null)
                {
                    mViewI.disPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }
}
