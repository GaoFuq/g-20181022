package com.code.clkj.menggong.activity.comLiveHistory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLiveHistory.comShowHistory.PreShowHistiryImpl;
import com.code.clkj.menggong.activity.comLiveHistory.comShowHistory.PreShowHistoryI;
import com.code.clkj.menggong.activity.comLiveHistory.comShowHistory.ViewShowHistoryI;
import com.code.clkj.menggong.adapter.LiveHistoryAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespShowHistory;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/11.
 */
public class ActShowLiveHistoryActivity extends TempActivity implements ViewShowHistoryI,LiveHistoryAdapter.OnCallBackListenr{

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//删除
    @Bind(R.id.toolbar_menu_tv_02)
    TextView toolbar_menu_tv_02;//完成
    @Bind(R.id.live_history_user)
    TempRecyclerView live_history_user;

    private LiveHistoryAdapter mHistoryAdapter;
    private PreShowHistoryI mPreI;
    private boolean isDel = false;



    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_show_history_layout);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {
        mPreI = new PreShowHistiryImpl(this);
        live_history_user.setLayoutManager(new GridLayoutManager(this,3));
        mHistoryAdapter = new LiveHistoryAdapter(this);
        mHistoryAdapter.setOnCallBackListenr(this);
        live_history_user.setAdapter(mHistoryAdapter);
        live_history_user.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMuseHistory(currentPage+"",pageSize+"");
            }
        });
        live_history_user.forceToRefresh();
        live_history_user.refreshing();
    }
    @Override
    protected void bindValues() {
        initData();
    }

    @OnClick({R.id.toolbar_menu_tv,R.id.toolbar_menu_tv_02})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_menu_tv:
                isDel = true;
                mHistoryAdapter.setIsableDel(true);
                live_history_user.setPullRefreshEnabled(false);
                toolbar_menu_tv.setVisibility(View.GONE);
                toolbar_menu_tv_02.setVisibility(View.VISIBLE);
                break;
            case R.id.toolbar_menu_tv_02:
                isDel = false;
                mHistoryAdapter.setIsableDel(false);
                live_history_user.setPullRefreshEnabled(true);
                toolbar_menu_tv.setVisibility(View.VISIBLE);
                toolbar_menu_tv_02.setVisibility(View.GONE);
                break;
        }
    }
    private void initData(){
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("历史记录");
        toolbar_title.setTextSize(18);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("删除");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
        toolbar_menu_tv_02.setText("完成");
        toolbar_menu_tv_02.setTextSize(16);
        toolbar_menu_tv_02.setTextColor(Color.parseColor("#f70052"));

    }

    @Override
    public void onLoadFinish() {
        live_history_user.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        live_history_user.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        live_history_user.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }
    @Override
    public void getMuseHistorySuccess(RespShowHistory data) {
        mHistoryAdapter.setDataList(data.getResult().getSource());
    }

    @Override
    public void deleteMuseHistorySuccee(TempResponse data) {
        live_history_user.refreshing();
    }

    @Override
    public void delPoint(RespShowHistory.ResultBean.SourceBean resultEntity) {
        mPreI.deleteMuseHistory(resultEntity.getMuhyId());
    }
}
