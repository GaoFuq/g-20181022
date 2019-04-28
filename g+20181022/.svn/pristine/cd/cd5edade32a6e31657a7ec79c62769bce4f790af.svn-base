package com.code.clkj.menggong.fragment.comHome.MoreLive;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.MoreLiveAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespQueryRoomMoreList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * item:frag_home_hot_live_gv_item
 */

public class ActMoreHotLive extends TempActivity implements ViewMoreLiveI{
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;
    @Bind(R.id.more_hot_live_rcy)
    TempRecyclerView more_hot_live_rcy;
    private MoreLiveAdapter moreLiveAdapter;
    private PreMoreLiveI mPreI;
    String type = "";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_act_more_hot_live2);
//    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_more_hot_live2);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        type = getIntent().getStringExtra("LiveType");
        mPreI = new PreMoreLiveImpl(this);
        if (toolbar_top != null) {
//            toolbar_top.setNavigationIcon(R.mipmap.back);
            toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));


            toolbar_title.setTextSize(20);
            toolbar_back.setImageResource(R.mipmap.back);
            toolbar_back.setVisibility(View.VISIBLE);
            if (type.equals("1")) {
                toolbar_title.setText("热门直播");
            }
            if (type.equals("2")) {
                toolbar_title.setText("直播新星");
            }
            if (type.equals("3")) {
                toolbar_title.setText("推荐直播");
            }
        }
        initDataAdapter();


    }

    private void initDataAdapter() {
        moreLiveAdapter = new MoreLiveAdapter(this);
        more_hot_live_rcy.setLayoutManager(new GridLayoutManager(getContext(),3));
        more_hot_live_rcy.setAdapter(moreLiveAdapter);
        more_hot_live_rcy.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                if (type.equals("1")) {
                    mPreI.queryRoomMoreList("1", currentPage + "", pageSize + "");
                }
                if (type.equals("2")) {
                    mPreI.queryRoomMoreList("2", currentPage + "", pageSize + "");
                }
                if (type.equals("3")) {
                    mPreI.queryRoomMoreList("3", currentPage + "", pageSize + "");
                }

//                mPreI.queryRoomList(mStart_age,mEnd_age,mSex,"29.465453","106.476253");
            }
        });

        moreLiveAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespQueryRoomMoreList.ResultBean.SourceBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespQueryRoomMoreList.ResultBean.SourceBean sourceBean) {

            }
        });
        more_hot_live_rcy.forceToRefresh();
        more_hot_live_rcy.refreshing();

    }
    @OnClick({R.id.toolbar_back})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
        }
    }

    @Override
    public void onLoadFinish() {
        more_hot_live_rcy.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        more_hot_live_rcy.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        more_hot_live_rcy.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
//        showToast(message);
    }

    @Override
    public void queryRoomMoreListSuccess(RespQueryRoomMoreList data) {
        if (more_hot_live_rcy.isMore()) {
            moreLiveAdapter.addAll(data.getResult().getSource());
        } else {
            moreLiveAdapter.setDataList(data.getResult().getSource());
        }
    }
}
