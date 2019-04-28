package com.code.clkj.menggong.activity.comSearch;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.holder.SearchLiveAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespGetSearchByKey;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class ActSearch extends TempActivity implements ViewActSearchI{

    @Bind(R.id.act_search_rcy)
    TempRecyclerView act_search_rcy;
    private SearchLiveAdapter mSearchLiveAdapter;
    private String searchKey;
    private PreActSearchI mPreI;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
//    @Bind(R.id.act_home_icon_search_detail)
//    ImageView act_home_icon_search_detail;
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;
    @Bind(R.id.toolbar_search_tx)
    TextView toolbar_search_tx;
    @Bind(R.id.toolbar_search_ed)
    EditText toolbar_search_ed;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_search);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
//        toolbar_top = (Toolbar) findViewById(R.id.toolbar_top);
//        toolbar_back = (ImageView) toolbar_top.findViewById(R.id.toolbar_back);
//        act_home_icon_search = (ImageView) toolbar_top.findViewById(R.id.act_home_icon_search);
//        toolbar_search_tx = (TextView) toolbar_top.findViewById(R.id.toolbar_search_tx);
        searchKey = getIntent().getStringExtra("searchKey");
        mPreI = new PreActSearchImpl(this);
        initAdapter();

    }

    private void initAdapter() {
        mSearchLiveAdapter = new SearchLiveAdapter(this);
        act_search_rcy.setLayoutManager(new GridLayoutManager(getContext(),3));
        act_search_rcy.setAdapter(mSearchLiveAdapter);
        act_search_rcy.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getSearchByKey(searchKey, currentPage + "" , pageSize + "");

            }
        });

        mSearchLiveAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespGetSearchByKey.ResultBean.SourseBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespGetSearchByKey.ResultBean.SourseBean sourseBean) {

            }
        });
        act_search_rcy.forceToRefresh();
        act_search_rcy.refreshing();
    }


    @Override
    @OnClick({R.id.toolbar_back,R.id.act_home_icon_search_detail,R.id.toolbar_search_tx})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.act_home_icon_search_detail:
                act_search_rcy.setRefreshing(new TempRecyclerView.initDataListener() {
                    @Override
                    public void initDataData(int currentPage, int pageSize) {
                        mPreI.getSearchByKey(toolbar_search_ed.getText().toString(), currentPage + "" , pageSize + "");
                    }
                });
                act_search_rcy.refreshing();
                break;
            case  R.id.toolbar_search_tx:
                act_search_rcy.setRefreshing(new TempRecyclerView.initDataListener() {
                    @Override
                    public void initDataData(int currentPage, int pageSize) {
                        mPreI.getSearchByKey(toolbar_search_ed.getText().toString(), currentPage + "" , pageSize + "");
                    }
                });
                act_search_rcy.refreshing();
                break;
        }
    }

    @Override
    public void onLoadFinish() {
        act_search_rcy.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        act_search_rcy.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        act_search_rcy.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
    }

    @Override
    public void getSearchByKeySuccess(RespGetSearchByKey data) {
        if (act_search_rcy.isMore()) {
            mSearchLiveAdapter.addAll(data.getResult().getSourse());
        } else {
            mSearchLiveAdapter.setDataList(data.getResult().getSourse());
        }
        act_search_rcy.setTotalCount(TempDataUtils.string2Int(data.getResult().getPageSize()));
    }
}
