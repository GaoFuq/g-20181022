package com.code.clkj.menggong.activity.comSetUp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comSetUp.comCommonProblem.PreActGetHelpListI;
import com.code.clkj.menggong.activity.comSetUp.comCommonProblem.PreActGetHelpListImpl;
import com.code.clkj.menggong.activity.comSetUp.comCommonProblem.ViewActGetHelpListI;
import com.code.clkj.menggong.activity.comWeb.ActWeb;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespGetHelpList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.lf.tempcore.tempActivity.TempActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ActCommonProblemActivity extends TempActivity implements ViewActGetHelpListI {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
//    @Bind(R.id.guide)
//    ImageView guide;
    @Bind(R.id.common_problem_trv)
    TempRecyclerView common_problem_trv;

    private PreActGetHelpListI mPreI;

    private List<RespGetHelpList.ResultBean> mdata = new ArrayList<>();
    private ListBaseAdapter<RespGetHelpList.ResultBean> mBaseAdapter;
    private String helpId;
    private int flag = 10000;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_common_problem_layout);
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
        mPreI = new PreActGetHelpListImpl(this);
        initRv();

    }

    private void initRv() {
        common_problem_trv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mBaseAdapter = new ListBaseAdapter<RespGetHelpList.ResultBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.common_problem_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                RespGetHelpList.ResultBean resultBean = mdata.get(position);
                if (!TextUtils.isEmpty(resultBean.getHelpTitle())){
                    holder.setText(R.id.Title,resultBean.getHelpTitle());
                }
                if (!TextUtils.isEmpty(resultBean.getHelpContext())){
                    holder.setText(R.id.helpContext,resultBean.getHelpContext());
                }
            }
        };
        common_problem_trv.setAdapter(mBaseAdapter);
        common_problem_trv.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getHelpList();
            }
        });
        mBaseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespGetHelpList.ResultBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespGetHelpList.ResultBean resultBean) {
                Intent intent = new Intent(getTempContext(),ActWeb.class);
                intent.putExtra(Constance.KEY_WEB_TITLE,resultBean.getHelpTitle());
                intent.putExtra(Constance.KEY_WEB_URL, BaseUriConfig.commonproblem+
                        "helpId=" + resultBean.getHelpId());
                startActivity(intent);
            }
        });
        common_problem_trv.refreshing();
        common_problem_trv.forceToRefresh();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if (common_problem_trv!=null && mPreI!=null){
            common_problem_trv.refreshing();
            common_problem_trv.forceToRefresh();
        }
    }


    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
        }

    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("常见问题");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void onLoadFinish() {
        if (isFinishing())return;
        common_problem_trv.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        showToast(e.message);
        common_problem_trv.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        common_problem_trv.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getHelpList(RespGetHelpList data) {
        mdata = data.getResult();
        if (NullUtils.isNotEmpty(mdata)){
            mBaseAdapter.setDataList(mdata);
        }else {
            mBaseAdapter.clear();
        }
    }
}
