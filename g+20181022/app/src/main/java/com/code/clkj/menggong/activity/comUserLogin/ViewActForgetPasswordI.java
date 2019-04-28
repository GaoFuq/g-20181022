package com.code.clkj.menggong.activity.comUserLogin;

import com.code.clkj.menggong.bean.BaseViewI;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-11.
 */

public interface ViewActForgetPasswordI extends BaseViewI {
    void doForgetSuccess(TempResponse data);

    void sendForgetCodeSuccess(TempResponse data);
}
