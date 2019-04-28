package com.code.clkj.menggong.activity.comOtherHomePage;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespOtherHomePage;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/5.
 */

public class PreOtherHomePageImpl implements PreOtherHomePageI {
    private ViewOtherHomePageI mViewI;

    public PreOtherHomePageImpl(ViewOtherHomePageI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getOtherDynamicPage(String frMuseId, String page, String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .getOtherDynamicPage(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                frMuseId, page, pagesize), new TempRemoteApiFactory.OnCallBack<RespOtherHomePage>() {
            @Override
            public void onSucceed(RespOtherHomePage data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getOtherDynamicPageSucceess(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else {
                    mViewI.onLoadDataSuccess();
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
    public void saveMuseFollow(String folMuseId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMuseFollow(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),
                folMuseId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveMuseFollowSuccess(data);
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
