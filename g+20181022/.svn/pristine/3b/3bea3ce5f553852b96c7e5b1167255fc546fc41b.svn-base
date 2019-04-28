package com.code.clkj.menggong.activity.comHomefollow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHomefollow.comCancleFollow.PreCancleFollowI;
import com.code.clkj.menggong.activity.comHomefollow.comCancleFollow.PreCancleFollowImpl;
import com.code.clkj.menggong.activity.comHomefollow.comCancleFollow.ViewCancleFollowI;
import com.code.clkj.menggong.activity.comHomefollow.comShowAttention.PreShowAttentionI;
import com.code.clkj.menggong.activity.comHomefollow.comShowAttention.PreShowAttentionImpl;
import com.code.clkj.menggong.activity.comHomefollow.comShowAttention.ViewShowAttentionI;
import com.code.clkj.menggong.adapter.FollowAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespMyAttention;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/9/8.
 */

public class ActHomefollow extends TempActivity implements ViewShowAttentionI, ViewCancleFollowI, FollowAdapter.OnCallBackListenr {
    @Bind(R.id.act_home_follow_rcv)
    TempRecyclerView act_home_follow_rcv;

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;


    private FollowAdapter folladpter;
    private PreShowAttentionI mPreI;

    private PreCancleFollowI mPreI_cancle;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_home_follow);

    }

    //初始化控件
    private void initView() {
        folladpter = new FollowAdapter(this);
        act_home_follow_rcv.setLayoutManager(new LinearLayoutManager(this));
        act_home_follow_rcv.setAdapter(folladpter);
        act_home_follow_rcv.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseFollow(TempLoginConfig.sf_getLocation_lng(),TempLoginConfig.sf_getlag(), currentPage + "", pageSize + "");
            }
        });
    }

    @Override
    protected void findViews() {
        initView();
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("我的关注");
        mPreI = new PreShowAttentionImpl(this);
        mPreI_cancle = new PreCancleFollowImpl(this);
        act_home_follow_rcv.refreshing();
        act_home_follow_rcv.forceToRefresh();
    }

    @Override
    protected void setListeners() {
        folladpter.setOnCallBackListenr(this);
    }

    @Override
    protected void bindValues() {

    }

    //监听

    @Override
    protected void OnViewClicked(View v) {
    }

    @Override
    public void onLoadFinish() {
        act_home_follow_rcv.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        act_home_follow_rcv.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        act_home_follow_rcv.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
      showToast(message);
    }

    @Override
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
    public void getMuseFollowSuccess(RespMyAttention data) {
        if (act_home_follow_rcv.isMore()){
            folladpter.addAll(data.getResult().getSource());
        }else {
            folladpter.setDataList(data.getResult().getSource());
        }
    }

    @Override
    public void delPoint(RespMyAttention.ResultBean.SourceBean resultbean) {
        mPreI_cancle.saveMuseFollow(resultbean.getMuseId());
    }

    @Override
    public void saveMuseFollowSuccess(TempResponse data) {
        act_home_follow_rcv.refreshing();
        act_home_follow_rcv.forceToRefresh();
        showToast(data.getMsg());
    }
}
