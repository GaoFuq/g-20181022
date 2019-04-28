package com.code.clkj.menggong.activity.comCameraStreaming;

import android.Manifest;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityI;
import com.code.clkj.menggong.activity.comLive.PreActLiveActivityImpl;
import com.code.clkj.menggong.activity.comLive.ViewActLiveActivityI;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.Config;
import com.code.clkj.menggong.util.EncodingConfig;
import com.lf.tempcore.comApp.AppManager;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 直播页面  弃用
 */
public class ExtCapStreamingActivity1 extends JFYActivity implements
        StreamStatusCallback,
        StreamingSessionListener,
        StreamingStateChangedListener, EasyPermissions.PermissionCallbacks, ViewActLiveActivityI {
//    @Bind(R.id.act_live_nickname)
//    TextView nickName;
//    @Bind(R.id.act_live_head_img)
//    ImageView headImg;


    public static final String INPUT_TEXT = "INPUT_TEXT";
    private static final int MSG_START_STREAMING  = 0;
    private static final int MSG_STOP_STREAMING   = 1;

    private ImageView mCameraChange;
    private SurfaceView mSurfaceView;

    private boolean mIsEncodingMirror = false;

    private boolean mShutterButtonPressed = false;
//private boolean mShutterButtonPressed = true;

    private String mStatusMsgContent;

    private String mLogContent = "\n";

    private ExtAudioCapture mExtAudioCapture;
    private ExtVideoCapture mExtVideoCapture;

    private StreamingManager mStreamingManager;
    private StreamingProfile mProfile = new StreamingProfile();

    private JSONObject mJSONObject;
    private boolean mOrientationChanged = false;

    private Switcher mSwitcher = new Switcher();

    private ImageView camera_switch_editex;

//    private TextView streamingStatus_room_number;
//    private  LinearLayout   mext_ly ;

    private boolean mIsReady = false;

    private  final String TAG = "dddd";
    private PreActLiveActivityI mPreI;
    private TextView mStreamStatus;
    private TextView mLogTextView;
    private TextView mStatusTextView;
    private Button mShutterButton;//开始

    protected EncodingConfig mEncodingConfig;


    /**每一页展示多少条数据*/
    protected int mCurrentPage = 0;
  /*  @Bind(R.id.recycler_view)
    protected LRecyclerView mRecyclerView;*/

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_START_STREAMING:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // disable the shutter button before startStreaming
//                            setShutterButtonEnabled(false);
//                            mShutterButtonPressed = true;
//                            boolean res = mStreamingManager.startStreaming();
//
//                            Log.i(TAG, "res:" + res);
//                            if (!res) {
////                                mShutterButtonPressed = false;
//                                mShutterButtonPressed = true;
//                                setShutterButtonEnabled(true);
//                            }
//                            setShutterButtonPressed(mShutterButtonPressed);
//                        }
//                    }).start();
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
//                    if (mShutterButtonPressed) {
//                        // disable the shutter button before stopStreaming
//                        setShutterButtonEnabled(false);
//                        boolean res = mStreamingManager.stopStreaming();
//                        if (!res) {
//                            mShutterButtonPressed = true;
//                            setShutterButtonEnabled(true);
//                        }
//                        setShutterButtonPressed(mShutterButtonPressed);
//                    }
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
    /**
     * 开始计时功能
     */
    private void startTimer(){
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.add(Calendar.HOUR_OF_DAY, -8);
        Date time = calendar.getTime();
        liveTime = time.getTime();
        handlerTime.post(timerRunnable);
    }
    private String  time="";
    String roomid="";
    /**
     * 循环执行线程
     */
    private Runnable timerRunnable=new Runnable() {
        @Override
        public void run() {
            handlerTime.postDelayed(timerRunnable, 1000);
            long sysTime = System.currentTimeMillis();
            liveTime+=1000;
            CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", liveTime);
            CharSequence sysDateStr = DateFormat.format("yyyy/MM/dd", sysTime);
             time=sysTimeStr.toString();
          //  tvtime.setText(sysTimeStr);
//            streamingStatus_room_data.setText(sysDateStr);
        }
    };
    private Handler handlerTime = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
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
        requestCodeQRCodePermissions();
        mCameraChange = (ImageView) findViewById(R.id.live_camera_change);

        mSurfaceView = (SurfaceView) findViewById(R.id.ext_camerapreview_surfaceview);

//        mext_ly = (LinearLayout) findViewById(R.id.extcapstreaming_ly);
        camera_switch_editex = (ImageView) findViewById(R.id.camera_switch_editex);
      /* String inputUrl="rtmp://pili-publish.cyz.lingkj.com/caiyiwang/cyw1493013251505A?e=1495526128&token=GvgWVkU3z7oeTCWQYeK2Ny-CXhqfpRXr5K6yeRGf:IyQpfTjJlt1fRGxs1APimU-bn6w=";
        String   publishUrlFromServer = Config.EXTRA_PUBLISH_URL_PREFIX+inputUrl;*/

        mPreI = new PreActLiveActivityImpl(this);
        String lat = "29.465457";
        String lng = "106.476440";

        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(), lat, lng);

        String publishUrlFromServer = getIntent().getStringExtra("URL");
//        roomid = getIntent().getStringExtra("roomid");

        Log.i(TAG, "publishUrlFromServer:" + publishUrlFromServer);
//        mext_ly.getBackground().setAlpha(100);
        StreamingProfile.AudioProfile aProfile = new StreamingProfile.AudioProfile(44100, 96 * 1024);
        StreamingProfile.VideoProfile vProfile = new StreamingProfile.VideoProfile(30, 1000 * 1024, 48);
        StreamingProfile.AVProfile avProfile = new StreamingProfile.AVProfile(vProfile, aProfile);
//        tijiao();

        try {
            if (publishUrlFromServer != null) {
                mProfile.setPublishUrl(publishUrlFromServer);
            }
//                mProfile.setPublishUrl(publishUrlFromServer.substring(Config.EXTRA_PUBLISH_URL_PREFIX.length()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
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

        mExtVideoCapture = new ExtVideoCapture(mSurfaceView);
        mExtVideoCapture.setOnPreviewFrameCallback(mOnPreviewFrameCallback);

        mExtAudioCapture = new ExtAudioCapture();

        initUIs();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mExtAudioCapture.startCapture();
        mExtAudioCapture.setOnAudioFrameCapturedListener(mOnAudioFrameCapturedListener);
        mStreamingManager.resume();
  //      ScreenRotateUtil.getInstance(this).start(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsReady = false;
        mShutterButtonPressed = false;
        mHandler.removeCallbacksAndMessages(null);
        mExtAudioCapture.stopCapture();
        mStreamingManager.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStreamingManager.destroy();
   //     ScreenRotateUtil.getInstance(this).stop();
        AppManager.getAppManager().finishActivityd(this);
//        closeWebsocket();
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
//        webSocketConnect();
        mShutterButton = (Button) findViewById(R.id.toggleRecording_button);
        mStatusTextView = (TextView) findViewById(R.id.streamingStatus);
        Button encodingMirrorBtn = (Button) findViewById(R.id.encoding_mirror_btn);

        mLogTextView = (TextView) findViewById(R.id.log_info);
        mStreamStatus = (TextView) findViewById(R.id.stream_status);
//        mRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view);


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
                Toast.makeText(ExtCapStreamingActivity1.this, "镜像成功", Toast.LENGTH_SHORT).show();
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
        startTimer();

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

    private class Switcher implements Runnable {
        @Override
        public void run() {
            if (mExtVideoCapture != null) {
                mSurfaceView.setVisibility(View.GONE);
                mExtVideoCapture.switchCamera();
                mSurfaceView.setVisibility(View.VISIBLE);
            }
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

//    private static WebSocketConnection webSocketConnection;
//    private static WebSocketOptions options = new WebSocketOptions();
//    // private static boolean isExitApp = false;
//    private static String websocketHost =    BaseUriConfig.BASE_URLWEB; //websocket服务端的url,,,ws是协议,和http一样,我写的时候是用的我们公司的服务器所以这里不能贴出来
//
//    private  final String TAG = ExtCapStreamingActivity.class.getSimpleName();
//    private static boolean isClosed = true;


//    public void webSocketConnect(){
//        webSocketConnection = new WebSocketConnection();
//        try {
//            webSocketConnection.connect(websocketHost,new WebSocketHandler(){
//                //websocket启动时候的回调
//                @Override
//                public void onOpen() {
//                    Log.d(TAG,"open");
//                    isClosed = false;
//                }
//
//                //websocket接收到消息后的回调
//                @Override
//                public void onTextMessage(String payload) {
//                    Log.d(TAG, "payload = " + payload);
//                    RoomComment.ResultEntity rest=new RoomComment.ResultEntity();
//                    try {
//                        JSONObject jsonObject = new JSONObject(payload);
//                        rest.setMuseNickName(jsonObject.getString("nickname"));
//                        rest.setRcomId(jsonObject.getString("roomId"));
//                        rest.setRcomContent(jsonObject.getString("comment"));
//                        mData.add(rest);
//                        executeOnLoadDataSuccess(mData);
//                       // onRefreshView();
//                      //  recyclerView.scrollToPosition(Datas.size()+1);
//                      //  adapter.notifyDataSetChanged();
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                    }
//                  /*  holder.tvGiftMsg.setText(item.getMuseNickName()+"");
//                    holder.live_comment_context.setText(item.getRcomContent()+"");*/
//                  //  onRefreshView();
//                    // mPrestener.sendDanmu(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getOnLineKey(),TempLoginConfig.sf_getPassWord(),inputText,roomId);
//                }
//
//                //websocket关闭时候的回调
//                @Override
//                public void onClose(int code, String reason) {
//                    isClosed = true;
//                    Log.d(TAG, "code = " + code + " reason = " + reason);
//                    switch (code) {
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//                        case 3://手动断开连接
////                            if (!isExitApp) {
////                                webSocketConnect();
////                            }
//                            break;
//                        case 4:
//                            break;
//                        /**
//                         * 由于我在这里已经对网络进行了判断,所以相关操作就不在这里做了
//                         */
//                        case 5://网络断开连接
////                            closeWebsocket(false);
////                            webSocketConnect();
//                            break;
//                    }
//                }
//            } , options);
//        } catch (WebSocketException e) {
//            e.printStackTrace();
//        }
//    }
//    public  void sendMsg(String s) {
//        Log.d(TAG, "sendMsg = " + s);
//        if (!TextUtils.isEmpty(s))
//            if (webSocketConnection != null) {
//                webSocketConnection.sendTextMessage(s);
//            }
//    }
//    public  void closeWebsocket() {
//        //  isExitApp = exitApp;
//        if (webSocketConnection != null && webSocketConnection.isConnected()) {
//            webSocketConnection.disconnect();
//            webSocketConnection = null;
//        }
//    }
//    List<RoomComment.ResultEntity> mData = new ArrayList<>();
    //访问服务器成功获取数据
//    protected void executeOnLoadDataSuccess(List<RoomComment.ResultEntity> data) {
//        if (data == null) {
//            data = new ArrayList<>();
//        }
//        mData = data;
//      //  mData=data;
//      //  generateSomeDanmaku(mData);
//      //  mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
//        if (mCurrentPage == 1) {
//            mListAdapter.setDataList(data);
//        } else {
//            mListAdapter.addAll(data);
//        }
//        //头部布局添加
//        //    hearparseList(data);
//        // 判断等于是因为最后有一项是listview的状态
//        if (mListAdapter.getItemCount() == 0) {
//          /*  if (needShowEmptyNoData()) {
//                mErrorLayout.setNoDataContent(getNoDataTip());
//                mErrorLayout.setErrorType(ErrorLayout.NODATA);
//            }*/
//        }
//
//       mListAdapter.notifyDataSetChanged();
//        mRecyclerView.scrollToPosition(mListAdapter.getItemCount());
//    }
//    protected void requestData() {
//        mCurrentPage++;
//        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getDanmuList(roomid), new TempRemoteApiFactory.OnCallBack<RoomComment>() {
//            @Override
//            public void onSucceed( RoomComment data) {
//                // totalPage = data.total;
//                executeOnLoadDataSuccess(data.getResult());
//            }
//            @Override
//            public void onCompleted() {
//                executeOnLoadFinish();
//            }
//            @Override
//            public void onError(Throwable e) {
//                executeOnLoadDataError(null);
//            }
//        });
//        isRequestInProcess = true;
//    /*    if(mCurrentPage == 1){
//            onRefresh();
//        }*/
//    }
//    protected ListBaseAdapter<RoomComment.ResultEntity> getListAdapter() {
//        return  new LiveZhiBoListAdapter(this);
//    }
    //加载失败
//    protected void executeOnLoadDataError(String error) {
//        executeOnLoadFinish();
//        if (mCurrentPage == 1) {
//          //  mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
//        } else {
//
//            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
//            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
//            //find by TopJohn
//            mCurrentPage--;
//         //   mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
////            mListAdapter.notifyDataSetChanged();
//        }
//    }
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
    /** 设置顶部正在加载的状态 */
    protected void setSwipeRefreshLoadingState() {
//        TLog.log("setSwipeRefreshLoadingState ");
    }
    private final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
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
}
