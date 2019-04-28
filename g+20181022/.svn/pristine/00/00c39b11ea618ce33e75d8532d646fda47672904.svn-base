package com.code.clkj.menggong.activity.comCameraStreaming;

import android.Manifest;
import android.content.Intent;
import android.hardware.Camera;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityI;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityImpl;
import com.code.clkj.menggong.activity.comLive.ViewActLiveActivityI;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity1;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.LiveZhiBoListAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.response.RoomComment;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.Config;
import com.code.clkj.menggong.util.TempDataUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.comApp.AppManager;
import com.lf.tempcore.recyclerview.LRecyclerView;
import com.lf.tempcore.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.SurfaceTextureCallback;
import com.qiniu.pili.droid.streaming.WatermarkSetting;
import com.rey.material.app.BottomSheetDialog;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_HIGH1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_HIGH2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_LOW1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_LOW2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_MEDIUM1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_MEDIUM2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH3;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW3;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM3;

/**
 * 直播页面
 */
public class ExtCapStreamingActivity extends JFYActivity implements
        StreamStatusCallback,
        StreamingSessionListener,
        StreamingStateChangedListener, EasyPermissions.PermissionCallbacks, ViewActLiveActivityI,SurfaceTextureCallback {


    private SimpleDraweeView act_live_head_img;//直播房间
    private SimpleDraweeView act_live_head_img_nickname;//直播头像
    private TextView act_live_nickname;//直播昵称
    private TextView timer_tv;//直播时间
    private ImageView red_envelopes_img;//红包
    private ImageView send_message_img;//发消息
    private TextView people_num;//在线人数
    private RecyclerView phb_rec;//显示录坞排行榜


    private String time = "";
    private String roomId = "";//房间ID
    private LinearLayout mext_ly;

    protected EncodingConfig mEncodingConfig ;
    public static final String INPUT_TEXT = "INPUT_TEXT";
    private static final int MSG_START_STREAMING = 0;
    private static final int MSG_STOP_STREAMING = 1;

    private ImageView mCameraChange;
//    private SurfaceView mSurfaceView;

    private boolean mIsEncodingMirror = false;

    private boolean mShutterButtonPressed = false;
//private boolean mShutterButtonPressed = true;

    private String mStatusMsgContent;
    private MediaStreamingManager mMediaStreamingManager;
    private String mLogContent = "\n";

  //  private ExtAudioCapture mExtAudioCapture;
   // private ExtVideoCapture mExtVideoCapture;

    private StreamingManager mStreamingManager;
    private StreamingProfile mProfile = new StreamingProfile();

    private JSONObject mJSONObject;
    private boolean mOrientationChanged = false;

    private Switcher mSwitcher = new Switcher();

    private ImageView camera_switch_editex;

//    private TextView streamingStatus_room_number;
//    private  LinearLayout   mext_ly ;

    private boolean mIsReady = false;

    //    private final String TAG = "dddd";
    private PreActLiveActivityI mPreI;
    private TextView mStreamStatus;
    private TextView mLogTextView;
    private TextView mStatusTextView;
    private Button mShutterButton;//开始

    private BottomSheetDialog pop_red, pop_send_message;
    private String mpricesedit;//红包金额
    private String mnumedit;//红包数量
    private String minputText;//消息内容


    private LRecyclerView mRecyclerView;
    protected ListBaseAdapter<RoomComment.ResultEntity> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;

    private int state_red = 0;
    private int state_message = 0;
    private ListBaseAdapter<RespGiftPaihangh.ResultBean> RankingListAdapter;
    CameraPreviewFrameView       cameraPreviewFrameView;

    /**
     * 每一页展示多少条数据
     */
    protected int mCurrentPage = 0;
  /*  @Bind(R.id.recycler_view)
    protected LRecyclerView mRecyclerView;*/

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_START_STREAMING:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            setShutterButtonEnabled(false);
                            boolean res = mStreamingManager.startStreaming();
                            mShutterButtonPressed = true;
                            if (!res) {
                                mShutterButtonPressed = false;
                                setShutterButtonEnabled(true);
                            }
                            setShutterButtonPressed(mShutterButtonPressed);
                        }
                    }).start();
                    break;
                case MSG_STOP_STREAMING:
                    if (mShutterButtonPressed) {
                        // disable the shutter button before stopStreaming
                        setShutterButtonEnabled(false);
                        boolean res = mStreamingManager.stopStreaming();
                        if (!res) {
                            mShutterButtonPressed = true;
                            setShutterButtonEnabled(true);
                        }
                        setShutterButtonPressed(mShutterButtonPressed);
                    }
                    break;
                default:
                    Log.e(TAG, "Invalid message");
                    break;
            }
        }
    };

    /* @Override
     public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
         // 检测屏幕的方向：纵向或横向
         if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
             //设置全屏
             getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);
             //当前为横屏， 在此处添加额外的处理代码
             setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
             getWindow().getDecorView().setSystemUiVisibility(View.GONE);//消除状态栏
             System.out.println("-----------------onConfigurationChanged--------->:1");
             DisplayMetrics dm = new DisplayMetrics();
             getWindowManager().getDefaultDisplay().getMetrics(dm);
         } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
             //当前为竖屏， 在此处添加额外的处理代码
             setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
             System.out.println("-----------------onConfigurationChanged--------->:2");
             getWindow().clearFlags(
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);
         }
         //检测实体键盘的状态：推出或者合上
         if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
             //实体键盘处于推出状态，在此处添加额外的处理代码
         } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
             //实体键盘处于合上状态，在此处添加额外的处理代码
         }
         //摄像头的旋转
         frontCameraRotate();

     }*/
    private long liveTime;
    private boolean mIsNeedFB = true;
    /**
     * 开始计时功能
     */
    private void startTimer() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.add(Calendar.HOUR_OF_DAY, -8);
        Date time = calendar.getTime();
        liveTime = time.getTime();
        handlerTime.post(timerRunnable);
    }
    private void initEncodingProfile() {
        mEncodingConfig = (EncodingConfig) getIntent().getSerializableExtra("EncodingConfig");
     //   mEncodingConfig=new EncodingConfig();
        StreamingProfile.AudioProfile aProfile = null;
        StreamingProfile.VideoProfile vProfile = null;

        if (!mEncodingConfig.mIsAudioOnly) {
            // video quality
            if (mEncodingConfig.mIsVideoQualityPreset) {
                mProfile.setVideoQuality(mEncodingConfig.mVideoQualityPreset);
            } else {
                vProfile = new StreamingProfile.VideoProfile(
                        mEncodingConfig.mVideoQualityCustomFPS,
                        mEncodingConfig.mVideoQualityCustomBitrate * 1024,
                        mEncodingConfig.mVideoQualityCustomMaxKeyFrameInterval,
                        StreamingProfile.H264Profile.HIGH
                );
            }

            // video size
            if (mEncodingConfig.mIsVideoSizePreset) {
                mProfile.setEncodingSizeLevel(mEncodingConfig.mVideoSizePreset);
            } else {
                mProfile.setPreferredVideoEncodingSize(mEncodingConfig.mVideoSizeCustomWidth, mEncodingConfig.mVideoSizeCustomHeight);
            }

            // video misc
            mProfile.setEncodingOrientation(mEncodingConfig.mVideoOrientationPortrait ? StreamingProfile.ENCODING_ORIENTATION.PORT : StreamingProfile.ENCODING_ORIENTATION.LAND);
            mProfile.setEncoderRCMode(mEncodingConfig.mVideoRateControlQuality ? StreamingProfile.EncoderRCModes.QUALITY_PRIORITY : StreamingProfile.EncoderRCModes.BITRATE_PRIORITY);
            mProfile.setBitrateAdjustMode(mEncodingConfig.mBitrateAdjustMode);
            mProfile.setFpsControllerEnable(mEncodingConfig.mVideoFPSControl);
            mProfile.setYuvFilterMode(mEncodingConfig.mYuvFilterMode);
            if (mEncodingConfig.mBitrateAdjustMode == StreamingProfile.BitrateAdjustMode.Auto) {
                mProfile.setVideoAdaptiveBitrateRange(mEncodingConfig.mAdaptiveBitrateMin * 1024, mEncodingConfig.mAdaptiveBitrateMax * 1024);
            }
        }

        // audio quality
        if (mEncodingConfig.mIsAudioQualityPreset) {
            mProfile.setAudioQuality(mEncodingConfig.mAudioQualityPreset);
        } else {
            aProfile = new StreamingProfile.AudioProfile(
                    mEncodingConfig.mAudioQualityCustomSampleRate,
                    mEncodingConfig.mAudioQualityCustomBitrate * 1024
            );
        }

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
     * 循环执行线程
     */
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            handlerTime.postDelayed(timerRunnable, 1000);
            long sysTime = System.currentTimeMillis();
            liveTime += 1000;
            CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", liveTime);
            CharSequence sysDateStr = DateFormat.format("yyyy/MM/dd", sysTime);
            time = sysTimeStr.toString();
            //  tvtime.setText(sysTimeStr);
//            streamingStatus_room_data.setText(sysDateStr);
            timer_tv.setText(time);

            if (TempDataUtils.string2Int(time)%10 ==0){
                mPreI.giftPaihangh(roomId);
            }
        }
    };
    private Handler handlerTime = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
            }
        }
    };
    //   protected MediaStreamingManager mMediaStreamingManager;

    /**
     * 旋转前置摄像头为正的
     */
//    private void frontCameraRotate() {
//
//        int degrees = mExtVideoCapture.getDeviceRotationDegree(ExtCapStreamingActivity.this);
//        Camera.CameraInfo camInfo = new Camera.CameraInfo();
//        Camera.getCameraInfo(mExtVideoCapture.mCurrentFacingId, camInfo);
//        int result;
//        if (mExtVideoCapture.mCurrentFacingId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//            result = (camInfo.orientation + degrees) % 360;
//            result = (360 - result) % 360; // compensate the mirror
//        } else { // back-facing
//            result = (camInfo.orientation - degrees + 360) % 360;
//        }
//        //设置摄像头的旋转
//        mExtVideoCapture. mCamera.setDisplayOrientation(result);
//
//    /*    Camera.Parameters params =mExtVideoCapture. mCamera.getParameters();
//        params.setPreviewFormat(ImageFormat.NV21);*/
//
//    }
    private CameraStreamingSetting mCameraStreamingSetting;
    private CameraConfig mCameraConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //    setRequestedOrientation(Config.SCREEN_ORIENTATION_LANDSCAPE);
     /*
        setRequestedOrientation(Config.SCREEN_ORIENTATION_LANDSCAPE);*/
//        setContentView(R.layout.activity_ext_camera_streaming);
        setContentView(R.layout.act_live_layout);

        AppManager.getAppManager().addActivity(this);
        initEncodingProfile();
    //    requestCodeQRCodePermissions();
        mCameraStreamingSetting = buildCameraStreamingSetting();
        mEncodingConfig.mCodecType =  AVCodecType.SW_AUDIO_CODEC;

        mIsNeedFB = mCameraConfig.mIsFaceBeautyEnabled;
      //  mIsPreviewMirror = mCameraConfig.mPreviewMirror;
        mIsEncodingMirror = mCameraConfig.mEncodingMirror;


               cameraPreviewFrameView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);
     //   cameraPreviewFrameView.setRenderer(new MyGLRenderer());
        mMediaStreamingManager = new MediaStreamingManager(this, cameraPreviewFrameView, mEncodingConfig.mCodecType);

        mCameraChange = (ImageView) findViewById(R.id.live_camera_change);

     //   mSurfaceView = (CameraPreviewFrameView) findViewById(R.id.ext_camerapreview_surfaceview);

        camera_switch_editex = (ImageView) findViewById(R.id.camera_switch_editex);
        mext_ly = (LinearLayout) findViewById(R.id.extcapstreaming_ly);

        act_live_head_img = (SimpleDraweeView) findViewById(R.id.act_live_head_img);
        act_live_head_img_nickname = (SimpleDraweeView) findViewById(R.id.act_live_head_img_nickname);
        act_live_nickname = (TextView) findViewById(R.id.act_live_nickname);
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        red_envelopes_img = (ImageView) findViewById(R.id.red_envelopes_img);
        send_message_img = (ImageView) findViewById(R.id.send_message_img);
        people_num = (TextView) findViewById(R.id.people_num);
        phb_rec = (RecyclerView) findViewById(R.id.phb_rec);

        mPreI = new PreActLiveActivityImpl(this);
        String lat = "29.465457";
        String lng = "106.476440";
        mCurrentCamFacingIndex = mCameraConfig.mFrontFacing ? 1 : 0;
        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng());
        Log.i("直播经纬度", TempLoginConfig.sf_getlag() + "...." + TempLoginConfig.sf_getlng());
//        传入地址
        String publishUrlFromServer = getIntent().getStringExtra("URL");

        Log.i(TAG, "publishUrlFromServer:1" + publishUrlFromServer);
//        mext_ly.getBackground().setAlpha(100);
        StreamingProfile.AudioProfile aProfile = new StreamingProfile.AudioProfile(44100, 96 * 1024);
        StreamingProfile.VideoProfile vProfile = new StreamingProfile.VideoProfile(30, 1000 * 1024, 48);
        StreamingProfile.AVProfile avProfile = new StreamingProfile.AVProfile(vProfile, aProfile);
//        tijiao();

        try {
            if (publishUrlFromServer != null) {
                mProfile.setPublishUrl(publishUrlFromServer);
                Log.i(TAG, "publishUrlFromServer:2" + publishUrlFromServer);
            }
//                mProfile.setPublishUrl(publishUrlFromServer.substring(Config.EXTRA_PUBLISH_URL_PREFIX.length()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


             if (mCameraConfig.mIsCustomFaceBeauty) {
            mMediaStreamingManager.setSurfaceTextureCallback(this);
        }
       /* if (publishUrlFromServer.startsWith(Config.EXTRA_PUBLISH_URL_PREFIX)) {
            // publish url

//        }
//        else
//        if (publishUrlFromServer.startsWith(Config.EXTRA_PUBLISH_JSON_PREFIX)) {
            try {
                mJSONObject = new JSONObject(publishUrlFromServer.substring(Config.EXTRA_PUBLISH_JSON_PREFIX.length()));
//               mJSONObject = new JSONObject(publishUrlFromServer);
                StreamingProfile.Stream stream = new StreamingProfile.Stream(mJSONObject);
                mProfile.setStream(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
//        } else {
//            Toast.makeText(this, "Invalid Publish Url", Toast.LENGTH_LONG).show();
//        }

      /*  WatermarkSetting watermarksetting = new WatermarkSetting(this);
        watermarksetting.setResourceId(R.drawable.qiniu_logo)
                .setAlpha(100)
                .setSize(WatermarkSetting.WATERMARK_SIZE.MEDIUM)
                .setCustomPosition(0.5f, 0.5f);*/
        //   , afl, cameraPreviewFrameView,
   /*     mMediaStreamingManager = new MediaStreamingManager(this,
                AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC); // sw codec

//        mMediaStreamingManager.prepare(mCameraStreamingSetting, mMicrophoneStreamingSetting, watermarksetting, mProfile, new PreviewAppearance(0.0f, 0.0f, 0.5f, 0.5f, PreviewAppearance.ScaleType.FIT));
        mMediaStreamingManager.prepare(mCameraStreamingSetting, mMicrophoneStreamingSetting, watermarksetting, mProfile);
*/


        mIsNeedFB=true;
        mMediaStreamingManager.setVideoFilterType(mIsNeedFB ?
                CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY
                : CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);

        mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH3)
                .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
                .setPreferredVideoEncodingSize(960, 544)
                .setEncodingSizeLevel(Config.ENCODING_LEVEL)
                .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY)
                .setAVProfile(avProfile)
                .setDnsManager(getMyDnsManager())
                .setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3))
//                .setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.PORT)
                .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));

        mStreamingManager = new StreamingManager(this, AVCodecType.HW_VIDEO_YUV_AS_INPUT_WITH_HW_AUDIO_CODEC); // sw codec

        mStreamingManager.prepare(mProfile);


        mStreamingManager.setStreamingSessionListener(this);
//        mMediaStreamingManager.setNativeLoggingEnabled(false);
        mStreamingManager.setStreamStatusCallback(this);
        mStreamingManager.setStreamingStateListener(this);
        // update the StreamingProfile
//        mProfile.setStream(new Stream(mJSONObject1));
//        mMediaStreamingManager.setStreamingProfile(mProfile);

    /*  mExtVideoCapture = new ExtVideoCapture(cameraPreviewFrameView);
        mExtVideoCapture.setOnPreviewFrameCallback(mOnPreviewFrameCallback);

        mExtAudioCapture = new ExtAudioCapture();*/

        initUIs();

        CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
        fbSetting.beautyLevel = 100 / 100.0f;
        fbSetting.whiten = 100 / 100.0f;
        fbSetting.redden = 100 / 100.0f;

        mMediaStreamingManager.updateFaceBeautySetting(fbSetting);
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  mExtAudioCapture.startCapture();
        mExtAudioCapture.setOnAudioFrameCapturedListener(mOnAudioFrameCapturedListener);*/
        mStreamingManager.resume();
//        mMediaStreamingManager.resume();
        //      ScreenRotateUtil.getInstance(this).start(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsReady = false;
        mShutterButtonPressed = false;
        mHandler.removeCallbacksAndMessages(null);
     //   mExtAudioCapture.stopCapture();
        mStreamingManager.pause();
        mMediaStreamingManager.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStreamingManager.destroy();
        mMediaStreamingManager.destroy();
        //     ScreenRotateUtil.getInstance(this).stop();
        AppManager.getAppManager().finishActivityd(this);
        closeWebsocket();
    }

    protected void setShutterButtonEnabled(final boolean enable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShutterButton.setFocusable(enable);
                mShutterButton.setClickable(enable);
                mShutterButton.setEnabled(enable);

            }
        });
    }

    protected void setShutterButtonPressed(final boolean pressed) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShutterButtonPressed = pressed;
                mShutterButton.setPressed(pressed);
            }
        });
    }

    protected void startStreaming() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_START_STREAMING), 50);
    }

    protected void stopStreaming() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_STOP_STREAMING), 50);
    }

    @Override
    public boolean onRecordAudioFailedHandled(int err) {
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int err) {
        Log.i(TAG, "onRestartStreamingHandled");
        return false;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        return null;
    }

    @Override
    public int onPreviewFpsSelected(List<int[]> list) {
        return 0;
    }

    @Override
    public void notifyStreamStatusChanged(final StreamingProfile.StreamStatus streamStatus) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStreamStatus.setText("bitrate:" + streamStatus.totalAVBitrate / 1024 + " kbps"
                        + "\naudio:" + streamStatus.audioFps + " fps"
                        + "\nvideo:" + streamStatus.videoFps + " fps");
            }
        });
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        switch (streamingState) {
            case PREPARING:
                mStatusMsgContent = getString(R.string.string_state_preparing);
                break;
            case READY:
                mIsReady = true;
                mStatusMsgContent = getString(R.string.string_state_ready);
                // start streaming when READY
                startStreaming();
                break;
            case CONNECTING:
                mStatusMsgContent = getString(R.string.string_state_connecting);
                break;
            case STREAMING:
                mStatusMsgContent = getString(R.string.string_state_streaming);
                setShutterButtonEnabled(true);
                setShutterButtonPressed(true);
                break;
            case SHUTDOWN:
                mStatusMsgContent = getString(R.string.string_state_ready);
                setShutterButtonEnabled(true);
                setShutterButtonPressed(false);
                if (mOrientationChanged) {
                    mOrientationChanged = false;
                    startStreaming();
                }
                break;
            case IOERROR:
                //  mLogContent += "\n";
                mLogContent += "IOERROR\n";
                mStatusMsgContent = getString(R.string.string_state_ready);
                setShutterButtonEnabled(true);
                break;
            case UNKNOWN:
                mStatusMsgContent = getString(R.string.string_state_ready);
                break;
            case SENDING_BUFFER_EMPTY:
                break;
            case SENDING_BUFFER_FULL:
                break;
            case DISCONNECTED:
                mLogContent += "DISCONNECTED\n";
                break;
            case INVALID_STREAMING_URL:
                Log.e(TAG, "Invalid streaming url:" + extra);
                break;
            case UNAUTHORIZED_STREAMING_URL:
                Log.e(TAG, "Unauthorized streaming url:" + extra);
                mLogContent += "Unauthorized Url\n";
                break;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLogTextView != null) {
                    mLogTextView.setText(mLogContent);
                }
                mStatusTextView.setText(mStatusMsgContent);
            }
        });
    }

    //   private GiftMsgAdapter adapter;
    private void initUIs() {
        webSocketConnect();
        mShutterButton = (Button) findViewById(R.id.toggleRecording_button);
        mStatusTextView = (TextView) findViewById(R.id.streamingStatus);
        Button encodingMirrorBtn = (Button) findViewById(R.id.encoding_mirror_btn);

        mLogTextView = (TextView) findViewById(R.id.log_info);
        mStreamStatus = (TextView) findViewById(R.id.stream_status);
        mRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view);


//
//        streamingStatus_room_number = (TextView) findViewById(R.id.streamingStatus_room_number);
//        streamingStatus_room_number.setText(roomid);
//        streamingStatus_room_data = (TextView) findViewById(streamingStatus_room_data);
        mShutterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShutterButtonPressed) {
                    stopStreaming();
                } else {
                    startStreaming();
                }
            }
        });

        encodingMirrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsEncodingMirror = !mIsEncodingMirror;
                Toast.makeText(ExtCapStreamingActivity.this, "镜像成功", Toast.LENGTH_SHORT).show();
            }
        });

        mCameraChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mSwitcher);
                mHandler.postDelayed(mSwitcher, 100);
            }
        });
        camera_switch_editex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mShutterButtonPressed) {
                    // disable the shutter button before stopStreaming
                    setShutterButtonEnabled(false);
                    boolean res = mStreamingManager.stopStreaming();
                    if (!res) {
//                        mShutterButtonPressed = true;
                        mShutterButtonPressed = false;
//                        setShutterButtonEnabled(true);
                        setShutterButtonEnabled(false);
                    }
                    setShutterButtonPressed(mShutterButtonPressed);
                }
                finish();
            }
        });
        //红包点击事件
        red_envelopes_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRed();
            }
        });

        //发消息事件
        send_message_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
        startTimer();
        //禁止下拉刷新
        mRecyclerView.setPullRefreshEnabled(false);
        if (mListAdapter != null) {
            //  mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        } else {
            mListAdapter = getListAdapter();
            if (requestDataIfViewCreated()) {
                //  mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
//                TLog.log("requestDataIfViewCreated  requestData");
//                requestData();
            } else {
                //  mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }

        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewAdapter = new LRecyclerViewAdapter(mListAdapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.mWrapAdapter.removeFooterView();
    }

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    private static DnsManager getMyDnsManager() {
        IResolver r0 = new DnspodFree();
        IResolver r1 = AndroidDnsServer.defaultResolver();
        IResolver r2 = null;
        try {
            r2 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(e.message);
    }

    @Override
    public void showPro() {
        showProgressDialog(false);
    }

    @Override
    public void disPro() {
        dismissProgressDialog();
    }

    @Override
    public void queryCanLivingRoomSuccess(RespQueryCanLivingRoom data) {
        if (data != null) {
            if (data.getResult().getRoomImage() != null) {
                act_live_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getRoomImage()));
            }
            if (data.getResult().getMemberUser().getMuseImage() != null) {
                act_live_head_img_nickname.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMemberUser().getMuseImage()));
            }
            if (data.getResult().getMemberUser().getMuseNickName() != null) {
                act_live_nickname.setText(data.getResult().getMemberUser().getMuseNickName());
            }
            roomId = data.getResult().getRoomId();
            people_num.setText(data.getResult().getRoomWatchNum() + "在线观看");
            Log.i("MyLog", data.getResult().getMemberUser().getMuseNickName());
        }
    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void saveConsumOrderSuccess(RespConsumOrder data) {
        //红包的订单生成成功跳支付
        Intent orderIntent = new Intent(this, ActPaymentMethodActivity1.class);
        orderIntent.putExtra("tradeNo", data.getResult().getMcorOrderNumber());
        orderIntent.putExtra("state", "1");
        orderIntent.putExtra("total_price_number", data.getResult().getMcorPrice());
        startActivity(orderIntent);

        pop_red.dismiss();
        state_red=0;
    }

    @Override
    public void getUnreadMessageSuccess(RespGetUnreadMessage data) {

    }

    @Override
    public void giftPaihanghSuccess(RespGiftPaihangh data) {
        phb_rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RankingListAdapter = new ListBaseAdapter<RespGiftPaihangh.ResultBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.item_circular_image;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                RespGiftPaihangh.ResultBean resultbean = getDataList().get(position);
                SimpleDraweeView head_img = holder.getView(R.id.head_img);

                head_img.setImageURI(BaseUriConfig.makeImageUrl(resultbean.getMuseImage()));
            }
        };
        if (data.getResult() != null)
            RankingListAdapter.addAll(data.getResult());
        phb_rec.setAdapter(RankingListAdapter);
    }
    private int mCurrentCamFacingIndex;


    @Override
    public void onSurfaceCreated() {
        Log.i(TAG, "onSurfaceCreated");
        /**
         * only used in custom beauty algorithm case
         */
        mFBO.initialize(this);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        Log.i(TAG, "onSurfaceChanged width:" + width + ",height:" + height);
        /**
         * only used in custom beauty algorithm case
         */
        mFBO.updateSurfaceSize(width, height);
    }

    @Override
    public void onSurfaceDestroyed() {
        Log.i(TAG, "onSurfaceDestroyed");
        /**
         * only used in custom beauty algorithm case
         */
        mFBO.release();
    }

    @Override
    public int onDrawFrame(int texId, int texWidth, int texHeight, float[] transformMatrix) {
        /**
         * When using custom beauty algorithm, you should return a new texId from the SurfaceTexture.
         * newTexId should not equal with texId, Otherwise, there is no filter effect.
         */
        int newTexId = mFBO.drawFrame(texId, texWidth, texHeight);
        return newTexId;
    }


    private class Switcher implements Runnable {
        @Override
        public void run() {

           mCurrentCamFacingIndex = (mCurrentCamFacingIndex + 1) % CameraStreamingSetting.getNumberOfCameras();
            CameraStreamingSetting.CAMERA_FACING_ID facingId;
            if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
            } else if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT.ordinal()) {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
            } else {
                facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
            }
            Log.i(TAG, "switchCamera:" + facingId);
            mMediaStreamingManager.switchCamera(facingId);

            /*if (mExtVideoCapture != null) {
              //  mSurfaceView.setVisibility(View.GONE);
                mExtVideoCapture.switchCamera();
               // mSurfaceView.setVisibility(View.VISIBLE);
            }*/
        }
    }

    private ExtVideoCapture.OnPreviewFrameCallback mOnPreviewFrameCallback = new ExtVideoCapture.OnPreviewFrameCallback() {
        @Override
        public void onPreviewFrameCaptured(byte[] data, int width, int height, int orientation, boolean mirror, int fmt, long tsInNanoTime) {
            mStreamingManager.inputVideoFrame(data, width, height, orientation, mIsEncodingMirror, fmt, tsInNanoTime);
        }
    };

    private ExtAudioCapture.OnAudioFrameCapturedListener mOnAudioFrameCapturedListener = new ExtAudioCapture.OnAudioFrameCapturedListener() {
        @Override
        public void onAudioFrameCaptured(byte[] audioData) {
            long timestamp = System.nanoTime();
            mStreamingManager.inputAudioFrame(audioData, timestamp, false);
        }
    };

    private static WebSocketConnection webSocketConnection;
    private static WebSocketOptions options = new WebSocketOptions();
    // private static boolean isExitApp = false;
    private static String websocketHost = BaseUriConfig.BASE_URLWEB; //websocket服务端的url,,,ws是协议,和http一样,我写的时候是用的我们公司的服务器所以这里不能贴出来

    private final String TAG = ExtCapStreamingActivity.class.getSimpleName();
    private static boolean isClosed = true;


    public void webSocketConnect() {
        webSocketConnection = new WebSocketConnection();
        try {
            webSocketConnection.connect(websocketHost, new WebSocketHandler() {
                //websocket启动时候的回调
                @Override
                public void onOpen() {
                    Log.d("开始连接", "open");
                    isClosed = false;
                }

                //websocket接收到消息后的回调
                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "payload = " + payload);
                    RoomComment.ResultEntity rest = new RoomComment.ResultEntity();
                    try {
                        JSONObject jsonObject = new JSONObject(payload);
                        rest.setMuseNickName(jsonObject.getString("nickname"));
                        rest.setRcomId(jsonObject.getString("roomId"));
                        rest.setRcomContent(jsonObject.getString("comment"));
                        mData.add(rest);
                        executeOnLoadDataSuccess(mData);
                        // onRefreshView();
                        //  recyclerView.scrollToPosition(Datas.size()+1);
                        //  adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                  /*  holder.tvGiftMsg.setText(item.getMuseNickName()+"");
                    holder.live_comment_context.setText(item.getRcomContent()+"");*/
                    //  onRefreshView();
                    // mPrestener.sendDanmu(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getOnLineKey(),TempLoginConfig.sf_getPassWord(),inputText,roomId);
                }

                //websocket关闭时候的回调
                @Override
                public void onClose(int code, String reason) {
                    isClosed = true;
                    Log.d(TAG, "code = " + code + " reason = " + reason);
                    switch (code) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3://手动断开连接
//                            if (!isExitApp) {
//                                webSocketConnect();
//                            }
                            break;
                        case 4:
                            break;
                        /**
                         * 由于我在这里已经对网络进行了判断,所以相关操作就不在这里做了
                         */
                        case 5://网络断开连接
//                            closeWebsocket(false);
//                            webSocketConnect();
                            break;
                    }
                }
            }, options);
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String s) {
        Log.d(TAG, "sendMsg = " + s);
        if (!TextUtils.isEmpty(s))
            if (webSocketConnection != null) {
                webSocketConnection.sendTextMessage(s);
            }
    }

    public void closeWebsocket() {
        //  isExitApp = exitApp;
        if (webSocketConnection != null && webSocketConnection.isConnected()) {
            webSocketConnection.disconnect();
            webSocketConnection = null;
        }
    }

    List<RoomComment.ResultEntity> mData = new ArrayList<>();

    //    访问服务器成功获取数据
    protected void executeOnLoadDataSuccess(List<RoomComment.ResultEntity> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mData = data;
        //  mData=data;
        //  generateSomeDanmaku(mData);
        //  mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        if (mCurrentPage == 1) {
            mListAdapter.setDataList(data);
        } else {
            mListAdapter.addAll(data);
        }
        //头部布局添加
        //    hearparseList(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {
          /*  if (needShowEmptyNoData()) {
                mErrorLayout.setNoDataContent(getNoDataTip());
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
            }*/
        }
        state_message = 0;
        mRecyclerView.scrollToPosition(mListAdapter.getItemCount());
        mListAdapter.notifyDataSetChanged();
        mData.clear();
    }

    protected ListBaseAdapter<RoomComment.ResultEntity> getListAdapter() {
        return new LiveZhiBoListAdapter(this);
    }

    //    加载失败
    protected void executeOnLoadDataError(String error) {
        executeOnLoadFinish();
        if (mCurrentPage == 1) {
            //  mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
        } else {

            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
            //find by TopJohn
            mCurrentPage--;
            //   mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
//            mListAdapter.notifyDataSetChanged();
        }
    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }

    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
//        if(null != mRecyclerView) {
//            mRecyclerView.refreshComplete(REQUEST_COUNT);
//        }
    }

    protected final int REQUEST_COUNT = 10;

    //下拉刷新 下拉刷新被禁止
    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        //    requestData();

    }

    /**
     * 设置顶部正在加载的状态
     */
    protected void setSwipeRefreshLoadingState() {
//        TLog.log("setSwipeRefreshLoadingState ");
    }

    private final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

  /*  @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }*/

    //    private void  tijiao(){
//        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveLive(roomid, TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getOnLineKey(), TempLoginConfig.sf_getPassWord()), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
//            @Override
//            public void onSucceed(TempResponse data) {
//            /*    WeiboDialogUtils.closeDialog(mWeiboDialog);
//                if (data.getFlag()==1){
//                    startActivity(new Intent(getBaseContext(), ActPaySuccess.class));
//                    myInputPwdUtil.hide();
//                    finish();
//
//                }else {
//                    Toast.makeText(ActPayment.this,data.getMsg(), Toast.LENGTH_SHORT).show();
//
//                }*/
//            }
//
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        });
//    }
 /*   @Override
    protected void onStart() {
        super.onStart();

    }*/
//红包pop
    private void showRed() {
        pop_red = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.pop_red_envelopes, null);
        final EditText prices_edit = (EditText) view.findViewById(R.id.red_prices);
        final EditText num_edit = (EditText) view.findViewById(R.id.red_num);
        view.findViewById(R.id.pop_choose_date_quit).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_choose_date_ok).setOnClickListener(clickListener);
        pop_red.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
        prices_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mpricesedit = prices_edit.getText().toString();
            }
        });

        num_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mnumedit = num_edit.getText().toString();
            }
        });
    }

    private void showMessage() {
        pop_send_message = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.pop_send_message, null);
        final EditText message = (EditText) view.findViewById(R.id.content_edt);
        view.findViewById(R.id.pop_send_message_ok).setOnClickListener(clickListener);
        pop_send_message.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                minputText = message.getText().toString();
            }
        });
    }

    //红包点击事件
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_choose_date_quit:
                    if (pop_red.isShowing() && pop_red != null) {
                        pop_red.dismiss();
                    }
                    break;
                case R.id.pop_choose_date_ok:
                    state_red++;
                    if (mpricesedit != null && mpricesedit != "" && mnumedit != null && mnumedit != ""&&state_red == 1) {
                        mPreI.saveConsumOrder(mpricesedit, null, null, "2", mnumedit);
                    } else if (TextUtils.isEmpty(mpricesedit)) {
                        showToast("请输入总金额");
                    } else if (TextUtils.isEmpty(mnumedit)) {
                        showToast("请输入个数");
                    } else {
                        if (state_red == 1) {
                            mPreI.saveConsumOrder(mpricesedit, null, null, "2", mnumedit);
                        }
                    }
                    break;
                case R.id.pop_send_message_ok://发送消息
                    state_message++;
                    if (state_message == 1) {
                        String msg;
                        if (TempLoginConfig.sf_getLoginState()) {
                            msg = TempLoginConfig.sf_getNickName() + "-" + minputText + "-" + roomId;
                        } else {
                            msg = "游客" + "-" + minputText + "-" + roomId;
                        }
                        sendMsg(msg);
                        pop_send_message.dismiss();
                    }
                    break;
            }
        }
    };
    private CameraStreamingSetting buildCameraStreamingSetting() {
        mCameraConfig = (CameraConfig) getIntent().getSerializableExtra("CameraConfig");
      //  mCameraConfig=new CameraConfig();


 //   mCameraConfig.mSizeLevel = mCameraConfig.PREVIEW_SIZE_LEVEL_PRESETS_MAPPING[0];
     //   Spinner sizeRatioSpinner = (Spinner) root.findViewById(R.id.preview_size_ratio_spinner);
    //    mCameraConfig.mSizeRatio = PREVIEW_SIZE_RATIO_PRESETS_MAPPING[sizeRatioSpinner.getSelectedItemPosition()];
     /*   Spinner focusModeSpinner = (Spinner) root.findViewById(R.id.focus_mode_spinner);
        mCameraConfig.mFocusMode = FOCUS_MODE_PRESETS_MAPPING[focusModeSpinner.getSelectedItemPosition()];*/
   /*     mCameraConfig.mIsFaceBeautyEnabled = ((CheckBox) root.findViewById(R.id.face_beauty)).isChecked();
        mCameraConfig.mIsCustomFaceBeauty = ((CheckBox) root.findViewById(R.id.external_face_beauty)).isChecked();
        mCameraConfig.mContinuousAutoFocus = ((CheckBox) root.findViewById(R.id.continuous_auto_focus)).isChecked();
        mCameraConfig.mPreviewMirror = ((CheckBox) root.findViewById(R.id.preview_mirror)).isChecked();
        mCameraConfig.mEncodingMirror = ((CheckBox) root.findViewById(R.id.encoding_mirror)).isChecked();*/


        mCameraConfig.mFrontFacing=true;

      //  cameraConfig.mFrontFacing = ((RadioButton) root.findViewById(R.id.facing_front)).isChecked();
        CameraStreamingSetting cameraStreamingSetting = new CameraStreamingSetting();
        cameraStreamingSetting.setCameraId(mCameraConfig.mFrontFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK)
                .setCameraPrvSizeLevel(mCameraConfig.mSizeLevel)
                .setCameraPrvSizeRatio(mCameraConfig.mSizeRatio)
                .setFocusMode(mCameraConfig.mFocusMode)
                .setContinuousFocusModeEnabled(mCameraConfig.mContinuousAutoFocus)
                .setFrontCameraPreviewMirror(mCameraConfig.mPreviewMirror)
                .setFrontCameraMirror(mCameraConfig.mEncodingMirror).setRecordingHint(false)
                .setResetTouchFocusDelayInMs(3000)
                .setBuiltInFaceBeautyEnabled(!mCameraConfig.mIsCustomFaceBeauty)
                .setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f));

        if (mCameraConfig.mIsFaceBeautyEnabled) {
            cameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
        } else {
            cameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);
        }

        return cameraStreamingSetting;
    }

    private FBO mFBO = new FBO();
}
