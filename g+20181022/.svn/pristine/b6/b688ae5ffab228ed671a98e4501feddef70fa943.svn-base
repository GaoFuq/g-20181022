package com.code.clkj.menggong.activity.comMyWallet.comShowBankCard;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespShowBankCard;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/22.
 */

public class PreShowBankCardImpl implements PreShowBankCardI {
    private ViewShowBankCardI mViewI;

    public PreShowBankCardImpl(ViewShowBankCardI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMuseBrank(String page) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseBrank(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), page), new TempRemoteApiFactory.OnCallBack<RespShowBankCard>() {
            @Override
            public void onSucceed(RespShowBankCard data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseBrankSuccee(data);
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
                    mViewI.toast(e.getMessage());
                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                }
            }
        });
    }

    @Override
    public void saveWithdraw(String mbanId, String price) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveWithdraw(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                mbanId, price), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveWithdrawSuccee(data);
                    }
                }else{
                    if (mViewI!=null){
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(mViewI!=null){

                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
