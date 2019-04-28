package com.code.clkj.menggong.activity.comAnchorStore.anchorStrore;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespQueryMallGoods;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by 92573 on 2018/1/4.
 */

public class PreActAnchorStoreActivityImpl implements PreActAnchorStoreActivityI{


    private ViewActAnchorStoreActivityI mViewI;

    public PreActAnchorStoreActivityImpl(ViewActAnchorStoreActivityI viewI) {
        this.mViewI = viewI;
    }
    //主播商店
    @Override
    public void queryMallGoods(final String museId,String currentPage, String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .queryMallGoods(museId,currentPage,pageSize),
                new TempRemoteApiFactory.OnCallBack<RespQueryMallGoods>() {
                    @Override
                    public void onSucceed(RespQueryMallGoods data) {
                        if (data.getFlag()==1){
                            if (mViewI!=null){
                                mViewI.queryMallGoodsSuccess(data);
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
                            mViewI.dismissPro();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI != null) {
                            mViewI.dismissPro();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }
                    }
                });
    }

}
