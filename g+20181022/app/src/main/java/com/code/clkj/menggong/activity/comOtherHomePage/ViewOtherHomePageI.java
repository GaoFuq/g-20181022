package com.code.clkj.menggong.activity.comOtherHomePage;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespOtherHomePage;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/5.
 */

public interface ViewOtherHomePageI extends BaseLViewI {
    void getOtherDynamicPageSucceess(RespOtherHomePage data);

    void saveMuseFollowSuccess(TempResponse data);
}
