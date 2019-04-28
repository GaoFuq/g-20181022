package com.code.clkj.menggong.activity.comHomefollow.comShowAttention;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespMyAttention;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/3.
 */

public class PreShowAttentionImpl implements PreShowAttentionI {

    private ViewShowAttentionI mViewI;

    public PreShowAttentionImpl(ViewShowAttentionI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMuseFollow(String lng, String lat,String page,String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseFollow(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),
                lng, lat,page,pagesize), new TempRemoteApiFactory.OnCallBack<RespMyAttention>() {
            @Override
            public void onSucceed(RespMyAttention data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseFollowSuccess(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else {
                    if (mViewI!=null){
                        mViewI.onLoadDataSuccess();
                    }
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
