package com.code.clkj.menggong.activity.comHomepassword.comForgetPayPassWord;

import com.code.clkj.menggong.action.TempAction;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public class PreForgetPayPassWordImpl implements PreForgetPayPassWordI {
    private ViewForgetPayPassWordI mView;

    public  PreForgetPayPassWordImpl(ViewForgetPayPassWordI mView){
        this.mView = mView;
    }

    @Override
    public void forgetUserPayPwd(String password, String code, String phone) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).forgetUserPayPwd(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), password, code, phone), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        TempLoginConfig.sf_savePayState(true);
                        mView.forgetUserPayPwdSuccee(data);
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
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).sendForgetPayPwdCode(phone), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1)
                {
                    if (mView!=null){
                        mView.sendForgetPayPwdCodeSuccess(data);
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
