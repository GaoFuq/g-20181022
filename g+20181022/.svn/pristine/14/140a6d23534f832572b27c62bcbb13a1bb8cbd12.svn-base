package com.code.clkj.menggong.activity.comAnchorStore;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreCommentDetail.PreActCommentActivityI;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreCommentDetail.PreActCommentActivityImpl;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreCommentDetail.ViewActCommentActivityI;
import com.code.clkj.menggong.adapter.ActGoodsCommentAdapater;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespQueryMgooCommentPage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/15.
 */

/**
 * 评论详情 item ：comment_item.xml
 *  图片列表 item ： goods_detail_img_item
 */

public class ActCommentActivity extends TempActivity implements ViewActCommentActivityI {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;

    @Bind(R.id.comment_recyclerview)
    TempRecyclerView comment_recyclerview;
    private ActGoodsCommentAdapater mAdapter;
    String commentTotalNumber;
    String goodId;
    private PreActCommentActivityI mPreI;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_comment_layout);

    }

    @Override
    protected void findViews() {
        initdata();

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        commentTotalNumber = getIntent().getStringExtra("commentTotalNumber");
        goodId = getIntent().getStringExtra("goodId");
        mPreI = new PreActCommentActivityImpl(this);
        initRecy();
    }

    private void initRecy() {
        comment_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        mAdapter = new ActGoodsCommentAdapater(this);
        comment_recyclerview.setAdapter(mAdapter);
        comment_recyclerview.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
//                mPreI.getMuseFriend(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), currentPage + "", pageSize + "");
//                mPreI.queryMallGoods(museId, currentPage + "", pageSize + "");
                mPreI.queryMgooCommentPage(goodId, currentPage + "", pageSize + "");

            }
        });
        comment_recyclerview.forceToRefresh();
        comment_recyclerview.refreshing();

    }

    @Override
    protected void OnViewClicked(View v) {

    }
    public void initdata() {
        toolbar_title.setText("全部评论" + "(" + commentTotalNumber + ")");
        toolbar_title.setTextSize(15);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));
    }

    @Override
    public void onLoadFinish() {
        comment_recyclerview.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        comment_recyclerview.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        comment_recyclerview.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void queryMgooCommentPageSuccess(RespQueryMgooCommentPage data) {
        mAdapter.setDataList(data.getResult().getSource());
    }

}
