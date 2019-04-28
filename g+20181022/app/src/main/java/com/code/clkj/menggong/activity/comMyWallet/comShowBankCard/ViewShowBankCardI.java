package com.code.clkj.menggong.activity.comMyWallet.comShowBankCard;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespShowBankCard;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/22.
 */

public interface ViewShowBankCardI extends BaseLViewI {
    void getMuseBrankSuccee(RespShowBankCard data);

    void saveWithdrawSuccee(TempResponse data);
}
