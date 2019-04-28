package com.code.clkj.menggong.activity.comLookLive;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAnchorStore.ActAnchorStoreActivity;
import com.code.clkj.menggong.activity.comMyWallet.ActPaymentMethodActivity1;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.LiveListAdapter;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.bean.GiftModel;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGiftList;
import com.code.clkj.menggong.response.RespGradRed;
import com.code.clkj.menggong.response.RespQueryRoom;
import com.code.clkj.menggong.response.RoomComment;
import com.code.clkj.menggong.util.Utils;
import com.code.clkj.menggong.widget.VideoPlayerBaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.PayAndShare.tempShare.TempShareVSCustomHelper;
import com.lf.tempcore.recyclerview.LRecyclerView;
import com.lf.tempcore.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;
import com.rey.material.app.BottomSheetDialog;
import com.umeng.socialize.media.UMImage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;


/**
 * This is a demo activity of PLVideoView
 */
public class PLVideoViewActivity extends VideoPlayerBaseActivity implements ViewActLookLiveDetailsI, View.OnClickListener {

    private static final String TAG = PLVideoViewActivity.class.getSimpleName();

    private static final int MESSAGE_ID_RECONNECTING = 0x01;

    //   private MediaController mMediaController;
    private PLVideoView mVideoView;
    private ImageView zhuBoShopImg;
    private ImageView mCancleImg;
    private Toast mToast = null;
    private String mVideoPath;
    private int mDisplayAspectRatio = PLVideoView.ASPECT_RATIO_FIT_PARENT;
    private boolean mIsActivityPaused = true;
    private View mLoadingView;
    private View mCoverView = null;
    private int mIsLiveStreaming = 1;
    private String roomId;
    private String flag;

    private SimpleDraweeView act_live_head_img;//观看直播人的房间头像
    private TextView nickname;//昵称
    private ImageView live_camera_change;//分享

    private SimpleDraweeView act_live_head_img_nickname;//观看直播人的头像

    private String museId;//直播所属人Id;
    private BottomSheetDialog pop_send_message;
    private ImageView send_message_img;//发送消息
    private TextView num_people;//观看人数
    private ImageView send_gift;//发送礼物
    private String musEpurse = "";//余额
    private ImageView qinghongbao;//抢红包

    private PreActLookLiveDetailsI mPreI;

    protected LRecyclerView mRecyclerView;


    private BottomSheetDialog mBoyOrGirl;
    private GiftPanelControl giftPanelControl;
    private ViewPager mViewpager;
    private LinearLayout mDotsLayout;
    private RecyclerView mLiwuRecyclerView;
    private String mGiftPrice = "";
    private String mGifTid = "";


    protected int mCurrentPage = 0;
    protected int totalPage = 50;
    protected final int REQUEST_COUNT = 10;
    private String minputText = "";

    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;

    private boolean isShowRed = false;//是否显示红包
    private String RedId;//红包Id


    protected ListBaseAdapter<RoomComment.ResultEntity> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;
//    @Bind(R.id.error_layout)
//    ErrorLayout mErrorLayout;

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
        //礼物接口
        mPreI.queryGiftList();
        mPreI.getMuseEpurse();

        mCancleImg = (ImageView) findViewById(R.id.camera_switch_editex);
        mVideoView = (PLVideoView) findViewById(R.id.VideoView);
        mCoverView = (ImageView) findViewById(R.id.CoverView);
        zhuBoShopImg = (ImageView) findViewById(R.id.look_live_zhubo_shop_img);
        send_message_img = (ImageView) findViewById(R.id.send_message_img);
        mRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view);
        live_camera_change = (ImageView) findViewById(R.id.live_camera_change);

        act_live_head_img = (SimpleDraweeView) findViewById(R.id.act_live_head_img);
        nickname = (TextView) findViewById(R.id.act_live_nickname);
        num_people = (TextView) findViewById(R.id.num_people);
        send_gift = (ImageView) findViewById(R.id.send_gift);
        qinghongbao = (ImageView) findViewById(R.id.qinghongbao);
        act_live_head_img_nickname = (SimpleDraweeView) findViewById(R.id.act_live_head_img_nickname);

        mVideoView.setCoverView(mCoverView);
        mLoadingView = findViewById(R.id.LoadingView);
        mVideoView.setBufferingIndicator(mLoadingView);
        mLoadingView.setVisibility(View.VISIBLE);


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
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);

//        mVideoView.setVideoPath(mVideoPath);

        // You can also use a custom `MediaController` widget
//        mMediaController = new MediaController(this, false, mIsLiveStreaming == 1);
       /* mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);*/
        mCancleImg.setOnClickListener(this);
        zhuBoShopImg.setOnClickListener(this);
        send_message_img.setOnClickListener(this);
        send_gift.setOnClickListener(this);
        qinghongbao.setOnClickListener(this);
        live_camera_change.setOnClickListener(this);

        mRecyclerView.setPullRefreshEnabled(false);//禁止下拉刷新
        if (mListAdapter == null) {
            mListAdapter = getListAdapter();
        }
        mRecyclerViewAdapter = new LRecyclerViewAdapter(mListAdapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        super.setContentView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
        mVideoView.start();
        webSocketConnect();
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
        closeWebsocket();
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
                mToast = Toast.makeText(PLVideoViewActivity.this, tips, Toast.LENGTH_SHORT);
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
            if (!Utils.isNetworkAvailable(PLVideoViewActivity.this)) {
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
        showProgressDialog(false);
    }

    @Override
    public void dismissPro() {
        dismissProgressDialog();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }


    @Override
    public void showConntectError() {
    }

    /**
     * 设置顶部正在加载的状态
     */
    protected void setSwipeRefreshLoadingState() {

    }

    //消息列表
    protected ListBaseAdapter<RoomComment.ResultEntity> getListAdapter() {
        return new LiveListAdapter(this);
    }

    List<RoomComment.ResultEntity> mData = new ArrayList<>();

    //访问服务器成功获取数据
    protected void executeOnLoadDataSuccess(List<RoomComment.ResultEntity> data) {
        mData = data;
        if (data == null) {
            data = new ArrayList<>();
        }
        if (mCurrentPage == 1) {
            mListAdapter.setDataList(data);
        } else {
            mListAdapter.addAll(data);
        }
        //头部布局添加
        //    hearparseList(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {

//            if (needShowEmptyNoData()) {
//                mErrorLayout.setNoDataContent(getNoDataTip());
//                mErrorLayout.setErrorType(ErrorLayout.NODATA);
//            }
        }
        mRecyclerView.scrollToPosition(mListAdapter.getItemCount());
        mListAdapter.notifyDataSetChanged();
        mRecyclerView.removeAllViews();
        minputText = "";
        mData.clear();
        //根据状态判断是否显示抢红包
        if (isShowRed) {
            qinghongbao.setVisibility(View.VISIBLE);
        } else {
            qinghongbao.setVisibility(View.GONE);
        }
    }

    //下拉刷新 下拉刷新被禁止
    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        //     requestData();

    }

    //"ws://192.168.0.150:8081/websocket"
    //websokect
    private static WebSocketConnection webSocketConnection;
    private static WebSocketOptions options = new WebSocketOptions();
    // private static boolean isExitApp = false;
    private static String websocketHost = BaseUriConfig.BASE_URLWEB; //websocket服务端的url,,,ws是协议,和http一样,我写的时候是用的我们公司的服务器所以这里不能贴出来

    private static boolean isClosed = true;

    public void webSocketConnect() {
        webSocketConnection = new WebSocketConnection();
        try {
            webSocketConnection.connect(websocketHost, new WebSocketHandler() {
                //websocket启动时候的回调
                @Override
                public void onOpen() {
                    Log.d(TAG, "open");
                    isClosed = false;
                }

                //websocket接收到消息后的回调
                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "payload = " + payload);
                    RoomComment.ResultEntity rest = new RoomComment.ResultEntity();
                    onRefreshView();
                    try {
                        JSONObject jsonObject = new JSONObject(payload);
                        rest.setMuseNickName(jsonObject.getString("nickname"));
                        rest.setRcomContent(jsonObject.getString("comment"));
                        rest.setRcomId(jsonObject.getString("roomId"));
                        Log.i("红包log1", jsonObject.getString("comment"));
                        if (jsonObject.getString("comment").equals("红包")) {
                            Log.i("红包log2", jsonObject.getString("comment"));
                            isShowRed = true;
                            RedId = jsonObject.getString("nickname");
                        }

                        mData.add(rest);
                        executeOnLoadDataSuccess(mData);
//                        addDanmaku(jsonObject.getString("comment"), true);
                    } catch (Exception e) {
                        Debug.error(e);
                        // TODO: handle exception
                    }
                }

                //    {"nickname":"才艺网游客","comment":"咯莫","roomId":"1"}
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

    //发送消息
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


    @Override
    public void queryRoomSuccess(RespQueryRoom data) {
        museId = data.getResult().getRoomMuseId();
        if (data.getResult().getRoomPlayUrl() != null) {
//            mVideoPath = BaseUriConfig.BASE_URL + data.getResult().getRoomPlayUrlFlv();

//            String ddd= data.getResult().getRoomPlayUrlFlv();
            mVideoPath = data.getResult().getRoomPlayUrl();
            Log.i("播放地址", mVideoPath);
            mVideoView.setVideoPath(mVideoPath);
            mVideoView.start();
        }

        if (data.getResult().getRoomImage() != null) {
            Glide.with(this)
                    .load(TempURIConfig.makeImageUrl(data.getResult().getRoomImage()))
                    .into((ImageView) mCoverView);

        }
        act_live_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getRoomImage()));
        nickname.setText(data.getResult().getMemberUser().getMuseNickName());
        num_people.setText(data.getResult().getRoomWatchNum() + "人在线观看");
        act_live_head_img_nickname.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMemberUser().getMuseImage()));
    }

    @Override
    public void saveConsumOrderSucceess(RespConsumOrder data) {
        Intent orderIntent = new Intent(this, ActPaymentMethodActivity1.class);
        orderIntent.putExtra("tradeNo", data.getResult().getMcorOrderNumber());
        orderIntent.putExtra("total_price_number", data.getResult().getMcorPrice());
        orderIntent.putExtra("state", "1");
        startActivity(orderIntent);
    }

    @Override
    public void queryGiftListSuccess(RespGiftList data) {

        giftListBeen = data.getResult();//来自网络礼物图片
    }

    @Override
    public void getMuseEpurseSuccess(RespCheckBalance data) {
        musEpurse = data.getResult().getEpurse();
    }

    @Override
    public void gradRedSuccess(RespGradRed data) {
        if (data.getResult()!= null) {
            showToast("已抢到红包" + data.getResult().getPriced() + "元");
        }else{
            showToast(data.getMsg());
        }
        qinghongbao.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_switch_editex:
                finish();
                break;
            case R.id.look_live_zhubo_shop_img:
                Intent intent = new Intent(PLVideoViewActivity.this, ActAnchorStoreActivity.class);
                intent.putExtra("museId", museId);
                startActivity(intent);
                break;
            case R.id.send_message_img:
                showMessage();
                break;
            case R.id.send_gift:
                initChooseBoyGirl();
                break;
            case R.id.qinghongbao:
                if (RedId != null) {
                    if (mPreI != null)
                        mPreI.gradRed(RedId);
                }
                break;
            case R.id.live_camera_change:
                TempShareVSCustomHelper mHelper = new TempShareVSCustomHelper(getTempContext(), BaseUriConfig.BASE_URL, "G+", "分享", new UMImage(this, R.mipmap.ic_launcher));
                mHelper.showShare();
                break;
        }
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

    //发送消息点击事件
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_send_message_ok://发送消息
                    String msg;
                    if (TempLoginConfig.sf_getLoginState()) {
                        msg = TempLoginConfig.sf_getNickName() + "-" + minputText + "-" + roomId;
                    } else {
                        msg = "游客" + "-" + minputText + "-" + roomId;
                    }
                    sendMsg(msg);
                    pop_send_message.dismiss();
                    break;
            }
        }
    };

    /**
     * 获取礼物数据
     *
     * @param
     * @return
     */
    List<RespGiftList.ResultBean> giftListBeen;
    private TextView toolbox_tv_recharge;
    private LinearLayout semmage;
    private String mGifname = "";
    private TextView toolbox_tv_num;

    private void initChooseBoyGirl() {
        mBoyOrGirl = new BottomSheetDialog(this);
        List<GiftModel> giftModels = toGiftModel(giftListBeen);//转化为发送礼物的集合
        //   GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
        View view = getLayoutInflater().inflate(R.layout.chat_tool_box, null);
        mLiwuRecyclerView = (RecyclerView) view.findViewById(R.id.rv_gift);
        mViewpager = (ViewPager) view.findViewById(R.id.toolbox_pagers_face);
        mDotsLayout = (LinearLayout) view.findViewById(R.id.face_dots_container);
        toolbox_tv_recharge = (TextView) view.findViewById(R.id.toolbox_tv_recharge);
        toolbox_tv_num = (TextView) view.findViewById(R.id.toolbox_tv_num);
        toolbox_tv_num.setText(musEpurse + "");
        semmage = (LinearLayout) view.findViewById(R.id.semmage);
        GiftPanelControl giftPanelControl = new GiftPanelControl(this, mViewpager, mLiwuRecyclerView, mDotsLayout);
        giftPanelControl.init(giftModels);//这里如果为null则加载本地礼物图片
        giftPanelControl.setGiftListener(new GiftPanelControl.GiftListener() {
            @Override
            public void getGiftInfo(String gifTid, String giftPic, String giftName, String giftPrice) {
                mGiftPrice = giftPrice;
                mGifTid = gifTid;
                mGifname = giftName;
            }
        });
        mBoyOrGirl.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();

        toolbox_tv_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (TempLoginConfig.sf_getLoginState()) {
//                    Intent intent = new Intent(PLVideoViewActivity.this, ActPaymentMethodActivity1.class);
//
//                    startActivity(intent);
//
//                } else {
//                    startActivity(new Intent(comActPlayerLive.this, ActLogin.class));
//                }

            }
        });
        semmage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TempLoginConfig.sf_getLoginState()) {
                    if (TextUtils.isEmpty(mGiftPrice)) {
                        Toast.makeText(getApplication(), "你还没选择礼物呢", Toast.LENGTH_SHORT).show();
                    } else {
                        //    Toast.makeText(getApplication(), mGiftPrice+"金豆"+mGifTid, Toast.LENGTH_SHORT).show();
                        if (mBoyOrGirl != null && mBoyOrGirl.isShowing()) {
                            mPreI.saveConsumOrder(mGiftPrice, roomId, mGifTid, "1", "1");
                            mBoyOrGirl.dismiss();
                            mGiftPrice = "";
                        }
                    }
                } else {
                    startActivity(new Intent(PLVideoViewActivity.this, LoginActivity.class));
                }

            }
        });

    }

    private List<GiftModel> toGiftModel(List<RespGiftList.ResultBean> datas) {
        List<GiftModel> giftModels = new ArrayList<>();
        GiftModel giftModel;
        for (int i = 0; i < datas.size(); i++) {
            RespGiftList.ResultBean giftListBean = datas.get(i);
            giftModel = new GiftModel(giftListBean.getRgbaId(), giftListBean.getRgbaName(), giftListBean.getRgbaIcon(), giftListBean.getRgbaImazamox());
            giftModels.add(giftModel);
        }
        return giftModels;
    }
}
