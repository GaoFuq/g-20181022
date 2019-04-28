package com.code.clkj.menggong.activity.comLiveApply.comAuthenticationState;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/16.
 */

public class PrequeryCanLivingRoomImpl implements PrequeryCanLivingRoomI {

    private ViewqueryCanLivingRoomI mViewI;

    public PrequeryCanLivingRoomImpl(ViewqueryCanLivingRoomI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void queryCanLivingRoom() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryCanLivingRoom(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng()), new TempRemoteApiFactory.OnCallBack<RespQueryCanLivingRoom>() {
            @Override
            public void onSucceed(RespQueryCanLivingRoom data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.queryCanLivingRoom(data);
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI!=null){
                    mViewI.disPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }
}
