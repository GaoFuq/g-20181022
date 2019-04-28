package com.code.clkj.menggong.fragment.comGift.comSendGift;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespSendGift;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2017/12/23.
 */

public class PreSendGiftImpl implements PreSendGiftI {
    private ViewSendGiftI mViewI;

    public PreSendGiftImpl(ViewSendGiftI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getGiftRecordOut(String page) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getGiftRecordOut(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), page), new TempRemoteApiFactory.OnCallBack<RespSendGift>() {
            @Override
            public void onSucceed(RespSendGift data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getGiftRecordOutSuccee(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
                    mViewI.onLoadDataSuccess();
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
