package com.code.clkj.menggong.activity.comLive;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2017/12/28.
 */

public class PreActLiveActivityImpl implements PreActLiveActivityI {
    private ViewActLiveActivityI mViewI;

    public PreActLiveActivityImpl(ViewActLiveActivityI viwI) {
        this.mViewI = viwI;
    }

    @Override
    public void queryCanLivingRoom(String museId, String musePassword, String lat, String lng) {
        if (mViewI != null) mViewI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryCanLivingRoom(museId, musePassword, lat, lng),
                new TempRemoteApiFactory.OnCallBack<RespQueryCanLivingRoom>() {
                    @Override
                    public void onSucceed(RespQueryCanLivingRoom data) {
                        if (data.getFlag() == 1) {
                            if (mViewI != null) {
                                mViewI.queryCanLivingRoomSuccess(data);
                            }
                        } else {
                            if (mViewI != null) {
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null)
                            mViewI.onLoadFinish();
                        mViewI.disPro();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.disPro();
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
                    if (mViewI!=null)
                    mViewI.saveConsumOrderSuccess(data);

                }else{
                    if (mViewI!=null)
                        mViewI.toast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null)
                    mViewI.onLoadFinish();
                mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.toast(ExceptionEngine.handleException(e).message);
                }
            }
        });
    }


    @Override
    public void getUnreadMessage(String museId, String musePassword) {
        if (mViewI != null) {
            mViewI.showPro();
        }
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getUnreadMessage(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd()),
                new TempRemoteApiFactory.OnCallBack<RespGetUnreadMessage>() {
                    @Override
                    public void onSucceed(RespGetUnreadMessage data) {
                        if (data.getFlag() == 1) {
                            if (mViewI != null) {
                                mViewI.getUnreadMessageSuccess(data);
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
    public void giftPaihangh(String roomId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).giftPaihangh(roomId), new TempRemoteApiFactory.OnCallBack<RespGiftPaihangh>() {
            @Override
            public void onSucceed(RespGiftPaihangh data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.giftPaihanghSuccess(data);
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
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
