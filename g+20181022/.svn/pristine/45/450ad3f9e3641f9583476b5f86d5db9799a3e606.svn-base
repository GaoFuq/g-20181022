package com.code.clkj.menggong.activity.comSetUp.comActFeedBackActivity;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-15.
 */

public class PreActFeedBackActivityImpl implements PreActFeedBackActivityI  {

    private ViewActFeedBackActivityI mViewI;

    public PreActFeedBackActivityImpl(ViewActFeedBackActivityI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveFeedBack(String phone, String content) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveFeedBack(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), phone, content), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveFeedBackSuccess(data);
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
                if (mViewI!=null){
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
