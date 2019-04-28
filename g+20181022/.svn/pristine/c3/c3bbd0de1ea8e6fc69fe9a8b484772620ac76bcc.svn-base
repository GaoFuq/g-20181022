package com.code.clkj.menggong.activity.comAddress.comShowAddress;

import android.util.Log;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespActAddress;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by Administrator on 2017-12-13.
 */

public class PreActAddressImpl implements PreActAddressI {
    private ViewActAddressI mViewI;

    public PreActAddressImpl(ViewActAddressI view) {
       this.mViewI = view;
    }

    @Override
    public void addressList(int Page) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).addressList(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), Page), new TempRemoteApiFactory.OnCallBack<RespActAddress>() {
            @Override
            public void onSucceed(RespActAddress data) {
                if (data.getFlag() == 1){
                    if (mViewI!=null){
                        mViewI.addressListSuccee(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
//                    mViewI.toast(data.getMsg());
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
