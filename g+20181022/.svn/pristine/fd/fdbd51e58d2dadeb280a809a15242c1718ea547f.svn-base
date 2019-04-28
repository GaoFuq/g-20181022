package com.code.clkj.menggong.fragment.comNear.comMyFriends;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespMyFriendsList;
import com.code.clkj.menggong.response.RespMyuserList;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2017/12/25.
 */

public class PreMyFriendsImpl implements PreMyFriendsI {
   private ViewMyFriendsI mViewI;

    public PreMyFriendsImpl(ViewMyFriendsI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMuseFriend(String museId, String musePassword, String page, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .getMuseFriend(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), page, pageSize),
                new TempRemoteApiFactory.OnCallBack<RespMyFriendsList>() {
            @Override
            public void onSucceed(RespMyFriendsList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseFriendSuccess(data);
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

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void mySpreadList(String museId, String musePassword, String page, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .mySpreadList(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), page, pageSize),
                new TempRemoteApiFactory.OnCallBack<RespMyuserList>() {
                    @Override
                    public void onSucceed(RespMyuserList data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.mySpreadListSuccess(data);
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

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
