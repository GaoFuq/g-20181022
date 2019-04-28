package com.code.clkj.menggong.activity.comLiveHistory.comShowHistory;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespShowHistory;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/21.
 */

public interface ViewShowHistoryI extends BaseLViewI {

    void getMuseHistorySuccess(RespShowHistory data);

    void deleteMuseHistorySuccee(TempResponse data);
}
