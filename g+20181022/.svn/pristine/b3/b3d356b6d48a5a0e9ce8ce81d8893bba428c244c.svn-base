package com.code.clkj.menggong.activity.comLiveMyGift;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.NearAdapter;
import com.code.clkj.menggong.fragment.comGift.FragReceiveGift;
import com.code.clkj.menggong.fragment.comGift.FragSendGift;
import com.lf.tempcore.tempActivity.TempActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ActMyGiftActivity extends TempActivity {

    @Bind(R.id.receive_gift_btn)
    Button receive_gift_btn;
    @Bind(R.id.send_gift_btn)
    Button send_gift_btn;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//礼物结算
    @Bind(R.id.mGiftrViewPager)
    ViewPager mGiftViewPager;  //展示2个界面的Viewpager
    List<Fragment> fragments;  //存放2个界面
    private final int TAB_01 = 0;
    private final int TAB_02 = 1;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_my_gift_layout);
    }

    @Override
    protected void findViews() {
        mGiftViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case TAB_01:
                        receive_gift_btn.setBackgroundResource(R.drawable.btn_right_01);
                        send_gift_btn.setBackgroundResource(R.drawable.btn_left);
                        break;
                    case TAB_02:
                        receive_gift_btn.setBackgroundResource(R.drawable.btn_right);
                        send_gift_btn.setBackgroundResource(R.drawable.btn_left_01);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void setListeners() {


    }

    @Override
    protected void bindValues() {
        initFragment();
        initData();
        mGiftViewPager.setAdapter(new NearAdapter(getSupportFragmentManager(), fragments));
    }

    @OnClick({R.id.receive_gift_btn,R.id.send_gift_btn,R.id.toolbar_menu_tv})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.receive_gift_btn:
                receive_gift_btn.setBackgroundResource(R.drawable.btn_right_01);
                send_gift_btn.setBackgroundResource(R.drawable.btn_left);
                mGiftViewPager.setCurrentItem(0);
                break;
            case R.id.send_gift_btn:
                receive_gift_btn.setBackgroundResource(R.drawable.btn_right);
                send_gift_btn.setBackgroundResource(R.drawable.btn_left_01);
                mGiftViewPager.setCurrentItem(1);
                break;
            case R.id.toolbar_menu_tv:
                Intent intent_Settlement = new Intent(this,ActMyGiftExchangeActivity.class);
                startActivity(intent_Settlement);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("我的礼物");
        toolbar_title.setTextSize(18);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("礼物结算");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
        receive_gift_btn.setBackgroundResource(R.drawable.btn_right_01);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        FragReceiveGift fragReceiveGift = new FragReceiveGift();
        fragments.add(fragReceiveGift);
        FragSendGift fragSendGift = new FragSendGift();
        fragments.add(fragSendGift);
    }
}
