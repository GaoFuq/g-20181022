package com.code.clkj.menggong.fragment.comGift.comReceiveGift;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespReceiveGift;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2017/12/23.
 */

public class PreReceiveGiftImpl implements PreReceiveGiftI {

    private ViewReceiveGiftI mViewI;

    public PreReceiveGiftImpl(ViewReceiveGiftI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getGiftRecordIn(String page) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getGiftRecordIn(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), page), new TempRemoteApiFactory.OnCallBack<RespReceiveGift>() {
            @Override
            public void onSucceed(RespReceiveGift data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getGiftRecordInSuccess(data);
                        mViewI.onLoadDataSuccess();

                    }
                }else {
                    if (mViewI!=null){
                        mViewI.onLoadDataSuccess();
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI!=null){
                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));

                }
            }
        });
    }
}
