package com.code.clkj.menggong.bean;


import com.code.clkj.menggong.throwable.ExceptionEngine;

/**
 * Created by caoyang on 2017/7/13.
 */

public interface BaseLViewI {
    void onLoadFinish();
    void onLoadDataError(ExceptionEngine.ApiException e);
    void onLoadDataSuccess();
    void toast(String message);
}
