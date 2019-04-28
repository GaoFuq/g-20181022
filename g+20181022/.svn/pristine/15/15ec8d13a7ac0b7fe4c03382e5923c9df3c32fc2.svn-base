package com.code.clkj.menggong.fragment.comNear.comNearLive;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/3.
 */

public class PreNearLiveImpl implements PreNearLiveI {

    private ViewNearLiveI mViewI;

    public PreNearLiveImpl(ViewNearLiveI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void queryRoomList(String startAge, String endAge, String sex, String lat, String lng,String page,String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryRoomList(startAge, endAge,
                sex, lat, lng,page,pagesize), new TempRemoteApiFactory.OnCallBack<RespNearLive>() {
            @Override
            public void onSucceed(RespNearLive data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.queryRoomListSuccees(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
                    mViewI.toast(data.getMsg());
                    mViewI.onLoadDataSuccess();
                }
            }

            @Override
            public void onCompleted() {

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
