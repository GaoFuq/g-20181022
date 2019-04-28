package com.code.clkj.menggong.activity.comLiveApply.comLiveApplyAgain;

import android.util.Log;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespLiveUser;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.google.gson.Gson;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;
import com.lzy.imagepicker.bean.ImageItem;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clkj on 2017/12/20.
 */

public class PreLiveApplyImpl implements PreActLiveApplyI {
    private ViewLiveApplyI mViewI;

    public PreLiveApplyImpl(ViewLiveApplyI viewI) {
        this.mViewI = viewI;
    }


    @Override
    public void updateAchorApply(String usceRealName, String usceSex, String usceAge, String uscePhone, String usceAddress, String usceIdentityFace, String usceIdentityBack, String usceId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).updateAchorApply(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),
                usceRealName, usceSex, usceAge, uscePhone, usceAddress, usceIdentityFace, usceIdentityBack, usceId), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.updateAchorApplySuccess(data);
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }

    @Override
    public void queryAreaCity(String AId) {
        if (mViewI != null)
            mViewI.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryAreaCity(AId), new TempRemoteApiFactory.OnCallBack<RespQueryAreaCity>() {
            @Override
            public void onSucceed(RespQueryAreaCity data) {
                if (data.getFlag() == 1) {
                    if (mViewI != null) {
                        mViewI.queryAreaCitySuccee(data);
                    }
                } else {
                    if (mViewI != null) {
                        mViewI.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }

    @Override
    public void getAchorApply(String usceId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getAchorApply(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(), usceId), new TempRemoteApiFactory.OnCallBack<RespLiveUser>() {
            @Override
            public void onSucceed(RespLiveUser data) {
                if (data.getFlag()==1){
                    if (mViewI!=null){
                        mViewI.getAchorApplySuccess(data);
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });

    }

    @Override
    public void uploadUEImg(ArrayList<ImageItem> filePath) {
        //图片上传多张，返回结果还是第一张，后台有问题
        Map<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < filePath.size(); i++) {
            Debug.error("filePath" + i + " :" + filePath.get(i).path);
            File currentFile = new File(filePath.get(i).path);
            if (currentFile.exists()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), currentFile);
                String[] arraypath = filePath.get(i).path.split("/");
                String name = arraypath[arraypath.length - 1];
                Debug.info("file name=" + name);
                map.put("image\"; filename=\"" + name, fileBody);
            } else {
                if (mViewI != null) {
                    mViewI.toast(filePath + "文件不存在");
                }
            }
        }
        if (mViewI != null) mViewI.showPro();

        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).uploadUEImg(map), new TempRemoteApiFactory.OnCallBack<ResponseUploadUEImg>() {
            @Override
            public void onSucceed(ResponseUploadUEImg data) {

                if (data.getState().equals("SUCCESS")) {
                    Log.i("uploadUEImg", "onSucceed: " + new Gson().toJson(data));
                    if (mViewI != null) {
                        mViewI.uploadUEImgSuccess(data);
                    }
                } else {
                    if (mViewI != null) {
                        mViewI.toast("操作失败，请重试！");
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mViewI != null) mViewI.disPro();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("uploadUEImg", "onError:" + e.getMessage());
                if (mViewI != null) {
                    mViewI.disPro();
                    mViewI.showConntectError(ExceptionEngine.handleException(e));
                }

            }
        });
    }
}
