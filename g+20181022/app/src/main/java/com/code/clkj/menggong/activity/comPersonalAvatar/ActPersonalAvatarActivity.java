package com.code.clkj.menggong.activity.comPersonalAvatar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.PersonalAvatarAdapter;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ActPersonalAvatarActivity extends TempActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//关注
    @Bind(R.id.Personal_Avatar)
    RecyclerView Personal_Avatar;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_personal_homepage_layout);
    }

    @Override
    protected void findViews() {
        initData();
        Personal_Avatar.setLayoutManager(new LinearLayoutManager(this));
        Personal_Avatar.setAdapter(new PersonalAvatarAdapter());

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    protected void OnViewClicked(View v) {

    }
    private void initData(){
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("丁丁猫");
        toolbar_title.setTextSize(20);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("关注");
        toolbar_menu_tv.setTextSize(18);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
    }
}
