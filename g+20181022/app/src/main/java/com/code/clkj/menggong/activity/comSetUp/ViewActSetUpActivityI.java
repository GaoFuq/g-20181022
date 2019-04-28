package com.code.clkj.menggong.activity.comSetUp;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespActPhone;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface ViewActSetUpActivityI extends BaseViewI {
    void updateLoginByIdSuccess(TempResponse data);
    void contactInformationSuccee(RespActPhone data);
}
