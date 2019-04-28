package com.code.clkj.menggong.activity.comHomepassword.comForgetPayPassWord;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface PreForgetPayPassWordI {
    void forgetUserPayPwd(String password,String code,String phone);
    void sendForgetCode(String phone);
}
