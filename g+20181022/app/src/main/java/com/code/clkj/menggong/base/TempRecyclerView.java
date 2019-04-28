package com.code.clkj.menggong.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.lf.tempcore.error.ErrorLayout;
import com.lf.tempcore.interfaces.IRefreshHeader;
import com.lf.tempcore.interfaces.OnItemClickListener;
import com.lf.tempcore.interfaces.OnLoadMoreListener;
import com.lf.tempcore.interfaces.OnRefreshListener;
import com.lf.tempcore.recyclerview.LRecyclerView;
import com.lf.tempcore.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.recyclerview.ProgressStyle;
import com.lf.tempcore.view.CommonHeader;
import com.lf.tempcore.view.LoadingFooter;


/**
 * Created by caoyang on 2017/7/27.
 */
public class TempRecyclerView extends LinearLayout {

    LRecyclerView mRecyclerView;
    TextView mTvErrorLayout;
    View empty_view;

    Button mTopBtn;
    private Context mContext;
    public ListBaseAdapter mDataAdapter;
    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;
    private boolean isTopBtn=false;
    /**
     * 每一页展示多少条数据
     */
        public int mCurrentPage = 0;
    public int totalPage = 100;

    public ErrorLayout getErrorLayout() {
        return mErrorLayout;
    }

    public ErrorLayout mErrorLayout;

    public void setTotalCount(int totalCount) {
        if (totalCount!=0){
            this.totalCount = totalCount;
            if (totalCount % REQUEST_COUNT>0){
                this.totalPage = (totalCount/REQUEST_COUNT)+1;
            }else {
                this.totalPage = (totalCount/REQUEST_COUNT);
            }
        }


    }

    public int totalCount ;
    public final int REQUEST_COUNT = 10;
    public LRecyclerViewAdapter mLRecyclerViewAdapter;

    private boolean isRefreshEnabled;

    private IRefreshHeader mRefreshHeader;

    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.ItemAnimator mItemAnimator;
    private boolean isNestedScrollView;

    public TempRecyclerView(Context context) {
        super(context);
        this.mContext = context;
        initView(context, 0);
    }

    public TempRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttrs(attrs);
        initView(context, 0);
    }


    private void initAttrs(AttributeSet attrs) {
    }

    public TempRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
        initView(context, defStyleAttr);
    }

    private void initView(Context context, int defStyleAttr) {
        if (isInEditMode()) {
            return;
        }
        //生成主View
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_pull_refresh_recyclerview, this);
        mRecyclerView = (LRecyclerView) v.findViewById(R.id.recycler_view);
        empty_view = v.findViewById(R.id.empty_view);
        mErrorLayout = (ErrorLayout) v.findViewById(R.id.error_layout);
        mTopBtn = (Button) v.findViewById(R.id.top_btn);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        bulid();
    }

    public void dissErrorLayout(){
        if(mErrorLayout!=null){
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            mErrorLayout=null;
        }

    }

    public void forceToRefresh() {
        mRecyclerView.forceToRefresh();
    }

    public void setAdapter(ListBaseAdapter listBaseAdapter) {
        mDataAdapter = listBaseAdapter;
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(listBaseAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        mRecyclerView.setLayoutManager(layout);
        this.mLayoutManager = layout;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        this.mItemAnimator = animator;
        mRecyclerView.setHasFixedSize(true);
    }

    public void refreshing() {
        onRefreshView();
    }

    private void animate(View view, int anim) {
        if (anim != 0) {
            Animation a = AnimationUtils.loadAnimation(view.getContext(), anim);
            view.startAnimation(a);
        }
    }

    public void setHeaderView(CommonHeader headerView) {
        this.headerView = headerView;
    }

    protected CommonHeader headerView;

    public void setLoadingFooter(LoadingFooter loadingFooter) {
        mLoadingFooter = loadingFooter;
        if (mLoadingFooter!=null){
            mLoadingFooter.setState(LoadingFooter.State.Normal);
            mRecyclerView.setLoadMoreFooter(mLoadingFooter);
        }

    }

    private LoadingFooter mLoadingFooter;



    //最后的操作
    public void bulid() {
        if (mDataAdapter != null) {
            mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
        }

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isNestedScrollView)
                    onRefreshView();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mCurrentPage < totalPage) {
                    // loading more
                    requestData();
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

   /*     if (isTopBtn){
            mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

                @Override
                public void onScrollUp() {
                    // 滑动时隐藏float button
                    if (mTopBtn.getVisibility() == View.VISIBLE) {
                        mTopBtn.setVisibility(View.GONE);
                        animate(mTopBtn, R.anim.floating_action_button_hide);
                    }
                }

                @Override
                public void onScrollDown() {
                    if (mTopBtn.getVisibility() != View.VISIBLE) {
                        mTopBtn.setVisibility(View.VISIBLE);
                        animate(mTopBtn, R.anim.floating_action_button_show);
                    }
                }

                @Override
                public void onScrolled(int distanceX, int distanceY) {

                    if (null != headerView) {
                        if (distanceY == 0 || distanceY < headerView.getHeight()) {
                            mTopBtn.setVisibility(View.GONE);
                        }
                    } else {
                        if (distanceY == 0) {
                            mTopBtn.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onScrollStateChanged(int state) {

                }

            });
        }*/


        mErrorLayout.setOnLayoutClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mErrorLayout.getErrorState()==ErrorLayout.NO_LOGIN){
                    if (mOnCallBack!=null){
                        mOnCallBack.toLogin();
                        mCurrentPage = 0;
                        executeOnLoadFinish();
                    }
                }else {
                    mCurrentPage = 0;
                    mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                    requestData();
                }
            }
        });

        mTopBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
                mTopBtn.setVisibility(View.GONE);
            }
        });
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecyclerView.addItemDecoration(decor);
    }
    public void setPullRefreshEnabled(boolean enabled) {
        if (mRecyclerView!=null)
       mRecyclerView.setPullRefreshEnabled(enabled);
    }

    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        /*if (mErrorLayout!=null&&mErrorLayout.getErrorState()!=ErrorLayout.NETWORK_LOADING){
            mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
        }*/
        // 设置顶部正在刷新
        mCurrentPage = 0;
        requestData();

    }

    protected void requestData() {
        mCurrentPage++;
        isRequestInProcess = true;

        if (mInitDataListener != null) {
            mInitDataListener.initDataData(mCurrentPage, REQUEST_COUNT);
        }
    }

    public void setRefreshing(initDataListener initDataListener) {
        mInitDataListener = initDataListener;
    }

    private initDataListener mInitDataListener;


    public interface initDataListener {
        void initDataData(int currentPage, int pageSize);
    }
    public void hideErrorLayout() {
        if (mErrorLayout != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }
    }
    public void setAllHint(boolean isHint){
        if (mErrorLayout != null) {
          mErrorLayout.setAllHint(true);
        }
    }
    public void setNotLoginLayout(OnCallBack onCallBack) {
        this.mOnCallBack = onCallBack;
        if(mErrorLayout!=null){
            mErrorLayout.setErrorType(ErrorLayout.NO_LOGIN);
            executeOnLoadFinish();
        }
    }
    public void executeOnLoadDataSuccess(String noDataStr,int img) {
        if (mDataAdapter!=null&&mDataAdapter.getItemCount()!=0&&totalPage<=0){
            totalPage=1;
        }
        if (mErrorLayout != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }
        if (mLoadingFooter!=null)
            mLoadingFooter.setState(LoadingFooter.State.Normal);

//        OnLoadDataSuccess();
        // 判断等于是因为最后有一项是listview的状态
        if (mDataAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setNoDataContent(getNoDataTip());
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
//                mErrorLayout.setClickable(false);
            }

        }
        executeOnLoadFinish();

    }
    public void executeOnLoadDataSuccess() {
        executeOnLoadDataSuccess(null,0);
     /*   if (mDataAdapter!=null&&mDataAdapter.getItemCount()!=0&&totalPage<=0){
            totalPage=1;
        }
        if (mErrorLayout != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }
        if (mLoadingFooter!=null)
            mLoadingFooter.setState(LoadingFooter.State.Normal);

        //        OnLoadDataSuccess();
        // 判断等于是因为最后有一项是listview的状态
        if (mDataAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setNoDataContent(getNoDataTip());
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
                //                mErrorLayout.setClickable(false);
            }

        }
        executeOnLoadFinish();*/

    }

//    private void OnLoadDataSuccess() {
//        if (mCurrentPage==1){
//
//        }
//    }


    protected boolean needShowEmptyNoData() {
        return true;
    }

    protected String getNoDataTip() {
        return null;
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
        if (null != mRecyclerView) {
            mRecyclerView.refreshComplete(REQUEST_COUNT);
        }
    }

    // 完成刷新
    public void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }

    public void executeOnLoadDataError() {
        executeOnLoadFinish();
        if (mLoadingFooter!=null)
            mLoadingFooter.setState(LoadingFooter.State.NetWorkError);
        if (mCurrentPage == 1) {
            if (mErrorLayout != null) {
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
            }
        } else {
            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
            //find by TopJohn
            mCurrentPage--;
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            mDataAdapter.notifyDataSetChanged();
        }
    }

    public boolean isMore() {
        if (mCurrentPage == 1)
            return false;
        else return true;
    }

    public boolean isEnd() {
        if (mCurrentPage == totalPage)
            return true;
        else return false;
    }


    public void setOnItemClickLinstener(OnItemClickListener onItemClickLinstener) {
        mLRecyclerViewAdapter.setOnItemClickListener(onItemClickLinstener);
    }
    public void setLoadMoreEnabled(boolean enabled){
        mRecyclerView.setLoadMoreEnabled(false);
        totalPage = 1;

    }
    public void setVerticalScrollbarPosition(int position) {
        mRecyclerView.setVerticalScrollbarPosition(0);
    }
   private OnCallBack mOnCallBack;
   public interface OnCallBack{
      void toLogin();
    }


}
