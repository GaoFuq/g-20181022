package com.code.clkj.menggong.fragment.comHome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.activity.comLookLive.PLVideoViewActivity;
import com.code.clkj.menggong.activity.comUserLogin.LoginActivity;
import com.code.clkj.menggong.activity.comWeb.ActWeb;
import com.code.clkj.menggong.adapter.FragHomeListNewAdapter;
import com.code.clkj.menggong.adapter.FragHomeRecommendLiveAdapter;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.fragment.comHome.MoreLive.ActMoreHotLive;
import com.code.clkj.menggong.fragment.comHome.homeLive.PrehomeLiveI;
import com.code.clkj.menggong.fragment.comHome.homeLive.PrehomeLiveImpl;
import com.code.clkj.menggong.fragment.comHome.homeLive.ViewhomeLiveI;
import com.code.clkj.menggong.holder.NetworkImageHolderView1;
import com.code.clkj.menggong.response.RespGetAverPage;
import com.code.clkj.menggong.response.RespQueryRoomIndex;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.lf.tempcore.tempFragment.TempFragment;
import com.lf.tempcore.tempViews.tempPullableViews.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by caoyang on 2017/9/4.
 * 直播
 */

public class FragHomeOnline1 extends TempFragment implements ViewhomeLiveI, View.OnClickListener {
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    //    @Bind(R.id.pullScrollView)
//    NestedScrollView pullScrollView;
    @Bind(R.id.mRoomItems)
    RecyclerView mRoomItems; //推荐直播列表
    @Bind(R.id.frag_new_star_live_rcy)
    RecyclerView mNewStarLiveRcy;
    private Integer[] banners = {R.mipmap.zye_1};

    @Bind(R.id.frag_home_hot_live_rly)
    RelativeLayout frag_home_hot_live_rly;

    @Bind(R.id.mRoom)
    RelativeLayout mRoom;
    @Bind(R.id.mRoom2)
    RelativeLayout mRoom2;
    @Bind(R.id.mRoom3)
    RelativeLayout mRoom3;
    @Bind(R.id.mRoom4)
    RelativeLayout mRoom4;
    @Bind(R.id.mRoom5)
    RelativeLayout mRoom5;
    @Bind(R.id.mRoom6)
    RelativeLayout mRoom6;
    @Bind(R.id.mRoomIcon)
    ImageView mRoomIcon;
    @Bind(R.id.mRoomIcon2)
    ImageView mRoomIcon2;
    @Bind(R.id.mRoomIcon3)
    ImageView mRoomIcon3;
    @Bind(R.id.mRoomIcon4)
    ImageView mRoomIcon4;
    @Bind(R.id.mRoomIcon5)
    ImageView mRoomIcon5;
    @Bind(R.id.mRoomIcon6)
    ImageView mRoomIcon6;


    @Bind(R.id.mRoomTitle)
    TextView mRoomTitle;
    @Bind(R.id.mRoomTitle2)
    TextView mRoomTitle2;
    @Bind(R.id.mRoomTitle3)
    TextView mRoomTitle3;
    @Bind(R.id.mRoomTitle4)
    TextView mRoomTitle4;
    @Bind(R.id.mRoomTitle5)
    TextView mRoomTitle5;
    @Bind(R.id.mRoomTitle6)
    TextView mRoomTitle6;

    @Bind(R.id.mUserCount)
    TextView mUserCount;
    @Bind(R.id.mUserCount2)
    TextView mUserCount2;
    @Bind(R.id.mUserCount3)
    TextView mUserCount3;
    @Bind(R.id.mUserCount4)
    TextView mUserCount4;
    @Bind(R.id.mUserCount5)
    TextView mUserCount5;
    @Bind(R.id.mUserCount6)
    TextView mUserCount6;


    //    @Bind(R.id.frag_home_hot_live_gv)
//    TempNestingGridView mHomeHotLiveGV;
    @Bind(R.id.frag_home_index_pull_layout)
    PullToRefreshLayout frag_home_index_pull_layout;

    @Bind(R.id.hot_live_tx)
            TextView hot_live_tx;
    @Bind(R.id.new_star_live_tx)
            TextView new_star_live_tx;
    @Bind(R.id.recommend_live_tx)
            TextView recommend_live_tx;

    @Bind(R.id.hot_live_ly)
    LinearLayout hot_live_ly;

    @Bind(R.id.new_star_live_ly)
    LinearLayout new_star_live_ly;

    @Bind(R.id.recommend_live_ly)
    LinearLayout recommend_live_ly;
    private PrehomeLiveI mPreI;
    private List<RespQueryRoomIndex.ResultBean.ListHostBean> list;

    private RespGetAverPage adverData;//广告
    private List<String> imageList;
    private FragHomeListNewAdapter mListNewAdapter;//新星直播adapter
    private FragHomeRecommendLiveAdapter fragHomeRecommendLiveAdapter;//推荐直播adapter


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mRoom.setOnClickListener(this);
        mRoom2.setOnClickListener(this);
        mRoom3.setOnClickListener(this);
        hot_live_ly.setOnClickListener(this);
        new_star_live_ly.setOnClickListener(this);
        recommend_live_ly.setOnClickListener(this);
        return rootView;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.frag_home_online_layout, container, false);
        if (mPreI == null) {
            mPreI = new PrehomeLiveImpl(FragHomeOnline1.this);
            mPreI.queryRoomIndex();
            mPreI.getAverPage();
        }
        return home;
    }


    @Override
    protected void setListeners() {
        frag_home_index_pull_layout.setPullUpEnable(false);
        frag_home_index_pull_layout.setPullDownEnable(true);
        frag_home_index_pull_layout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                if (mPreI == null) {
                    mPreI = new PrehomeLiveImpl(FragHomeOnline1.this);
                    mPreI.queryRoomIndex();
                    mPreI.getAverPage();
                }else{
                    mPreI.queryRoomIndex();
                    mPreI.getAverPage();
                }
//                mPreI.findMallGoodsRecommend("","1", "6");
              /*  mPrestener.homeMallGoodsList(null,"1", "1", "6");
                mPrestener.homeMallGoodsList(null,"2", "1", "6");*/
//                mPrestener.queryAdvertismentById("0");
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });

    }

    @Override
    protected void bundleValues() {
//        initData();
        InitAdapter();
        initRv();
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
    }
    //直播新星列表
    private void initRv() {
        mListNewAdapter = new FragHomeListNewAdapter(getContext());
        mNewStarLiveRcy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mNewStarLiveRcy.setAdapter(mListNewAdapter);
    }
    //推荐直播列表
    private void InitAdapter() {
        mRoomItems.setLayoutManager(new GridLayoutManager(getContext(),3));
        fragHomeRecommendLiveAdapter = new FragHomeRecommendLiveAdapter(getActivity());
        mRoomItems.setAdapter(fragHomeRecommendLiveAdapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (convenientBanner != null)
            convenientBanner.stopTurning();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (convenientBanner != null)
            convenientBanner.startTurning(2000);
    }
    Intent i ;
    @Override
    protected void OnViewClicked(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }






    private void initAd(final ConvenientBanner player, final List<String> data) {
//        initImageLoader();
        // 网络加载例子
        player.setPages(new CBViewHolderCreator<NetworkImageHolderView1>() {
            @Override
            public NetworkImageHolderView1 createHolder() {
                return new NetworkImageHolderView1();
            }
        }, data) //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.zye_3, R.mipmap.zye_2})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        if (adverData == null) {
                            mPreI.getAverPage();
                        } else {
                            String image = data.get(position);
                            List<RespGetAverPage.ResultBean> dataEntityList = adverData.getResult();
                            for (int i = 0; i < dataEntityList.size(); i++) {
                                if (TempURIConfig.makeImageUrl(dataEntityList.get(i).getAdvImage()).equals(image)) {
                                    String advId = dataEntityList.get(i).getAdvId();//广告Id
                                    String advType = dataEntityList.get(i).getAdvType();//1 广告（广告详情接口） 2 活动(红包详情接口)
                                    String advCreateTime = dataEntityList.get(i).getAdvCreateTime();
                                    if (advType != null && advType.equals("1")) {

                                        ActWeb.startActivityIntent(getContext(), "" , BaseUriConfig.ADVER_DETAIL
                                                + "advId="+ advId + "&"
                                                + "advType=" +advType);

                                    } else {
                                        ActWeb.startActivityIntent(getContext(), " ", BaseUriConfig.HONGBAO_DETAIL
                                                + "museId=" + TempLoginConfig.sf_getSueid() + "&"
                                                + "musePassword=" + TempLoginConfig.sf_getPwd() + "&"
                                                + "advId="+ advId + "&"
                                                + "advType=" +advType);

                                    }


                                }
                            }
                        }

                    }
                })
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
        //设置翻页的效果，不需要翻页效果可用不设
        ;
    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {

    }

    @Override
    public void onLoadDataSuccess() {

    }

    @Override
    public void toast(String message) {
//        showToast(message);
    }



    @Override
    public void queryRoomIndexSuccess(final RespQueryRoomIndex data) {
        if (data!=null&&data.getFlag()==1){
            frag_home_index_pull_layout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }else {
            frag_home_index_pull_layout.refreshFinish(PullToRefreshLayout.FAIL);
            return;
        }
        mListNewAdapter.setDataList(data.getResult().getListNew());
        fragHomeRecommendLiveAdapter.setDataList(data.getResult().getListRecommend());
        list = data.getResult().getListHost();
            if (data.getResult().getListHost().size() == 0 || data.getResult().getListHost() == null) {
                frag_home_hot_live_rly.setVisibility(View.GONE);
            }
        if (data.getResult().getListHost().size()==1) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mRoom2.setVisibility(View.GONE);
            mRoom3.setVisibility(View.GONE);
            mRoom4.setVisibility(View.GONE);
            mRoom5.setVisibility(View.GONE);
            mRoom6.setVisibility(View.GONE);

        }
        if (data.getResult().getListHost().size()==2) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(1).getRoomImage())).into(mRoomIcon2);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mRoomTitle2.setText(data.getResult().getListHost().get(1).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mUserCount2.setText(data.getResult().getListHost().get(1).getRoomWatchNum());
            mRoom3.setVisibility(View.GONE);
            mRoom4.setVisibility(View.GONE);
            mRoom5.setVisibility(View.GONE);
            mRoom6.setVisibility(View.GONE);
        }
        if (data.getResult().getListHost().size()==3) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(1).getRoomImage())).into(mRoomIcon2);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl( data.getResult().getListHost().get(2).getRoomImage())).into(mRoomIcon3);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mRoomTitle2.setText(data.getResult().getListHost().get(1).getRoomName());
            mRoomTitle3.setText(data.getResult().getListHost().get(2).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mUserCount2.setText(data.getResult().getListHost().get(1).getRoomWatchNum());
            mUserCount3.setText(data.getResult().getListHost().get(2).getRoomWatchNum());
            mRoom4.setVisibility(View.GONE);
            mRoom5.setVisibility(View.GONE);
            mRoom6.setVisibility(View.GONE);
        }
        if (data.getResult().getListHost().size()==4) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(1).getRoomImage())).into(mRoomIcon2);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl( data.getResult().getListHost().get(2).getRoomImage())).into(mRoomIcon3);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl( data.getResult().getListHost().get(3).getRoomImage())).into(mRoomIcon4);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mRoomTitle2.setText(data.getResult().getListHost().get(1).getRoomName());
            mRoomTitle3.setText(data.getResult().getListHost().get(2).getRoomName());
            mRoomTitle4.setText(data.getResult().getListHost().get(3).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mUserCount2.setText(data.getResult().getListHost().get(1).getRoomWatchNum());
            mUserCount3.setText(data.getResult().getListHost().get(2).getRoomWatchNum());
            mUserCount4.setText(data.getResult().getListHost().get(3).getRoomWatchNum());
            mRoom5.setVisibility(View.GONE);
            mRoom6.setVisibility(View.GONE);
        }
        if (data.getResult().getListHost().size()==5) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(1).getRoomImage())).into(mRoomIcon2);
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(2).getRoomImage())).into(mRoomIcon3);
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(3).getRoomImage())).into(mRoomIcon4);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl( data.getResult().getListHost().get(4).getRoomImage())).into(mRoomIcon5);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mRoomTitle2.setText(data.getResult().getListHost().get(1).getRoomName());
            mRoomTitle3.setText(data.getResult().getListHost().get(2).getRoomName());
            mRoomTitle4.setText(data.getResult().getListHost().get(3).getRoomName());
            mRoomTitle5.setText(data.getResult().getListHost().get(4).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mUserCount2.setText(data.getResult().getListHost().get(1).getRoomWatchNum());
            mUserCount3.setText(data.getResult().getListHost().get(2).getRoomWatchNum());
            mUserCount4.setText(data.getResult().getListHost().get(3).getRoomWatchNum());
            mUserCount5.setText(data.getResult().getListHost().get(4).getRoomWatchNum());
            mRoom6.setVisibility(View.GONE);
        }
        if (data.getResult().getListHost().size()==6) {
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(0).getRoomImage())).into(mRoomIcon);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(1).getRoomImage())).into(mRoomIcon2);
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(2).getRoomImage())).into(mRoomIcon3);
            Glide.with(getActivity()).load( BaseUriConfig.makeImageUrl(data.getResult().getListHost().get(3).getRoomImage())).into(mRoomIcon4);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl( data.getResult().getListHost().get(4).getRoomImage())).into(mRoomIcon5);
            Glide.with(getActivity()).load(BaseUriConfig.makeImageUrl(  data.getResult().getListHost().get(5).getRoomImage())).into(mRoomIcon6);
            mRoomTitle.setText(data.getResult().getListHost().get(0).getRoomName());
            mRoomTitle2.setText(data.getResult().getListHost().get(1).getRoomName());
            mRoomTitle3.setText(data.getResult().getListHost().get(2).getRoomName());
            mRoomTitle4.setText(data.getResult().getListHost().get(3).getRoomName());
            mRoomTitle5.setText(data.getResult().getListHost().get(4).getRoomName());
            mRoomTitle6.setText(data.getResult().getListHost().get(5).getRoomName());
            mUserCount.setText(data.getResult().getListHost().get(0).getRoomWatchNum());
            mUserCount2.setText(data.getResult().getListHost().get(1).getRoomWatchNum());
            mUserCount3.setText(data.getResult().getListHost().get(2).getRoomWatchNum());
            mUserCount4.setText(data.getResult().getListHost().get(3).getRoomWatchNum());
            mUserCount5.setText(data.getResult().getListHost().get(4).getRoomWatchNum());
            mUserCount6.setText(data.getResult().getListHost().get(5).getRoomWatchNum());
        }

    }
    //查询广告列表成功
    @Override
    public void getAverPageSuccess(RespGetAverPage data) {
        if (data!=null&&data.getFlag()==1&&frag_home_index_pull_layout!=null){
            frag_home_index_pull_layout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }else {
            if(frag_home_index_pull_layout!=null)
            frag_home_index_pull_layout.refreshFinish(PullToRefreshLayout.FAIL);
            return;
        }
        this.adverData = data;
        imageList = new ArrayList<>();
        Observable.from(data.getResult()).map(new Func1<RespGetAverPage.ResultBean, String>() {
            @Override
            public String call(RespGetAverPage.ResultBean resultBean) {
                return TempURIConfig.makeImageUrl(resultBean.getAdvImage());
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                initAd(convenientBanner,imageList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                imageList.add(s);

            }
        });
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
    }





    @Override
    public void onClick(View view) {
        if (list == null || list.size() == 0) {
            return;
        }
        switch (view.getId()) {
            case R.id.mRoom:
                if (TempLoginConfig.sf_getLoginState()) {
                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(0).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }

                break;
            case R.id.mRoom2:

                if (TempLoginConfig.sf_getLoginState()) {
                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(1).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }


                break;
            case R.id.mRoom3:

                if (TempLoginConfig.sf_getLoginState()) {

                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(2).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }


                break;
            case R.id.mRoom4:
                if (TempLoginConfig.sf_getLoginState()) {

                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(3).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }


                break;
            case R.id.mRoom5:

                if (TempLoginConfig.sf_getLoginState()) {

                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(4).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }



                break;
            case R.id.mRoom6:

                if (TempLoginConfig.sf_getLoginState()) {

                    i = new Intent(getActivity(), PLVideoViewActivity.class);//跳转到观看直播页面
                    i.putExtra("roomId",list.get(5).getRoomId());
                    startActivity(i);
                }
                else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //  finish();
                }


                break;
            case R.id.hot_live_ly:
                i = new Intent(getActivity(), ActMoreHotLive.class);
                i.putExtra("LiveType", "1");
                startActivity(i);
                break;
            case R.id.new_star_live_ly:
                i = new Intent(getActivity(), ActMoreHotLive.class);
                i.putExtra("LiveType", "2");
                startActivity(i);
                break;
            case R.id.recommend_live_ly:
                i = new Intent(getActivity(), ActMoreHotLive.class);
                i.putExtra("LiveType", "3");
                startActivity(i);
                break;

        }
    }
}
