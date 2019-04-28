package com.code.clkj.menggong.activity.comHomepassword.comForgetPayPassWord;

import com.code.clkj.menggong.bean.BaseViewI;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface ViewForgetPayPassWordI extends BaseViewI {
    void forgetUserPayPwdSuccee(TempResponse data);
    void sendForgetPayPwdCodeSuccess(TempResponse data);
}
