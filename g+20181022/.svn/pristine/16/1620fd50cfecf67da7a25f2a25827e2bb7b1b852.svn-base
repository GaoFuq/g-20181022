package com.code.clkj.menggong.fragment.comNear.friendsCircle;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespCommentList;
import com.code.clkj.menggong.response.RespGetDynamicLikes;
import com.code.clkj.menggong.response.RespGetynamicPage;
import com.code.clkj.menggong.response.RespSaveDynamicLike;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2017/12/26.
 */

public class PreFriendsCircleFragmentImpl implements PreFriendsCircleFragmentI {


    private ViewFriendsCircleFragmentI mViewI;

    public PreFriendsCircleFragmentImpl(ViewFriendsCircleFragmentI viewI) {
        this.mViewI = viewI;
    }

    @Override
    public void getDynamicPage(String museId, String frMuseId, String page,String pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .getDynamicPage(museId, frMuseId,page,pageSize), new TempRemoteApiFactory.OnCallBack<RespGetynamicPage>() {
            @Override
            public void onSucceed(RespGetynamicPage data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getDynamicPageSuccess(data);
                        mViewI.onLoadDataSuccess();

                    }
                }else {
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
    public void saveDynamicLike(String museId, String musePassword, String dyId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .saveDynamicLike(museId, musePassword,dyId), new TempRemoteApiFactory.OnCallBack<RespSaveDynamicLike>() {
            @Override
            public void onSucceed(RespSaveDynamicLike data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.saveDynamicLikeSuccess(data);
                        mViewI.onLoadDataSuccess();

                    }
                }else {
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
//, final int position
    @Override
    public void getDynamicLikes(String dyId, final int position) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                .getDynamicLikes(dyId,position), new TempRemoteApiFactory.OnCallBack<RespGetDynamicLikes>() {
            @Override
            public void onSucceed(RespGetDynamicLikes data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getDynamicLikesSuccess(data,position);
//                        mViewI.onLoadDataSuccess();
                    }
                }else {
                    if (mViewI!=null){
//                        mViewI.onLoadDataSuccess();
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
    public void getDynamicComment(String dyId, final int position) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getDynamicComment(dyId,position), new TempRemoteApiFactory.OnCallBack<RespCommentList>() {
            @Override
            public void onSucceed(RespCommentList data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getDynamicCommentSuccsee(data,position);
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
