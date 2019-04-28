package com.code.clkj.menggong.activity.comSetUp;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespActPhone;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public class PreActSetUpActivityImpl implements PreActSetUpActivityI {
    private ViewActSetUpActivityI mView;

    public PreActSetUpActivityImpl(ViewActSetUpActivityI mView){
        this.mView = mView;
    }

    @Override
    public void updateLoginById(String museId, String musePassword, String museOnlieTag) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).updateLoginById(museId, musePassword, museOnlieTag), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        mView.updateLoginByIdSuccess(data);
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
    public void contactInformation() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).contactInformation(), new TempRemoteApiFactory.OnCallBack<RespActPhone>() {
            @Override
            public void onSucceed(RespActPhone data) {
                if (data.getFlag()==1){
                    if (mView!=null){
                       mView.contactInformationSuccee(data);
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
