package com.code.clkj.menggong.activity.comAddress.comAddAddress;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-14.
 */

public class PreAddAddressImpl implements PreAddAddressI{


    private ViewAddAddressI mViewI;

    public PreAddAddressImpl(ViewAddAddressI mViewI) {
        this.mViewI = mViewI;
    }

    @Override
    public void saveAddress(String msadMobileNo, String msadReceiverName, String msadAddrName, String msadAddr, String msadIsDefault) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveAddress(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),TempLoginConfig.sf_getOnlineTag(),
                msadMobileNo, msadReceiverName, msadAddrName, msadAddr, msadIsDefault), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1){
                    if (mViewI!= null){
                        mViewI.saveAddressSuccee(data);
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
    public void queryAreaCity(String AId) {
        if (mViewI != null)
            mViewI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryAreaCity(AId), new TempRemoteApiFactory.OnCallBack<RespQueryAreaCity>() {
            @Override
            public void onSucceed(RespQueryAreaCity data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.queryAreaCitySuccee(data);
                    }
                } else {
                    if (mViewI != null) {
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
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
