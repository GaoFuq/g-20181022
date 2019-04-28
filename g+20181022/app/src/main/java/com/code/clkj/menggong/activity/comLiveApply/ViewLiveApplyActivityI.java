package com.code.clkj.menggong.activity.comLiveApply;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-19.
 */

public interface ViewLiveApplyActivityI extends BaseViewI {
    void usceIdentityBackSuccess(TempResponse data);

    void uploadUEImgSuccess(ResponseUploadUEImg data);

    void queryAreaCitySuccee(RespQueryAreaCity data);
}
