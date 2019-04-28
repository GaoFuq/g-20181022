package com.code.clkj.menggong.activity.comOtherHomePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespOtherHomePage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempModule.previewComponments.activity.ActImagePreview;
import com.lf.tempcore.tempModule.previewComponments.model.ImageInfo;
import com.lf.tempcore.tempResponse.TempResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * Created by clkj on 2018/1/5.
 */

/**
 * 别人的朋友圈
 */

public class ActOtherHomePage extends TempActivity implements ViewOtherHomePageI {
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
    private Context mContext;

    private String nc;
    private String frMuseId;
    private ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean> mAdapter;

    private PreOtherHomePageI mPreI;

    private String otherNickName;
    private String str;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.other_friends_circle);
        frMuseId = getIntent().getStringExtra("frMuseId");
    }

    @Override
    protected void findViews() {
        mPreI = new PreOtherHomePageImpl(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        initRcy();

        toolbar_title.setTextSize(18);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_menu_tv.setText("关注");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setTextColor(Color.parseColor("#ec447c"));

    }

    @Override
    @OnClick({R.id.siliao, R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.siliao:
                RongIM.getInstance().startPrivateChat(this, frMuseId, nc);
                break;
            case R.id.toolbar_menu_tv:
                mPreI.saveMuseFollow(frMuseId);
                break;
        }
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
        Circle_friends.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
    showToast(message);
    }

    @Override
    public void getOtherDynamicPageSucceess(RespOtherHomePage data) {
        if (data != null) {
            my_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage()));
            my_nick_name_tx.setText(data.getResult().getMember().getMuseNickName());
            if (Circle_friends.isMore()) {
                mAdapter.addAll(data.getResult().getBean().getSource());
            } else {
                mAdapter.setDataList(data.getResult().getBean().getSource());
            }
            String otherNickName = data.getResult().getMember().getMuseNickName();
            toolbar_title.setText(otherNickName);
            nc = otherNickName;
            Glide.with(this).load(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage())).into(friends_circle_top_img);//头像背景大图
        }
    }

    @Override
    public void saveMuseFollowSuccess(TempResponse data) {
        showToast(data.getMsg());
        if (data.getMsg().equals("添加关注成功")){
            toolbar_menu_tv.setText("取消关注");
        }else{
            toolbar_menu_tv.setText("关注");
        }
    }

    private void initRcy() {
        Circle_friends.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.circle_friends_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                final RespOtherHomePage.ResultBean.BeanBean.SourceBean resultbean = getDataList().get(position);

                List<RespOtherHomePage.ResultBean.BeanBean.SourceBean.LikesBean> mLikesBeanList = resultbean.getLikes();//回复人
                List<RespOtherHomePage.ResultBean.BeanBean.SourceBean.CommentsBean> mCommentsBeanList = resultbean.getComments();//评论

                TextView circle_friends_name = holder.getView(R.id.circle_friends_name);//好友昵称
                TextView circle_friends_title = holder.getView(R.id.circle_friends_title);//动态内容
                TextView circle_friends_time = holder.getView(R.id.circle_friends_time);//发布动态时间
                SimpleDraweeView friend_head_img = holder.getView(R.id.circle_friend_head_img);//头像
//                ImageView circle_friend_content_img = holder.getView(R.id.circle_friend_content_img);//内容图片

                circle_friends_name.setText(resultbean.getMemberUser().getMuseNickName());
                friend_head_img.setImageURI(BaseUriConfig.makeImageUrl(resultbean.getMemberUser().getMuseImage()));
                circle_friends_title.setText(resultbean.getDyContent());

//                Glide.with(mContext).load(BaseUriConfig.makeImageUrl(resultbean.getDyImage())).into(circle_friend_content_img);

                RecyclerView circle_friend_img_rev = holder.getView(R.id.circle_friend_img_rev);//动态图
                RecyclerView good_people_name = holder.getView(R.id.good_people_name);//点赞人
                RecyclerView good_people_list = holder.getView(R.id.good_people_list);//评论内容
                LinearLayout my_friends = holder.getView(R.id.my_friends);

                DateFormat formart= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try{
                    Date curDate =formart.parse(resultbean.getDyCreateTime());

                    Date endDate = new Date(System.currentTimeMillis());

                    long diff = endDate.getTime() - curDate.getTime();

                    long days = diff/ (1000 * 60* 60 * 24);

                    long hours =(diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);

                    if (hours>24){
                        circle_friends_time.setText(resultbean.getDyCreateTime());
                    }else{
                        circle_friends_time.setText(hours+"小时前");
                    }

                }catch (Exception e){

                }

                str = resultbean.getDyImage();

                if (NullUtils.isNotEmpty(str)) {
                    String[] splits = str.split(",");
                    List<String> my_image = new ArrayList<>();
                    for (int i = 0; i < splits.length; i++) {
                        Collections.addAll(my_image, splits[i]);
                    }

                    final List<String> my_image_02 = new ArrayList<>();
                    for (int i = 0; i < splits.length; i++) {
                        Collections.addAll(my_image_02, BaseUriConfig.makeImageUrl(splits[i]));
                    }

                    //动态图片
                    circle_friend_img_rev.setLayoutManager(new GridLayoutManager(mContext, 3));
                    ListBaseAdapter<String> mImageAdapter = new ListBaseAdapter<String>(mContext) {
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
                    if (my_image != null && my_image.size() > 0) {
                        mImageAdapter.setDataList(my_image);
                    } else {
                        circle_friend_img_rev.setVisibility(View.GONE);
                    }
                    circle_friend_img_rev.setAdapter(mImageAdapter);

                    mImageAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
                        @Override
                        public void OnItemClick(View itemView, int position, String s) {
                            final List<String> imgs = new ArrayList<>();
                            for (int i = 0; i < my_image_02.size(); i++) {
                                imgs.add(my_image_02.get(i));
                            }
                            toBigImage(imgs, position);
                        }
                    });
                }

                //回复人
                good_people_name.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean.LikesBean> mLikesBeanAdapter = new ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean.LikesBean>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.people_name;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        RespOtherHomePage.ResultBean.BeanBean.SourceBean.LikesBean peopleresultbean = getDataList().get(position);
                        TextView people_name = holder.getView(R.id.huifu_name);
                        people_name.setText(peopleresultbean.getMuseNickName() + "、");
                    }
                };
                if (NullUtils.isNotEmpty(mLikesBeanList)){
                    mLikesBeanAdapter.setDataList(mLikesBeanList);
                }else{
                    my_friends.setVisibility(View.GONE);
                }
                good_people_name.setAdapter(mLikesBeanAdapter);


                //评论列表
                good_people_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean.CommentsBean> mCommentsBeanAdapter = new ListBaseAdapter<RespOtherHomePage.ResultBean.BeanBean.SourceBean.CommentsBean>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.good_people_list_item;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        String bycmContent = null;
                        final RespOtherHomePage.ResultBean.BeanBean.SourceBean.CommentsBean resultbean = getDataList().get(position);
                        if (resultbean.getBycmContent() != null) {
                            bycmContent = resultbean.getBycmContent();
                        } else {
                            bycmContent = "";
                        }
                        final String ByHfId;
                        String state = resultbean.getBysmType();
                        TextView Reply_Tv_01 = holder.getView(R.id.Reply_Tv_01);
                        TextView Reply_Tv_02 = holder.getView(R.id.Reply_Tv_02);
                        TextView Reply_Tv_03 = holder.getView(R.id.Reply_Tv_03);
                        TextView Reply_Tv_04 = holder.getView(R.id.Reply_Tv_04);
                        ByHfId = resultbean.getMuseId();
                        if (state.equals("1")) {
                            Reply_Tv_03.setVisibility(View.GONE);
                            Reply_Tv_04.setVisibility(View.GONE);
                            Reply_Tv_01.setText(resultbean.getMuseNickName() + ":");
                            Reply_Tv_02.setText(bycmContent);
                        } else {
                            Reply_Tv_01.setText(resultbean.getMuseNickNameReply());
                            Reply_Tv_02.setText("回复");
                            if (resultbean.getMuseNickName() != null) {
                                Reply_Tv_03.setText(resultbean.getMuseNickName());
                            } else {
                                Reply_Tv_03.setText("");
                            }
                            Reply_Tv_04.setText(bycmContent);
                        }
                    }
                };
                if (mCommentsBeanList != null) {
                    mCommentsBeanAdapter.setDataList(mCommentsBeanList);
                }
                good_people_list.setAdapter(mCommentsBeanAdapter);

            }
        };

        Circle_friends.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getOtherDynamicPage(frMuseId, currentPage + "", pageSize + "");
            }
        });
        Circle_friends.setAdapter(mAdapter);
        Circle_friends.refreshing();
        Circle_friends.forceToRefresh();
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
        Intent intent_pre = new Intent(this, ActImagePreview.class);
        intent_pre.putExtra("data", dataPri);
        intent_pre.putExtra("index", position);
        intent_pre.putExtra("type", -1);
        this.startActivity(intent_pre);
    }
}
