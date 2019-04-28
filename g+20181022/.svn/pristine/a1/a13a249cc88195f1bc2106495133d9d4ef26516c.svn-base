package com.code.clkj.menggong.activity.comHomeDetection;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.checkIdAdapter;
import com.code.clkj.menggong.response.RespgetCheckList;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempModule.tempUtils.TempTimeUtil;
import com.lf.tempcore.tempResponse.TempResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 检查预约
 * Created by Administrator on 2017/9/8.
 */

public class ActDetection extends TempActivity implements ViewActDetectionI, OnDateSetListener {
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.mSendVerificationCode)
    TextView mSendVerificationCode;//检查预约
    @Bind(R.id.phone_num)
    EditText phone_num;
    @Bind(R.id.check_name)
    TextView check_name;//监测点
    @Bind(R.id.check_name_root)
    LinearLayout check_name_root;
    @Bind(R.id.Code)
    EditText Code;
    @Bind(R.id.tv_time)
    TextView tv_time;
    TimePickerDialog mDialogAll;

    private PopupWindow mPopupClassify;

    private TempRegexUtil tempRegexUtil;
    private TempTimeUtil tempTimeUtil;
    private PreActDetectionI mPreI;

    private String phonecode = "";
    private String mucrCkId;

    private List<RespgetCheckList.ResultBean> dataList;

    private List<String> strings;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_home_detection);
        showTime();

    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("检测预约");
    }

    @Override
    protected void setListeners() {
        mPreI = new PreActDetectionImpl(this);
        mPreI.getCheckList();
        tempRegexUtil = new TempRegexUtil();
        tempTimeUtil = new TempTimeUtil(60000, 1000, mSendVerificationCode);
        tempTimeUtil.setTickString(getString(R.string.Send));
        tempTimeUtil.setFinishString(getString(R.string.Retrieve_verification_code));

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.mSendVerificationCode, R.id.check_name_root, R.id.textView3,R.id.yy_time})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mSendVerificationCode:
                phonecode = phone_num.getText().toString();
                if (!TextUtils.isEmpty(phonecode) && tempRegexUtil.checkMobile(phonecode)) {
                    mPreI.sendForgetCode(phonecode);
                } else {
                    showToast("请输入正确的电话号码");
                }
                break;
            case R.id.check_name_root:
                if (mPopupClassify != null && mPopupClassify.isShowing()) {
                    mPopupClassify.dismiss();
                    mPopupClassify = null;
                } else {
                    initPopCheck();
                }
                break;
            case R.id.textView3:
                if (TextUtils.isEmpty(Code.getText().toString())) {
                    showToast("请输入验证码");
                }
                if (TextUtils.isEmpty(phonecode)) {
                    showToast("请输入电话号码");
                }
                mPreI.saveCheckReserve(mucrCkId, tv_time.getText().toString(), "2018-01-15 12:43:00", Code.getText().toString(), phonecode);
                break;
            case R.id.yy_time:
                mDialogAll.show(getSupportFragmentManager(), "all");
                break;
        }
    }

    //显示检查点Pop
    private void initPopCheck() {
        View view = getLayoutInflater().inflate(R.layout.menu_drop_all_order_layout, null);
        ListView listView = (ListView) view.findViewById(R.id.menu_all_order_lv);
        View dismiss = (View) view.findViewById(R.id.view_dismiss);
        //        dataList = new ArrayList<>();

        final checkIdAdapter adapter = new checkIdAdapter(dataList, ActDetection.this, R.layout.item_menu_all_order_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                check_name.setText(adapter.getData().get(position).getCkName());
                mucrCkId = adapter.getData().get(position).getCkId();
                adapter.notifyDataSetChanged();
                if (mPopupClassify != null && mPopupClassify.isShowing()) {
                    mPopupClassify.dismiss();
                    mPopupClassify = null;
                }
            }
        });
        mPopupClassify = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        mPopupClassify.setFocusable(true);
        // 设置允许在外点击消失
        mPopupClassify.setOutsideTouchable(true);
        mPopupClassify.setBackgroundDrawable(new BitmapDrawable());
        //        mPopupClassify.showAsDropDown(mPopRoot, 0, 0);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupClassify.dismiss();
            }
        });
        PopupWindowCompat.showAsDropDown(mPopupClassify, check_name_root, 0, 0, Gravity.LEFT);
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


    @Override
    public void sendForgetCodeSuccess(TempResponse data) {
        tempTimeUtil.start();
    }

    @Override
    public void getCheckListSuccess(RespgetCheckList data) {
        if (data.getResult() != null) {
            dataList = data.getResult();
        }
    }

    @Override
    public void saveCheckReserveSuccess(TempResponse data) {
        showToast(data.getMsg());
        finish();
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = getDateToString(millseconds);
        tv_time.setText(text);
    }

    private void showTime() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("时间选择")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("小时")
                .setMinuteText("分钟")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
