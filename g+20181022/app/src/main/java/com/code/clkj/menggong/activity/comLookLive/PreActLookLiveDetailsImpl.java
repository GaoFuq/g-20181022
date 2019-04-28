package com.code.clkj.menggong.activity.comLookLive;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGiftList;
import com.code.clkj.menggong.response.RespGradRed;
import com.code.clkj.menggong.response.RespQueryRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/2.
 */

public class PreActLookLiveDetailsImpl implements PreActLookLiveDetailsI {


    private ViewActLookLiveDetailsI mViewI;

    public PreActLookLiveDetailsImpl(ViewActLookLiveDetailsI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void queryRoom(String roomId, String museId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryRoom(roomId, museId),
                new TempRemoteApiFactory.OnCallBack<RespQueryRoom>() {
                    @Override
                    public void onSucceed(RespQueryRoom data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryRoomSuccess(data);
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null)
                            mViewI.dismissPro();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI !=null){
                            mViewI.dismissPro();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }

    @Override
    public void saveConsumOrder(String price, String roomId, String giftId, String type, String number) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveConsumOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                price, roomId, giftId, type, number), new TempRemoteApiFactory.OnCallBack<RespConsumOrder>() {
            @Override
            public void onSucceed(RespConsumOrder data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveConsumOrderSucceess(data);
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
                if (mViewI !=null){
                    mViewI.dismissPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void queryGiftList() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryGiftList(), new TempRemoteApiFactory.OnCallBack<RespGiftList>() {
            @Override
            public void onSucceed(RespGiftList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.queryGiftListSuccess(data);
                    }
                }else{
                    if (mViewI!=null){
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI !=null){
                    mViewI.dismissPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getMuseEpurse() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseEpurse(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd()), new TempRemoteApiFactory.OnCallBack<RespCheckBalance>() {
            @Override
            public void onSucceed(RespCheckBalance data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseEpurseSuccess(data);
                    }
                }else{
                    mViewI.toast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI !=null){
                    mViewI.dismissPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void gradRed(String redId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).gradRed(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), redId), new TempRemoteApiFactory.OnCallBack<RespGradRed>() {
            @Override
            public void onSucceed(RespGradRed data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.gradRedSuccess(data);
                    }
                }else{
                    mViewI.toast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mViewI !=null){
                    mViewI.dismissPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }
}
