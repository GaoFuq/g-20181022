package com.code.clkj.menggong.activity.comHome;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.activity.comCameraStreaming.AVStreamingActivity;
import com.code.clkj.menggong.activity.comCameraStreaming.CameraConfigFragment;
import com.code.clkj.menggong.activity.comCameraStreaming.EncodingConfigFragment;
import com.code.clkj.menggong.activity.comCameraStreaming.StreamingBaseActivity;
import com.code.clkj.menggong.activity.comChatRoom.ActChatRoom;
import com.code.clkj.menggong.activity.comHome.comSaveLatLng.PreSaveLatLngI;
import com.code.clkj.menggong.activity.comHome.comSaveLatLng.PreSaveLatLngImpl;
import com.code.clkj.menggong.activity.comHome.comSaveLatLng.ViewSaveLatLngI;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityI;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityImpl;
import com.code.clkj.menggong.activity.comLive.ViewActLiveActivityI;
import com.code.clkj.menggong.activity.comLiveApply.ActLiveApplyActivity;
import com.code.clkj.menggong.activity.comLiveApply.comAuthenticationState.ActAuthenticationState;
import com.code.clkj.menggong.activity.comMessageCenter.ActMessageCenterActivity;
import com.code.clkj.menggong.activity.comNear.NearHomeActivity;
import com.code.clkj.menggong.activity.comSearch.ActSearch;
import com.code.clkj.menggong.activity.comSetUp.ActSetUpActivity;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.app.SealUserInfoManager;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.fragment.comHome.FragHomeMine;
import com.code.clkj.menggong.fragment.comHome.FragHomeNearBy;
import com.code.clkj.menggong.fragment.comHome.FragHomeOnline1;
import com.code.clkj.menggong.fragment.comHome.comCertifiedAnchor.PreCertifiedHostI;
import com.code.clkj.menggong.fragment.comHome.comCertifiedAnchor.PreCertifiedHostImpl;
import com.code.clkj.menggong.fragment.comHome.comCertifiedAnchor.ViewCertifiedHostI;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetAchorApplyStatus;
import com.code.clkj.menggong.response.RespGetMuseToken;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.Cache;
import com.code.clkj.menggong.util.PermissionChecker;
import com.code.clkj.menggong.util.TempDataUtils;
import com.code.clkj.menggong.util.Util;
import com.code.clkj.menggong.widget.PermissionUtil;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.listener.AppWakeUpAdapter;
import com.fm.openinstall.model.AppData;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempResponse.TempResponse;
import com.qiniu.pili.droid.streaming.StreamingProfile;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 作者:caoyang
 * 创建时间:2017/9/4 9:09
 * 描述: 首页
 */

public class ActHome extends TempActivity implements ViewActLiveActivityI, ViewCertifiedHostI, ViewSaveLatLngI {
    private static final String TAG = "ActHome";
    @Bind(R.id.toolbar_search_address_iv)
    ImageView toolbar_search_address_iv;//消息图片
    @Bind(R.id.toolbar_search_address_tv)
    TextView toolbar_search_address_tv;//定位图片
    @Bind(R.id.toolbar_search_ed)
    EditText toolbar_search_ed;//搜索编辑
    @Bind(R.id.toolbar_search_address_gr)
    ImageView toolbar_search_address_gr;//首页右方组群
    @Bind(R.id.toolbar_search_layout)
    LinearLayout toolbar_search_layout;//搜索
    @Bind(R.id.toolbar_mess_iv)
    ImageView toolbar_mess_iv;//消息图片
    @Bind(R.id.toolbar_mess_num)
    TextView toolbar_mess_num;//消息数量
    @Bind(R.id.toolbar_mess_layout)
    RelativeLayout toolbar_mess_layout;//消息视图
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;//返回
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//标题
    @Bind(R.id.toolbar_right_fj)
    ImageView toolbar_right_fj;//附近图标
    @Bind(R.id.toolbar_right_setting)
    ImageView toolbar_right_setting;//设置图标
    @Bind(R.id.toolbar_normal_w)
    FrameLayout toolbar_normal_w;//通用 返回+标题
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;//整个toolbar
    @Bind(R.id.tv_live)
    TextView tv_live;

    @Bind(R.id.act_home_bar_1)
    FrameLayout act_home_bar_1;
    @Bind(R.id.act_home_bar_2)
    FrameLayout act_home_bar_2;
    @Bind(R.id.act_home_bar_3)
    FrameLayout act_home_bar_3;
    @Bind(R.id.act_home_bar_1_iv)
    ImageView act_home_bar_1_iv;
    @Bind(R.id.act_home_bar_1_tv)
    TextView act_home_bar_1_tv;
    @Bind(R.id.act_home_bar_2_iv)
    ImageView act_home_bar_2_iv;
    @Bind(R.id.act_home_bar_2_tv)
    TextView act_home_bar_2_tv;
    @Bind(R.id.act_home_bar_3_iv)
    ImageView act_home_bar_3_iv;


    @Bind(R.id.act_home_bar_3_tv)
    TextView act_home_bar_3_tv;


    private Fragment[] fragments;

    private int mIndex;
    FrameLayout[] mTabs;
    private long firstTime = 0; // 记录点击时间
    //    private FragHomeOnline mFragHomeOnline;
    private FragHomeOnline1 mFragHomeOnline;
    private FragHomeNearBy mFragHomeNearBy;
    private FragHomeMine mFragHomeMine;
    private int toobar_status = -1;
    private static final int Mars = 20;
    private PermissionChecker mPermissionChecker = new PermissionChecker(this);
    private PreActLiveActivityI mPreI;
    protected StreamingProfile mProfile = new StreamingProfile();
    private String url = "";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EncodingConfigFragment mEncodingConfigFragment;
    private PreCertifiedHostI myPreI;
    //定位
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private String lat;
    private String lng;

    private String messageNumber;

    private PreSaveLatLngI mPreII;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    AppWakeUpAdapter wakeUpAdapter = new AppWakeUpAdapter() {
        @Override
        public void onWakeUp(AppData appData) {
            //获取渠道数据
            String channelCode = appData.getChannel();
            //获取绑定数据
            String bindData = appData.getData();
            Log.d("OpenInstall", "getWakeUp : wakeupData = " + appData.toString());
        }
    };
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 此处要调用，否则App在后台运行时，会无法截获
        OpenInstall.getWakeUp(intent, wakeUpAdapter);
    }
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_home_layout);
        //获取唤醒参数
        OpenInstall.getWakeUp(getIntent(), wakeUpAdapter);
    }

    @Override
    protected void findViews() {
        initFragment();//底部导航栏,3个frame
        setToolbar(ToolbarStatus.FRAGE_HOME_SREARCH);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
    }

    @Override
    protected void setListeners() {
//        //声明定位回调监听器
//        AMapLocationListener mLocationListener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//
//            }
//        };
////        //初始化定位
////        mLocationClient = new AMapLocationClient(getApplicationContext());
////        //设置定位回调监听
////        mLocationClient.setLocationListener(mLocationListener);
////        //开启定位
////        mLocationClient.startLocation();
        OpenInstall.getInstall(new AppInstallAdapter() {
            @Override
            public void onInstall(AppData appData) {
                //获取渠道数据
                String channelCode = appData.getChannel();
                //获取自定义数据
                String bindData = appData.getData();
                Log.d("OpenInstall", "getInstall : installData = " + appData.toString());
            }
        });
    }

    @Override
    protected void bindValues() {
        mPreI = new PreActLiveActivityImpl(this);
        mPreII = new PreSaveLatLngImpl(this);
        myPreI = new PreCertifiedHostImpl(this);
        initLocation();
        // 设置定位参数
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClient.setLocationOption(locationOption);


        // 启动定位
        InputMethodManager imm = (InputMethodManager) ActHome.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//隐藏软键盘 //
        imm.hideSoftInputFromWindow(toolbar_search_ed.getWindowToken(), 0);

        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION );



//        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(), lat, lng);
        mPreI.getUnreadMessage(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd());
//        mPreI = new PreActLiveActivityImpl(this);
////        String lat = "28.465457";
////        String lng = "106.476440";
//
//        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(), lat, lng);
        Log.i("经纬度", lat + "" + lng + "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        mEncodingConfigFragment = (EncodingConfigFragment) fragmentManager.findFragmentById(R.id.encoding_config_fragment);
        mCameraConfigFragment = (CameraConfigFragment) fragmentManager.findFragmentById(R.id.camera_config_fragment);
    }


    //    @Override
//    protected void setListeners() {
//
//    }
//
//    @Override
//    protected void bindValues() {
//
//    }
/*    private void updateInputTextView(final String url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Cache.saveURL(ActHome.this, url);
            }
        });
    }*/
    private String type=null;
    @OnClick({R.id.tv_live, R.id.toolbar_right_setting, R.id.toolbar_mess_iv, R.id.act_home_icon_search, R.id.toolbar_search_address_gr})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_live:
//                if (TempLoginConfig.sf_getLoginState()){
//                    Intent intent = new Intent(this, ExtCapStreamingActivity.class);
//                    intent.putExtra("URL", url);
//                        startActivity(intent);
//
//                } else {
//                    Intent intent = new Intent(this, LoginActivity.class);
//                    startActivity(intent);
//                }
                if (TempLoginConfig.sf_getLoginState()) {
                    type="kaibo";
                    requestPermission(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                else{
                    startActivity(new Intent(this, LoginActivity.class));
                }
             //   String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                break;
            case R.id.toolbar_right_setting:
                Intent intenttoolbar_right_setting = new Intent(this, ActSetUpActivity.class);
                startActivity(intenttoolbar_right_setting);
                break;
            case R.id.toolbar_mess_iv:
                Intent intenttoolbar_mess_iv = new Intent(this, ActMessageCenterActivity.class);
                startActivity(intenttoolbar_mess_iv);
                break;
            case R.id.act_home_icon_search:
                Intent intent = new Intent(this, ActSearch.class);
                String searchKey = toolbar_search_ed.getText().toString();
                intent.putExtra("searchKey", searchKey);
                startActivity(intent);
                break;
            case R.id.toolbar_search_address_gr:
                Constance.ISSWITHCFRAGMENT = true;
                Intent intentNearHome = new Intent(ActHome.this, ActChatRoom.class);
                intentNearHome.putExtra("friends", 3);
                startActivity(intentNearHome);
               // NearHomeActivity

                break;
                default:
                    break;
        }

    }

    public void getGenPublishURL() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String publishUrl = genPublishURL();
                if (publishUrl != null) {
                    Cache.saveURL(ActHome.this, publishUrl);
                    //   updateInputTextView(publishUrl);
                }
            }

        }).start();
    }

    private String genPublishURL() {
        String publishUrl = "rtmp://pili-publish.media.g.lingkj.com/gaylive/G1513828337908A?e=1514461905&token=SIGGR8oimlIlyE9562JLjZLHGhfcjxAnYgKvGtnT:aS3QtOnxJ6ja4_0H-TYzN9rJUw8=";

        if (publishUrl == null) {
            Util.showToast(ActHome.this, "Get publish GENERATE_STREAM_TEXT_V1 failed !!!");
            return null;
        }

        return publishUrl;
    }

    private void initFragment() {
        mTabs = new FrameLayout[]{act_home_bar_1, act_home_bar_2, act_home_bar_3};
        mTabs[0].setSelected(true);
//        mFragHomeOnline = new FragHomeOnline();
        mFragHomeOnline = new FragHomeOnline1();
        mFragHomeNearBy = new FragHomeNearBy();
        mFragHomeMine = new FragHomeMine();
//        fragments = new Fragment[]{mFragHomeOnline, mFragHomeNearBy, mFragHomeMine};
        fragments = new Fragment[]{mFragHomeOnline, mFragHomeNearBy, mFragHomeMine};
        getSupportFragmentManager().beginTransaction().add(R.id.act_home_body, fragments[0])
                .commit();


    }

    public int index = 0;
   public int currentTabIndex = 0;
    public void onTabClicked(View view) {

        switch (view.getId()) {
            case R.id.act_home_bar_1:
                initTab();
                index = 0;
                setToolbar(ToolbarStatus.FRAGE_HOME_SREARCH);
                act_home_bar_1_iv.setImageResource(R.mipmap.rounded_1);
                act_home_bar_1_tv.setTextColor(Color.parseColor("#f70052"));
                break;
            case R.id.act_home_bar_2:
                //获取登录状态,true为已登录
                if (TempLoginConfig.sf_getLoginState()) {
                    if(index==0){
                        setToolbar(ToolbarStatus.FRAGE_HOME_SREARCH);
                        act_home_bar_1_iv.setImageResource(R.mipmap.rounded_1);
                        act_home_bar_1_tv.setTextColor(Color.parseColor("#f70052"));
                    }
                    if(index==2){
                        setToolbar(ToolbarStatus.FRAGE_NORMAL_SETTING, "个人中心");
                        setMessageNum(messageNumber);
                        act_home_bar_3_iv.setImageResource(R.mipmap.coz_3);
                        act_home_bar_3_tv.setTextColor(Color.parseColor("#f70052"));
                    }
                //    index = 1;
                  /*  setToolbar(ToolbarStatus.FRAGE_NORMAL_NEARBY, "附近");
                    act_home_bar_2_iv.setImageResource(R.mipmap.compass_1);
                    act_home_bar_2_tv.setTextColor(Color.parseColor("#f70052"));*/

                    //NearHomeActivity
                    Intent intent = new Intent(this, NearHomeActivity.class);
                    startActivityForResult(intent, Mars);



                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                  //  finish();
                }

                break;
            case R.id.act_home_bar_3:
                initTab();
                index = 2;
                if (TempLoginConfig.sf_getLoginState()) {
                    setToolbar(ToolbarStatus.FRAGE_NORMAL_SETTING, "个人中心");
                    setMessageNum(messageNumber);
                    act_home_bar_3_iv.setImageResource(R.mipmap.coz_3);
                    act_home_bar_3_tv.setTextColor(Color.parseColor("#f70052"));
                }else{
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
                default:
                    break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.act_home_body, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    /**
     * 还原到初始状态
     */
    private void initTab() {
        act_home_bar_1_iv.setImageResource(R.mipmap.rounded);
        act_home_bar_1_tv.setTextColor(Color.parseColor("#262626"));
        act_home_bar_2_iv.setImageResource(R.mipmap.compass);
        act_home_bar_2_tv.setTextColor(Color.parseColor("#262626"));
        act_home_bar_3_iv.setImageResource(R.mipmap.coz_2);
        act_home_bar_3_tv.setTextColor(Color.parseColor("#262626"));
    }

    /**
     * @param toobar_status ToolbarStatus.NORMAL
     */
    protected void setToolbar(int toobar_status, String title) {
        if (this.toobar_status == toobar_status || this.toolbar_top == null) {
            return;
        }
        this.toobar_status = toobar_status;
        initToolbar();
        switch (toobar_status) {
            case ToolbarStatus.FRAGE_NORMAL:
                toolbar_title.setVisibility(View.VISIBLE);
                break;
            case ToolbarStatus.FRAGE_HOME_SREARCH:
                toolbar_search_address_gr.setVisibility(View.VISIBLE);
                toolbar_search_address_iv.setVisibility(View.VISIBLE);
                toolbar_search_address_tv.setVisibility(View.VISIBLE);
                toolbar_right_setting.setVisibility(View.VISIBLE);
                break;
            case ToolbarStatus.FRAGE_NORMAL_NEARBY:
                toolbar_back.setVisibility(View.VISIBLE);
                toolbar_right_fj.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(title);
                break;
            case ToolbarStatus.FRAGE_NORMAL_SETTING:
                toolbar_right_setting.setVisibility(View.VISIBLE);
                toolbar_mess_layout.setVisibility(View.VISIBLE);
                toolbar_mess_num.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(title);
                break;


        }
    }

    /**
     * @param toobar_status ToolbarStatus.NORMAL
     */
    protected void setToolbar(int toobar_status) {
        setToolbar(toobar_status, "");
    }

    protected void initToolbar() {

        if (toobar_status <= ToolbarStatus.FRAGE_NORMAL_SETTING) {
            toolbar_normal_w.setVisibility(View.VISIBLE);
            toolbar_search_layout.setVisibility(View.GONE);
            for (int i = 0; i < toolbar_normal_w.getChildCount(); i++) {
                View view = toolbar_normal_w.getChildAt(i);
                view.setVisibility(View.GONE);
            }

        } else {
            toolbar_normal_w.setVisibility(View.GONE);
            toolbar_search_layout.setVisibility(View.VISIBLE);
        }
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
//        if (data.getMsg().equals("未申请直播,请申请直播")){
//            tv_live.setClickable(false);
//        }
//        try {
//            mProfile.setPublishUrl(url);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            JSONObject jsonObject = new JSONObject(url);
//            StreamingProfile.Stream stream = new StreamingProfile.Stream(jsonObject);
//            mProfile.setStream(stream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void saveConsumOrderSuccess(RespConsumOrder data) {

    }

    int status = -1;//标记认证状态
    String mUsceId;
    String failureCause;//认证失败原因
    private CameraConfigFragment mCameraConfigFragment;
    @Override
    public void getAchorApplyStatusSuccess(RespGetAchorApplyStatus data) {
        if (data.getResult() != null) {
            status = TempDataUtils.string2Int(data.getResult().getUsceStatus());
            mUsceId = data.getResult().getUsceId();
            failureCause = data.getResult().getFailureCause();
            if (status == 0) {
                startActivity(new Intent(this, ActLiveApplyActivity.class));
            } else if (status == 2) {
              //  Intent intent = new Intent(this, ExtCapStreamingActivity.class);
                Intent intent = new Intent(this, AVStreamingActivity.class);

                intent.putExtra("URL", url);
           //     int pos = mStreamTypeSpinner.getSelectedItemPosition();
                intent.putExtra(StreamingBaseActivity.INPUT_TYPE, StreamingBaseActivity.INPUT_TYPE_URL);
             /*   intent.putExtra(StreamingBaseActivity.INPUT_TEXT, streamText);*/
               /* mEncodingConfigFragment=new EncodingConfigFragment();
                mCameraConfigFragment=new CameraConfigFragment();*/

                intent.putExtra("EncodingConfig", mEncodingConfigFragment.buildEncodingConfig());
                intent.putExtra("CameraConfig", mCameraConfigFragment.buildCameraConfig());
            //   intent.putExtras(mEncodingConfigFragment.getIntent());
             //   intent.putExtras(mCameraConfigFragment.getIntent());
                startActivity(intent);
            } else {
                Intent intentmCertification = new Intent(this, ActAuthenticationState.class);
                intentmCertification.putExtra("state", status);
                intentmCertification.putExtra("usceId", mUsceId);
                intentmCertification.putExtra("failureCause", failureCause);
                startActivity(intentmCertification);
            }
        } else {
            startActivity(new Intent(this, ActLiveApplyActivity.class));
        }
    }
/*    private EncodingConfig buildEncodingConfig() {
        EncodingConfig encodingConfig = new EncodingConfig();

      //  View root = getView();
        // if audio only
      *//*  encodingConfig.mIsAudioOnly = root.findViewById(R.id.video_config_panel).getVisibility() == View.GONE;
        // set codec type
        boolean codecSW= false;
        boolean codecHW = false;
        if (encodingConfig.mIsAudioOnly) {
            encodingConfig.mCodecType = codecSW ? AVCodecType.SW_AUDIO_CODEC : AVCodecType.HW_AUDIO_CODEC;
        } else {
            encodingConfig.mCodecType = codecSW ? AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC :
                    codecHW? AVCodecType.HW_VIDEO_SURFACE_AS_INPUT_WITH_HW_AUDIO_CODEC :
                            AVCodecType.HW_VIDEO_YUV_AS_INPUT_WITH_HW_AUDIO_CODEC;
        }
        // set video if not audio only
        if (!encodingConfig.mIsAudioOnly) {
            // quality
         //   encodingConfig.mIsVideoQualityPreset = ((RadioButton) root.findViewById(R.id.video_quality_preset)).isChecked();
            if (encodingConfig.mIsVideoQualityPreset) {
              //  Spinner presetSpinner = (Spinner) root.findViewById(R.id.video_quality_presets);
              //  encodingConfig.mVideoQualityPreset = mEncodingConfigFragment.VIDEO_QUALITY_PRESETS_MAPPING[presetSpinner.getSelectedItemPosition()];
            } else {
              *//**//*  encodingConfig.mVideoQualityCustomFPS = Integer.parseInt(((EditText) root.findViewById(R.id.video_quality_custom_fps)).getText().toString());
                encodingConfig.mVideoQualityCustomBitrate = Integer.parseInt(((EditText) root.findViewById(R.id.video_quality_custom_bitrate)).getText().toString());
                encodingConfig.mVideoQualityCustomMaxKeyFrameInterval = Integer.parseInt(((EditText) root.findViewById(R.id.video_quality_custom_max_key_frame_interval)).getText().toString());*//**//*
            }
*//*
            // size
         *//*   encodingConfig.mIsVideoSizePreset = ((RadioButton) root.findViewById(R.id.video_size_preset)).isChecked();
            if (encodingConfig.mIsVideoSizePreset) {
                Spinner presetSpinner = (Spinner) root.findViewById(R.id.video_size_presets);
                encodingConfig.mVideoSizePreset = presetSpinner.getSelectedItemPosition();
            } else {
                encodingConfig.mVideoSizeCustomWidth = Integer.parseInt(((EditText) root.findViewById(R.id.video_size_custom_width)).getText().toString());
                encodingConfig.mVideoSizeCustomHeight = Integer.parseInt(((EditText) root.findViewById(R.id.video_size_custom_height)).getText().toString());
            }

            // misc
            encodingConfig.mVideoOrientationPortrait = ((RadioButton) root.findViewById(R.id.orientation_portrait)).isChecked();
            encodingConfig.mVideoRateControlQuality = ((RadioButton) root.findViewById(R.id.rate_control_quality)).isChecked();
            boolean isAdaptiveBitrate = ((RadioButton) root.findViewById(R.id.bitrate_auto)).isChecked();
            boolean isManualBitrate = ((RadioButton) root.findViewById(R.id.bitrate_manual)).isChecked();
            encodingConfig.mBitrateAdjustMode = isAdaptiveBitrate ? StreamingProfile.BitrateAdjustMode.Auto :
                    (isManualBitrate ? StreamingProfile.BitrateAdjustMode.Manual : StreamingProfile.BitrateAdjustMode.Disable);
            if (isAdaptiveBitrate) {
                encodingConfig.mAdaptiveBitrateMin = Integer.parseInt(((EditText) root.findViewById(R.id.auto_bitrate_min)).getText().toString());
                encodingConfig.mAdaptiveBitrateMax = Integer.parseInt(((EditText) root.findViewById(R.id.auto_bitrate_max)).getText().toString());
            }
*//*
         *//*   encodingConfig.mVideoFPSControl = ((CheckBox) root.findViewById(R.id.fps_control)).isChecked();

            // YUV filter mode
            Spinner yuvFilterModeSpinner = (Spinner) root.findViewById(R.id.yuv_filter_mode_set);
            encodingConfig.mYuvFilterMode  = StreamingProfile.YuvFilterMode.values()[yuvFilterModeSpinner.getSelectedItemPosition()];*//*

            // watermark
      //      CheckBox cbWatermarkContro = (CheckBox) getView().findViewById(R.id.watermark_control);
            encodingConfig.mIsWatermarkEnabled = false;
        //    LinearLayout layoutWatermarkPanel = (LinearLayout) getView().findViewById(R.id.watermark_panel);
         //   layoutWatermarkPanel.setVisibility(encodingConfig.mIsWatermarkEnabled? View.VISIBLE : View.GONE);
          *//*  if (getView().findViewById(R.id.watermark_panel).getVisibility() == View.VISIBLE) {*//*
              *//*  encodingConfig.mWatermarkAlpha = Integer.parseInt(((EditText) root.findViewById(R.id.watermark_alpha)).getText().toString());
                Spinner sizeSpinner = (Spinner) root.findViewById(R.id.watermark_size_presets);
                encodingConfig.mWatermarkSize =mEncodingConfigFragment. WATERMARK_SIZE_PRESETS_MAPPING[sizeSpinner.getSelectedItemPosition()];
                encodingConfig.mIsWatermarkLocationPreset = ((RadioButton) root.findViewById(R.id.watermark_location_preset)).isChecked();
                if (encodingConfig.mIsWatermarkLocationPreset) {
                    Spinner presetSpinner = (Spinner) root.findViewById(R.id.watermark_location_presets);
                    encodingConfig.mWatermarkLocationPreset = mEncodingConfigFragment.WATERMARK_LOCATION_PRESETS_MAPPING[presetSpinner.getSelectedItemPosition()];
                } else {
                    encodingConfig.mWatermarkLocationCustomX = Float.parseFloat(((EditText) root.findViewById(R.id.watermark_location_custom_x)).getText().toString());
                    encodingConfig.mWatermarkLocationCustomY = Float.parseFloat(((EditText) root.findViewById(R.id.watermark_location_custom_y)).getText().toString());
                }*//*
         *//*   }*//*

            // picture streaming
           *//* encodingConfig.mIsPictureStreamingEnabled = ((CheckBox) getView().findViewById(R.id.pic_streaming_control)).isChecked();
            if (mPictureFilePath != null) {
                encodingConfig.mPictureStreamingFilePath = mPictureFilePath;
            }*//*
        }
        // set audio
     *//*   encodingConfig.mIsAudioQualityPreset = ((RadioButton) root.findViewById(R.id.audio_quality_preset)).isChecked();
        if (encodingConfig.mIsAudioQualityPreset) {
            Spinner presetSpinner = (Spinner) root.findViewById(R.id.audio_quality_presets);
            encodingConfig.mAudioQualityPreset = mEncodingConfigFragment.AUDIO_QUALITY_PRESETS_MAPPING[presetSpinner.getSelectedItemPosition()];
        } else {
            encodingConfig.mAudioQualityCustomSampleRate = Integer.parseInt(((EditText) root.findViewById(R.id.audio_quality_custom_sample_rate)).getText().toString());
            encodingConfig.mAudioQualityCustomBitrate = Integer.parseInt(((EditText) root.findViewById(R.id.audio_quality_custom_bitrate)).getText().toString());
        }*//*

        return encodingConfig;
    }*/
    @Override
    public void getMuseEpurseSuccess(RespCheckBalance data) {

    }

    @Override
    public void getUnreadMessageSuccess(RespGetUnreadMessage data) {
        messageNumber = data.getResult().getNum();

    }

    @Override
    public void giftPaihanghSuccess(RespGiftPaihangh data) {

    }

    @Override
    public void saveLatLngSuccess(TempResponse data) {

    }

    public static class ToolbarStatus {

        public static final int FRAGE_NORMAL = 1;
        public static final int FRAGE_NORMAL_NEARBY = 3;
        public static final int FRAGE_NORMAL_SETTING = 4;
        public static final int FRAGE_HOME_SREARCH = 5;//首页直播

    }

    /**
     * 设置消息数量
     *
     * @param messNum
     */
    public void setMessageNum(String messNum) {
        int num = TempDataUtils.string2Int(messNum);
        if (num == 0) {
            toolbar_mess_num.setVisibility(View.GONE);
        } else {
            toolbar_mess_num.setVisibility(View.VISIBLE);
            toolbar_mess_num.setText(messNum);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Mars) {
            //  initTab();
            //   index = 0;
           /* setToolbar(ToolbarStatus.FRAGE_HOME_SREARCH);
            act_home_bar_1_iv.setImageResource(R.mipmap.rounded_1);
            act_home_bar_1_tv.setTextColor(Color.parseColor("#f70052"));
            if (currentTabIndex != index) {
                FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                trx.hide(fragments[currentTabIndex]);
                if (!fragments[index].isAdded()) {
                    trx.add(R.id.act_home_body, fragments[index]);
                }
                trx.show(fragments[index]).commit();
            }
            if (mTabs != null) {
                mTabs[currentTabIndex].setSelected(false);
                mTabs[index].setSelected(true);
            }
            currentTabIndex = index;
        }*/
        }
    }
/*  @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int mShowPosition = intent.getIntExtra("souye",0);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        intUsertoke();
        if (mPreI != null) {
            mPreI.getUnreadMessage(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd());
        }
    }


    //直播获取融云
    private void intUsertoke() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .getMuseToken(TempLoginConfig.sf_getSueid()),
                new TempRemoteApiFactory.OnCallBack<RespGetMuseToken>() {
                    @Override
                    public void onSucceed(RespGetMuseToken data) {
                        if (data.getFlag() == 1) {
                            connect(data.getResult().getMuseCommentToken());
                            Log.i("myToken", data.getResult().getMuseCommentToken());
                        } else {

                        }
                    }

                    @Override
                    public void onCompleted() {
                        //  if (mViewActLoginI!=null) mViewActLoginI.dismissPro();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  if (mViewActLoginI!=null) mViewActLoginI.dismissPro();
                    }
                });
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(final String token) {

//        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

        /**
         * IMKit SDK调用第二步,建立与服务器的连接
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {

                Debug.error("ActHome", "-- 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 TokenonTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {
                Debug.error("ActHome", "--连接融云成功onSuccess--" + userid);
                Debug.error("ActHome", "--连接融云成功token--" + token);
//                    editor.putString("loginToken", token);
//                    editor.commit();
                SealUserInfoManager.getInstance().openDB();
//                Toast.makeText(ActHome.this, "用户连接融云成功", Toast.LENGTH_SHORT).show();
                Log.i("RongYun", "用户连接融云成功");
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Debug.error("ActHome", "--连接融云失败onError" + errorCode);
            }
        });
//        }
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
        wakeUpAdapter = null;
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    //  sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
                //       sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                TempLoginConfig.sf_saveLocation_lat(location.getLatitude()+"");
                TempLoginConfig.sf_saveLocation_long(location.getLongitude() + "");
                TempLoginConfig.sf_saveLocation_lat_lng(new String[]{location.getLatitude() + "", location.getLongitude() + ""});
                //  tvResult.setText(result);
            } else {
                //    tvResult.setText("定位失败，loc is null");
            }
            toolbar_search_address_tv.setText(location.getDistrict() + "");


//        String lat = "28.465457";
//        String lng = "106.476440";
            mPreII.saveLatLng(location.getLongitude() + "", location.getLatitude() + "");
            mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), location.getLongitude() + "", location.getLatitude() + "");
            Log.i("经纬度2", location.getLongitude() + "" + location.getLatitude() + "");

            TempLoginConfig.sf_saveLocation_lat_lngadress(location.getDistrict());




            if (!TextUtils.isEmpty(location.getDistrict())) {
                stopLocation();
            }


        }
    };

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;// 更新firstTime
                    return true;
                } else { // 两次按键小于2秒时，退出应用
                    //   DBLoginConfig.destory();
                    TempApplication.getInstance().onTerminate();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        switch (requestCode) {
            case PERMISSION_CODE: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(ActHome.this, permissions[i])) {
                            PermissionUtil.requestForeverDenyDialog(ActHome.this, permissions);
                        } else {
                            new AlertDialog.Builder(ActHome.this)
                                    .setTitle("权限管理")
                                    .setMessage("定位权限")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri packageURI = Uri.parse("package:" + getAppProcessName(ActHome.this));
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                            startActivity(intent);
                                            // ActivityCompat.requestPermissions(getActivity(), PERMISSION_STARTSPOT, REQUEST_STARTSPOT);
                                        }
                                    })
                                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).setCancelable(false)
                                    .show();

                            //用户拒绝权限请求，但未选中“不再提示”选项
                        }
                        if(mRequestPermissionCallBack!=null){
                            mRequestPermissionCallBack.denied();
                        }
                        hasAllGranted = false;
                        break;
                    }
                }
                //判断grantResults.length==0 则忽略，不是requestPermisssion而来，onRestart可能被调用多次
                if (grantResults.length > 0 && hasAllGranted) {
                    if (mRequestPermissionCallBack!=null){
                        mRequestPermissionCallBack.granted();
                    }

                }
            }

        }

    }
    public  String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
    /**
     * 发起权限请求
     *
     * @param context
     * @param permissions
     * @param callback
     */
    public static final int PERMISSION_CODE = 10001;
    private PermissionUtil.RequestPermissionCallBack mRequestPermissionCallBack;
    public void requestPermission(final Context context,
                                  PermissionUtil.RequestPermissionCallBack callback,final String... permissions) {
        this.mRequestPermissionCallBack = callback;

        //如果所有权限都已授权，则直接返回授权成功,只要有一项未授权，则发起权限请求
        boolean isAllGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(((Activity) context), permissions, PERMISSION_CODE);
                //   ActivityCompat.requestPermissions(getActivity(), PERMISSION_STARTSPOT, REQUEST_STARTSPOT);
                isAllGranted = false;
                //  mRequestPermissionCallBack.denied();
                break;
            }
        }
        if (isAllGranted) {
            mRequestPermissionCallBack.granted();
        }
    }


    public void requestPermission(String... permissions) {

        requestPermission(ActHome.this, new PermissionUtil.RequestPermissionCallBack() {

            @Override
            public void granted() {
                if(TextUtils.isEmpty(type)){
                    locationClient.startLocation();
                }
                else{
                    myPreI.getAchorApplyStatus();
                }
              /*  Intent intent = new Intent(ActHome.this, PaymentScanActivity.class);
                intent.putExtra(PaymentScanActivity.STYLE, PaymentScanActivity.STYLE_TEXT);
                startActivity(intent);*/
                //Toast.makeText(getActivity(), "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
            }

            @Override
            public void denied() {
              /*  new AlertDialog.Builder(getActivity())
                        .setTitle("Permission to apply")
                        .setMessage("Permission of camera: to scan the QR code (required)")
                        .setPositiveButton(R.string.imtrue, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri packageURI = Uri.parse("package:" + getAppProcessName(getActivity()));
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                startActivity(intent);
                                // ActivityCompat.requestPermissions(getActivity(), PERMISSION_STARTSPOT, REQUEST_STARTSPOT);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setCancelable(false)
                        .show();*/
             /*   Uri packageURI = Uri.parse("package:" + getAppProcessName(getActivity()));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                startActivity(intent);*/
                // Toast.makeText(getActivity(), "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        }, permissions);

    }
}
