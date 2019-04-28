package com.code.clkj.menggong.activity.comNear.comRongToken;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetMuseToken;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by clkj on 2018/1/5.
 */

public class PreRongTokenImpl implements PreRongTokenI {
    private ViewRongTokenI mView;

    public PreRongTokenImpl(ViewRongTokenI view) {
        this.mView = view;
    }

    @Override
    public void getMuseToken() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseToken(TempLoginConfig.sf_getSueid()), new TempRemoteApiFactory.OnCallBack<RespGetMuseToken>() {
            @Override
            public void onSucceed(RespGetMuseToken data) {
                if (data.getFlag()==1){
                    if (mView!=null){
                        mView.getMuseTokenSuccess(data);
                    }
                }else{
                    if (mView!=null){
                        mView.disPro();                  }
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
