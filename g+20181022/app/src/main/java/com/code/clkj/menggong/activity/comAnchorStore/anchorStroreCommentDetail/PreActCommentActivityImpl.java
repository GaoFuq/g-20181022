package com.code.clkj.menggong.activity.comAnchorStore.anchorStroreCommentDetail;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryMgooCommentPage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/6.
 */

public class PreActCommentActivityImpl implements PreActCommentActivityI{

    private ViewActCommentActivityI mViewI;
    public PreActCommentActivityImpl(ViewActCommentActivityI viewI) {
        this.mViewI = viewI;
    }
    //商品详情
    @Override
    public void queryMgooCommentPage(String mgooId, String currentPage, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryMgooCommentPage(mgooId, currentPage, pageSize),
                new TempRemoteApiFactory.OnCallBack<RespQueryMgooCommentPage>() {
                    @Override
                    public void onSucceed(RespQueryMgooCommentPage data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryMgooCommentPageSuccess(data);
                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI !=null){
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (mViewI != null) {
                            mViewI.onLoadFinish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.onLoadFinish();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }
}
