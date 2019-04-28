package com.code.clkj.menggong.activity.comDynamics;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespSaveDynamic;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2017/12/25.
 */

public class PreActDynamicsActivityImpl implements PreActDynamicsActivityI {

    private ViewActDynamicsActivityI mViwI;

    public PreActDynamicsActivityImpl(ViewActDynamicsActivityI viwI) {
        this.mViwI = viwI;
    }


    @Override
    public void saveDynamic(String museId, String musePassword, String dyContent, String dyImage) {
        if (mViwI != null) mViwI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .saveDynamic(museId, musePassword, dyContent, dyImage),
                new TempRemoteApiFactory.OnCallBack<RespSaveDynamic>() {
                    @Override
                    public void onSucceed(RespSaveDynamic data) {
                        if (data.getFlag()==1){
                            if (mViwI!=null){
                                mViwI.saveDynamicSucess(data);
                            }
                        }else {
                            if (mViwI !=null){
                                mViwI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViwI != null)

                        mViwI.disPro();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViwI !=null){
                            mViwI.disPro();
                            mViwI.toast(ExceptionEngine.handleException(e).message);
                        }

                    }
                });
    }

}
