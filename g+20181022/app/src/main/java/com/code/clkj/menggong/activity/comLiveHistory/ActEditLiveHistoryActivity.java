package com.code.clkj.menggong.activity.comLiveHistory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/11.
 */

public class ActEditLiveHistoryActivity extends TempActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//删除
    @Bind(R.id.eidt_live_history_user)
    RecyclerView eidt_live_history_user;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_edit_live_history_layout);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        initData();
    }

    @Override
    protected void OnViewClicked(View v) {

    }
    private void initData(){
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("历史记录");
        toolbar_title.setTextSize(20);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("完成");
        toolbar_menu_tv.setTextSize(18);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
    }
}
