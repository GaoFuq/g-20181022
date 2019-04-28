package com.code.clkj.menggong.activity.comChatRoom;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.fragment.comNear.comNearLive.ViewNearLiveI;
import com.code.clkj.menggong.response.RespCahtRoom;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

import retrofit.http.Field;

/**
 * Created by chenlingkeji on 2018/10/22.
 * for chenlingkeji Company
 * link for more detail www.lingkj.com
 */
public class PreActChatRoomImpl implements PreActChatRoomI{

    private ViewActChatRoomI mViewI;

    public PreActChatRoomImpl(ViewActChatRoomI viewI) {
        this.mViewI = viewI;
    }
    @Override
    public void queryseletChat(String lon, String lat,String page,String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).seletChat(lon, lat,page,pagesize
               ), new TempRemoteApiFactory.OnCallBack<RespCahtRoom>() {
            @Override
            public void onSucceed(RespCahtRoom data) {
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
