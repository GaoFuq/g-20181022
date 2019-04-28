package com.code.clkj.menggong.activity.comUserLogin;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespActRegister;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-09.
 */

public interface ViewActRegisterI extends BaseViewI {
    void appUserRegisterSuccess(RespActRegister data);
    void registerCodeSuccess(TempResponse data);
    void userLoginSuccess(ResponseLoginInfo data);
}
