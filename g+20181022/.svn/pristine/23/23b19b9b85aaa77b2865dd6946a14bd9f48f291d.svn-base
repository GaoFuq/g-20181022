package com.code.clkj.menggong.activity.comHomepassword.comPayPassWord;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.LiveRoomAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.adapter.TuiguangAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.PreMyFriendsI;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.PreMyFriendsImpl;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.ViewMyFriendsI;
import com.code.clkj.menggong.response.RespMyFriendsList;
import com.code.clkj.menggong.response.RespMyuserList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 设置支付密码
 * Created by Administrator on 2017-12-12.
 */

public class ActtuijianUser extends TempActivity implements ViewMyFriendsI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.mNearUser)
    TempRecyclerView mNearUser;  //展示附近用户的列表

    private ListBaseAdapter<RespMyuserList.ResultEntity.SourceEntity> mAdapter;

    private PreMyFriendsI mPreI;
 //   private TuiguangAdapter mAdapter;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_set_user_layout);
    }

    @Override
    protected void findViews() {
        mPreI = new PreMyFriendsImpl(this);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("我推荐的用户");
        toolbar_title.setTextSize(18);





        mNearUser.setLayoutManager(new LinearLayoutManager(ActtuijianUser.this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListBaseAdapter<RespMyuserList.ResultEntity.SourceEntity>(ActtuijianUser.this) {
            @Override
            public int getLayoutId() {
                return R.layout.friends_item_two;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
               // io.rong.imlib.model.UserInfo userInfo;
                final RespMyuserList.ResultEntity.SourceEntity data = getDataList().get(position);
                SimpleDraweeView headImage = holder.getView(R.id.friends_hend_img);
                ImageView image_state = holder.getView(R.id.image_state);
                TextView title = holder.getView(R.id.title);
                TextView friends_item_sex_tx = holder.getView(R.id.friends_item_sex_tx);//性别
                TextView friends_item_address_tx = holder.getView(R.id.friends_item_address_tx);//地址


               // headImage.setImageURI(BaseUriConfig.makeImageUrl(data.getMuseImage()));
                if(!TextUtils.isEmpty(data.getMgprGetIntegral())){
                    friends_item_sex_tx.setText(data.getMgprGetIntegral());
                    friends_item_address_tx.setText("元");
                }
                else{
                    friends_item_address_tx.setText("未完成注册");
                }

                title.setText(data.getMsprPhone());
              /*  NickName = data.getMuseNickName();

                museId = data.getMuseId();*/
               /* if (!TextUtils.isEmpty(data.getRoomStatus()) && data.getRoomStatus().equals("0")) {
                    image_state.setVisibility(View.GONE);

                } else {
                    image_state.setVisibility(View.VISIBLE);
                }
                if (!TextUtils.isEmpty(data.getMuseNickName())) {
                    title.setText(data.getMuseNickName());
                }
                if (!TextUtils.isEmpty(data.getMuseSex())) {
                    friends_item_sex_tx.setText(data.getMuseSex());
                }
                if (!TextUtils.isEmpty(data.getAddress())) {
                    friends_item_address_tx.setText(data.getAddress());
                }
               */


//                userInfo = new UserInfo(TempLoginConfig.sf_getSueid(),data.getMuseNickName(), Uri.parse(BaseUriConfig.makeImageUrl(data.getMuseImage())) );
//                RongIM.getInstance().setCurrentUserInfo(userInfo);
            }
        };
     /*   mAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespMyFriendsList.ResultBean.SourceBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespMyFriendsList.ResultBean.SourceBean sourceBean) {
                if (!TextUtils.isEmpty(sourceBean.getMuseId())){
                    RongIM.getInstance().startPrivateChat(ActtuijianUser.this, sourceBean.getMuseId(), sourceBean.getMuseNickName());
                }
            }
        });*/

        mNearUser.setAdapter(mAdapter);
        mNearUser.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.mySpreadList(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), currentPage + "", pageSize + "");
            }
        });
        mNearUser.forceToRefresh();
        mNearUser.refreshing();





     /*   mAdapter = new TuiguangAdapter(ActtuijianUser.this);
        mNearUser.setLayoutManager(new LinearLayoutManager(ActtuijianUser.this));
        mNearUser.setAdapter(mAdapter);
        mNearUser.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                *//*if (mSex != null) {
                    if (mSex.equals("全部")||mSex.equals("其他")) {
                        mPreI.queryRoomList(mStart_age, mEnd_age, null, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                    } else {
                        mPreI.queryRoomList(mStart_age, mEnd_age, mSex, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                    }
                } else {
                    mPreI.queryRoomList(mStart_age, mEnd_age, null, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                }*//*
            }
        });*/
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    /*@OnClick(R.id.confirm_btn)*/
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
          /*  case R.id.confirm_btn:
              *//*  PassWord_01 = password_01.getText().toString();
                PassWord_02 = password_02.getText().toString();
                if (TextUtils.isEmpty(PassWord_01)){
                   showToast("请输入密码");
                }
                if (TextUtils.isEmpty(PassWord_02)){
                    showToast("请再次输入密码");
                }
                mView.setUserPayPwd(PassWord_01,PassWord_02);*//*
                break;*/
        }
    }

    @Override
    public void onLoadFinish() {
        mNearUser.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        mNearUser.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        if (mNearUser != null) {
            mNearUser.executeOnLoadDataSuccess();
        }
    }

    @Override
    public void toast(String message) {
        showToast(message);

    }

    /*@Override
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
    public void setUserPayPwdSuccee(TempResponse data) {
        showToast(data.getMsg());
        TempLoginConfig.sf_savePayState(true);
        finish();
    }*/

    @Override
    public void getMuseFriendSuccess(RespMyFriendsList data) {

    }

    @Override
    public void mySpreadListSuccess(RespMyuserList data) {
        if (mNearUser.isMore()){
            mAdapter.addAll(data.getResult().getSource());
        }else{
            mAdapter.setDataList(data.getResult().getSource());
        }

    }
}
