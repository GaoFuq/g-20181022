package com.code.clkj.menggong.activity.comMyWallet.comRechargeMoney;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespSaveEpurseOrder;

/**
 * Created by clkj on 2018/1/16.
 */

public interface ViewRechargeMoneyI extends BaseViewI {
    void saveEpurseOrderSuccess(RespSaveEpurseOrder data);
}
