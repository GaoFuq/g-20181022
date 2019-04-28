package com.code.clkj.menggong.activity.comLiveApply.comLiveApplyAgain;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by clkj on 2017/12/20.
 */

public interface PreActLiveApplyI {
    void updateAchorApply(String usceRealName,String usceSex,String usceAge,String uscePhone,String usceAddress,String usceIdentityFace,String usceIdentityBack,String usceId);

    void queryAreaCity(String AId);

    void getAchorApply(String usceId);

    void uploadUEImg(ArrayList<ImageItem> filePath);
}
