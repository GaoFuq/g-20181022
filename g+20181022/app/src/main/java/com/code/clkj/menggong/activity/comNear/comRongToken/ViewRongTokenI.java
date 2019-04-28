package com.code.clkj.menggong.activity.comNear.comRongToken;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespGetMuseToken;

/**
 * Created by clkj on 2018/1/5.
 */

public interface ViewRongTokenI extends BaseViewI {
    void getMuseTokenSuccess(RespGetMuseToken data);
}
