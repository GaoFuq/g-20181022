package com.code.clkj.menggong.activity.comLiveMyOrder.comMyOrderEvaluate;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/16.
 */

public class PreMyOrderEvaluateImpl implements PreMyOrderEvaluateI {
    private ViewMyOrderEvaluateI mViewI;

    public PreMyOrderEvaluateImpl(ViewMyOrderEvaluateI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void saveMgooComment(String mgcoOrderId, String mgcoImage, String mgcoGoodsContent) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMgooComment(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(),
                mgcoOrderId, mgcoImage, mgcoGoodsContent), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveMgooCommentSuccess(data);
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
                if (mViewI!=null){
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }
}
