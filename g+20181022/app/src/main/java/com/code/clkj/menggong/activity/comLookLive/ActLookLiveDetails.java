package com.code.clkj.menggong.activity.comLookLive;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.controller.BaseVideoController;
import com.code.clkj.menggong.controller.MediaController;
import com.code.clkj.menggong.player.IjkVideoView;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGiftList;
import com.code.clkj.menggong.response.RespGradRed;
import com.code.clkj.menggong.response.RespQueryRoom;
import com.lf.tempcore.recyclerview.LRecyclerView;
import com.lf.tempcore.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;





/**
 * Created by chenlingkeji on 2017/5/31.
 * for chenlingkeji Company
 * link for more detail www.lingkj.com
 */

public class ActLookLiveDetails extends TempActivity implements EasyPermissions.PermissionCallbacks,
        MediaController.OnHiddenListener, ViewActLookLiveDetailsI,IjkVideoView.OnRecyclerViewItemClickListener {


    private BaseVideoController mBaseVideoController;
    private MediaController mMediaController;
    @Bind(R.id.player)
    IjkVideoView ijkVideoView;

    private PreActLookLiveDetailsI mPreI;





    private String mVideoPath = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

    private String type = "";

    private String roomId = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_look_live_layout);

        ijkVideoView.setOnItemClickListener(this);
        roomId = getIntent().getStringExtra("roomid");
        type = getIntent().getStringExtra("type");
//        mgtyId=roomId;
    }

    private LRecyclerViewAdapter mPoprcv1LRecyclerViewAdapter = null;

    private LRecyclerView act_video_detail_rcv_1;
   private RecyclerView act_video_detail_rcv_2;
    private LinearLayoutManager layoutManager;

    @Override
    protected void findViews() {
        mPreI = new PreActLookLiveDetailsImpl(this);
        roomId = getIntent().getStringExtra("roomId");
        mPreI.queryRoom(roomId, TempLoginConfig.sf_getSueid());


        //  finish();
        //禁止下拉刷新
        //  mRecyclerView.setPullRefreshEnabled(false);
        ijkVideoView
                .autoRotate();



    }







    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }


    private String minputText = "";


    @Override
    @OnClick({
            })
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        ijkVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ijkVideoView.start();
        ijkVideoView.stopFloatWindow();
        //ScreenRotateUtil.getInstance(this).start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ijkVideoView != null) {
            ijkVideoView.release();
            //mVideoView.stopPlayback();
        }
        //  ScreenRotateUtil.getInstance(this).stop();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onHidden() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void requestShare(final Context mContext) {
//        UMImage imageurl = new UMImage(this, image);
//     //   imageurl.setThumb(image);
//        TempShareVSCustomHelper mHelper = new TempShareVSCustomHelper(mContext, BaseUriConfig.BASE_URL_SHARE + mShareUrl + roomId, title, mgoodescription, imageurl);
//        mHelper.showShare(this);
    }

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
//        requestData();
    }

    /**
     * 设置顶部正在加载的状态
     */
    protected void setSwipeRefreshLoadingState() {
//        TLog.log("setSwipeRefreshLoadingState ");
    }

    protected boolean isRequestInProcess = false;
    /**
     * 每一页展示多少条数据
     */
    protected int mCurrentPage = 0;
    protected int totalPage = 0;
    protected boolean mIsStart = false;


    protected void executeOnLoadDataError(String error) {
        executeOnLoadFinish();
//        if (mCurrentPage == 1) {
//            mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
//        } else {
//            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
//            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
//            //find by TopJohn
//            mCurrentPage--;
//            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
//            mListAdapter.notifyDataSetChanged();
//        }
    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
//        if (null != mRecyclerView) {
//            mRecyclerView.refreshComplete(TempApplication.REQUEST_COUNT);
//        }

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
    public void onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed();
//            if (isPingmu == false) {
//                if (TextUtils.isEmpty(type)) {
//                    finish();
//                } else {
//                    Intent i = new Intent();
//                    i.putExtra("roomid", shoucang);
//                    setResult(Activity.RESULT_OK, i);
//                    finish();
//                }
//            }
        }
    }

    @Override
    public void onItemClick(String url, int position) {



    }

    @Override
    public void queryRoomSuccess(RespQueryRoom data) {
        if (data.getResult().getRoomPlayUrl() != null) {
            String url = BaseUriConfig.BASE_BOVIDEO_URL + data.getResult().getRoomPlayUrl();
            mBaseVideoController.setMediaPlayer(ijkVideoView);
            ijkVideoView.setVideoController(mBaseVideoController);
            data.getResult().setRoomPlayUrl(url);
        }



//        data.setMgooAppVideo(BaseUriConfig.BASE_BOVIDEO_URL + mDataAdapter.getDataList().get(i).getVideoApp());
//        quanbu.setMgooId(mDataAdapter.getDataList().get(i).getMgooId());
//        quanbu.setVideoId(mDataAdapter.getDataList().get(i).getVideoId());
//        quanbu.setTitle(data.getResult().getMallGood().getMgooTitle());
//
//        videos.add(quanbu);


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

//    public class AdController extends BaseVideoController implements View.OnClickListener {
//        protected TextView adTime, adDetail;
//        protected ImageView back, volume, fullScreen, playButton;
//        protected  int time;
//        protected String shyipinLinkUrl="";
//        protected FrameLayout fremeng_guyangao;
//        private  Context mContext;
//        public AdController(@NonNull Context context) {
//            super(context);
//            this.mContext=context;
//        }
//
//        public AdController(@NonNull Context context, @Nullable AttributeSet attrs) {
//            super(context, attrs);
//            this.mContext=context;
//        }
//
//        @Override
//        protected int getLayoutId() {
////            return R.layout.layout_ad_controller;
//            return 0;
//        }
//
//        @Override
//        protected void initView() {
//
//
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//
////        private void doMute() {
////            mediaPlayer.setMute();
////            if (mediaPlayer.isMute()) {
////                volume.setImageResource(R.mipmap.ic_action_volume_off);
////            } else {
////                volume.setImageResource(R.mipmap.ic_action_volume_up);
////
////            }
////        }
//
////        @Override
////        public void setPlayState(int playState) {
////            super.setPlayState(playState);
////            switch (playState) {
////                case IjkVideoView.STATE_PLAYING:
////                    post(mShowProgress);
////                    playButton.setSelected(true);
////                    break;
////                case IjkVideoView.STATE_PAUSED:
////                    playButton.setSelected(false);
////                    break;
////            }
////        }
//
////        @Override
////        public void setPlayerState(int playerState) {
////            super.setPlayerState(playerState);
////            switch (playerState) {
////                case IjkVideoView.PLAYER_NORMAL:
////                    back.setVisibility(GONE);
////                    break;
////                case IjkVideoView.PLAYER_FULL_SCREEN:
////                    back.setVisibility(VISIBLE);
////                    break;
////            }
////        }
//
////        @Override
////        protected int setProgress() {
////            if (mediaPlayer == null) {
////                return 0;
////            }
////            int position = mediaPlayer.getCurrentPosition();
////            int duration = mediaPlayer.getDuration();
////            int lest=position/1000;
////            if (adTime != null) {
////                if(lest>=time){
////                    adTime.setText(String.format("广告"+"%s 秒| 跳过", (duration - position) / 1000)+"广告");
////                    adTime.setEnabled(true);
////                }
////                else{
////                    adTime.setText(String.format("广告"+"%s "+"秒", (duration - position) / 1000));
////                    adTime.setEnabled(false);
////                }
////
////            }
////            //    adDetail.setText(position/1000+"");
////      /*  if((duration - position) / 1000)>){
////
////        }*/
////            return position;
////        }
//
////        @Override
////        public boolean onTouchEvent(MotionEvent event) {
//////            switch (event.getAction()) {
//////                case MotionEvent.ACTION_DOWN:
//////                    if (listener != null) listener.onAdClick();
//////                    break;
//////            }
//////            return false;
////        }
//    }
}
