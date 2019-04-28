package com.code.clkj.menggong.activity.comMyWallet.comAddBankCard;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/22.
 */

public class PreAddBankCardImpl implements PreAddBankCardI {
    private ViewAddBankCardI mViewI;

    public PreAddBankCardImpl(ViewAddBankCardI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveMuseBrank(String mbanName, final String mbanCard, String mbanMemberName) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMuseBrank(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                mbanName, mbanCard, mbanMemberName), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveMuseBrankSuccess(data);
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
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
