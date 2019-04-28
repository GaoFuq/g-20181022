package com.code.clkj.menggong.activity.comCameraStreaming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.code.clkj.menggong.R;
import com.lf.tempcore.comApp.AppManager;
import com.lf.tempcore.tempViews.TempCustomProgressDialog;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by chenlingkeji on 2017/3/14.
 */

public abstract class JFYActivity extends Activity {

    private boolean keyboardAutoHide;
    private CompositeSubscription mCompositeSubscription;
    private AlertDialog.Builder mCustomDialogBuilder;

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    //  private WeakReference<TempActivity> mContext = new WeakReference<>(TempActivity.this);
    /**
     * 等待加载对话框
     */
    private TempCustomProgressDialog mProgressDailog;
 /*   public TempActivity getTempContext() {
        if (mContext.get() == null) {
            mContext = new WeakReference<>(TempActivity.this);
        }
        return mContext.get();
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Width = dm.widthPixels;
        Height = dm.heightPixels;
        //     Debug.info("Width="+Width+"||Height="+Height);
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        //returnBack();
        //TempApplication.getInstance().addActivity(this);
    }

    public void performBackClicked() {
        onBackPressed();
    }

    /**
     * 显示等待加载对话框
     * 默认不允许触摸隐藏
     */
    private void showProgressDialog() {
        if (mProgressDailog != null) {
            mProgressDailog.show();
        }
    }

    /**
     * 显示等待加载对话框
     *
     * @param shouldCanceledOnTouchOutside 允许触摸隐藏
     */
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

    //    <API> Class<API> createAction(Class<API> clazz){
//        return clazz;
//    }
//   protected   <API> API getAction(Class<API> clazz){
//
//       return RemoteApiFactory.createRemoteApi(clazz);
//   }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDailog != null) {
            mProgressDailog.dismiss();
            mProgressDailog = null;
        }
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivityd(this);
    }

    protected void showConnectedFaildToast() {
        showToast("连接失败,请稍后重试！");
    }

    protected void showToast(String msg) {
      /*  if (mContext.get()!=null){*/
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
       /* }*/

    }


    /**
     * 导航返回上一页
     */
   /* private void returnBack() {
//app_tool_bar_layout
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        if (toolbarTop != null) {
            toolbarTop.setTitle("");
            toolbarTop.setBackgroundColor(getResources().getColor(R.color.white));
            ImageView backIv = (ImageView) toolbarTop.findViewById(toolbar_back);

            if (backIv != null) {
                backIv.setImageResource(R.mipmap.top_bar_back_icon);
                backIv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

            TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
            if (mTitle != null) {
                mTitle.setText(getResources().getString(R.string.app_name));
                mTitle.setTextColor(getResources().getColor(R.color.black));
            }

            setSupportActionBar(toolbarTop);
        }

    }
*/
    public boolean isKeyboardAutoHide() {
        return keyboardAutoHide;
    }

    /**
     * 让键盘自动隐藏
     *
     * @param keyboardAutoHide
     */
    public void setKeyboardAutoHide(boolean keyboardAutoHide) {
        this.keyboardAutoHide = keyboardAutoHide;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
//          Log.d("lf", "dianji");
        if (ev.getAction() == MotionEvent.ACTION_DOWN && keyboardAutoHide) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 屏幕宽度
    private float Width;
    // 屏幕高度
    private float Height;

    public float getWidth() {
        return Width;
    }

    public void setWidth(float width) {
        Width = width;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }


    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private AlertDialog mAlertDialog;
/*
    *//**
     * @param title            弹出框标题
     * @param mess             弹出框信息 如果为空 不显示
     * @param onReturnListener 弹出框 如果为空 默认点击确定消失
     */
    public void showSuccessDialog(String title, String mess, final OnReturnListener onReturnListener) {
        View v = getLayoutInflater().inflate(R.layout.pop_success, null);
        TextView tv = (TextView) v.findViewById(R.id.pop_time);
      /*  TextView messtv = (TextView) v.findViewById(R.id.pop_success_text_detail);*/
        if (title != null) {
            tv.setText("直播时长:  "+title);
         /*   messtv.setVisibility(View.VISIBLE);
            messtv.setText(mess);*/
        } else {
        //    messtv.setVisibility(View.GONE);
        }
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.setCancelable(false);
    //    tv.setText(title);
        v.findViewById(R.id.act_login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onReturnListener != null) {
                    onReturnListener.onReturnListener(mAlertDialog);
                }else {
                    mAlertDialog.dismiss();
                }
                mAlertDialog.dismiss();
            }
        });
        mAlertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(v);
    }
    public interface OnReturnListener {
        void onReturnListener(AlertDialog mAlertDialog);
    }

}
