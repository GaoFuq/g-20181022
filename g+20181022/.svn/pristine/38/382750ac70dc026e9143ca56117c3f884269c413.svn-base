package com.code.clkj.menggong.activity.comLiveMyOrder;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ActMyOrderdetailsActivity extends TempActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_my_order_details_layout);

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

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("订单详情(待付款)");
        toolbar_title.setTextSize(18);
    }
}
