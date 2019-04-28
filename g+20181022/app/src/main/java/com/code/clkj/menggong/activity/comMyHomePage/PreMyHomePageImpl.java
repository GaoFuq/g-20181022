package com.code.clkj.menggong.activity.comMyHomePage;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespMyHomePage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/5.
 */

public class PreMyHomePageImpl implements PreMyhomePageI {

    private ViewMyHomePageI mViewI;

    public PreMyHomePageImpl(ViewMyHomePageI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getMuseDynamicPage(String page, String pagesize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getMuseDynamicPage(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), page, pagesize), new TempRemoteApiFactory.OnCallBack<RespMyHomePage>() {
            @Override
            public void onSucceed(RespMyHomePage data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getMuseDynamicPageSuccess(data);
                        mViewI.onLoadDataSuccess();
                    }
                }else{
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

    @Override
    public void deleteMuseDynamic(String dyId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).deleteMuseDynamic(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), dyId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.deleteMuseDynamicSucceess(data);
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
    public void saveDynamicComment(String dyId, String content, String type, String rpMuseId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveDynamicComment(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                dyId, content, type, rpMuseId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveDynamicCommentSucceees(data);
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
