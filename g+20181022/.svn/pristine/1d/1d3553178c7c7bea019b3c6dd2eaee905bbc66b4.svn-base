package com.lf.tempcore.tempUtil;

import java.util.Calendar;

/**
 * Created by caoyang on 2017/8/15.
 * 防止快速点击
 */

public class QuickClickUtils {
    public static final int MIN_CLICK_DELAY_TIME = 500;//这里设置不能超过多长时间
    private static long lastClickTime = 0;

    public static boolean checkClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        }
        return true;
    }
}
