
package com.lf.PayAndShare.tempShare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lf.PayAndShare.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by longf on 2016/7/27.
 */

public class TempShareVSCustomHelper extends PopupWindow implements View.OnClickListener{
    private Context mContext;
    private String mShareUrl, mTitle, mShareContent;
    private UMImage mImage;
    private OnShareResultListener mOnShareResultListener;

/**
     * @param cxt must be Activity context

     */


    public TempShareVSCustomHelper(Context cxt,String shareUrl, String title, String shareContent, UMImage image){
        upDateShareContent(shareUrl,title,shareContent,image);
        mContext =cxt;
        mImage = image;
        mShareUrl = shareUrl;
        mTitle = title;
        mShareContent = shareContent;
        initView(cxt);
    }
    public void upDateShareContent(String shareUrl, String title, String shareContent, UMImage image){
        mImage = image;
        mShareUrl = shareUrl;
        mTitle = title;
        mShareContent = shareContent;
    }

    public OnShareResultListener getOnShareResultListener() {
        return mOnShareResultListener;
    }

    public void setOnShareResultListener(OnShareResultListener onShareResultListener) {
        mOnShareResultListener = onShareResultListener;
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.temp_custom_shar_board_layout_1, null);//temp_custom_shar_board_layout,
        rootView.findViewById(R.id.temp_share_wechat).setOnClickListener(this);
        rootView.findViewById(R.id.temp_share_circle).setOnClickListener(this);
        rootView.findViewById(R.id.temp_share_qq).setOnClickListener(this);
        rootView.findViewById(R.id.temp_share_qzone).setOnClickListener(this);
        rootView.findViewById(R.id.temp_share_close).setOnClickListener(this);
        rootView.findViewById(R.id.temp_share_out_view).setOnClickListener(this);
        setContentView(rootView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
    }
    public void showShare(){
     showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.temp_share_circle:
//                break;
//        }

        int i = v.getId();
        if (i == R.id.temp_share_wechat) {
            if (mOnShareResultListener!=null){
                mOnShareResultListener.sharePlatformClicked(SHARE_MEDIA.WEIXIN);
            }
            performShare(mContext,SHARE_MEDIA.WEIXIN,mShareUrl,mTitle,mShareContent,mImage,mOnShareResultListener);

        } else if (i == R.id.temp_share_circle) {
            if (mOnShareResultListener!=null){
                mOnShareResultListener.sharePlatformClicked(SHARE_MEDIA.WEIXIN_CIRCLE);
            }
            performShare(mContext,SHARE_MEDIA.WEIXIN_CIRCLE,mShareUrl,mTitle,mShareContent,mImage,mOnShareResultListener);

        } else if (i == R.id.temp_share_qq) {
            if (mOnShareResultListener!=null){
                mOnShareResultListener.sharePlatformClicked(SHARE_MEDIA.QQ);
            }
            performShare(mContext,SHARE_MEDIA.QQ,mShareUrl,mTitle,mShareContent,mImage,mOnShareResultListener);

        } else if (i == R.id.temp_share_qzone) {
            if (mOnShareResultListener!=null){
                mOnShareResultListener.sharePlatformClicked(SHARE_MEDIA.QZONE);
            }
            performShare(mContext,SHARE_MEDIA.QZONE,mShareUrl,mTitle,mShareContent,mImage,mOnShareResultListener);
         //   TempLoginConfig.sf_getSueid();
        }else if (i==R.id.temp_share_close||i==R.id.temp_share_out_view){
            dismiss();
        }
    }

    private void performShare(Context cxt, SHARE_MEDIA platform,String shareUrl, String title, String shareContent, UMImage media, OnShareResultListener mOnShareResultListener ){
        UMWeb web = new UMWeb(shareUrl);
        web.setTitle(title);
        web.setThumb(media);
        web.setDescription(shareContent);
        new ShareAction((Activity) cxt)
                .setPlatform(platform)
                .setCallback(mOnShareResultListener==null?umShareListener:mOnShareResultListener)
                .withText(title)
                .withMedia(web)
                // .setPlatform(share_media)
               /* .withText(shareContent)
                .withTitle(title)
                .withTargetUrl(shareUrl)
                .withMedia(media)*/
                .share();

       /* new ShareAction((Activity) cxt)
                .setPlatform(platform)
                .setCallback(mOnShareResultListener==null?umShareListener:mOnShareResultListener)
                .withText(shareContent)
                .withTargetUrl(shareUrl)
                .withMedia(media)
                .share();*/
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(mContext,platform + " 收藏成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext, platform + " 分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mContext,platform + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext,platform + " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };
    public interface OnShareResultListener extends UMShareListener {
        void sharePlatformClicked(SHARE_MEDIA platform);

    }
    public void onResult(int requestCode, int resultCode, Intent data){
        UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
    }
}

