package com.code.clkj.menggong.activity.comMyHomePage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comDynamics.ActDynamicsActivity;
import com.code.clkj.menggong.adapter.MyHomePageAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespMyHomePage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;
import com.rey.material.app.BottomSheetDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by clkj on 2018/1/3.
 */

public class ActMyHomePage extends TempActivity implements ViewMyHomePageI,MyHomePageAdapter.OnCallBackListenr {

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

    private MyHomePageAdapter mAdapter;
    private PreMyhomePageI mPreI;
    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_friends_circle);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {
        mPreI = new PreMyHomePageImpl(this);
        Circle_friends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new MyHomePageAdapter(this);
        mAdapter.setOnCallBackListenr(this);
        Circle_friends.setAdapter(mAdapter);
        Circle_friends.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseDynamicPage(currentPage+"",pageSize+"");
            }
        });
        Circle_friends.refreshing();
        Circle_friends.forceToRefresh();
    }

    @Override
    protected void bindValues() {
        toolbar_title.setText("我的主页");
        toolbar_title.setTextSize(18);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_menu_tv.setText("发动态");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setTextColor(Color.parseColor("#ec447c"));
    }

    @Override
    @OnClick({R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_menu_tv://发动态
                Intent intent = new Intent(this, ActDynamicsActivity.class);
                startActivity(intent);
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

    }

    @Override
    public void getMuseDynamicPageSuccess(RespMyHomePage data) {

        my_head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage()));
        my_nick_name_tx.setText(data.getResult().getMember().getMuseNickName());
        Glide.with(this).load(BaseUriConfig.makeImageUrl(data.getResult().getMember().getMuseImage())).into(friends_circle_top_img);
        if (Circle_friends.isMore()){
            mAdapter.addAll(data.getResult().getBean().getSource());
        }else{
            mAdapter.setDataList(data.getResult().getBean().getSource());
        }
    }

    @Override
    public void deleteMuseDynamicSucceess(TempResponse data) {

    }

    @Override
    public void saveDynamicCommentSucceees(TempResponse data) {
        Circle_friends.refreshing();
    }

    @Override
    public void delPoint(String mDyId, String ByHfId) {
//        if (ByHfId.equals(TempLoginConfig.sf_getSueid())){
//            showToast("自己不能回复自己");
//        }else{
//            showDialog(mDyId,ByHfId);
//            Log.i(ByHfId,ByHfId);
//            Log.i(ByHfId,TempLoginConfig.sf_getSueid());
//        }
        showDialog(mDyId,ByHfId);
    }

    private String mContent;

    private void showDialog(final String dyId, final String rpMuseId) {
        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.pop_answer, null, false);
        final EditText content = (EditText) view.findViewById(R.id.pop_answer_edit);
        TextView pop_answer_btn = (TextView) view.findViewById(R.id.pop_answer_btn);
        pop_answer_btn.setText("回复");

        mBottomSheetDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
        pop_answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContent != null && mContent != "") {
                    mPreI.saveDynamicComment(dyId, mContent, "2",rpMuseId);
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


}
