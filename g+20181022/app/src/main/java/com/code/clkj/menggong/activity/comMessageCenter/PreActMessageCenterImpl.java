package com.code.clkj.menggong.activity.comMessageCenter;

import android.util.Log;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespActgetMessagePage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

import retrofit.http.Field;

/**
 * Created by Administrator on 2017-12-15.
 */

public class PreActMessageCenterImpl implements PreMessageCenterI{

    private ViewActMessageCenterI mViewI;

    public PreActMessageCenterImpl(ViewActMessageCenterI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMessagePage(String page,String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMessagePage(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),page,pagesize), new TempRemoteApiFactory.OnCallBack<RespActgetMessagePage>() {
            @Override
            public void onSucceed(RespActgetMessagePage data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMessagePageSuccess(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
                    mViewI.toast(data.getMsg());
                    mViewI.onLoadDataSuccess();
                }
            }

            @Override
            public void onCompleted() {
                mViewI.onLoadFinish();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    Log.i("addressList", "onError: "+e.getMessage());
                    mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
