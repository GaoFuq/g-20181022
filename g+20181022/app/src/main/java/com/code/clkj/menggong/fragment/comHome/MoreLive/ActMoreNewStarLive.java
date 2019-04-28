package com.code.clkj.menggong.fragment.comHome.MoreLive;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

public class ActMoreNewStarLive extends TempActivity {
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_act_more_new_star_live);
//    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_more_new_star_live);

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        if (toolbar_top != null) {
//            toolbar_top.setNavigationIcon(R.mipmap.back);
            toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
            toolbar_title.setText("直播新星");
            toolbar_title.setTextSize(20);
            toolbar_back.setImageResource(R.mipmap.back);
            toolbar_back.setVisibility(View.VISIBLE);

        }

    }

    @Override
    protected void OnViewClicked(View v) {

    }
}
