package com.code.clkj.menggong.activity.comMyWallet.comPaymentMethod;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetAlipayInfo;
import com.code.clkj.menggong.response.RespGetWxpayInfo;
import com.code.clkj.menggong.response.RespPayOrder;
import com.code.clkj.menggong.response.RespQueryAppOrderPayMentType;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/10.
 */

public class PreActPaymentMethodActivityImpl implements PreActPaymentMethodActivityI {


    private ViewActPaymentMethodActivityI mViewI;

    public PreActPaymentMethodActivityImpl(ViewActPaymentMethodActivityI viewI) {
        this.mViewI = viewI;
    }

    /**
     * 支付
     */
    @Override
    public void payOrder(String museId, String musePassword, String payment, String tradeNo,
                         String payTime, String outTradeNo, String payPwd) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .payOrder(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),payment,
                                tradeNo,payTime,outTradeNo,payPwd),
                new TempRemoteApiFactory.OnCallBack<RespPayOrder>() {
                    @Override
                    public void onSucceed(RespPayOrder data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.payOrderSuccess(data);
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

    /**
     * 获取支付类型
     */
    @Override
    public void queryAppOrderPayMentType(String museId, String musePassword) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryAppOrderPayMentType(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd()),
                new TempRemoteApiFactory.OnCallBack<RespQueryAppOrderPayMentType>() {
                    @Override
                    public void onSucceed(RespQueryAppOrderPayMentType data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryAppOrderPayMentTypeSuccess(data);
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

    /**
     * 获取支付宝配置接口
     * @param museId
     * @param musePassword
     */
    @Override
    public void getAlipayInfo(String museId, String musePassword) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getAlipayInfo(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd()),
                new TempRemoteApiFactory.OnCallBack<RespGetAlipayInfo>() {
                    @Override
                    public void onSucceed(RespGetAlipayInfo data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.getAlipayInfoSuccess(data);
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

    /**
     * 获取微信支付配置
     * @param museId
     * @param musePassword
     */
    @Override
    public void getWxpayInfo(String museId, String musePassword) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getWxpayInfo(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd()),
                new TempRemoteApiFactory.OnCallBack<RespGetWxpayInfo>() {
                    @Override
                    public void onSucceed(RespGetWxpayInfo data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.getWxpayInfoSuccess(data);
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
