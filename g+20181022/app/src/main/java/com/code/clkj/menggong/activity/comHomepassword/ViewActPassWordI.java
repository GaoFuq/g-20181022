package com.code.clkj.menggong.activity.comHomepassword;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespActPassWord;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface ViewActPassWordI extends BaseViewI {
    void isSetPayPwdSuccee(RespActPassWord data);
    void resetLoginPwdSuccee(TempResponse data);
    void resetPayPwdSuccee(TempResponse data);
}
