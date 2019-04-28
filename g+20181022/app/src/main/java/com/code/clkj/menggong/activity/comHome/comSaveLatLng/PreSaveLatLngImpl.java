package com.code.clkj.menggong.activity.comHome.comSaveLatLng;

import com.code.clkj.menggong.action.TempAction;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/18.
 */

public class PreSaveLatLngImpl implements PreSaveLatLngI {
    private ViewSaveLatLngI mViewI;

    public PreSaveLatLngImpl(ViewSaveLatLngI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveLatLng(String lng, String lat) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveLatLng(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                lng, lat), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveLatLngSuccess(data);
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

            }
        });
    }
}
