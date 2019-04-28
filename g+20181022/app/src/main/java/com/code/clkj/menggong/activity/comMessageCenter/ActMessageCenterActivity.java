package com.code.clkj.menggong.activity.comMessageCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comWeb.ActWeb;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespActgetMessagePage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 消息中心
 * Created by Administrator on 2017/9/12.
 */

public class ActMessageCenterActivity extends TempActivity implements ViewActMessageCenterI {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.live_Message_Center)
    TempRecyclerView live_Message_Center;

    private PreMessageCenterI mPreI;

    private ListBaseAdapter<RespActgetMessagePage.ResultEntity.SourceEntity> baseAdapter;
  //  private List<RespActgetMessagePage.ResultEntity.SourceEntity> mData = new ArrayList<>();

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_message_center_layout);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if (live_Message_Center!=null && mPreI!=null){
            live_Message_Center.refreshing();
            live_Message_Center.forceToRefresh();
        }
    }

    @Override
    protected void findViews() {
        initData();

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        mPreI = new PreActMessageCenterImpl(this);
        initRv();
    }

    @Override
    protected void OnViewClicked(View v) {

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("消息中心");
        toolbar_title.setTextSize(18);
    }

    private void initRv() {
        live_Message_Center.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        baseAdapter = new ListBaseAdapter<RespActgetMessagePage.ResultEntity.SourceEntity>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.message_center_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                final RespActgetMessagePage.ResultEntity.SourceEntity resultBean = getDataList().get(position);
                if (!TextUtils.isEmpty(resultBean.getMessCreateTime())){
                    holder.setText(R.id.CreateTime,resultBean.getMessCreateTime());
                }
                if (!TextUtils.isEmpty(resultBean.getMessTitle())){
                    holder.setText(R.id.title_message,resultBean.getMessTitle());
                }
                if (!TextUtils.isEmpty(resultBean.getMessContent())){
                    holder.setText(R.id.message_text,resultBean.getMessContent());
                }
                if (!TextUtils.isEmpty(resultBean.getMessIsRead())){
                   if (resultBean.getMessIsRead().equals("0")){
                       holder.getView(R.id.img_state).setVisibility(View.VISIBLE);
                   }else{
                       holder.getView(R.id.img_state).setVisibility(View.GONE);
                   }
                }
                holder.getView(R.id.messige_llt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getTempContext(),ActWeb.class);
                        intent.putExtra(Constance.KEY_WEB_TITLE,resultBean.getMessTitle());
                        intent.putExtra(Constance.KEY_WEB_URL, BaseUriConfig.message+
                                "?museId="+ TempLoginConfig.sf_getSueid()+"&musePassword="+TempLoginConfig.sf_getPwd()+"&messType="+"1"+"&messId="
                                +resultBean.getMessId());
                        startActivity(intent);
                    }
                });
            }
        };
        live_Message_Center.setAdapter(baseAdapter);
        live_Message_Center.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getMessagePage(currentPage+"", pageSize+"");
            }
        });
        live_Message_Center.refreshing();
        live_Message_Center.forceToRefresh();

    }

    @Override
    public void onLoadFinish() {
        if (isFinishing())return;
        live_Message_Center.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        showToast(e.message);
        live_Message_Center.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        live_Message_Center.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getMessagePageSuccess(RespActgetMessagePage data) {

        if (live_Message_Center.isMore()) {
            baseAdapter.addAll(data.getResult().getSource());
        } else {
            baseAdapter.setDataList(data.getResult().getSource());
        }
        live_Message_Center.setTotalCount(data.getResult().getSize());
       /*
        if (NullUtils.isNotEmpty(mData)){
            baseAdapter.setDataList(mData);
        }else {
            baseAdapter.clear();
        }*/


//        baseAdapter.notifyDataSetChanged();
    }
}
