package com.code.clkj.menggong.activity.comAnchorStore.anchorStroreDetail;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryMallGoodsDetail;
import com.code.clkj.menggong.response.RespToConfirmOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/5.
 */

public class PreActCommodityDetailsActivityImpl implements PreActCommodityDetailsActivityI {

    private ViewActCommodityDetailsActivityI mViewI;
    public PreActCommodityDetailsActivityImpl(ViewActCommodityDetailsActivityI viewI) {
        this.mViewI = viewI;
    }
    //商品详情
    @Override
    public void queryMallGoodsDetail(String mgooId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryMallGoodsDetail(mgooId),
                new TempRemoteApiFactory.OnCallBack<RespQueryMallGoodsDetail>() {
                    @Override
                    public void onSucceed(RespQueryMallGoodsDetail data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryMallGoodsDetailSuccess(data);
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
                            mViewI.dismissPro();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.dismissPro();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
    //到订单页--确认订单之前调的接口
    @Override
    public void toConfirmOrder(String museId, String musePassword, String mgooId, String modeNum) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .toConfirmOrder(museId, musePassword, mgooId, modeNum),
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
}
