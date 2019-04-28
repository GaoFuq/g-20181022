package com.code.clkj.menggong.activity.comMyWallet.comCheckBalance;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespCheckLS;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2017/12/21.
 */

public class PreCheckBalanceImpl implements PreCheckBalanceI {

    private ViewCheckBalanceI mViewI;

    public PreCheckBalanceImpl(ViewCheckBalanceI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMuseEpurse() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseEpurse(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd()), new TempRemoteApiFactory.OnCallBack<RespCheckBalance>() {
            @Override
            public void onSucceed(RespCheckBalance data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.getMuseEpurseSuccee(data);
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
    public void getMuseEpurseLog(String page) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseEpurseLog(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),page), new TempRemoteApiFactory.OnCallBack<RespCheckLS>() {
            @Override
            public void onSucceed(RespCheckLS data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.getMuseEpurseLogSuccee(data);
                        mViewI.onLoadDataSuccess();
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.toast(e.getMessage());
                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                }
            }
        });

    }
}
