package com.code.clkj.menggong.activity.comMyWallet.comCheckBalance;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespCheckLS;

/**
 * Created by clkj on 2017/12/21.
 */

public interface ViewCheckBalanceI extends BaseLViewI {

    void getMuseEpurseSuccee(RespCheckBalance data);
    void getMuseEpurseLogSuccee(RespCheckLS data);
}
