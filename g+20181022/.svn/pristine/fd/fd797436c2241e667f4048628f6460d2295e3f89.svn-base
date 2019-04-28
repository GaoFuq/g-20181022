package com.code.clkj.menggong.fragment.comGift;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.fragment.comGift.comReceiveGift.PreReceiveGiftI;
import com.code.clkj.menggong.fragment.comGift.comReceiveGift.PreReceiveGiftImpl;
import com.code.clkj.menggong.fragment.comGift.comReceiveGift.ViewReceiveGiftI;
import com.code.clkj.menggong.response.RespReceiveGift;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempFragment.TempFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/12.
 */

public class FragReceiveGift extends TempFragment implements ViewReceiveGiftI {
    @Bind(R.id.receive_gift)
    TempRecyclerView receive_gift;

    private ListBaseAdapter<RespReceiveGift.ResultBean.SourceBean> mAdapter;
    private List<RespReceiveGift.ResultBean.SourceBean> mData = new ArrayList<>();
    private PreReceiveGiftI mPreI;


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_receive_gift, container, false);
    }

    @Override
    protected void setListeners() {
        mPreI = new PreReceiveGiftImpl(this);
    }

    @Override
    protected void bundleValues() {
        initRv();
    }

    @Override
    protected void OnViewClicked(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        receive_gift.forceToRefresh();
        receive_gift.refreshing();
    }

    @Override
    public void onLoadFinish() {
        receive_gift.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        receive_gift.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        receive_gift.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getGiftRecordInSuccess(RespReceiveGift data) {
//        mData = data.getResult().getSource();
        if (receive_gift.isMore()){
            mAdapter.addAll(data.getResult().getSource());
        }else{
            mAdapter.setDataList(data.getResult().getSource());
        }
        receive_gift.setTotalCount(TempDataUtils.string2Int(data.getResult().getSize()));
    }

    private void initRv(){
        receive_gift.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new ListBaseAdapter<RespReceiveGift.ResultBean.SourceBean>(getActivity()) {
            @Override
            public int getLayoutId() {
                return R.layout.my_gift_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                final RespReceiveGift.ResultBean.SourceBean resultbean = getDataList().get(position);
                ImageView image_gift = holder.getView(R.id.image_gift);

                if (NullUtils.isNotEmpty(resultbean.getName())){
                    holder.setText(R.id.name,resultbean.getName());
                }
                if (NullUtils.isNotEmpty(resultbean.getTime())){
                    holder.setText(R.id.time,resultbean.getTime());
                }
                if (NullUtils.isNotEmpty(resultbean.getImage())){
                    ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(resultbean.getImage()),image_gift);
                }
            }
        };

        receive_gift.setAdapter(mAdapter);
        receive_gift.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getGiftRecordIn(currentPage+"");
            }
        });
        receive_gift.forceToRefresh();
        receive_gift.refreshing();
    }
}
