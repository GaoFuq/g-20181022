package com.code.clkj.menggong.app;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fm.openinstall.OpenInstall;
import com.lf.tempcore.tempApplication.TempApplication;
import com.qiniu.pili.droid.streaming.StreamingEnv;

import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.RealTimeLocationMessageProvider;
import io.rong.message.GroupNotificationMessage;

/**
 * Created by chenlingkeji on 2016/11/6.
 * for chenlingkeji Company
 * link for more detail www.lingkj.com
 */
public class App extends TempApplication {

    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //             * IMKit SDK调用第一步 初始化
        RongIM.init(this);
//        StreamingEnv.init(getApplicationContext());
//        initShareConfig();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        StreamingEnv.init(getApplicationContext());
        if (isMainProcess()) {
            OpenInstall.init(this);
        }
        try {
            SealAppContext.init(this);
            RongIM.registerMessageType(GroupNotificationMessage.class);
//                RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
            RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
//                RongIM.registerMessageTemplate(new GroupNotificationMessageProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        JPushInterface.init(this);  */          // 初始化 JPush
     //   LeakCanary.install(this);
      //

    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }
 /*   private void initShareConfig(){
//        new TempShareConfiger().initConfig();
    }
    @Override
    public void initImageLoader() {
    }*/

    /**
     * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
