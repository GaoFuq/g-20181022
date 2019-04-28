package com.code.clkj.menggong.activity.comSetUp.comCommonProblem;

import android.util.Log;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetHelpList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by Administrator on 2017-12-15.
 */

public class PreActGetHelpListImpl implements PreActGetHelpListI {

    private ViewActGetHelpListI mViewI;

    public PreActGetHelpListImpl(ViewActGetHelpListI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getHelpList() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getHelpList(),new TempRemoteApiFactory.OnCallBack<RespGetHelpList>() {
            @Override
            public void onSucceed(RespGetHelpList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getHelpList(data);
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
