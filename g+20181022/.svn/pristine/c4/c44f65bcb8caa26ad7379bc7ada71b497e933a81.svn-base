package com.code.clkj.menggong.activity.comHomepassword.comPayPassWord;

import com.code.clkj.menggong.action.TempAction;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public class PreSetPayPassWordImpl implements PreSetPayPassWordI {
    private ViewSetPayPassWordI mView;

    public PreSetPayPassWordImpl(ViewSetPayPassWordI mView){
        this.mView = mView;
    }

    @Override
    public void setUserPayPwd(String musePayPwdOne, String musePayPwdTwo) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).setUserPayPwd(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), musePayPwdOne,
                musePayPwdTwo), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mView!=null){
                        TempLoginConfig.sf_savePayState(true);
                        mView.setUserPayPwdSuccee(data);
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
}
