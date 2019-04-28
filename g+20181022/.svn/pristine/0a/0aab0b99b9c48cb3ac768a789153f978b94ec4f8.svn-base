package com.code.clkj.menggong.activity.comAddress.comEditAddress;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespActAddressDetails;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-13.
 */

public class PreEditAddressImpl implements PreEditAddressI {
    private ViewEditAddressI mViewI;

    public PreEditAddressImpl(ViewEditAddressI viewI) {
        this.mViewI = viewI;
    }

    //获取地址详情
    @Override
    public void getAddress(String msadId) {
        if (mViewI != null)
            mViewI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getAddress(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), msadId), new TempRemoteApiFactory.OnCallBack<RespActAddressDetails>() {
            @Override
            public void onSucceed(RespActAddressDetails data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.getAddressSuccee(data);
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

    //修改收货地址
    @Override
    public void updateAddress(String msadMobileNo, String msadReceiverName, String msadAddrName, String msadAddr, String msadId, String msadIsDefault) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).updateAddress(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), msadMobileNo, msadReceiverName, msadAddrName, msadAddr, msadId, msadIsDefault), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.updateAddressSuccee(data);
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

    @Override
    public void deleteAddress(String msadId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).deleteAddress(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), msadId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.deleteAddressSuccee(data);
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
