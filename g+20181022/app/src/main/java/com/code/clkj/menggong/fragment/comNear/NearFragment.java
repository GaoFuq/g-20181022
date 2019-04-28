package com.code.clkj.menggong.fragment.comNear;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comNear.NearHomeActivity;
import com.code.clkj.menggong.activity.comOtherHomePage.ActOtherHomePage;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.LiveRoomAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.fragment.comNear.comNearLive.PreNearLiveI;
import com.code.clkj.menggong.fragment.comNear.comNearLive.PreNearLiveImpl;
import com.code.clkj.menggong.fragment.comNear.comNearLive.ViewNearLiveI;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempFragment.TempFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 附近的Fragment
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends TempFragment implements ViewNearLiveI {


    @Bind(R.id.mNearUser)
    TempRecyclerView mNearUser;  //展示附近用户的列表
    @Bind(R.id.mBack)
    ImageView mBack;  //返回按钮
    @Bind(R.id.mUserName)
    TextView mUserName;  //名称
    @Bind(R.id.mFilter)
    ImageView mFilter; //筛选
    //    @Bind(R.id.right)
//    RelativeLayout right;  //右侧
    //    @Bind(R.id.mDrawerLayout)
//    DrawerLayout mDrawerLayout;  //抽屉
//    private List<Room> list = new ArrayList<Room>();
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_menu)
    ImageView toolbar_menu;
    @Bind(R.id.toolbar_back)
    ImageView toolbar_back;
    private LiveRoomAdapter mAdapter;

    private PreNearLiveI mPreI;
    private String mSex, mStart_age, mEnd_age;


    public NearFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bundleValues() {
        mPreI = new PreNearLiveImpl(this);
        initDataAdapter();

        if (toolbar_top != null) {
//            toolbar_top.setNavigationIcon(R.mipmap.back);
            toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
            toolbar_title.setText("附近");
            toolbar_title.setTextSize(20);

            toolbar_menu.setVisibility(View.VISIBLE);
            toolbar_menu.setImageResource(R.mipmap.tb_1);
            toolbar_back.setImageResource(R.mipmap.back);
            toolbar_back.setVisibility(View.VISIBLE);

        }

//        mNearUser.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        mNearUser.setAdapter(new LiveRoomAdapter(list));//NearLiveRoomAdapter
//        //关闭DrawerLayout的手势滑动
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    @Override
    @OnClick({R.id.toolbar_menu, R.id.toolbar_top, R.id.toolbar_back})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu:
                ((NearHomeActivity) getActivity()).openDrawlayout();
//                mDrawerLayout.openDrawer(right);
//                //开启手势滑动
//                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
//            case R.id.toolbar_top:
//                getActivity().finish();
//                break;

            case R.id.toolbar_back:
                getActivity().finish();
                break;

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 模拟附近人数据
     */
    private void initDataAdapter() {
        mAdapter = new LiveRoomAdapter(getContext());
        mNearUser.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mNearUser.setAdapter(mAdapter);
        mNearUser.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                if (mSex != null) {
                    if (mSex.equals("全部")||mSex.equals("其他")) {
                        mPreI.queryRoomList(mStart_age, mEnd_age, null, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                    } else {
                        mPreI.queryRoomList(mStart_age, mEnd_age, mSex, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                    }
                } else {
                    mPreI.queryRoomList(mStart_age, mEnd_age, null, TempLoginConfig.sf_getlag(), TempLoginConfig.sf_getlng(), currentPage + "", pageSize + "");
                }
            }
        });
        mAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespNearLive.ResultBean.SourceBean>() {

            @Override
            public void OnItemClick(View itemView, int position, RespNearLive.ResultBean.SourceBean sourceBean) {

            }
        });
        mAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespNearLive.ResultBean.SourceBean>() {
            @Override
            public void OnItemClick(View itemView, int position, RespNearLive.ResultBean.SourceBean sourceBean) {
                Intent otherintent = new Intent(getActivity(), ActOtherHomePage.class);
                otherintent.putExtra("frMuseId", sourceBean.getMuseId());
                startActivity(otherintent);
            }
        });
        update();
    }

    public void update() {
        if (mNearUser != null) {
            mNearUser.refreshing();
            mNearUser.forceToRefresh();
        }
    }

    @Override
    public void onLoadFinish() {
        if (mNearUser != null)
            mNearUser.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        if (mNearUser != null)
            mNearUser.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        if (mNearUser != null)
            mNearUser.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void queryRoomListSuccees(RespNearLive data) {
        if (mNearUser.isMore()) {
            mAdapter.addAll(data.getResult().getSource());
        } else {
            mAdapter.setDataList(data.getResult().getSource());
        }
        mNearUser.setTotalCount(TempDataUtils.string2Int(data.getResult().getPageSize()));
    }

    public void setDrawData(String sex, String start_age, String end_age) {
        mSex = sex;
        mStart_age = start_age;
        mEnd_age = end_age;
    }

    @Override
    public void onStart() {
        super.onStart();
        mNearUser.refreshing();
        mNearUser.forceToRefresh();
    }
}
