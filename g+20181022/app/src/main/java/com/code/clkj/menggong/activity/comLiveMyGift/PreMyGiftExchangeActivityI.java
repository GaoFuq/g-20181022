package com.code.clkj.menggong.activity.comLiveMyGift;

/**
 * Created by 92573 on 2017/12/25.
 */

public interface PreMyGiftExchangeActivityI {

    void getGiftBalance(String museId, String musePassword, String museOnlieTag);
    void saveEpurse(String museId, String musePassword, String price);
}
