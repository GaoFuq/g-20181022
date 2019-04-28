package com.code.clkj.menggong.fragment.comHome.homeLive;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetAverPage;
import com.code.clkj.menggong.response.RespQueryRoomIndex;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2017/12/29.
 */

public class PrehomeLiveImpl implements PrehomeLiveI {

    private ViewhomeLiveI mViewI;

    public PrehomeLiveImpl(ViewhomeLiveI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void queryRoomIndex() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryRoomIndex(),
                new TempRemoteApiFactory.OnCallBack<RespQueryRoomIndex>() {
                    @Override
                    public void onSucceed(RespQueryRoomIndex data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryRoomIndexSuccess(data);
//                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.queryRoomIndexSuccess(data);
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
//                        if (mViewI != null)
////                            mViewI.onLoadFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI !=null){
                            mViewI.queryRoomIndexSuccess(null);
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }

    //广告
    @Override
    public void getAverPage() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getAverPage(),
                new TempRemoteApiFactory.OnCallBack<RespGetAverPage>() {
                    @Override
                    public void onSucceed(RespGetAverPage data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.getAverPageSuccess(data);
//                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.getAverPageSuccess(data);
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null)
                            mViewI.onLoadFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI !=null){
                            mViewI.getAverPageSuccess(null);
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
}
