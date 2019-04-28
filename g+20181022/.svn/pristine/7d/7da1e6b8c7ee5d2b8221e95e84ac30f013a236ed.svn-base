package com.code.clkj.menggong.activity.comPersonalCenter;

import android.util.Log;

import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.response.RespPersonalDataActivity;
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
 * Created by Administrator on 2017-12-12.
 */

public class PrePersonalDatActImpl implements PrePersonalDatActI{
    private ViewPersonalDatActI mView;

    public PrePersonalDatActImpl(ViewPersonalDatActI mView){
        this.mView = mView;
    }

    @Override
    public void queryMemberInfoById() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryMemberInfoById(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),TempLoginConfig.sf_getOnlineTag()), new TempRemoteApiFactory.OnCallBack<RespPersonalDataActivity>() {
            @Override
            public void onSucceed(RespPersonalDataActivity data) {
                if (data.getFlag() == 1){
                    if (mView!=null){
                        mView.queryMemberInfoByIdSuccee(data);
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
    public void queryAreaCity(String AId) {
        if (mView != null)
            mView.showPro();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryAreaCity(AId), new TempRemoteApiFactory.OnCallBack<RespQueryAreaCity>() {
            @Override
            public void onSucceed(RespQueryAreaCity data) {
                if (data.getFlag() == 1) {
                    if (mView != null) {
                        mView.queryAreaCitySuccee(data);
                    }
                } else {
                    if (mView != null) {
                        mView.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mView != null) mView.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.disPro();
                    mView.showConntectError(ExceptionEngine.handleException(e));
                }
            }
        });
    }

    @Override
    public void updateMember(String museNickName, String museAddress, String museBirthday, String museSex, String museSignature, String museImage) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).updateMember(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getOnlineTag(),
                museNickName, museAddress, museBirthday, museSex, museSignature, museImage), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getFlag() == 1) {
                    if (mView != null) {
                        mView.updateMemberSuccee(data);
                    }
                } else {
                    if (mView != null) {
                        mView.toast(data.getMsg());
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mView != null) mView.disPro();
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.disPro();
                    mView.showConntectError(ExceptionEngine.handleException(e));
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
                if (mView != null) {
                    mView.toast(filePath + "文件不存在");
                }
            }
        }
        if (mView!=null) mView.showPro();

        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).uploadUEImg(map), new TempRemoteApiFactory.OnCallBack<ResponseUploadUEImg>() {
            @Override
            public void onSucceed(ResponseUploadUEImg data) {

                if (data.getState().equals("SUCCESS")) {
                    Log.i("uploadUEImg", "onSucceed: " + new Gson().toJson(data));
                    if (mView != null) {
                        mView.uploadUEImgSuccess(data);
                    }
                } else {
                    if (mView != null) {
                        mView.toast("操作失败，请重试！");
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mView!=null) mView.disPro();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("uploadUEImg","onError:"+e.getMessage());
                if (mView != null) {
                    mView.disPro();mView.showConntectError(ExceptionEngine.handleException(e));
                }

            }
        });
    }
}
