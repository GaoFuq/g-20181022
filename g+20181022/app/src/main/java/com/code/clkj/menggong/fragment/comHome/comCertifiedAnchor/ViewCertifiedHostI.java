package com.code.clkj.menggong.fragment.comHome.comCertifiedAnchor;

import com.code.clkj.menggong.bean.BaseViewI;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespGetAchorApplyStatus;

/**
 * Created by Administrator on 2017-12-19.
 */

public interface ViewCertifiedHostI extends BaseViewI {
    void getAchorApplyStatusSuccess(RespGetAchorApplyStatus data);

    void getMuseEpurseSuccess(RespCheckBalance data);
}
