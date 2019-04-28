package com.code.clkj.menggong.activity.comCameraStreaming;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
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
import com.code.clkj.menggong.util.Cache;
import com.code.clkj.menggong.util.Config;

import com.code.clkj.menggong.util.TempDataUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.comApp.AppManager;
import com.lf.tempcore.recyclerview.LRecyclerView;
import com.lf.tempcore.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempViews.TempCustomProgressDialog;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.FrameCapturedCallback;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamingPreviewCallback;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.SurfaceTextureCallback;
import com.qiniu.pili.droid.streaming.WatermarkSetting;
import com.qiniu.pili.droid.streaming.av.common.PLFourCC;
/*import com.qiniu.pili.droid.streaming.demo.R;
import com.qiniu.pili.droid.streaming.demo.ui.RotateLayout;
import com.qiniu.pili.droid.streaming.demo.utils.Cache;
import com.qiniu.pili.droid.streaming.demo.utils.Config;*/
import com.qiniu.pili.droid.streaming.microphone.AudioMixer;
import com.qiniu.pili.droid.streaming.microphone.OnAudioMixListener;
import com.rey.material.app.BottomSheetDialog;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;

public class AVStreamingActivity extends StreamingBaseActivity implements
        StreamingPreviewCallback,
        CameraPreviewFrameView.Listener,
        SurfaceTextureCallback ,ViewActLiveActivityI {
    private static final String TAG = "AVStreamingActivity";

    private CameraStreamingSetting mCameraStreamingSetting;
    private CameraConfig mCameraConfig;

    private Button mMuteButton;
    private Button mTorchBtn;
    private Button camera_switch_btnon;
    private Button mCaptureFrameBtn;
    private Button mEncodingOrientationSwitcherBtn;
    private Button mFaceBeautyBtn;
   // private RotateLayout mRotateLayout;

    private Button mMixToggleBtn;
//    private SeekBar mMixProgress;

    private boolean mIsTorchOn = false;
    private boolean mIsNeedMute = false;
    private boolean mIsNeedFB = true;
    private boolean mIsPreviewMirror = false;
    private boolean mIsEncodingMirror = false;
    private boolean mIsPlayingback = false;
    private TextView act_live_nickname;//直播昵称
    private TextView timer_tv;//直播时间
    private int mCurrentZoom = 0;
    private int mMaxZoom = 0;
    private boolean mOrientationChanged = false;
    private int mCurrentCamFacingIndex;

    private FBO mFBO = new FBO();

    private ScreenShooter mScreenShooter = new ScreenShooter();
    private Switcher mSwitcher = new Switcher();
    private EncodingOrientationSwitcher mEncodingOrientationSwitcher = new EncodingOrientationSwitcher();
    private ImageSwitcher mImageSwitcher;

    private MediaStreamingManager mMediaStreamingManager;
    private AudioMixer mAudioMixer;
    private String mAudioFile;
    ImageView    red_envelopes_img;
    private Handler mHandler;
    private int mTimes = 0;
    private boolean mIsPictureStreaming = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        normalPause();
    }
    List<RoomComment.ResultEntity> mData = new ArrayList<>();
    private static String websocketHost = BaseUriConfig.BASE_URLWEB; //websocket服务端的url,,,ws是协议,和http一样,我写的时候是用的我们公司的服务器所以这里不能贴出来
    /**
     * 开始计时功能
     */
    ImageView send_message_img;
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
    /**
     * 每一页展示多少条数据
     */
   protected int mCurrentPage = 0;
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

    private static WebSocketConnection webSocketConnection;
    private static WebSocketOptions options = new WebSocketOptions();
    private static boolean isClosed = true;
    private void initUIs() {
        webSocketConnect();
        AppManager.getAppManager().addActivity(this);
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        send_message_img = (ImageView) findViewById(R.id.send_message_img);
        mRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view);
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
    private void startTimer() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.add(Calendar.HOUR_OF_DAY, -8);
        Date time = calendar.getTime();
        liveTime = time.getTime();
        handlerTime.post(timerRunnable);
    }
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
    private long liveTime;
    protected boolean requestDataIfViewCreated() {
        return true;
    }
    protected ListBaseAdapter<RoomComment.ResultEntity> getListAdapter() {
        return new LiveZhiBoListAdapter(this);
    }

    private LRecyclerView mRecyclerView;
    protected ListBaseAdapter<RoomComment.ResultEntity> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;
    /**
     * 循环执行线程
     */
    private String time = "";
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

    private void normalPause() {
        mIsReady = false;
        mShutterButtonPressed = false;
        mIsPictureStreaming = false;
        if (mHandler != null) {
            mHandler.getLooper().quit();
        }
        mMediaStreamingManager.pause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaStreamingManager.destroy();
        AppManager.getAppManager().finishActivityd(this);
        closeWebsocket();
    }

    @Override
    protected void initStreamingManager() {


        CameraPreviewFrameView cameraPreviewFrameView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);
        mMediaStreamingManager = new MediaStreamingManager(this, cameraPreviewFrameView, mEncodingConfig.mCodecType);


        if (mEncodingConfig.mIsPictureStreamingEnabled) {
            if (mEncodingConfig.mPictureStreamingFilePath == null) {
                mProfile.setPictureStreamingResourceId(R.drawable.pause_publish);
            } else {
                mProfile.setPictureStreamingFilePath(mEncodingConfig.mPictureStreamingFilePath);
            }
        }
        MicrophoneStreamingSetting microphoneStreamingSetting = null;
        if (mAudioStereoEnable) {
            /**
             * Notice !!! {@link AudioFormat#CHANNEL_IN_STEREO} is NOT guaranteed to work on all devices.
             */
            microphoneStreamingSetting = new MicrophoneStreamingSetting();
            microphoneStreamingSetting.setChannelConfig(AudioFormat.CHANNEL_IN_STEREO);
        }
        mMediaStreamingManager.prepare(mCameraStreamingSetting, microphoneStreamingSetting, null, mProfile);
        //mMediaStreamingManager.prepare(mCameraStreamingSetting, microphoneStreamingSetting, buildWatermarkSetting(), mProfile);
        if (mCameraConfig.mIsCustomFaceBeauty) {
            mMediaStreamingManager.setSurfaceTextureCallback(this);
        }
        cameraPreviewFrameView.setListener(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        mMediaStreamingManager.setStreamingStateListener(this);

        mAudioMixer = mMediaStreamingManager.getAudioMixer();
        mAudioMixer.setOnAudioMixListener(new OnAudioMixListener() {
            @Override
            public void onStatusChanged(MixStatus mixStatus) {
                mMixToggleBtn.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AVStreamingActivity.this, "mix finished", Toast.LENGTH_LONG).show();
                        updateMixBtnText();
                    }
                });
            }

            @Override
            public void onProgress(long l, long l1) {
             /*   mMixProgress.setProgress((int) l);
                mMixProgress.setMax((int) l1);*/
            }
        });
        mAudioFile = Cache.getAudioFile(this);
        if (mAudioFile != null) {
            try {
                mAudioMixer.setFile(mAudioFile, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
        fbSetting.beautyLevel = 100.0f / 100.0f;
        fbSetting.whiten = 100.0f / 100.0f;
        fbSetting.redden = 100.0f / 100.0f;
        mMediaStreamingManager.updateFaceBeautySetting(fbSetting);
    }
    private SimpleDraweeView act_live_head_img;//直播房间
    private SimpleDraweeView act_live_head_img_nickname;//直播头像
    @Override
    protected boolean startStreaming() {
        return mMediaStreamingManager.startStreaming();
    }
    private String roomId = "";//房间ID
    @Override
    protected boolean stopStreaming() {
        return mMediaStreamingManager.stopStreaming();
    }
    private TextView people_num;//在线人数
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
    private RecyclerView phb_rec;//显示录坞排行榜
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
    private ListBaseAdapter<RespGiftPaihangh.ResultBean> RankingListAdapter;
    @Override
    public void toast(String message) {
         //   showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
       /* showProgressDialog(false);*/
    }
    /**
     * 等待加载对话框
     */
    private TempCustomProgressDialog mProgressDailog;
    @Override
    public void showPro() {
        showProgressDialog(false);
    }
    /**
     * 显示等待加载对话框
     *
     * @param shouldCanceledOnTouchOutside 允许触摸隐藏
     */
    /**
     * 显示等待加载对话框
     * 默认不允许触摸隐藏
     */
    private void showProgressDialog() {
        if (mProgressDailog != null) {
            mProgressDailog.show();
        }
    }

    protected void showProgressDialog(boolean shouldCanceledOnTouchOutside) {

        if (mProgressDailog != null) {
            mProgressDailog.setCanceledOnTouchOutside(shouldCanceledOnTouchOutside);
            showProgressDialog();
        } else {
            mProgressDailog = new TempCustomProgressDialog(this, getResources().getString(R.string.temp_loading));
            mProgressDailog.setCanceledOnTouchOutside(shouldCanceledOnTouchOutside);
            showProgressDialog();
        }
    }
    /**
     * 消失等待对话框
     */
    protected void dismissProgressDialog() {
        if (mProgressDailog != null) {
            mProgressDailog.dismiss();
        }
    }
    @Override
    public void disPro() {
            dismissProgressDialog();
    }

    private class EncodingOrientationSwitcher implements Runnable {
        @Override
        public void run() {
            Log.i(TAG, "mIsEncOrientationPort:" + mIsEncOrientationPort);
            mOrientationChanged = true;
            mIsEncOrientationPort = !mIsEncOrientationPort;
            mProfile.setEncodingOrientation(mIsEncOrientationPort ? StreamingProfile.ENCODING_ORIENTATION.PORT : StreamingProfile.ENCODING_ORIENTATION.LAND);
            mMediaStreamingManager.setStreamingProfile(mProfile);
            stopStreamingInternal();
            setRequestedOrientation(mIsEncOrientationPort ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mMediaStreamingManager.notifyActivityOrientationChanged();
            updateOrientationBtnText();
            Toast.makeText(AVStreamingActivity.this, Config.HINT_ENCODING_ORIENTATION_CHANGED,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "EncodingOrientationSwitcher -");
        }
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
        }
    }

    private class ScreenShooter implements Runnable {
        @Override
        public void run() {
            final String fileName = "PLStreaming_" + System.currentTimeMillis() + ".jpg";
            mMediaStreamingManager.captureFrame(100, 100, new FrameCapturedCallback() {
                private Bitmap bitmap;
                @Override
                public void onFrameCaptured(Bitmap bmp) {
                    if (bmp == null) {
                        return;
                    }
                    bitmap = bmp;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                saveToSDCard(fileName, bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (bitmap != null) {
                                    bitmap.recycle();
                                    bitmap = null;
                                }
                            }
                        }
                    }).start();
                }
            });
        }
    }

    /**
     * switch picture during streaming
     */
    private class ImageSwitcher implements Runnable {
        @Override
        public void run() {
            if (!mIsPictureStreaming) {
                Log.d(TAG, "is not picture streaming!!!");
                return;
            }

           /* if (mTimes % 2 == 0) {
                if (mEncodingConfig.mPictureStreamingFilePath != null) {
                    mMediaStreamingManager.setPictureStreamingFilePath(mEncodingConfig.mPictureStreamingFilePath);
                } else {
             //       mMediaStreamingManager.setPictureStreamingResourceId(R.drawable.qiniu_logo);
                }
            } else {
                mMediaStreamingManager.setPictureStreamingResourceId(R.drawable.pause_publish);
            }
            mTimes++;
            if (mHandler != null && mIsPictureStreaming) {
                mHandler.postDelayed(this, 1000);
            }*/
        }
    }

    private boolean isPictureStreaming() {
        if (mIsPictureStreaming) {
            Toast.makeText(AVStreamingActivity.this, "is picture streaming, operation failed!", Toast.LENGTH_SHORT).show();
        }
        return mIsPictureStreaming;
    }

    private void togglePictureStreaming() {
        boolean isOK = mMediaStreamingManager.togglePictureStreaming();
        if (!isOK) {
            Toast.makeText(AVStreamingActivity.this, "toggle picture streaming failed!", Toast.LENGTH_SHORT).show();
            return;
        }

        mIsPictureStreaming = !mIsPictureStreaming;

        mTimes = 0;
        if (mIsPictureStreaming) {
            if (mImageSwitcher == null) {
                mImageSwitcher = new ImageSwitcher();
            }

            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
            mHandler.postDelayed(mImageSwitcher, 1000);
        } else {
            if (mHandler != null) {
                mHandler.getLooper().quit();
            }
        }
    }

    private void saveToSDCard(String filename, Bitmap bmp) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bmp.compress(Bitmap.CompressFormat.PNG, 90, bos);
                bmp.recycle();
                bmp = null;
            } finally {
                if (bos != null) bos.close();
            }

            final String info = "Save frame to:" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AVStreamingActivity.this, info, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Accept only 32 bit png (ARGB)
     * @return
     */
    private WatermarkSetting buildWatermarkSetting() {
        if (!mEncodingConfig.mIsWatermarkEnabled) {
            return null;
        }
        WatermarkSetting watermarkSetting = new WatermarkSetting(this);
     //   watermarkSetting.setResourceId(R.drawable.qiniu_logo);
        watermarkSetting.setAlpha(mEncodingConfig.mWatermarkAlpha);
        watermarkSetting.setSize(mEncodingConfig.mWatermarkSize);
        if (mEncodingConfig.mIsWatermarkLocationPreset) {
            watermarkSetting.setLocation(mEncodingConfig.mWatermarkLocationPreset);
        } else {
            watermarkSetting.setCustomPosition(mEncodingConfig.mWatermarkLocationCustomX, mEncodingConfig.mWatermarkLocationCustomY);
        }

        return watermarkSetting;
    }

    private CameraStreamingSetting buildCameraStreamingSetting() {
        mCameraConfig = (CameraConfig) getIntent().getSerializableExtra("CameraConfig");

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

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        /**
         * You should choose a suitable size to avoid image scale
         * eg: If streaming size is 1280 x 720, you should choose a camera preview size >= 1280 x 720
         */
        Camera.Size size = null;
        if (list != null) {
            StreamingProfile.VideoEncodingSize encodingSize = mProfile.getVideoEncodingSize(mCameraConfig.mSizeRatio);
            for (Camera.Size s : list) {
                if (s.width >= encodingSize.width && s.height >= encodingSize.height) {
                    if (mEncodingConfig.mIsVideoSizePreset) {
                        size = s;
                        Log.d(TAG, "selected size :" + size.width + "x" + size.height);
                    }
                    break;
                }
            }
        }
        return size;
    }

    private PreActLiveActivityI mPreI;
    private ImageView  camera_switch_editex;
    @Override
    public void initView() {
        mCameraStreamingSetting = buildCameraStreamingSetting();
        mIsEncOrientationPort = mEncodingConfig.mVideoOrientationPortrait;
        mIsNeedFB = mCameraConfig.mIsFaceBeautyEnabled;
        mIsPreviewMirror = mCameraConfig.mPreviewMirror;
        mIsEncodingMirror = mCameraConfig.mEncodingMirror;
        mCurrentCamFacingIndex = mCameraConfig.mFrontFacing ? 1 : 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        people_num = (TextView) findViewById(R.id.people_num);
        setRequestedOrientation(mIsEncOrientationPort ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_av_streaming);
        phb_rec = (RecyclerView) findViewById(R.id.phb_rec);
        mMuteButton = (Button) findViewById(R.id.mute_btn);
        mTorchBtn = (Button) findViewById(R.id.torch_btn);
        camera_switch_btnon = (Button) findViewById(R.id.camera_switch_btnon);
            red_envelopes_img = (ImageView) findViewById(R.id.red_envelopes_img);
        //红包点击事件
        red_envelopes_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRed();
            }
        });
        initUIs();
        camera_switch_editex = (ImageView) findViewById(R.id.camera_switch_editex);
        mCaptureFrameBtn = (Button) findViewById(R.id.capture_btn);
        mFaceBeautyBtn = (Button) findViewById(R.id.fb_btn);
        Button previewMirrorBtn = (Button) findViewById(R.id.preview_mirror_btn);
        Button encodingMirrorBtn = (Button) findViewById(R.id.encoding_mirror_btn);
        Button picStreamingBtn = (Button) findViewById(R.id.pic_streaming_btn);
        act_live_head_img = (SimpleDraweeView) findViewById(R.id.act_live_head_img);
        act_live_head_img_nickname = (SimpleDraweeView) findViewById(R.id.act_live_head_img_nickname);
        act_live_nickname = (TextView) findViewById(R.id.act_live_nickname);
        mPreI = new PreActLiveActivityImpl(this);
        mPreI.queryCanLivingRoom(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng());
        camera_switch_btnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPictureStreaming()) {
                    return;
                }
                camera_switch_btnon.removeCallbacks(mSwitcher);
                camera_switch_btnon.postDelayed(mSwitcher, 100);
            }
        });
        camera_switch_editex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if (mShutterButtonPressed) {
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
                }*/
                finish();
            }
        });
        mFaceBeautyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsNeedFB = !mIsNeedFB;
                mMediaStreamingManager.setVideoFilterType(mIsNeedFB ?
                        CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY
                        : CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_NONE);
                updateFBButtonText();
            }
        });

        mMuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsNeedMute = !mIsNeedMute;
                mMediaStreamingManager.mute(mIsNeedMute);
                updateMuteButtonText();
            }
        });

        previewMirrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPictureStreaming()) {
                    return;
                }

                mIsPreviewMirror = !mIsPreviewMirror;
                mMediaStreamingManager.setPreviewMirror(mIsPreviewMirror);
                Toast.makeText(AVStreamingActivity.this, "镜像成功", Toast.LENGTH_SHORT).show();
            }
        });

        encodingMirrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPictureStreaming()) {
                    return;
                }

                mIsEncodingMirror = !mIsEncodingMirror;
                mMediaStreamingManager.setEncodingMirror(mIsEncodingMirror);
                Toast.makeText(AVStreamingActivity.this, "镜像成功", Toast.LENGTH_SHORT).show();
            }
        });

        picStreamingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfile.setPictureStreamingFps(10);
                togglePictureStreaming();
            }
        });

        mTorchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPictureStreaming()) {
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mIsTorchOn) {
                            mMediaStreamingManager.turnLightOff();
                        } else {
                            mMediaStreamingManager.turnLightOn();
                        }
                        mIsTorchOn = !mIsTorchOn;
                        setTorchEnabled(mIsTorchOn);
                    }
                }).start();
            }
        });



        mCaptureFrameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPictureStreaming()) {
                    return;
                }

                mCaptureFrameBtn.removeCallbacks(mScreenShooter);
                mCaptureFrameBtn.postDelayed(mScreenShooter, 100);
            }
        });

        mEncodingOrientationSwitcherBtn = (Button) findViewById(R.id.orientation_btn);
        mEncodingOrientationSwitcherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPictureStreaming()) {
                    return;
                }

                mEncodingOrientationSwitcherBtn.removeCallbacks(mEncodingOrientationSwitcher);
                mEncodingOrientationSwitcherBtn.postDelayed(mEncodingOrientationSwitcher, 100);
            }
        });



        initButtonText();
        initAudioMixerPanel();

      /*  SeekBar seekBarBeauty = (SeekBar) findViewById(R.id.beautyLevel_seekBar);
        seekBarBeauty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CameraStreamingSetting.FaceBeautySetting fbSetting = mCameraStreamingSetting.getFaceBeautySetting();
                fbSetting.beautyLevel = progress / 100.0f;
                fbSetting.whiten = progress / 100.0f;
                fbSetting.redden = progress / 100.0f;

                mMediaStreamingManager.updateFaceBeautySetting(fbSetting);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });*/


    }

    private void initButtonText() {
        updateFBButtonText();
        updateCameraSwitcherButtonText(mCameraStreamingSetting.getReqCameraId());
        mCaptureFrameBtn.setText("Capture");
        updateFBButtonText();
        updateMuteButtonText();
        updateOrientationBtnText();
    }
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
    private String mpricesedit;//红包金额
    private String mnumedit;//红包数量
    private String minputText;//消息内容
    private BottomSheetDialog pop_red, pop_send_message;

    private int state_red = 0;
    private int state_message = 0;
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
                       /* showToast("请输入总金额");*/
                        Toast.makeText(AVStreamingActivity.this, "请输入总金额", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(mnumedit)) {
                     //   showToast("请输入个数");
                        Toast.makeText(AVStreamingActivity.this, "请输入个数", Toast.LENGTH_SHORT).show();
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
    private void initAudioMixerPanel() {
        Button mixPanelBtn = (Button) findViewById(R.id.mix_panel_btn);
        mixPanelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View panel = findViewById(R.id.mix_panel);
                panel.setVisibility(panel.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

  /*      mMixProgress = (SeekBar) findViewById(R.id.mix_progress);
        mMixProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mAudioMixer != null) {
                    mAudioMixer.seek(1.0f * seekBar.getProgress() / seekBar.getMax());
                }
            }
        });*/

     /*   SeekBar mixVolume = (SeekBar) findViewById(R.id.mix_volume);
        mixVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mAudioMixer != null) {
                    mAudioMixer.setVolume(1.0f, 1.0f * seekBar.getProgress() / seekBar.getMax());
                }
            }
        });*/

        Button mixFileBtn = (Button) findViewById(R.id.mix_file_btn);
    /*    mixFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProperties properties = new DialogProperties();
                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File(DialogConfigs.STORAGE_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = new String[]{"mp3"};

                FilePickerDialog dialog = new FilePickerDialog(AVStreamingActivity.this, properties);
                dialog.setTitle("Select a File");
                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        String filePath = files[0];
                        try {
                            mAudioMixer.setFile(filePath, true);
                            Cache.setAudioFile(AVStreamingActivity.this, filePath);
                            Toast.makeText(AVStreamingActivity.this, "setup mix file " + filePath + " success. duration:" + mAudioMixer.getDuration(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(AVStreamingActivity.this, "setup mix file " + filePath + " failed !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.show();
            }
        });*/

   /*     mMixToggleBtn = (Button) findViewById(R.id.mix_btn);
        mMixToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAudioMixer != null) {
                    String text;
                    if (mAudioMixer.isRunning()) {
                        boolean s = mAudioMixer.pause();
                        text = s ? "mixing pause success" : "mixing pause failed !!!";
                    } else {
                        boolean s = mAudioMixer.play();
                        text = s ? "mixing play success" : "mixing play failed !!!";
                    }
                    Toast.makeText(AVStreamingActivity.this, text, Toast.LENGTH_LONG).show();

                    updateMixBtnText();
                }
            }
        });

        Button mixStopBtn = (Button) findViewById(R.id.mix_stop_btn);
        mixStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAudioMixer != null) {
                    boolean stopSuccess = mAudioMixer.stop();
                    String text = stopSuccess ? "mixing stop success" : "mixing stop failed !!!";
                    Toast.makeText(AVStreamingActivity.this, text, Toast.LENGTH_LONG).show();
                    if (stopSuccess) {
                        updateMixBtnText();
                    }
                }
            }
        });

        Button playbackToggleBtn = (Button) findViewById(R.id.playback_btn);
        playbackToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPlayingback) {
                    mMediaStreamingManager.stopPlayback();
                } else {
                    mMediaStreamingManager.startPlayback();
                }
                mIsPlayingback = !mIsPlayingback;
            }
        });*/

        updateMixBtnText();
    }

    private void updateMixBtnText() {
      /*  if (mAudioMixer != null && mAudioMixer.isRunning()) {
            mMixToggleBtn.setText("Pause");
        } else {
            mMixToggleBtn.setText("Play");
        }*/
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        /**
         * general states are handled in the `StreamingBaseActivity`
         */
        super.onStateChanged(streamingState, extra);
        switch (streamingState) {
            case READY:
                mMaxZoom = mMediaStreamingManager.getMaxZoom();
                break;
            case SHUTDOWN:
                if (mOrientationChanged) {
                    mOrientationChanged = false;
                    startStreamingInternal();
                }
                break;
            case OPEN_CAMERA_FAIL:
                Log.e(TAG, "Open Camera Fail. id:" + extra);
                break;
            case CAMERA_SWITCHED:
                if (extra != null) {
                    Log.i(TAG, "current camera id:" + (Integer) extra);
                }
                Log.i(TAG, "camera switched");
                final int currentCamId = (Integer) extra;
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  updateCameraSwitcherButtonText(currentCamId);
                    }
                });
                break;
            case TORCH_INFO:
                if (extra != null) {
                    final boolean isSupportedTorch = (Boolean) extra;
                    Log.i(TAG, "isSupportedTorch=" + isSupportedTorch);
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isSupportedTorch) {
                                mTorchBtn.setVisibility(View.VISIBLE);
                            } else {
                                mTorchBtn.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                break;
        }
    }

/*    protected void setFocusAreaIndicator() {
        if (mRotateLayout == null) {
            mRotateLayout = (RotateLayout) findViewById(R.id.focus_indicator_rotate_layout);
            mMediaStreamingManager.setFocusAreaIndicator(mRotateLayout,
                    mRotateLayout.findViewById(R.id.focus_indicator));
        }
    }*/

    private void setTorchEnabled(final boolean enabled) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String flashlight = enabled ? getString(R.string.flash_light_off) : getString(R.string.flash_light_on);
                mTorchBtn.setText(flashlight);
            }
        });
    }

    private void updateOrientationBtnText() {
        if (mIsEncOrientationPort) {
            mEncodingOrientationSwitcherBtn.setText("Land");
        } else {
            mEncodingOrientationSwitcherBtn.setText("Port");
        }
    }

    private void updateFBButtonText() {
        if (mFaceBeautyBtn != null) {
            mFaceBeautyBtn.setText(mIsNeedFB ? "FB Off" : "FB On");
        }
    }

    private void updateMuteButtonText() {
        if (mMuteButton != null) {
            mMuteButton.setText(mIsNeedMute ? "Unmute" : "Mute");
        }
    }

    private void updateCameraSwitcherButtonText(int camId) {
       /* if (mCameraSwitchBtn == null) {
            return;
        }
        if (camId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            mCameraSwitchBtn.setText("Back");
        } else {
            mCameraSwitchBtn.setText("Front");
        }*/
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "onSingleTapUp X:" + e.getX() + ",Y:" + e.getY());
        if (mIsReady) {
        //    setFocusAreaIndicator();
            mMediaStreamingManager.doSingleTapUp((int) e.getX(), (int) e.getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean onZoomValueChanged(float factor) {
        if (mIsReady && mMediaStreamingManager.isZoomSupported()) {
            mCurrentZoom = (int) (mMaxZoom * factor);
            mCurrentZoom = Math.min(mCurrentZoom, mMaxZoom);
            mCurrentZoom = Math.max(0, mCurrentZoom);
            Log.d(TAG, "zoom ongoing, scale: " + mCurrentZoom + ",factor:" + factor + ",maxZoom:" + mMaxZoom);
            mMediaStreamingManager.setZoomValue(mCurrentZoom);
        }
        return false;
    }

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

    @Override
    public boolean onPreviewFrame(byte[] bytes, int width, int height, int rotation, int fmt, long tsInNanoTime) {
        Log.i(TAG, "onPreviewFrame " + width + "x" + height + ",fmt:" + (fmt == PLFourCC.FOURCC_I420 ? "I420" : "NV21") + ",ts:" + tsInNanoTime + ",rotation:" + rotation);
        /**
         * When using custom beauty algorithm in sw encode mode, you should change the bytes array's values here
         * eg: byte[] beauties = readPixelsFromGPU();
         * System.arraycopy(beauties, 0, bytes, 0, bytes.length);
         */
        return true;
    }
}
