package com.code.clkj.menggong.activity.comHomeDetection;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespgetCheckList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-14.
 */

public class PreActDetectionImpl implements PreActDetectionI {

    private ViewActDetectionI mViewI;

    public PreActDetectionImpl(ViewActDetectionI mViewI) {
        this.mViewI = mViewI;
    }
    @Override
    public void sendForgetCode(String phone) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).sendForgetCode(phone), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.sendForgetCodeSuccess(data);
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
    public void getCheckList() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getCheckList(), new TempRemoteApiFactory.OnCallBack<RespgetCheckList>() {
            @Override
            public void onSucceed(RespgetCheckList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getCheckListSuccess(data);
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
    public void saveCheckReserve(String mucrCkId, String mucrBeginTime, String mucrEndTime, String code, String mucrPhone) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveCheckReserve(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),
                mucrCkId, mucrBeginTime, mucrEndTime, code, mucrPhone), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1){
                    if (mViewI!=null){
                        mViewI.saveCheckReserveSuccess(data);
                    }
                }else {
                    if (mViewI != null) {
                        mViewI.toast("添加检测预约失败");
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
