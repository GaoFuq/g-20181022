package com.code.clkj.menggong.activity.comAddress;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAddress.comAddAddress.PreAddAddressI;
import com.code.clkj.menggong.activity.comAddress.comAddAddress.PreAddAddressImpl;
import com.code.clkj.menggong.activity.comAddress.comAddAddress.ViewAddAddressI;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempResponse.TempResponse;
import com.rey.material.app.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ActAddAddressActivity extends TempActivity implements ViewAddAddressI {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.toolbar_menu_tv)
    TextView toolbar_menu_tv;//删除
    @Bind(R.id.select_checkbox)
    CheckBox select_checkbox;
    @Bind(R.id.phone_edit)
    EditText phone_edit;//电话
    @Bind(R.id.consignee_edit)
    EditText consignee_edit;//收货人
    @Bind(R.id.body_address_city_text)
    TextView body_address_city_text;
    @Bind(R.id.body_address_area_text)
    TextView body_address_area_text;
    @Bind(R.id.address_edit)
    EditText address_edit;//详细地址
    @Bind(R.id.baocun_btn)
    TextView baocun_btn;

    private String msadIsDefault;//是否设为默认 0-非默认 1-默认
    private static final String CityId = "20238";//默认重庆市
    private static final String chognqing = "10022";//重庆
    private PreAddAddressI mPreI;
    private BottomSheetDialog mPopChooseEducation;
    private String mEduStr;
    private List<RespQueryAreaCity.ResultBean> dataList;
    private int selecte;
    private String AddressId;

    private ListBaseAdapter<String> pop_adapter;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_edit_address_layout);
    }

    @Override
    protected void findViews() {
        initData();
    }

    @Override
    protected void setListeners() {
        mPreI = new PreAddAddressImpl(this);
        mPreI.queryAreaCity(CityId);
    }

    @Override
    protected void bindValues() {


    }

    @Override
    @OnClick({R.id.baocun_btn, R.id.body_address_area_text,R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        String Consignee = consignee_edit.getText().toString();//收货人
        String phone = phone_edit.getText().toString();
        String Num = chognqing + "," + CityId + "," + AddressId;
        String Detailed_address = address_edit.getText().toString();

        if (select_checkbox.isChecked()) {
            msadIsDefault = "1";
        } else {
            msadIsDefault = "0";
        }
        switch (v.getId()) {
            case R.id.baocun_btn:
                if (TextUtils.isEmpty(consignee_edit.getText().toString())) {
                    showToast("收货人不能为空");
                }
                else if (TextUtils.isEmpty(phone_edit.getText().toString())) {
                    showToast("联系电话不能为空");
                }
                else if (body_address_area_text.getText().toString().equals("请选择区")) {
                    showToast("请选择区");
                }
                else if (AddressId != null) {
                    mPreI.saveAddress(phone, Consignee, Detailed_address, Num, msadIsDefault);
                } else {
                    showToast("请选择区");
                }
                break;

            case R.id.body_address_area_text:
                initChooseRole01();
                break;
            case R.id.toolbar_menu_tv:
                finish();
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("新增收货地址");
        toolbar_title.setTextSize(18);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("取消");
        toolbar_menu_tv.setTextSize(16);
        toolbar_menu_tv.setTextColor(Color.parseColor("#f70052"));
    }

    @Override
    public void saveAddressSuccee(TempResponse data) {
        baocun_btn.setClickable(false);
        showToast(data.getMsg());
        finish();
    }

    @Override
    public void queryAreaCitySuccee(RespQueryAreaCity data) {
        if (data.getResult() != null) {
            dataList = data.getResult();
        }

    }

    @Override
    public void toast(String message) {
        showToast(message);

    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(e.message);
    }

    @Override
    public void showPro() {
        showProgressDialog(false);
    }

    @Override
    public void disPro() {
        dismissProgressDialog();
    }

//    /**
//     * pop  选择 区
//     */
//    private void initChooseRole() {
//        if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
//            mPopChooseEducation.dismiss();
//        }
//
//        View view = getLayoutInflater().inflate(R.layout.pop_choose_education_layout, null, false);
//
//        final PickerView pickerView = (PickerView) view.findViewById(R.id.pop_choose_education_picker);
//        Button mBtnQuit = (Button) view.findViewById(R.id.pop_choose_education_quit);
//        Button mBtnComfire = (Button) view.findViewById(R.id.pop_choose_education_ok);
//
//        mBtnComfire.setOnClickListener(listener);
//        mBtnQuit.setOnClickListener(listener);
//        List<String> strings = new ArrayList<>();
//        for (RespQueryAreaCity.ResultBean resultBean : dataList) {
//            strings.add(resultBean.getAName());
//        }
//        pickerView.setData(strings);
//        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
//            @Override
//            public void onSelect(String text) {
//                mEduStr = text;
//                for (int i = 0; i < dataList.size(); i++) {
//                    if (text.equals(dataList.get(i).getAName())) {
//                        selecte = i;
//                        AddressId = dataList.get(selecte).getAId();
//                        Log.i("区", AddressId + "," + selecte + "," + mEduStr);
//                    }
//
//                }
//            }
//        });
//        mPopChooseEducation = new BottomSheetDialog(this);
//        mPopChooseEducation.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
//
//    }
//
//    private View.OnClickListener listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.pop_choose_education_quit:
//
//                    if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
//                        mPopChooseEducation.dismiss();
//                    }
//                    break;
//                case R.id.pop_choose_education_ok:
//
//                    if (mEduStr != null && !mEduStr.equals("")) {
//                        body_address_area_text.setText(mEduStr);
//                        if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
//                            mPopChooseEducation.dismiss();
//                        }
//                    } else {
//                        showToast("请选择区");
//                    }
//                    break;
//            }
//        }
//    };

    private void initChooseRole01() {
        if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
            mPopChooseEducation.dismiss();
        }

        View view = getLayoutInflater().inflate(R.layout.pop_choose_address_layout, null, false);
        mPopChooseEducation = new BottomSheetDialog(this);
        mPopChooseEducation.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();

        RecyclerView my_address = (RecyclerView) view.findViewById(R.id.my_address);
        my_address.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pop_adapter = new ListBaseAdapter<String>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.pop_choose_address_item;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(R.id.my_tv,getDataList().get(position));
            }
        };

        List<String> strings = new ArrayList<>();
        for (RespQueryAreaCity.ResultBean resultBean : dataList) {
            strings.add(resultBean.getAName());
        }
        pop_adapter.setDataList(strings);

        pop_adapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void OnItemClick(View itemView, int position, String s) {
                mEduStr = s;
                body_address_area_text.setText(mEduStr);
                for (int i = 0; i < dataList.size(); i++) {
                    if (s.equals(dataList.get(i).getAName())) {
                        selecte = i;
                        AddressId = dataList.get(selecte).getAId();
                        Log.i("区", AddressId + "," + selecte + "," + mEduStr);
                    }
                }
                mPopChooseEducation.dismiss();
            }
        });
        my_address.setAdapter(pop_adapter);
    }
}
