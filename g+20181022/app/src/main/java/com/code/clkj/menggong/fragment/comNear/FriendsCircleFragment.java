package com.code.clkj.menggong.fragment.comNear;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comDynamics.ActDynamicsActivity;
import com.code.clkj.menggong.activity.comMyHomePage.ActMyHomePage;
import com.code.clkj.menggong.activity.comOtherHomePage.ActOtherHomePage;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.fragment.comNear.friendsCircle.PreFriendsCircleFragmentI;
import com.code.clkj.menggong.fragment.comNear.friendsCircle.PreFriendsCircleFragmentImpl;
import com.code.clkj.menggong.fragment.comNear.friendsCircle.ViewFriendsCircleFragmentI;
import com.code.clkj.menggong.response.RespCommentList;
import com.code.clkj.menggong.response.RespGetDynamicLikes;
import com.code.clkj.menggong.response.RespGetynamicPage;
import com.code.clkj.menggong.response.RespSaveDynamicLike;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.code.clkj.menggong.util.TempDataUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempFragment.TempFragment;
import com.lf.tempcore.tempModule.previewComponments.activity.ActImagePreview;
import com.lf.tempcore.tempModule.previewComponments.model.ImageInfo;
import com.lf.tempcore.tempResponse.TempResponse;
import com.rey.material.app.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 朋友圈的Fragment
 * A simple {@link Fragment} subclass.
 */
public class FriendsCircleFragment extends TempFragment implements ViewFriendsCircleFragmentI {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;

    @Bind(R.id.Circle_friends)
    TempRecyclerView Circle_friends;
    @Bind(R.id.my_head_img)//我头像
            SimpleDraweeView my_head_img;
    @Bind(R.id.my_nick_name_tx)//我的昵称
            TextView my_nick_name_tx;
    @Bind(R.id.friends_circle_top_img)
    ImageView friends_circle_top_img;
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;

    private ListBaseAdapter<RespGetynamicPage.ResultBean.BeanBean.SourceBean> mAdapter;
    private PreFriendsCircleFragmentI mPreI;
    private int countSum = 0;
    private List<RespGetDynamicLikes.ResultBean.ListBean> people_name_list = new ArrayList<>();
//    private List<RespCommentList.ResultBean> reply_people = new ArrayList<>();

    private String mPageSize;
    private String fbId;
    private String mContent;
//    private ListBaseAdapter<RespGetDynamicLikes.ResultBean.ListBean> people_name_adapter;

    private BottomSheetDialog mBottomSheetDialog;

//    private List<RespCommentList.ResultBean.MemberUserReplyBean> by_reply_people = new ArrayList<>();


    public FriendsCircleFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_friends_circle, container, false);
    }

    @Override
    protected void setListeners() {
        mPreI = new PreFriendsCircleFragmentImpl(this);

    }

    @Override
    protected void bundleValues() {

        toolbar_title.setText("朋友圈");
        toolbar_title.setTextSize(18);
//        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_back = (ImageView) toolbar_top.findViewById(R.id.toolbar_back);
        toolbar_back.setImageResource(R.mipmap.back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_menu_tv.setText("发动态");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setTextColor(Color.parseColor("#ec447c"));
//        Circle_friends.setLayoutManager(new LinearLayoutManager(getContext()));
//        Circle_friends.setAdapter(new CircleFriendsAdapter());
//        Circle_friends.setAdapter();
        initRv();
    }

    @OnClick({R.id.toolbar_menu_tv, R.id.toolbar_top, R.id.my_head_img, R.id.toolbar_back})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu_tv:
                Intent intent = new Intent(getContext(), ActDynamicsActivity.class);
                startActivity(intent);
                break;
//            case R.id.toolbar_top:
//                getActivity().finish();
//                break;
            case R.id.my_head_img:
                if (TempLoginConfig.sf_getLoginState()) {
                    startActivity(new Intent(getActivity(), ActMyHomePage.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
                break;
            case R.id.toolbar_back:
                getActivity().finish();
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Circle_friends.forceToRefresh();
        Circle_friends.refreshing();
    }

    private void initRv() {
        Circle_friends.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListBaseAdapter<RespGetynamicPage.ResultBean.BeanBean.SourceBean>(getActivity()) {
            @Override
            public int getLayoutId() {
                return R.layout.circle_friends_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, final int position) {
                final RespGetynamicPage.ResultBean.BeanBean.SourceBean data = getDataList().get(position);
                final String dyId = data.getDyId();
//                mPreI.getDynamicLikes(dyId, position);
//                mPreI.getDynamicComment(dyId);
                TextView circle_friends_name = holder.getView(R.id.circle_friends_name);//好友昵称
                TextView circle_friends_title = holder.getView(R.id.circle_friends_title);//动态内容
                TextView circle_friends_time = holder.getView(R.id.circle_friends_time);//发布动态时间
                SimpleDraweeView friend_head_img = holder.getView(R.id.circle_friend_head_img);//头像
//                ImageView circle_friend_content_img = holder.getView(R.id.circle_friend_content_img);//内容图片
                ImageView hf = holder.getView(R.id.hf);
//                final ImageView circle_friends_like_img = holder.getView(R.id.circle_friends_like_img);//点赞图片
                LinearLayout my_friends = holder.getView(R.id.my_friends);
//                TextView circle_friends_like_preson_tx = holder.getView(R.id.circle_friends_like_preson_tx);//点赞人昵称
                final ImageView isLike_img = holder.getView(R.id.isLike_img);//是否点赞

                if (data.isUpdateLike()) {
//                    mPreI.saveDynamicLike(TempLoginConfig.sf_getSueid(),TempLoginConfig.sf_getPwd(),dyId,position);//点赞接口
                    mPreI.getDynamicLikes(dyId, position);//获取点赞人信息接口
                } else {
                    //获取点赞人
                    RecyclerView good_people_name = holder.getView(R.id.good_people_name);
                    good_people_name.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    ListBaseAdapter<RespGetDynamicLikes.ResultBean.ListBean> people_name_adapter = new ListBaseAdapter<RespGetDynamicLikes.ResultBean.ListBean>(getContext()) {
                        @Override
                        public int getLayoutId() {
                            return R.layout.good_people_name_item;
                        }

                        @Override
                        public void onBindItemHolder(SuperViewHolder holder, int position) {
                            RespGetDynamicLikes.ResultBean.ListBean resultbean = getDataList().get(position);
                            holder.setText(R.id.circle_friends_like_preson_tx, resultbean.getMuseNickName() + "、");
                        }
                    };
                    if (NullUtils.isNotEmpty(data.getRespGetDynamicLikes().getResult().getList())) {
                        people_name_adapter.setDataList(data.getRespGetDynamicLikes().getResult().getList());
                        my_friends.setVisibility(View.VISIBLE);
                    } else {
                        my_friends.setVisibility(View.GONE);
                    }
                    good_people_name.setAdapter(people_name_adapter);
                }

//                if (data.isUpdateComment()) {
//                    mPreI.getDynamicComment(dyId, position);
//                } else {
//
//                }
                String str = data.getDyImage();
                String[] splits = str.split(",");
                final List<String> my_image = new ArrayList<>();
                for (int i = 0; i < splits.length; i++) {
                    Collections.addAll(my_image, splits[i]);
                }
                final List<String> my_image_02 = new ArrayList<>();
                for (int i = 0; i < splits.length; i++) {
                    Collections.addAll(my_image_02, BaseUriConfig.makeImageUrl(splits[i]));
                }


                //动态图片
                RecyclerView circle_friend_img_rev = holder.getView(R.id.circle_friend_img_rev);
                circle_friend_img_rev.setLayoutManager(new GridLayoutManager(mContext, 3));
                ListBaseAdapter<String> imageAdapter = new ListBaseAdapter<String>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.myhome_image_item;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        String iamge_str = getDataList().get(position);
                        ImageView imageView = holder.getView(R.id.my_home_image);
                        Glide.with(mContext).load(BaseUriConfig.makeImageUrl(iamge_str)).into(imageView);
                    }
                };
                if (my_image != null)
                    imageAdapter.setDataList(my_image);
                circle_friend_img_rev.setAdapter(imageAdapter);

                imageAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
                    @Override
                    public void OnItemClick(View itemView, int position, String s) {
                        final List<String> imgs = new ArrayList<>();
                        for (int i = 0; i < my_image_02.size(); i++) {
                            imgs.add(my_image_02.get(i));
                        }
                        toBigImage(imgs, position);
                    }
                });


                //获取评论列表
                RecyclerView good_people_list = holder.getView(R.id.good_people_list);
                good_people_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                ListBaseAdapter<RespGetynamicPage.ResultBean.BeanBean.SourceBean.CommentsBean> resultBeanListBaseAdapter = new ListBaseAdapter<RespGetynamicPage.ResultBean.BeanBean.SourceBean.CommentsBean>(getContext()) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.good_people_list_item;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        RespGetynamicPage.ResultBean.BeanBean.SourceBean.CommentsBean mCommentsBean = getDataList().get(position);
                        String bycmContent = getDataList().get(position).getBycmContent();
                        String state = getDataList().get(position).getBysmType();
                        TextView Reply_Tv_01 = holder.getView(R.id.Reply_Tv_01);
                        TextView Reply_Tv_02 = holder.getView(R.id.Reply_Tv_02);
                        TextView Reply_Tv_03 = holder.getView(R.id.Reply_Tv_03);
                        TextView Reply_Tv_04 = holder.getView(R.id.Reply_Tv_04);
                        if (state.equals("1")) {
                            Reply_Tv_03.setVisibility(View.GONE);
                            Reply_Tv_04.setVisibility(View.GONE);
                            Reply_Tv_01.setText(mCommentsBean.getMuseNickName() + ":");
                            Reply_Tv_02.setText(bycmContent);
                        } else {
                            Reply_Tv_01.setText(mCommentsBean.getMuseNickNameReply());
                            Reply_Tv_02.setText("回复");
                            Reply_Tv_03.setText(mCommentsBean.getMuseNickName());
                            Reply_Tv_04.setText(bycmContent);
                        }
                    }
                };
                resultBeanListBaseAdapter.setDataList(data.getComments());
                good_people_list.setAdapter(resultBeanListBaseAdapter);


                fbId = data.getMemberUser().getMuseId();
                if (!TextUtils.isEmpty(data.getMemberUser().getMuseNickName())) {
                    circle_friends_name.setText(data.getMemberUser().getMuseNickName());
                }
                friend_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getMemberUser().getMuseImage()));
                friend_head_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherintent = new Intent(getActivity(), ActOtherHomePage.class);
                        otherintent.putExtra("frMuseId", data.getMemberUser().getMuseId());
                        startActivity(otherintent);
                    }
                });

//                friend_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getMemberUser().getMuseImage()));
                Log.i("头像", data.getMemberUser().getMuseImage());
                if (!TextUtils.isEmpty(data.getDyContent())) {
                    circle_friends_title.setText(data.getDyContent());
                }
                List<String> imgUrl = Arrays.asList(data.getDyImage().split(","));
//                Glide.with(getContext())
//                        .load(TempURIConfig.makeImageUrl(data.getDyImage()))
//                        .into(circle_friend_content_img);

                if (!TextUtils.isEmpty(data.getDyCreateTime())) {
                    DateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date curDate = formart.parse(data.getDyCreateTime());
                        Date endDate = new Date(System.currentTimeMillis());

                        long diff = endDate.getTime() - curDate.getTime();
                        long days = diff / (1000 * 60 * 60 * 24);
                        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);

                        if (hours > 24) {
                            circle_friends_time.setText(data.getDyCreateTime());
                        } else {
                            circle_friends_time.setText(hours + "小时前");
                        }

                    } catch (Exception e) {

                    }
                }
                if (!TextUtils.isEmpty(data.getIsLike()) && data.getIsLike().equals("0")) {
                    isLike_img.setImageResource(R.mipmap.item_act_personal_video_zan1);
                } else {
                    isLike_img.setImageResource(R.mipmap.item_act_personal_video_zan_select);
                }
                holder.getView(R.id.isLike_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPreI.saveDynamicLike(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), dyId);
                    }
                });

                holder.getView(R.id.hf).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(dyId, fbId);
                    }
                });
            }
        };

        Circle_friends.setAdapter(mAdapter);
        Circle_friends.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getDynamicPage(TempLoginConfig.sf_getSueid(), "", currentPage + "", pageSize + "");

            }
        });
        Circle_friends.forceToRefresh();
        Circle_friends.refreshing();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoadFinish() {
        Circle_friends.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        Circle_friends.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        if (Circle_friends != null) {
            Circle_friends.executeOnLoadDataSuccess();
        }
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getDynamicPageSuccess(RespGetynamicPage data) {
        //我的昵称和我的头像
        if (!TextUtils.isEmpty(data.getResult().getMember().getMuseNickName())) {
            my_nick_name_tx.setText(data.getResult().getMember().getMuseNickName());
        }
//        Glide.with(getContext())
//                .load(TempURIConfig.makeImageUrl(data.getResult().getMember().getMuseImage()))
//                .into(my_head_img);
        my_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage()));
        Glide.with(getContext()).load(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage())).into(friends_circle_top_img);//朋友圈头像背景图

        if (Circle_friends.isMore()) {
            mAdapter.addAll(data.getResult().getBean().getSource());
        } else {
            mAdapter.setDataList(data.getResult().getBean().getSource());
        }

        mPageSize = data.getResult().getBean().getPageSize();
        Circle_friends.setTotalCount(TempDataUtils.string2Int(mPageSize));
    }

    @Override
    public void saveDynamicLikeSuccess(RespSaveDynamicLike data) {
        if (data != null) {
            toast(data.getMsg());
        }
        Circle_friends.forceToRefresh();
        Circle_friends.refreshing();
    }

//    @Override
//    public void getDynamicLikesSuccess(RespGetDynamicLikes data, int position) {
//        mAdapter.getDataList().get(position).setRespGetDynamicLikes(data);
//        mAdapter.getDataList().get(position).setUpdateLike(false);
//        mAdapter.notifyItemChanged(position);
//    }

    @Override
    public void getDynamicLikesSuccess(RespGetDynamicLikes data, int position) {
        if (data != null) {
            mAdapter.getDataList().get(position).setRespGetDynamicLikes(data);
            mAdapter.getDataList().get(position).setUpdateLike(false);
            mAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void getDynamicCommentSuccsee(RespCommentList data, int position) {

    }

    @Override
    public void saveDynamicCommentSucceees(TempResponse data) {
        Circle_friends.refreshing();
//        mAdapter.getDataList().get(position).setUpdatacomments(false);
//        RespGetynamicPage.ResultBean.BeanBean.SourceBean.CommentsBean commentsBean
//                = new RespGetynamicPage.ResultBean.BeanBean.SourceBean.CommentsBean();
////        commentsBean.setBycmContent();
//        mAdapter.getDataList().get(position).getComments().add(commentsBean);
//        mAdapter.notifyItemChanged(position);
    }

    private void showDialog(final String dyId, final String rpMuseId) {
        mBottomSheetDialog = new BottomSheetDialog(getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.pop_answer, null, false);
        final EditText content = (EditText) view.findViewById(R.id.pop_answer_edit);
        TextView pop_answer_btn = (TextView) view.findViewById(R.id.pop_answer_btn);

        mBottomSheetDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
        pop_answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContent != null && mContent != "") {
                    mPreI.saveDynamicComment(dyId, mContent, "1", rpMuseId);
                    mBottomSheetDialog.dismiss();

                } else {
                    toast("请输入回复内容");
                }
            }
        });

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mContent = content.getText().toString();
            }
        });

    }
    /**
     * 查看大图
     */
    private void toBigImage(List<String> pics, int position) {
        ArrayList<ImageInfo> dataPri = new ArrayList<>();
        for (int i = 0; i < pics.size(); i++) {
            ImageInfo info = new ImageInfo();
            info.url = pics.get(i);
            info.width = 1280;
            info.height = 720;
            dataPri.add(info);
        }
        Intent intent_pre = new Intent(getContext(), ActImagePreview.class);
        intent_pre.putExtra("data", dataPri);
        intent_pre.putExtra("index", position);
        intent_pre.putExtra("type", -1);
        getContext().startActivity(intent_pre);
    }
//    @Override
//    public void saveDynamicLikeSuccess(RespSaveDynamicLike data,int position) {
//        mAdapter.getDataList().get(position).setRespSaveDynamicLike(data);
//        mAdapter.getDataList().get(position).setUpdateLike(false);
//        mAdapter.notifyItemChanged(position);
//
//    }
//
//    @Override
//    public void getDynamicLikesSuccess(RespGetDynamicLikes data, int positon) {
//        mAdapter.getDataList().get(positon).setRespGetDynamicLikes(data);
//        mAdapter.getDataList().get(positon).setUpdateLike(false);
//        mAdapter.notifyItemChanged(positon);
//
//    }
}
