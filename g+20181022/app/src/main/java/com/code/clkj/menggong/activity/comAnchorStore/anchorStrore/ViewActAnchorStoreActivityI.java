package com.code.clkj.menggong.activity.comAnchorStore.anchorStrore;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespQueryMallGoods;
import com.code.clkj.menggong.response.RespQueryMallGoodsDetail;

/**
 * Created by 92573 on 2018/1/4.
 */

public interface ViewActAnchorStoreActivityI extends BaseLViewI {
    void queryMallGoodsSuccess(RespQueryMallGoods data);


    void dismissPro();


}
