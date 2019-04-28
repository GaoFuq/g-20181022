package com.code.clkj.menggong.activity.comLiveHistory.comShowHistory;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespShowHistory;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/21.
 */

public class PreShowHistiryImpl implements PreShowHistoryI {
    private ViewShowHistoryI mViewI;

    public PreShowHistiryImpl(ViewShowHistoryI mViewI) {
        this.mViewI = mViewI;
    }

    @Override
    public void getMuseHistory(String page,String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseHistory(TempLoginConfig.sf_getSueid(),
                TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),page,pagesize), new TempRemoteApiFactory.OnCallBack<RespShowHistory>() {
            @Override
            public void onSucceed(RespShowHistory data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseHistorySuccess(data);
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
    public void deleteMuseHistory(String muhyId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).deleteMuseHistory(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                muhyId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.deleteMuseHistorySuccee(data);
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
}
