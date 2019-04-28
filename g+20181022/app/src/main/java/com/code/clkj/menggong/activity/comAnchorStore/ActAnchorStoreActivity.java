package com.code.clkj.menggong.activity.comAnchorStore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStrore.PreActAnchorStoreActivityI;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStrore.PreActAnchorStoreActivityImpl;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStrore.ViewActAnchorStoreActivityI;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespQueryMallGoods;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.interfaces.OnItemClickListener;
import com.lf.tempcore.tempActivity.TempActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/14.
 */

/**
 * 主播商店
 * item:anchor_store_item1.xml
 */

public class ActAnchorStoreActivity extends TempActivity implements ViewActAnchorStoreActivityI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
//    @Bind(R.id.AnchorStore_rec)
//    RecyclerView AnchorStore_rec;
//    @Bind(R.id.store_goods_picture)
//    ImageView store_goods_picture;
    @Bind(R.id.AnchorStore_rec)
    TempRecyclerView AnchorStore_rec;
    private ListBaseAdapter<RespQueryMallGoods.ResultBean.SourceBean> mAdapter;
    private PreActAnchorStoreActivityI mPreI;
    private String museId;//直播间所属人Id;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_anchor_store_layout);

    }

    @Override
    protected void findViews() {
        initdata();
//        AnchorStore_rec.setAdapter(new AnchorStoreAdapter());
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        museId = getIntent().getStringExtra("museId");
        mPreI = new PreActAnchorStoreActivityImpl(this);
        initRv();

    }

    private void initRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(ActAnchorStoreActivity.this,2);
        AnchorStore_rec.setLayoutManager(layoutManager);
        mAdapter = new ListBaseAdapter<RespQueryMallGoods.ResultBean.SourceBean>(ActAnchorStoreActivity.this) {
            @Override
            public int getLayoutId() {
                return R.layout.anchor_store_item1;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                RespQueryMallGoods.ResultBean.SourceBean data = mAdapter.getDataList().get(position);
                ImageView store_goods_picture = holder.getView(R.id.store_goods_picture);
                TextView store_goods_name = holder.getView(R.id.store_goods_name);
                TextView store_goods_price = holder.getView(R.id.store_goods_price);
                TextView store_goods_num = holder.getView(R.id.store_goods_num);

                if (data.getMgooImage() != null) {
                    Glide.with(ActAnchorStoreActivity.this).load(BaseUriConfig.makeImageUrl(data.getMgooImage())).into(store_goods_picture);
                }
                if (!TextUtils.isEmpty(data.getMgooName())) {
                    store_goods_name.setText(data.getMgooName());
                }
                if (!TextUtils.isEmpty(data.getDiscountPrice())) {
                    store_goods_price.setText(data.getDiscountPrice());
                }
                if (!TextUtils.isEmpty(data.getMgooStockNum())) {
                    store_goods_num.setText("已售" + data.getMgooStockNum());
                }



            }
        };
        AnchorStore_rec.setAdapter(mAdapter);
        AnchorStore_rec.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
//                mPreI.getMuseFriend(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), currentPage + "", pageSize + "");
                mPreI.queryMallGoods(museId, currentPage + "", pageSize + "");

            }
        });
        AnchorStore_rec.setOnItemClickLinstener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ActAnchorStoreActivity.this,ActCommodityDetailsActivity.class);
                intent.putExtra("goodId", mAdapter.getDataList().get(position).getMgooId());
                startActivity(intent);
            }
        });
        AnchorStore_rec.forceToRefresh();
        AnchorStore_rec.refreshing();
    }


    //    @OnClick({R.id.store_goods_picture})
    @Override
    protected void OnViewClicked(View v) {

    }

    public void initdata() {
        toolbar_title.setText("主播商店");
        toolbar_title.setTextSize(15);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));
    }


    @Override
    public void onLoadFinish() {
        AnchorStore_rec.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        AnchorStore_rec.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        AnchorStore_rec.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
//        toast(message);

    }


    @Override
    public void queryMallGoodsSuccess(RespQueryMallGoods data) {
        mAdapter.setDataList(data.getResult().getSource());


    }

    @Override
    public void dismissPro() {

    }
}
