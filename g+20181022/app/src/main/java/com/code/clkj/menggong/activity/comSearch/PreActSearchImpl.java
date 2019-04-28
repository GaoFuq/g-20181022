package com.code.clkj.menggong.activity.comSearch;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespGetSearchByKey;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/13.
 */

public class PreActSearchImpl implements PreActSearchI {


    private ViewActSearchI mViewI;

    public PreActSearchImpl(ViewActSearchI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getSearchByKey(String key,String currentPage, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getSearchByKey(key,currentPage,pageSize),
                new TempRemoteApiFactory.OnCallBack<RespGetSearchByKey>() {
                    @Override
                    public void onSucceed(RespGetSearchByKey data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.getSearchByKeySuccess(data);
                                mViewI.onLoadDataSuccess();
                            }
                        }else{
                            if (mViewI!=null) {
                                mViewI.toast(data.getMsg());
                                mViewI.onLoadDataSuccess();

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
                        if (mViewI!=null){
                            mViewI.onLoadDataError(ExceptionEngine.handleException(e));
                        }
                    }
                });
    }
}
