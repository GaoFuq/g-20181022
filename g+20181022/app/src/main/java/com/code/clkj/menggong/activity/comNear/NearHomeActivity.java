package com.code.clkj.menggong.activity.comNear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.adapter.NearAdapter;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.fragment.comNear.FriendsCircleFragment;
import com.code.clkj.menggong.fragment.comNear.FriendsFragment;
import com.code.clkj.menggong.fragment.comNear.NearFragment;
import com.lf.tempcore.tempActivity.TempActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;

/**
 * 附近首页的界面
 */

public class NearHomeActivity extends TempActivity implements IUnReadMessageObserver {

    @Bind(R.id.mNear)
    FrameLayout mNear; //附近
    @Bind(R.id.mInfo)
    FrameLayout mInfo; //消息
    @Bind(R.id.mFriendsCircle)
    FrameLayout mFriendsCircle; //朋友圈
    @Bind(R.id.mFriends)
    FrameLayout mFriends;  //好友
    @Bind(R.id.mNearViewPager)
    ViewPager mNearViewPager;  //展示四个界面的Viewpager
    List<Fragment> fragments;  //存放四个界面

    @Bind(R.id.act_near_bar_1_iv)
    ImageView act_near_bar_1_iv;
    @Bind(R.id.act_near_bar_1_tv)
    TextView act_near_bar_1_tv;
    @Bind(R.id.act_near_bar_2_iv)
    ImageView act_near_bar_2_iv;
    @Bind(R.id.act_near_bar_2_tv)
    TextView act_near_bar_2_tv;
    @Bind(R.id.act_near_bar_3_iv)
    ImageView act_near_bar_3_iv;
    @Bind(R.id.act_near_bar_3_tv)
    TextView act_near_bar_3_tv;
    @Bind(R.id.act_near_bar_4_iv)
    ImageView act_near_bar_4_iv;
    @Bind(R.id.act_near_bar_4_tv)
    TextView act_near_bar_4_tv;
    @Bind(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;  //抽屉
    @Bind(R.id.right)
    RelativeLayout right;
    @Bind(R.id.zero_btn)
    TextView zero_btn;
    @Bind(R.id.zero_five_btn)
    TextView zero_five_btn;
    @Bind(R.id.one_btn)
    TextView one_btn;
    @Bind(R.id.mConfirm)
    TextView mConfirm;
    @Bind(R.id.mQuite)
    TextView mQuite;
    @Bind(R.id.start_age)
    EditText start_age;
    @Bind(R.id.end_age)
    EditText end_age;
    @Bind(R.id.all_btn)
    TextView all_btn;


    private NearFragment nearFragment;
//    private InfoFragment infoFragment;
    private FriendsCircleFragment friendsCircleFragment;
    private FriendsFragment friendsFragment;

    private Fragment ConversationFragmentEx = null;
    private Fragment mConversationList;

    private String mSex;


//    @Bind(R.id.parents_layout)
//    RelativeLayout parents_layout;

    private final int TAB_Near = 0;
    private final int TAB_MES = 1;
    private final int TAB_Circle_friends = 2;
    private final int TAB_friends = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_home_layout);
        ButterKnife.bind(this);

        mConversationList = initConversationList(); // 获取融云会话列表的对象
    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {

    }

    @Override
    protected void findViews() {
        initFragment();
//        initData();
        mNearViewPager.setAdapter(new NearAdapter(getSupportFragmentManager(), fragments));
        mNearViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case TAB_Near:

                        jumpNearFragment();
                        break;
                    case TAB_MES:

                        jumpInfoFragment();
                        break;
                    case TAB_Circle_friends:

                        jumpFriendsCircleFragment();
                        break;
                    case TAB_friends:

                        jumpFriendsFragment();
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //关闭DrawerLayout的手势滑动
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.mNear, R.id.mInfo, R.id.mFriendsCircle, R.id.mFriends, R.id.zero_btn, R.id.zero_five_btn, R.id.one_btn, R.id.mConfirm,R.id.all_btn,R.id.mQuite})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mNear:

                jumpNearFragment();
                mNearViewPager.setCurrentItem(0);
                break;
            case R.id.mInfo:

                jumpInfoFragment();
                mNearViewPager.setCurrentItem(1);
                break;
            case R.id.mFriendsCircle:

                jumpFriendsCircleFragment();
                mNearViewPager.setCurrentItem(2);
                break;
            case R.id.mFriends:

                jumpFriendsFragment();
                mNearViewPager.setCurrentItem(3);
                break;

            case R.id.zero_btn:
                zero_btn.setTextColor(Color.parseColor("#f5427f"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                mSex = zero_btn.getText().toString();
                break;
            case R.id.zero_five_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#f5427f"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                mSex = zero_five_btn.getText().toString();
                break;
            case R.id.one_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#f5427f"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                mSex = one_btn.getText().toString();
                break;
            case R.id.all_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#f5427f"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                mSex = all_btn.getText().toString();
                break;
            case R.id.mConfirm:
                nearFragment.setDrawData(mSex, start_age.getText().toString(), end_age.getText().toString());
                nearFragment.update();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.mQuite:
                mDrawerLayout.closeDrawers();
                break;
        }
    }

    /**
     * 初始化Fragment界面
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        nearFragment = new NearFragment();
        fragments.add(nearFragment);
//        infoFragment = new InfoFragment();
        fragments.add(mConversationList);
        friendsCircleFragment = new FriendsCircleFragment();
        fragments.add(friendsCircleFragment);
        friendsFragment = new FriendsFragment();
        fragments.add(friendsFragment);
    }

    public void jumpNearFragment() {
        act_near_bar_1_iv.setImageResource(R.mipmap.compass_1);
        act_near_bar_1_tv.setTextColor(Color.parseColor("#f70052"));
        act_near_bar_2_iv.setImageResource(R.mipmap.bubble);
        act_near_bar_2_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_3_iv.setImageResource(R.mipmap.coz);
        act_near_bar_3_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_4_iv.setImageResource(R.mipmap.coz_2);
        act_near_bar_4_tv.setTextColor(Color.parseColor("#262626"));
    }

    public void jumpInfoFragment() {
        act_near_bar_1_iv.setImageResource(R.mipmap.compass);
        act_near_bar_1_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_2_iv.setImageResource(R.mipmap.bubble_1);
        act_near_bar_2_tv.setTextColor(Color.parseColor("#f70052"));
        act_near_bar_3_iv.setImageResource(R.mipmap.coz);
        act_near_bar_3_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_4_iv.setImageResource(R.mipmap.coz_2);
        act_near_bar_4_tv.setTextColor(Color.parseColor("#262626"));
    }

    public void jumpFriendsCircleFragment() {
        act_near_bar_1_iv.setImageResource(R.mipmap.compass);
        act_near_bar_1_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_2_iv.setImageResource(R.mipmap.bubble);
        act_near_bar_2_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_3_iv.setImageResource(R.mipmap.coz_1);
        act_near_bar_3_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_4_iv.setImageResource(R.mipmap.coz_2);
        act_near_bar_4_tv.setTextColor(Color.parseColor("#262626"));
    }

    public void jumpFriendsFragment() {
        act_near_bar_1_iv.setImageResource(R.mipmap.compass);
        act_near_bar_1_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_2_iv.setImageResource(R.mipmap.bubble);
        act_near_bar_2_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_3_iv.setImageResource(R.mipmap.coz);
        act_near_bar_3_tv.setTextColor(Color.parseColor("#262626"));
        act_near_bar_4_iv.setImageResource(R.mipmap.coz_3);
        act_near_bar_4_tv.setTextColor(Color.parseColor("#f70052"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return super.onKeyDown(keyCode, event);
    }

    public void getDrawlayout() {

    }
    public void openDrawlayout() {
        mDrawerLayout.openDrawer(right);
        //开启手势滑动
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//        mDrawerLayout.getHeight()-getWindow()
    }

    private Fragment initConversationList() {
        // appendQueryParameter 对具体的会话列表做展示
        if (ConversationFragmentEx == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationList")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")     // 设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                    //.appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")    // 公共服务号
                    //.appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")// 公共服务号
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")  // 设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")      // 设置私聊会是否聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return ConversationFragmentEx;
        }
    }
    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {

            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");

            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {

                    if (conversation != null) {

                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
//                            startActivity(new Intent(getContext(), NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }

    protected void initData() {

        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(NearHomeActivity.this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
    }

    /**
     * 得到不落地 push 消息
     */
    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(NearHomeActivity.this, LoginActivity.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        //    LoadDialog.show(mContext);
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                // LoadDialog.dismiss(mContext);
                            }

                            @Override
                            public void onSuccess(String s) {
                                // LoadDialog.dismiss(mContext);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                // LoadDialog.dismiss(mContext);
                            }
                        });
                    }
                }
            }
        }
    }

    @Override
    public void onCountChanged(int i) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Constance.ISSWITHCFRAGMENT) {
            int mShowPosition = intent.getIntExtra("friends", 3);
            jumpFriendsFragment();
            mNearViewPager.setCurrentItem(3);
            Constance.ISSWITHCFRAGMENT = false;
        }
    }
}
