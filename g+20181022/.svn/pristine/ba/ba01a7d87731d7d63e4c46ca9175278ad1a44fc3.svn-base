package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespMyHomePage;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempModule.previewComponments.activity.ActImagePreview;
import com.lf.tempcore.tempModule.previewComponments.model.ImageInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by clkj on 2018/1/5.
 */

public class MyHomePageAdapter extends ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean> {

    private OnCallBackListenr mOnCallBackListenr;

    private Context mContext;
//    private List<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean> mListBeans;

    public MyHomePageAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_home_page_item_home;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final RespMyHomePage.ResultBean.BeanBean.SourceBean resultbean = getDataList().get(position);
//        mListBeans = resultbean.getList();
        List<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean> mListBeans = resultbean.getList();


        TextView my_time = holder.getView(R.id.my_time);
        my_time.setText(resultbean.getName());


        RecyclerView mRecyclerView = holder.getView(R.id.time_rec);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean> mListBaseAdapter = new ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean>(mContext) {
            @Override
            public int getLayoutId() {
                return R.layout.my_home_page_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean mResultbean = getDataList().get(position);

                List<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.LikesBean> mLikesBeanList = mResultbean.getLikes();//回复人
                List<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.CommentsBean> mCommentsBeanList = mResultbean.getComments();//评论
                final String mDyId = mResultbean.getDyId();//获得动态ID

                LinearLayout my_friends = holder.getView(R.id.my_friends);

                String str = mResultbean.getDyImage();
                String[] splits = str.split(",");
                List<String> my_image = new ArrayList<>();
                for (int i = 0; i < splits.length; i++) {
                    Collections.addAll(my_image, splits[i]);
                }
                final List<String> my_image_02 = new ArrayList<>();
                for (int i = 0; i < splits.length; i++) {
                    Collections.addAll(my_image_02, BaseUriConfig.makeImageUrl(splits[i]));
                }

                SimpleDraweeView circle_friend_head_img = holder.getView(R.id.circle_friend_head_img);//发布头像
//                SimpleDraweeView circle_friend_content_img = holder.getView(R.id.circle_friend_content_img);//发布内容图片
                TextView circle_friends_time = holder.getView(R.id.circle_friends_time);//发布时间
//                SimpleDraweeView circle_friend_content_img_02 = holder.getView(R.id.circle_friend_content_img_02);//发布内容图片
//                SimpleDraweeView circle_friend_content_img_03 = holder.getView(R.id.circle_friend_content_img_03);//发布内容图片
                TextView circle_friends_name = holder.getView(R.id.circle_friends_name);//昵称
                TextView circle_friends_title = holder.getView(R.id.circle_friends_title);//内容

                RecyclerView show_image = holder.getView(R.id.circle_friend_img_rev);//展示动态图片
                RecyclerView good_people_name = holder.getView(R.id.good_people_name);//展示动态点点赞人
                RecyclerView good_people_list = holder.getView(R.id.good_people_list);//展示评论内容

                circle_friend_head_img.setImageURI(BaseUriConfig.makeImageUrl(mResultbean.getMemberUser().getMuseImage()));

                if (!TextUtils.isEmpty(mResultbean.getDyCreateTime())) {
                    DateFormat formart= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        Date curDate =formart.parse(mResultbean.getDyCreateTime());
                        Date endDate = new Date(System.currentTimeMillis());

                        long diff = endDate.getTime() - curDate.getTime();
                        long days = diff/ (1000 * 60* 60 * 24);
                        long hours =(diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);

                        if (hours>24){
                            circle_friends_time.setText(mResultbean.getDyCreateTime());
                        }else{
                            circle_friends_time.setText(hours+"小时前");
                        }

                    }catch (Exception e){

                    }
                }

                circle_friends_name.setText(mResultbean.getMemberUser().getMuseNickName());
                circle_friends_title.setText(mResultbean.getDyContent());

                //动态图片
                show_image.setLayoutManager(new GridLayoutManager(mContext, 3));
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
                if (my_image != null)
                    mImageAdapter.setDataList(my_image);
                show_image.setAdapter(mImageAdapter);

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


                //回复人
                good_people_name.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.LikesBean> mLikesBeanAdapter = new ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.LikesBean>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.people_name;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.LikesBean peopleresultbean = getDataList().get(position);
                        TextView people_name = holder.getView(R.id.huifu_name);
                        people_name.setText(peopleresultbean.getMuseNickName() + "、");
                    }
                };
                if (mLikesBeanList.size()!=0){
                    my_friends.setVisibility(View.VISIBLE);
                    mLikesBeanAdapter.setDataList(mLikesBeanList);
                }else{
                    my_friends.setVisibility(View.GONE);
                }
                good_people_name.setAdapter(mLikesBeanAdapter);


                //评论列表
                good_people_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.CommentsBean> mCommentsBeanAdapter = new ListBaseAdapter<RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.CommentsBean>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.good_people_list_item;
                    }

                    @Override
                    public void onBindItemHolder(SuperViewHolder holder, int position) {
                        String bycmContent = null;
                        final RespMyHomePage.ResultBean.BeanBean.SourceBean.ListBean.CommentsBean resultbean = getDataList().get(position);
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

                        Reply_Tv_01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mOnCallBackListenr != null) {
                                    mOnCallBackListenr.delPoint(mDyId, ByHfId);
                                }
                            }
                        });
                    }

                };
                if (mCommentsBeanList != null) {
                    mCommentsBeanAdapter.setDataList(mCommentsBeanList);
                    mCommentsBeanAdapter.notifyDataSetChanged();
                }

                good_people_list.setAdapter(mCommentsBeanAdapter);
            }
        };
        mListBaseAdapter.setDataList(mListBeans);
        mRecyclerView.setAdapter(mListBaseAdapter);
    }

    public void setOnCallBackListenr(MyHomePageAdapter.OnCallBackListenr onCallBackListenr) {
        mOnCallBackListenr = onCallBackListenr;
    }

    public interface OnCallBackListenr {
        void delPoint(String mDyId, String ByHfId);
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
        Intent intent_pre = new Intent(mContext, ActImagePreview.class);
        intent_pre.putExtra("data", dataPri);
        intent_pre.putExtra("index", position);
        intent_pre.putExtra("type", -1);
        mContext.startActivity(intent_pre);
    }
}
