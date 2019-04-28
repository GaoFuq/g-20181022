package com.code.clkj.menggong.activity.comLiveMyGift;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespSaveEpurse;
import com.code.clkj.menggong.response.RespgetGiftBalance;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;


/**
 * Created by 92573 on 2017/12/25.
 */

public class PreMyGiftExchangeActivityImpl implements PreMyGiftExchangeActivityI {

    private ViewMyGiftExchangeActivityI mViwI;

    public PreMyGiftExchangeActivityImpl(ViewMyGiftExchangeActivityI viwI) {
        this.mViwI = viwI;
    }


    @Override
    public void getGiftBalance(String museId, String musePassword, String museOnlieTag) {

        if (mViwI != null) mViwI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .getGiftBalance(museId, musePassword, museOnlieTag),
                new TempRemoteApiFactory.OnCallBack<RespgetGiftBalance>() {
            @Override
            public void onSucceed(RespgetGiftBalance data) {
                if (data.getFlag()==1){
                    if (mViwI!=null){
                        mViwI.getGiftBalanceSuccess(data);
                    }
                }else {
                    if (mViwI !=null){
                        mViwI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViwI != null)
                    mViwI.onLoadFinish();
                     mViwI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViwI !=null){
                    mViwI.disPro();
                    mViwI.toast(ExceptionEngine.handleException(e).message);
                }

            }
        });
    }


    @Override
    public void saveEpurse(String museId, String musePassword, String price) {
        if (mViwI != null) mViwI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .saveEpurse(museId, musePassword, price),
                new TempRemoteApiFactory.OnCallBack<RespSaveEpurse>() {
                    @Override
                    public void onSucceed(RespSaveEpurse data) {
                        if (data.getFlag()==1){
                            if (mViwI!=null){
                                mViwI.saveEpurseSuccess(data);
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
