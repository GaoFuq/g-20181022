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
import com.code.clkj.menggong.fragment.comGift.comSendGift.PreSendGiftI;
import com.code.clkj.menggong.fragment.comGift.comSendGift.PreSendGiftImpl;
import com.code.clkj.menggong.fragment.comGift.comSendGift.ViewSendGiftI;
import com.code.clkj.menggong.response.RespSendGift;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempFragment.TempFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/12.
 */

public class FragSendGift extends TempFragment implements ViewSendGiftI {
    @Bind(R.id.send_gift)
    TempRecyclerView send_gift;

    private ListBaseAdapter<RespSendGift.ResultBean.SourceBean> mBaseAdapter;
//    private List<RespSendGift.ResultBean.SourceBean> mList = new ArrayList<>();
    private PreSendGiftI mPreI;


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_send_gift, container, false);
    }

    @Override
    protected void setListeners() {
        mPreI = new PreSendGiftImpl(this);
    }

    @Override
    protected void bundleValues() {
        initRec();
    }

    @Override
    protected void OnViewClicked(View v) {

    }

    @Override
    public void onLoadFinish() {
        send_gift.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        send_gift.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        send_gift.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void getGiftRecordOutSuccee(RespSendGift data) {
//        mList = data.getResult().getSource();
        if (send_gift.isMore()){
            mBaseAdapter.addAll(data.getResult().getSource());
        }else{
            mBaseAdapter.setDataList(data.getResult().getSource());
        }
        send_gift.setTotalCount(TempDataUtils.string2Int(data.getResult().getSize()));
    }
    private void initRec(){
        send_gift.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mBaseAdapter = new ListBaseAdapter<RespSendGift.ResultBean.SourceBean>(getContext()) {
            @Override
            public int getLayoutId() {
                return R.layout.my_gift_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                final RespSendGift.ResultBean.SourceBean resultbean = getDataList().get(position);
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
        send_gift.setAdapter(mBaseAdapter);
        send_gift.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPreI.getGiftRecordOut(currentPage+"");
            }
        });
        send_gift.refreshing();
        send_gift.forceToRefresh();
    }
}
