package com.code.clkj.menggong.activity.comUserLogin;

/**
 * Created by Administrator on 2017-12-11.
 */

public interface PreActForgetPasswordI {
    void doForget(String code,String userName,String password);
    void sendForgetCode(String phone);
}
