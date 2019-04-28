package com.code.clkj.menggong.activity.comLookLive;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAnchorStore.ActAnchorStoreActivity;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGiftList;
import com.code.clkj.menggong.response.RespGradRed;
import com.code.clkj.menggong.response.RespQueryRoom;
import com.code.clkj.menggong.util.Utils;
import com.code.clkj.menggong.widget.VideoPlayerBaseActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;


/**
 * This is a demo activity of PLVideoView
 */
public class PLVideoViewActivity2 extends VideoPlayerBaseActivity implements ViewActLookLiveDetailsI, View.OnClickListener {

    private static final String TAG = PLVideoViewActivity.class.getSimpleName();

    private static final int MESSAGE_ID_RECONNECTING = 0x01;

 //   private MediaController mMediaController;
    private PLVideoView mVideoView;
    private ImageView zhuBoShopImg;
    private ImageView mCancleImg;
    private Toast mToast = null;
    private String mVideoPath ;
    private int mDisplayAspectRatio = PLVideoView.ASPECT_RATIO_FIT_PARENT;
    private boolean mIsActivityPaused = true;
    private View mLoadingView;
    private View mCoverView = null;
    private int mIsLiveStreaming = 1;
    private String roomId;
    private String flag;

    private String museId;//直播所属人Id;

    private PreActLookLiveDetailsI mPreI;
    private void setOptions(int codecType) {
        AVOptions options = new AVOptions();

        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_PROBESIZE, 128 * 1024);
        // Some optimization with buffering mechanism when be set to 1
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming);
        if (mIsLiveStreaming == 1) {
            options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        }

        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, codecType);

        // whether start play automatically after prepared, default value is 1
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);

        mVideoView.setAVOptions(options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl_video_view);
        roomId = getIntent().getStringExtra("roomId");


//        roomId6 = getIntent().getStringExtra("roomId6");
        mPreI = new PreActLookLiveDetailsImpl(this);
        mPreI.queryRoom(roomId, TempLoginConfig.sf_getSueid());
        mCancleImg = (ImageView) findViewById(R.id.camera_switch_editex);
        mVideoView = (PLVideoView) findViewById(R.id.VideoView);
        mCoverView = (ImageView) findViewById(R.id.CoverView);
        zhuBoShopImg = (ImageView) findViewById(R.id.look_live_zhubo_shop_img);

        mVideoView.setCoverView(mCoverView);
        mLoadingView = findViewById(R.id.LoadingView);
        mVideoView.setBufferingIndicator(mLoadingView);
        mLoadingView.setVisibility(View.VISIBLE);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);


//        mIsLiveStreaming = getIntent().getIntExtra("liveStreaming", 1);

        // 1 -> hw codec enable, 0 -> disable [recommended]
       // int codec = getIntent().getIntExtra("mediaCodec", AVOptions.MEDIA_CODEC_SW_DECODE);
        setOptions(1);

        // Set some listeners
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnSeekCompleteListener(mOnSeekCompleteListener);
        mVideoView.setOnErrorListener(mOnErrorListener);

//        mVideoView.setVideoPath(mVideoPath);

        // You can also use a custom `MediaController` widget
//        mMediaController = new MediaController(this, false, mIsLiveStreaming == 1);
       /* mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);*/
        mCancleImg.setOnClickListener(this);
        zhuBoShopImg.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mToast = null;
        mIsActivityPaused = true;
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

//    public void onClickSwitchScreen(View v) {
//        mDisplayAspectRatio = (mDisplayAspectRatio + 1) % 5;
//        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
//        switch (mVideoView.getDisplayAspectRatio()) {
//            case PLVideoView.ASPECT_RATIO_ORIGIN:
//                showToastTips("Origin mode");
//                break;
//            case PLVideoView.ASPECT_RATIO_FIT_PARENT:
//                showToastTips("Fit parent !");
//                break;
//            case PLVideoView.ASPECT_RATIO_PAVED_PARENT:
//                showToastTips("Paved parent !");
//                break;
//            case PLVideoView.ASPECT_RATIO_16_9:
//                showToastTips("16 : 9 !");
//                break;
//            case PLVideoView.ASPECT_RATIO_4_3:
//                showToastTips("4 : 3 !");
//                break;
//            default:
//                break;
//        }
//    }

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
            Log.d(TAG, "onInfo: " + what + ", " + extra);
            return false;
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer plMediaPlayer, int errorCode) {
            boolean isNeedReconnect = false;
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                    showToastTips("Invalid URL !");
                    break;
                case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                    showToastTips("404 resource not found !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                    showToastTips("Connection refused !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                    showToastTips("Connection timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                    showToastTips("Empty playlist !");
                    break;
                case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                    showToastTips("Stream disconnected !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    showToastTips("Network IO Error !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                    showToastTips("Unauthorized Error !");
                    break;
                case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                    showToastTips("Prepare timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                    showToastTips("Read frame timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_HW_DECODE_FAILURE:
                    setOptions(AVOptions.MEDIA_CODEC_SW_DECODE);
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                    break;
                default:
                    showToastTips("unknown error !");
                    break;
            }
            // Todo pls handle the error status here, reconnect or call finish()
            if (isNeedReconnect) {
                sendReconnectMessage();
            } else {
                finish();
            }
            // Return true means the error has been handled
            // If return false, then `onCompletion` will be called
            return true;
        }
    };

    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {
            Log.d(TAG, "Play Completed !");
            showToastTips("Play Completed !");
            finish();
        }
    };

    private PLMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new PLMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int precent) {
            Log.d(TAG, "onBufferingUpdate: " + precent);
        }
    };

    private PLMediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener = new PLMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(PLMediaPlayer plMediaPlayer) {
            Log.d(TAG, "onSeekComplete !");
        }
    };

    private PLMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int width, int height) {
            Log.d(TAG, "onVideoSizeChanged: " + width + "," + height);
        }
    };

    private void showToastTips(final String tips) {
        if (mIsActivityPaused) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(PLVideoViewActivity2.this, tips, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (mIsActivityPaused || !Utils.isLiveStreamingAvailable()) {
                finish();
                return;
            }
            if (!Utils.isNetworkAvailable(PLVideoViewActivity2.this)) {
                sendReconnectMessage();
                return;
            }

        }
    };

    private void sendReconnectMessage() {
        showToastTips("正在重连...");
        mLoadingView.setVisibility(View.VISIBLE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 500);
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void showPro() {

    }

    @Override
    public void dismissPro() {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void showConntectError() {

    }
    @Override
    public void queryRoomSuccess(RespQueryRoom data) {
         museId = data.getResult().getRoomMuseId();
        if (data.getResult().getRoomPlayUrl() != null) {
//            mVideoPath = BaseUriConfig.BASE_URL + data.getResult().getRoomPlayUrlFlv();
//            String ddd= data.getResult().getRoomPlayUrlFlv();
            mVideoPath = data.getResult().getRoomPlayUrl();
            Log.i("播放地址",data.getResult().getRoomPlayUrl());
            mVideoView.setVideoPath(mVideoPath);
            mVideoView.start();
        }

        if (data.getResult().getRoomImage() != null) {
            Glide.with(this)
                    .load(TempURIConfig.makeImageUrl(data.getResult().getRoomImage()))
                    .into((ImageView) mCoverView);

        }
    }

    @Override
    public void saveConsumOrderSucceess(RespConsumOrder data) {

    }

    @Override
    public void queryGiftListSuccess(RespGiftList data) {

    }

    @Override
    public void getMuseEpurseSuccess(RespCheckBalance data) {

    }

    @Override
    public void gradRedSuccess(RespGradRed data) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_switch_editex:
                finish();
                break;
            case R.id.look_live_zhubo_shop_img:
                Intent intent = new Intent(PLVideoViewActivity2.this, ActAnchorStoreActivity.class);
                intent.putExtra("museId", museId);
                startActivity(intent);
                break;
        }

    }
}