package com.lf.tempcore.tempUtil.statusUtil;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * After Lollipop use system method.
 * Created by qiu on 8/27/16.
 */
class StatusBarCompatOther {

    public static int getStatusBarHeight(Context context) {
        return 0;
    }


    public static void setStatusBarColorForCollapsingToolbar(Activity activity, AppBarLayout appBarLayout, final CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, final int statusColor, final TextView title) {


        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        if (behavior != null && behavior instanceof AppBarLayout.Behavior) {
            int verticalOffset = ((AppBarLayout.Behavior) behavior).getTopAndBottomOffset();
            if (Math.abs(verticalOffset) > appBarLayout.getHeight() - collapsingToolbarLayout.getScrimVisibleHeightTrigger()) {

                title.setAlpha(1f);
            } else {

                title.setAlpha(0f);
            }
        } else {
            title.setAlpha(0f);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > appBarLayout.getHeight() - collapsingToolbarLayout.getScrimVisibleHeightTrigger()) {
                    if (title.getAlpha() == 0f) {
                        ValueAnimator animator = ValueAnimator.ofObject(new MyEvaluator(), new Bean(Color.TRANSPARENT, 0f), new Bean(statusColor,1f))
                                .setDuration(collapsingToolbarLayout.getScrimAnimationDuration());
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                Bean bean= (Bean) valueAnimator.getAnimatedValue();
                                title.setAlpha(bean.alpha);
                            }
                        });
                        animator.start();
                    }
                } else {
                    if (title.getAlpha() == 1f) {
                        ValueAnimator animator = ValueAnimator.ofObject(new MyEvaluator(), new Bean(statusColor,1f), new Bean(Color.TRANSPARENT, 0f))
                                .setDuration(collapsingToolbarLayout.getScrimAnimationDuration());
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                Bean bean= (Bean) valueAnimator.getAnimatedValue();
                                title.setAlpha(bean.alpha);
                            }
                        });
                        animator.start();
                    }
                }
            }
        });
        collapsingToolbarLayout.getChildAt(0).setFitsSystemWindows(false);
        collapsingToolbarLayout.setStatusBarScrimColor(statusColor);
        title.setAlpha(0f);
    }

    static class MyEvaluator implements TypeEvaluator<Bean> {
        public MyEvaluator() {
        }

        @Override
        public Bean evaluate(float fraction, Bean startValue, Bean endValue) {

            return new Bean((int) (startValue.color + (endValue.color - startValue.color) * fraction), startValue.alpha + (endValue.alpha - startValue.alpha) * fraction);
        }
    }

    static class Bean {
        private int color;
        private float alpha;

        public Bean(int color, float alpha) {
            this.color = color;
            this.alpha = alpha;
        }

    }

}
