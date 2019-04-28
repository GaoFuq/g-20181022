package com.code.clkj.menggong.activity.comLiveMyGift;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespSaveEpurse;
import com.code.clkj.menggong.response.RespgetGiftBalance;

/**
 * Created by 92573 on 2017/12/25.
 */

public interface ViewMyGiftExchangeActivityI  extends BaseViewI {
    void getGiftBalanceSuccess(RespgetGiftBalance data) ;//礼物结算
    void saveEpurseSuccess(RespSaveEpurse data);//礼物金额结算

    void onLoadFinish();

}
