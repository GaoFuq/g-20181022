package com.code.clkj.menggong.activity.comWeb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.Constance;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempWebComponment.TempWebHelper;

import butterknife.Bind;

/**
 * Created by whb on 2016/6/7.
 * 邮箱 673337538@qq.com
 */
public class ActWeb extends TempActivity {

    @Bind(R.id.act_web) WebView act_web;

//    private TempShareVSCustomHelper mShareHelper;
    private TempWebHelper mHelper;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_web_layout);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void findViews() {
        Toolbar toolbar_top = (Toolbar) findViewById(R.id.toolbar_top);
        String title = getIntent().getStringExtra(Constance.KEY_WEB_TITLE);//标题
        if (toolbar_top != null) {
            TextView toolbar_title = (TextView) toolbar_top.findViewById(R.id.toolbar_title);
            if (toolbar_title != null)
                toolbar_title.setText(title);
                toolbar_title.setTextSize(18);
        }
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));

    }
    public static  void startActivityIntent(Context context, String title, String url){
        Intent intent = new Intent(context,ActWeb.class);
        intent.putExtra(Constance.KEY_WEB_TITLE,title);
        intent.putExtra(Constance.KEY_WEB_URL,url);
        context.startActivity(intent);
    }
    public static  void startActivityIntent2(Context context, String title, String url, String advId, String advType){
        Intent intent = new Intent(context,ActWeb.class);
        intent.putExtra(Constance.KEY_WEB_TITLE,title);
        intent.putExtra(Constance.KEY_WEB_URL,url);
        intent.putExtra("advId", advId);
        intent.putExtra("advType", advType);
        context.startActivity(intent);
    }

    @Override
    protected void bindValues() {

        String url = getIntent().getStringExtra(Constance.KEY_WEB_URL);//url地址
        if (mHelper == null) {
            mHelper = new TempWebHelper(act_web, getTempContext());
            Debug.error("-------------url-------" + url);
            if (url.startsWith("http://") || url.startsWith("https://")) {
                mHelper.loadWeb(url);
            } else {
                mHelper.loadWeb("http://" + url);
            }
//            mHelper.loadWeb(url);
        }
    }


    @Nullable
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
//            case R.id.top_bar_right_image:
//                mShareHelper = new TempShareVSCustomHelper(this, "http://lingkj.com/", getResources().getString(R.string.app_name), getResources().getString(R.string.app_name), new UMImage(getTempContext(), R.mipmap.ic_launcher));
//                mShareHelper.showShare();
//                break;
        }

    }

    private  void  Share(){
//        TempShareVSCustomHelper mHelper = new TempShareVSCustomHelper(getTempContext(), TempURIConfig.BASE_SERVICE_URL+"ibds/mall/buyer/goToRecommend.spm?userId="+ TempLoginConfig.sf_getSueid(), "大十字", "邀请你注册", new UMImage(this, R.mipmap.ic_launcher));
//        mHelper.showShare();
    }
}
