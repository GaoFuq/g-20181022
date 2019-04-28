package com.lf.tempcore.tempUtil.statusUtil;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Window;

/**
 * 状态栏接口
 *
 * @author msdx (msdx.android@qq.com)
 * @version 0.3
 * @since 0.3
 */

interface IStatusBar {
    void setStatusBarColor(Window window, int color);
    void setStatusBarColorForCollapsingToolbar(Activity activity, final AppBarLayout appBarLayout, final CollapsingToolbarLayout collapsingToolbarLayout,
                                               Toolbar toolbar, final int statusColor);
}
