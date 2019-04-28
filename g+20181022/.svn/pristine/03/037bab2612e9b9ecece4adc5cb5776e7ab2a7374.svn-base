package com.code.clkj.menggong.activity.comLiveMyOrder.myOrder;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetMuseOrderBuy;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2018/1/10.
 */

public class PreActMyOrderActivityImpl implements PreActMyOrderActivityI {

    private ViewActMyOrderActivityI mViewI;
    public PreActMyOrderActivityImpl(ViewActMyOrderActivityI viewI) {
        this.mViewI = viewI;
    }
    /**
     *  购买的订单 --- 我的订单
     * @param museId
     * @param musePassword
     * @param museOlineTag
     * @param currentPage
     * @param pageSize
     */
    @Override
    public void getMuseOrderBuy(String museId, String musePassword, String museOlineTag,
                                String currentPage, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getMuseOrderBuy(museId, musePassword, museOlineTag, currentPage, pageSize),
                new TempRemoteApiFactory.OnCallBack<RespGetMuseOrderBuy>() {
                    @Override
                    public void onSucceed(RespGetMuseOrderBuy data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.getMuseOrderBuySuccess(data);
                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.toast(data.getMsg());
                                mViewI.onLoadDataSuccess();
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null) {
                            mViewI.onLoadFinish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }

    /**
     * 修改订单状态（增）
     * @param museId
     * @param musePassword
     * @param mordId
     * @param status
     * @param type
     */
    @Override
    public void saveOrderStatus(String museId, String musePassword, String mordId, String status,
                                String type) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .saveOrderStatus(museId, musePassword, mordId, status, type),
                new TempRemoteApiFactory.OnCallBack<TempResponse>() {
                    @Override
                    public void onSucceed(TempResponse data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.saveOrderStatusSuccess(data);
                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null) {
                            mViewI.onLoadFinish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.onLoadFinish();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
}
