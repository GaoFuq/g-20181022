package com.code.clkj.menggong.activity.comAddress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAddress.comShowAddress.PreActAddressI;
import com.code.clkj.menggong.activity.comAddress.comShowAddress.PreActAddressImpl;
import com.code.clkj.menggong.activity.comAddress.comShowAddress.ViewActAddressI;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.response.RespActAddress;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.interfaces.OnItemClickListener;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempUtil.QuickClickUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ActAddress extends TempActivity implements ViewActAddressI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//增加地址
    @Bind(R.id.addresslist_rcv)
    TempRecyclerView addresslist_rcv;
    private ListBaseAdapter<RespActAddress.ResultBean.SourceBean> baseAdapter;
    private List<RespActAddress.ResultBean.SourceBean> mData = new ArrayList<>();
    private boolean isSelect, isRestart;

    private PreActAddressI mPre;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_show_address_layout);

    }

    @Override
    protected void findViews() {
        isSelect = getIntent().getBooleanExtra("isSelect", false);
        initData();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void bindValues() {
        mPre = new PreActAddressImpl(this);
        initRv();
    }

    private void initRv() {
        addresslist_rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        baseAdapter = new ListBaseAdapter<RespActAddress.ResultBean.SourceBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.item_show_address;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                final RespActAddress.ResultBean.SourceBean resultBean = getDataList().get(position);
                if (!TextUtils.isEmpty(resultBean.getMsadReceiverName())) {
                    holder.setText(R.id.Consignee, resultBean.getMsadReceiverName());
                }
                if (!TextUtils.isEmpty(resultBean.getMsadMobileNo())) {
                    holder.setText(R.id.phone_number_01, resultBean.getMsadMobileNo());
                }
                if (!TextUtils.isEmpty(resultBean.getAddress())) {
                    holder.setText(R.id.address_01, resultBean.getAddress());
                }

                ImageView img = holder.getView(R.id.edit_img_01);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (QuickClickUtils.checkClick()) return;
                        isRestart = true;
                        Intent intent = new Intent(ActAddress.this, ActEditAddressActivity.class);
                        intent.putExtra("isSelect", isSelect);
                        intent.putExtra("msadId", resultBean.getMsadId());
                        intent.putExtra("msadIsDefault", resultBean.getMsadIsDefault());
                        startActivity(intent);
                    }
                });
                if (!TextUtils.isEmpty(resultBean.getMsadIsDefault())) {
                    if (resultBean.getMsadIsDefault().equals("1")) {//默认
                        holder.getView(R.id.edit_img_01).setBackgroundResource(R.mipmap.tb_3);
                        holder.getView(R.id.default_tv).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.edit_img_01).setBackgroundResource(R.mipmap.tb_2);
                        holder.getView(R.id.default_tv).setVisibility(View.GONE);
                    }
                }
            }
        };

        addresslist_rcv.setAdapter(baseAdapter);
        addresslist_rcv.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mPre.addressList(currentPage);
            }
        });
        addresslist_rcv.refreshing();
        addresslist_rcv.forceToRefresh();
        addresslist_rcv.setOnItemClickLinstener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isSelect) {
                    RespActAddress.ResultBean.SourceBean resultBean = baseAdapter.getDataList().get(position);
                    Intent intent = new Intent();
                    intent.putExtra("select_address_id", resultBean.getMsadId());
                    intent.putExtra("select_address_name", resultBean.getMsadReceiverName());
                    intent.putExtra("select_address_phone", resultBean.getMsadMobileNo());
                    intent.putExtra("select_address", resultBean.getAddress());
                    intent.putExtra("address_state", resultBean.getMsadIsDefault());
                    setResult(Constance.KEY_RESULT_CODE_ADDRESS_1, intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (addresslist_rcv != null && mPre != null) {
            addresslist_rcv.refreshing();
            addresslist_rcv.forceToRefresh();
        }
    }

    @OnClick({R.id.toolbar_menu_tv})
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            //新增地址
            case R.id.toolbar_menu_tv:
                isRestart = true;
                Intent intent = new Intent(this, ActAddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("管理收货地址");
        toolbar_title.setTextSize(18);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("新增地址");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
    }

    @Override
    public void onLoadFinish() {
        if (isFinishing()) return;
        addresslist_rcv.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        showToast(e.message);
        addresslist_rcv.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        addresslist_rcv.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void addressListSuccee(RespActAddress data) {
        if (data.getResult().getSource() != null) {
            if (addresslist_rcv.isMore()) {
                baseAdapter.addAll(data.getResult().getSource());
            } else {
                baseAdapter.setDataList(data.getResult().getSource());
            }
        }
    }
}
