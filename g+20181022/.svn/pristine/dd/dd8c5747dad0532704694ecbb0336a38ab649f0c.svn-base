package com.lf.tempcore.tempUtil.statusUtil;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Created by caoyang on 2017/7/4.
 */

public class WindowAttr {
    /**
     * 获取状态栏高度,在页面还没有显示出来之前
     * // 通过反射机制获取手机状态栏高度
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = a.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
