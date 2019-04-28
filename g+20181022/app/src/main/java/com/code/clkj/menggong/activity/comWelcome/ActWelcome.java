package com.code.clkj.menggong.activity.comWelcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.activity.comUserGuidance.ActUserGuidance;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

public class ActWelcome extends TempActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context context = this;

    private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟2秒 欢迎界面

    @Bind(R.id.act_welcome_iv)
    ImageView actWelcomeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("isfirst", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (preferences.getBoolean("firststart", true)) {
                    editor = preferences.edit();
                    // 将登录标志位设置为false，下次登录时不在显示首次登录界面
                    editor.putBoolean("firststart", false);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(context, ActUserGuidance.class);
                    startActivity(intent);
                    finish();
                } else {
                startActivity(new Intent(context,ActHome.class));
                finish();

            }

            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    protected void findViews() {

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
}
