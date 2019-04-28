package com.code.clkj.menggong.activity.comUserGuidance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.adapter.ViewPagerAdapter;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempUtil.QuickClickUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页
 */
public class ActUserGuidance extends TempActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.guidance_enter_iv)
    ImageView guidanceEnterIv;
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;

    //引导图片资源
    private static final int[] pics = {R.mipmap.boot_page1,
            R.mipmap.boot_page2, R.mipmap.boot_page3};

    //底部小店图片
    private ImageView[] dots;

    //记录当前选中位置
    private int currentIndex;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_guidance);
//        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#00000000"));
//        statusUtil.setMiuiStatusBarDarkMode(this, true);
    }

    @Override
    protected void findViews() {
        views = new ArrayList<View>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);
        }
        vp = (ViewPager) findViewById(R.id.viewpager);
        //初始化Adapter
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);
        //绑定回调
        vp.setOnPageChangeListener(this);

        //初始化底部小点
        initDots();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick(R.id.guidance_enter_iv)
    protected void OnViewClicked(View v) {
        if (QuickClickUtils.checkClick())return;
        switch (v.getId()){
            case R.id.guidance_enter_iv:
                startActivity(new Intent(this, ActHome.class));
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if (QuickClickUtils.checkClick())return;
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setImageResource(R.mipmap.circle);//都设为空心
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setImageResource(R.mipmap.circle_1);//设置为红色，即选中状态
    }

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    /**
     * 这只当前引导小点的选中
     */
    private void setCurDot(int positon) {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }

        dots[positon].setImageResource(R.mipmap.circle_1);
        dots[currentIndex].setImageResource(R.mipmap.circle);

        currentIndex = positon;
        if (positon == 2) {
            guidanceEnterIv.setVisibility(View.VISIBLE);
        } else {
            guidanceEnterIv.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
