package com.code.clkj.menggong.activity.comMyHomePage;

/**
 * Created by clkj on 2018/1/5.
 */

public interface PreMyhomePageI {
    void getMuseDynamicPage(String page,String pagesize);
    void deleteMuseDynamic(String dyId);
    void saveDynamicComment(String dyId,String content,String type,String rpMuseId);
}
