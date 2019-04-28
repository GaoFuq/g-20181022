package com.code.clkj.menggong.fragment.comHome.MoreLive;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryRoomMoreList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/13.
 */

public class PreMoreLiveImpl implements PreMoreLiveI {



    private ViewMoreLiveI mViewI;

    public PreMoreLiveImpl(ViewMoreLiveI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void queryRoomMoreList(String type, String currentPage, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .queryRoomMoreList(type, currentPage, pageSize),
                new TempRemoteApiFactory.OnCallBack<RespQueryRoomMoreList>() {
            @Override
            public void onSucceed(RespQueryRoomMoreList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.queryRoomMoreListSuccess(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
                    if (mViewI!=null) {
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
                if (mViewI!=null){
                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
