package com.code.clkj.menggong.activity.comPersonalCenter;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface PrePersonalDatActI {
    void queryMemberInfoById();

    void queryAreaCity(String AId);

    void updateMember(String museNickName,String museAddress,String museBirthday,String museSex,String museSignature,String museImage);

    void uploadUEImg(ArrayList<ImageItem> filePath);
}
