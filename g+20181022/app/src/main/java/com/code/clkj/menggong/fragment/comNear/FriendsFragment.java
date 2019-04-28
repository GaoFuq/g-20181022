package com.code.clkj.menggong.fragment.comNear;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.PreMyFriendsI;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.PreMyFriendsImpl;
import com.code.clkj.menggong.fragment.comNear.comMyFriends.ViewMyFriendsI;
import com.code.clkj.menggong.response.RespMyFriendsList;
import com.code.clkj.menggong.response.RespMyuserList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempFragment.TempFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 好友的Fragment
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends TempFragment implements ViewMyFriendsI,RongIM.UserInfoProvider {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.Friends_recyclerview)
    TempRecyclerView Friends_recyclerview;
    private PreMyFriendsI mPreI;
    private String museId;
    private String NickName;


    private ListBaseAdapter<RespMyFriendsList.ResultBean.SourceBean> mAdapter;
    private List<RespMyFriendsList.ResultBean.SourceBean> friends_list = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUserInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        intUsertokeType();
    }

    @Override
    protected void bundleValues() {
        mPreI = new PreMyFriendsImpl(this);
        toolbar_title.setText("好友");
        toolbar_title.setTextSize(18);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));
        initRv();
    }

    private void initRv() {

        Friends_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListBaseAdapter<RespMyFriendsList.ResultBean.SourceBean>(getActivity()) {
            @Override
            public int getLayoutId() {
                return R.layout.friends_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                io.rong.imlib.model.UserInfo userInfo;
                final RespMyFriendsList.ResultBean.SourceBean data = getDataList().get(position);
                SimpleDraweeView headImage = holder.getView(R.id.friends_hend_img);
                ImageView image_state = holder.getView(R.id.image_state);
                TextView title = holder.getView(R.id.title);
                TextView friends_item_sex_tx = holder.getView(R.id.friends_item_sex_tx);//性别
                TextView friends_item_address_tx = holder.getView(R.id.friends_item_address_tx);//地址
                 NickName = data.getMuseNickName();

                museId = data.getMuseId();
                if (!TextUtils.isEmpty(data.getRoomStatus()) && data.getRoomStatus().equals("0")) {
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
                headImage.setImageURI(BaseUriConfig.makeImageUrl(data.getMuseImage()));
//                userInfo = new UserInfo(TempLoginConfig.sf_getSueid(),data.getMuseNickName(), Uri.parse(BaseUriConfig.makeImageUrl(data.getMuseImage())) );
//                RongIM.getInstance().setCurrentUserInfo(userInfo);
            }
        };
        mAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespMyFriendsList.ResultBean.SourceBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespMyFriendsList.ResultBean.SourceBean sourceBean) {
               if (!TextUtils.isEmpty(sourceBean.getMuseId())){
                   RongIM.getInstance().startPrivateChat(getActivity(), sourceBean.getMuseId(), sourceBean.getMuseNickName());
               }
            }
        });

        Friends_recyclerview.setAdapter(mAdapter);
        Friends_recyclerview.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseFriend(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), currentPage + "", pageSize + "");

            }
        });
        Friends_recyclerview.forceToRefresh();
        Friends_recyclerview.refreshing();
    }

    @OnClick(R.id.toolbar_top)
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_top:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoadFinish() {
        Friends_recyclerview.executeOnLoadFinish();

    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        Friends_recyclerview.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        if (Friends_recyclerview != null) {
            Friends_recyclerview.executeOnLoadDataSuccess();
        }
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getMuseFriendSuccess(RespMyFriendsList data) {
        if (Friends_recyclerview.isMore()){
            mAdapter.addAll(data.getResult().getSource());
        }else{
            mAdapter.setDataList(data.getResult().getSource());
        }
        friends_list = data.getResult().getSource();
    }

    @Override
    public void mySpreadListSuccess(RespMyuserList data) {

    }

    private void initUserInfo() {
        RongIM.setUserInfoProvider(this, true);
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (RespMyFriendsList.ResultBean.SourceBean i : friends_list) {
            if (i.getMuseId().equals(s)) {
                return new UserInfo(i.getMuseId(), i.getMuseNickName(), Uri.parse(i.getMuseImage()));
            }
        }
        return null;
    }

//    //获取好友的token
//    private void intUsertokeType() {
//        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
//                        .getMuseToken(museId),
//                new TempRemoteApiFactory.OnCallBack<RespGetMuseToken>() {
//                    @Override
//                    public void onSucceed(RespGetMuseToken data) {
//                        if (data.getFlag() == 1) {
//                            connect(data.getResult().getMuseCommentToken());
//                            Log.i("myToken",data.getResult().getMuseCommentToken());
//                        } else {
//
//                        }
//                    }
//                    @Override
//                    public void onCompleted() {
//                        //  if (mViewActLoginI!=null) mViewActLoginI.dismissPro();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //  if (mViewActLoginI!=null) mViewActLoginI.dismissPro();
//                    }
//                });
//    }
//    /**
//     * 建立与融云服务器的连接
//     *
//     * @param token
//     */
//    private void connect(final String token) {
//
////        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {
//
//        /**
//         * IMKit SDK调用第二步,建立与服务器的连接
//         */
//        RongIM.connect(token, new RongIMClient.ConnectCallback() {
//
//            /**
//             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
//             */
//            @Override
//            public void onTokenIncorrect() {
//
//                Debug.error("ActHome", "-- 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 TokenonTokenIncorrect");
//            }
//
//            /**
//             * 连接融云成功
//             * @param userid 当前 token
//             */
//            @Override
//            public void onSuccess(String userid) {
//                Debug.error("ActHome", "--连接融云成功onSuccess--" + userid);
//                Debug.error("ActHome", "--连接融云成功token--" + token);
//                SealUserInfoManager.getInstance().openDB();
//                Log.i("RongYun","用户连接融云成功");
//
//            }
//
//            /**
//             * 连接融云失败
//             * @param errorCode 错误码，可到官网 查看错误码对应的注释
//             */
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Debug.error("ActHome", "--连接融云失败onError" + errorCode);
//            }
//        });
////        }
//    }
}
