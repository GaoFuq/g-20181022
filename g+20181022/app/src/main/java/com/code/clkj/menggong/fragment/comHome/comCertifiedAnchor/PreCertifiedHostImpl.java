package com.code.clkj.menggong.fragment.comHome.comCertifiedAnchor;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespGetAchorApplyStatus;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by Administrator on 2017-12-19.
 */

public class PreCertifiedHostImpl implements PreCertifiedHostI {

    private ViewCertifiedHostI mViewI;

    public PreCertifiedHostImpl(ViewCertifiedHostI viewI) {
        this.mViewI = viewI;
    }
    @Override
    public void getAchorApplyStatus() {
        if (mViewI!=null)
        {
            mViewI.showPro();
        }
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getAchorApplyStatus(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag()), new TempRemoteApiFactory.OnCallBack<RespGetAchorApplyStatus>() {
            @Override
            public void onSucceed(RespGetAchorApplyStatus data) {
                if (data.getFlag() == 1){
                    if (mViewI!=null){
                        mViewI.getAchorApplyStatusSuccess(data);
                    }
                }else{
                    if (mViewI!=null){
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
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

    @Override
    public void getMuseEpurse() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseEpurse(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd()), new TempRemoteApiFactory.OnCallBack<RespCheckBalance>() {
            @Override
            public void onSucceed(RespCheckBalance data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseEpurseSuccess(data);
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
                if (mViewI!=null){
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }


}
