package com.code.clkj.menggong.activity.comHomefollow.comCancleFollow;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/3.
 */

public class PreCancleFollowImpl implements PreCancleFollowI {
    private ViewCancleFollowI mViewI;

    public PreCancleFollowImpl(ViewCancleFollowI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveMuseFollow(String folMuseId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMuseFollow(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),folMuseId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveMuseFollowSuccess(data);
                    }
                }else {
                    mViewI.disPro();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI!=null){
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                    mViewI.disPro();
                }
            }
        });
    }
}
