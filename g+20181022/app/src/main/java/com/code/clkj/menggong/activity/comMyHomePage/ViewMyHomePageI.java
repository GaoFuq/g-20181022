package com.code.clkj.menggong.activity.comMyHomePage;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespMyHomePage;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2018/1/5.
 */

public interface ViewMyHomePageI extends BaseLViewI {
    void getMuseDynamicPageSuccess(RespMyHomePage data);

    void deleteMuseDynamicSucceess(TempResponse data);

    void saveDynamicCommentSucceees(TempResponse data);
}
