package com.code.clkj.menggong.activity.comLive;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.PermissionChecker;
import com.code.clkj.menggong.util.Util;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.StreamingProfile;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ActLiveActivity extends TempActivity implements ViewActLiveActivityI{


    protected StreamingProfile mProfile = new StreamingProfile();
//    protected EncodingConfig mEncodingConfig;
    private PreActLiveActivityI mPreI;
//    protected abstract void initStreamingManager();
    private  String url;
    private PermissionChecker mPermissionChecker = new PermissionChecker(this);
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_live_layout);
        initEncodingProfile();

        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || mPermissionChecker.checkPermission();
        if (!isPermissionOK) {
            Util.showToast(this, "Some permissions is not approved !!!");
            return;
        }
//        initStreamingManager();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        mPreI = new PreActLiveActivityImpl(this);
//        Intent intent = getIntent();
//        String inputText = intent.getStringExtra("INPUT_TEXT");


//        mAudioStereoEnable = intent.getBooleanExtra(AUDIO_CHANNEL_STEREO, false);

//        initStreamingManager();
        String lat = "29.465457";
       String lng = "106.476440";

        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(), lat, lng);
    }

    @OnClick({R.id.cancle_img})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.cancle_img:
                finish();
        }
    }
    private void initEncodingProfile() {
//        mEncodingConfig = (EncodingConfig) getIntent().getSerializableExtra("EncodingConfig");

        StreamingProfile.AudioProfile aProfile = null;
        StreamingProfile.VideoProfile vProfile = null;



        // custom
        if (aProfile != null || vProfile != null) {
            StreamingProfile.AVProfile avProfile = new StreamingProfile.AVProfile(vProfile, aProfile);
            mProfile.setAVProfile(avProfile);
        }

        mProfile.setDnsManager(getMyDnsManager())
                .setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3))
                .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
    }
    /**
     * If you want to use a custom DNS server, config it
     * Not required.
     */
    private static DnsManager getMyDnsManager() {
        IResolver r0 = null;
        IResolver r1 = new DnspodFree();
        IResolver r2 = AndroidDnsServer.defaultResolver();
        try {
            r0 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {

    }

    @Override
    public void showPro() {

    }

    @Override
    public void disPro() {

    }

    @Override
    public void queryCanLivingRoomSuccess(RespQueryCanLivingRoom data) {
       url = data.getResult().getRoomPushUrl();

        try {
            mProfile.setPublishUrl(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(url);
            StreamingProfile.Stream stream = new StreamingProfile.Stream(jsonObject);
            mProfile.setStream(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void saveConsumOrderSuccess(RespConsumOrder data) {

    }

    @Override
    public void getUnreadMessageSuccess(RespGetUnreadMessage data) {

    }

    @Override
    public void giftPaihanghSuccess(RespGiftPaihangh data) {

    }
}

