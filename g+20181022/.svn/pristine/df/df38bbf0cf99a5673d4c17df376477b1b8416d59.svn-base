package com.code.clkj.menggong.activity.comMyWallet.comRechargeMoney;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespSaveEpurseOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/16.
 */

public class PreRechargeMoneyImpl implements PreRechargeMoneyI {
    private ViewRechargeMoneyI mViewI;

    public PreRechargeMoneyImpl(ViewRechargeMoneyI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveEpurseOrder(String price) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveEpurseOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), price), new TempRemoteApiFactory.OnCallBack<RespSaveEpurseOrder>() {
            @Override
            public void onSucceed(RespSaveEpurseOrder data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.saveEpurseOrderSuccess(data);
                    }
                } else {
                    if (mViewI != null) {
//                        mViewI.disPro();
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }
}
