package com.code.clkj.menggong.activity.comHomeDetection;

/**
 * Created by Administrator on 2017-12-14.
 */

public interface PreActDetectionI {
    void sendForgetCode(String phone);
    void getCheckList();
    void saveCheckReserve(String mucrCkId,String mucrBeginTime,String mucrEndTime,String code,String mucrPhone);
}
