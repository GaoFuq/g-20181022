package com.code.clkj.menggong.activity.comOrder;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespSaveMallOrder;
import com.code.clkj.menggong.response.RespToConfirmOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/8.
 */

public class PreOrderImpl implements PreOrderI {
    private ViewOrderI mViewI;

    public PreOrderImpl(ViewOrderI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void toConfirmOrder(String mgooId, String modeNum) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .toConfirmOrder(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),mgooId,modeNum),
                new TempRemoteApiFactory.OnCallBack<RespToConfirmOrder>() {
                    @Override
                    public void onSucceed(RespToConfirmOrder data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.toConfirmOrderSuccess(data);
                            }
                        }else{
                            if (mViewI !=null){
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
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
    //确认订单
    @Override
    public void saveMallOrder(String museId, String musePassword, String mgooId, String modeNum, String maorAddressId, String mordRemark) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .saveMallOrder(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),mgooId,modeNum, maorAddressId, mordRemark),
                new TempRemoteApiFactory.OnCallBack<RespSaveMallOrder>() {
                    @Override
                    public void onSucceed(RespSaveMallOrder data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.saveMallOrderSuccess(data);
                            }
                        }else{
                            if (mViewI !=null){
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
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
}
