package com.code.clkj.menggong.activity.comAnchorStore.anchorStroreDetail;

/**
 * Created by 92573 on 2018/1/5.
 */

public interface PreActCommodityDetailsActivityI {
    void queryMallGoodsDetail(String mgooId);//商品详情
    void toConfirmOrder(String museId, String musePassword, String mgooId, String modeNum);//到订单页

}
