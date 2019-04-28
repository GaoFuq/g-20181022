package com.code.clkj.menggong.activity.comUserLogin;

import com.code.clkj.menggong.bean.BaseViewI;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;

/**
 * Created by Administrator on 2017-12-09.
 */

public interface ViewActLoginI extends BaseViewI {

    void userLoginSuccess(ResponseLoginInfo data);
}
