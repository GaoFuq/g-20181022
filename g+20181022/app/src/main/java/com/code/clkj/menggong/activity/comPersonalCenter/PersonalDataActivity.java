package com.code.clkj.menggong.activity.comPersonalCenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespPersonalDataActivity;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHandler;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHelper;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKParams;
import com.lf.tempcore.tempModule.tempUtils.TempPermissionUtil;
import com.lf.tempcore.tempResponse.TempResponse;
import com.lzy.imagepicker.bean.ImageItem;
import com.rey.material.app.BottomSheetDialog;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 个人资料界面
 */
public class PersonalDataActivity extends TempActivity implements ViewPersonalDatActI, TempPKHandler {

    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.nickname_tv)
    EditText nickname_tv;//昵称
    @Bind(R.id.autograph_tv)
    EditText autograph_tv;//签名
    @Bind(R.id.role_tv)
    TextView role_tv;//角色
    @Bind(R.id.birthday_tv)
    TextView birthday_tv;//生日
    @Bind(R.id.body_address_area_text)
    TextView body_address_area_text;//区
    @Bind(R.id.head_img)
    SimpleDraweeView head_img;//头像
    private static final String CityId = "20238";//默认重庆市

    private static final String County = "10022";//重庆
    private ListBaseAdapter<String> pop_adapter;

    private BottomSheetDialog mDialog, mPopChooseDate, mPopChooseEducation, mPopChooseRole;
    private TempPKParams params;
    private android.widget.DatePicker DatePicker;
    private Calendar mCalendar;
    private String mBirthdayStr, mEduStr2;
    private String mSaveImagePath = "init";
    private List<RespQueryAreaCity.ResultBean> dataList;
    private ArrayList<ImageItem> imageItems;
    private String AddressId, AddressId2;
    private int selecte;
    private String imageTitle;
    private String mdafang;

    private String mNickname;//昵称
    private String mRegion;//区域
    private String mBirthday;//生日
    private String mSex;//角色
    private String mSignature;//签名
    private String mEduStr;

    //检查权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERSONINFO_CODE = 100;

    private PrePersonalDatActI mView;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_personal_data_layout);
        TempPermissionUtil.requestCameraPermission(this, 0);

        if (savedInstanceState != null) {
            mSaveImagePath = savedInstanceState.getString("saveImagePath");
            Debug.info("ActPersonalInfo", "mSaveImagePath=" + mSaveImagePath);
        }
        setKeyboardAutoHide(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //6.0后动态申请权限
        requestMyPermissions();
    }

    private void requestMyPermissions() {
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this, "拍照需要开启相机权限", PERSONINFO_CODE, permissions);
        }
    }


    @Override
    protected void findViews() {
        params = new TempPKParams();
        params.compress = true;
        initData();
    }

    @Override
    protected void setListeners() {
        mView = new PrePersonalDatActImpl(this);
        mView.queryMemberInfoById();
        mView.queryAreaCity(CityId);
    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.head_img, R.id.my_birthday, R.id.role_tv, R.id.region, R.id.baocun_btn})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.head_img:
                initDialog();
                break;
            case R.id.my_birthday:
                initChooseDate();
                break;
            case R.id.role_tv:
                initChooseRole();
                break;
            case R.id.region:
//                initChooseRegion();
                initChooseRole01();
                break;
            case R.id.baocun_btn:
                if (!nickname_tv.getText().equals("")) {
                    mNickname = nickname_tv.getText().toString();
                } else {
                    showToast("请输入昵称");
                    return;
                }
                if (nickname_tv.getText().toString()!=null) {
                    mNickname = nickname_tv.getText().toString();
                } else {
                    showToast("请输入昵称");
                    return;
                }
                if (!birthday_tv.getText().equals("")) {
                    mBirthday = birthday_tv.getText().toString();
                } else {
                    showToast("请输入出生日期");
                    return;
                }
                if (NullUtils.isNotEmpty(AddressId)) {
                    mRegion = County + "," + CityId + "," + AddressId;
                } else if (NullUtils.isNotEmpty(AddressId2) && NullUtils.isEmpty(AddressId)) {
                    mRegion = AddressId2;
                } else {
                    showToast("请选择地址");
                    return;
                }

                mSex = role_tv.getText().toString();
                mSignature = autograph_tv.getText().toString();
                //                mView.updateMember(mNickname,mRegion,mBirthday,mSex,mSignature,);
                mView.updateMember(mNickname, mRegion, mBirthday, mSex, mSignature, imageTitle);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("个人资料");
        toolbar_title.setTextSize(18);
    }

    @Override
    public void onSucceed(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(uri);
        imagePipeline.evictFromDiskCache(uri);
        imagePipeline.evictFromCache(uri);

        head_img.setImageURI(uri);
        Debug.info("ActPersonalInfo", "Uri in path=" + uri.getPath());
        Log.i("照片", mSaveImagePath);
        imageItems = new ArrayList<>();
        ImageItem itemImg = new ImageItem();
        itemImg.path = uri.getPath();
        imageItems.add(itemImg);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (NullUtils.isNotEmpty(imageItems)) {
            mView.uploadUEImg(imageItems);
        }
    }

    @Override
    public void onCancel() {
        showToast("已取消当前操作！");
        Debug.info("ActPersonalInfo", "onCancel");
    }

    @Override
    public void onFailed(String message) {
        showToast("已取消当前操作！");
        Debug.info("ActPersonalInfo", "onCancel");
    }

    @Override
    public TempPKParams getPkParams() {
        return params;
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        if (getPkParams() != null)
            TempPKHelper.clearCachedByUri(getPkParams().uri);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TempPKHelper.TEMP_REQUEST_CODE_CAMERA || requestCode == TempPKHelper.TEMP_REQUEST_CODE_GALLERY) {
            TempPKHelper.onResult(this, requestCode, resultCode, data);
        }
    }

    /**
     * 选择照片
     */
    private void initDialog() {

        mDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.act_pick_photos_layout, null);
        view.findViewById(R.id.pop_quit_layout).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_take_pic_layout).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_choose_pic_layout).setOnClickListener(clickListener);
        mDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //选择图片开始
                case R.id.pop_quit_layout:
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    break;
                case R.id.pop_take_pic_layout:
                    startActivityForResult(TempPKHelper.makeCaptureIntent(params), TempPKHelper.TEMP_REQUEST_CODE_CAMERA);
                    break;
                case R.id.pop_choose_pic_layout:
                    startActivityForResult(TempPKHelper.makeGalleryIntent(params), TempPKHelper.TEMP_REQUEST_CODE_GALLERY);
                    break;
                //选择图片结束
            }
        }
    };

    /**
     * pop  选择 角色
     */
    private void initChooseRole() {

        mPopChooseRole = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.pop_choose_role, null);
        TextView one_tv = (TextView) view.findViewById(R.id.one_tv);
        TextView two_tv = (TextView) view.findViewById(R.id.two_tv);
        TextView three_tv = (TextView) view.findViewById(R.id.three_tv);
        one_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role_tv.setText("1");
                mPopChooseRole.dismiss();
            }
        });
        two_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role_tv.setText("0.5");
                mPopChooseRole.dismiss();
            }
        });
        three_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role_tv.setText("0");
                mPopChooseRole.dismiss();
            }
        });
        mPopChooseRole.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }

    String birthday = "";

    /**
     * 选择生日
     */
    private void initChooseDate() {

        mCalendar = Calendar.getInstance();
        if (TempLoginConfig.sf_getBirthday() != null) {
            birthday = TempLoginConfig.sf_getBirthday();
            if (birthday == null || birthday.equals("")) {

            } else {
                birthday = birthday_tv.getText().toString();
            }
        }

        if (birthday != null && !birthday.equals("")) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = formater.parse(birthday);
                mCalendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        View view = getLayoutInflater().inflate(R.layout.pop_choose_date_layout, null, false);

        DatePicker = (DatePicker) view.findViewById(R.id.pop_choose_date_datepicker);
        Button mBtnQuit = (Button) view.findViewById(R.id.pop_choose_date_quit);
        Button mBtnComfire = (Button) view.findViewById(R.id.pop_choose_date_ok);

        mBtnComfire.setOnClickListener(listener);
        mBtnQuit.setOnClickListener(listener);
        setDatePickerDividerColor(DatePicker);

        DatePicker.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mounth = monthOfYear + 1;
                String mounthStr;
                if (mounth < 10) {
                    mounthStr = "0" + mounth;
                } else {
                    mounthStr = mounth + "";
                }
                String day = dayOfMonth + "";
                if (dayOfMonth < 10) {
                    day = "0" + dayOfMonth;
                }
                mBirthdayStr = year + "-" + mounthStr + "-" + day;
            }
        });

        mPopChooseDate = new BottomSheetDialog(this);
        mPopChooseDate.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }

//    /**
//     * pop  选择 区
//     */
//    private void initChooseRegion() {
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
//                mEduStr2 = text;
//                for (int i = 0; i < dataList.size(); i++) {
//                    if (text.equals(dataList.get(i).getAName())) {
//                        selecte = i;
//                        AddressId = dataList.get(selecte).getAId();
//                        Log.i("区", AddressId + "," + selecte + "," + mEduStr2);
//                    }
//                }
//
//            }
//        });
//        mPopChooseEducation = new BottomSheetDialog(this);
//        mPopChooseEducation.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
//
//    }

    /**
     * pop view 监听处理
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

//                case R.id.pop_choose_education_quit:
//
//                    if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
//                        mPopChooseEducation.dismiss();
//                    }
//                    break;
//                case R.id.pop_choose_education_ok:
//                    if (mEduStr2 != null && !mEduStr2.equals("")) {
//                        body_address_area_text.setText(mEduStr2);
//                        if (mPopChooseEducation != null && mPopChooseEducation.isShowing()) {
//                            mPopChooseEducation.dismiss();
//                        }
//                    }
//                    break;
                case R.id.pop_choose_date_quit:
                    if (mPopChooseDate != null && mPopChooseDate.isShowing()) {
                        mPopChooseDate.dismiss();
                    }
                    break;
                case R.id.pop_choose_date_ok:
                    if (mBirthdayStr != null && !mBirthdayStr.equals("")) {
                        birthday_tv.setText(mBirthdayStr);
                        if (mPopChooseDate != null && mPopChooseDate.isShowing()) {
                            mPopChooseDate.dismiss();
                        }
                    } else {
                        showToast("请选择生日");
                    }
                    break;
            }
        }
    };

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
                holder.setText(R.id.my_tv, getDataList().get(position));
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
    public void queryMemberInfoByIdSuccee(RespPersonalDataActivity data) {
        head_img.setImageURI(BaseUriConfig.makeImageUrl(data.getResult().getMuseImage()));
        nickname_tv.setText(data.getResult().getMuseNickName());
        autograph_tv.setText(data.getResult().getMuseSignature());
        role_tv.setText(data.getResult().getMuseSex());
        birthday_tv.setText(data.getResult().getMuseBirthday());
        body_address_area_text.setText(data.getResult().getAddress());

        AddressId2 = data.getResult().getMuseAddress().trim();
    }

    @Override
    public void queryAreaCitySuccee(RespQueryAreaCity data) {
        if (data.getResult() != null) {
            dataList = data.getResult();
        }

//        Log.i("mdafang",mdafang);
//        for (int i = 0; i < dataList.size(); i++) {
//            if (mdafang.equals(dataList.get(i).getAName())) {
//                selecte = i;
//                AddressId2 = dataList.get(selecte).getAId();
//                Log.i("AddressId2", AddressId2);
//            }
//        }
    }

    @Override
    public void updateMemberSuccee(TempResponse data) {
        toast(data.getMsg());
        TempLoginConfig.sf_saveNickName(mNickname);
        if (NullUtils.isNotEmpty(imageTitle))
            TempLoginConfig.sf_saveLoginImage(imageTitle);
        finish();
    }

    @Override
    public void uploadUEImgSuccess(ResponseUploadUEImg data) {
        imageTitle = data.getTitle();
        showToast("头像上传成功!");
//        mView.updateMember(mNickname, mRegion, mBirthday, mSex, mSignature, imageTitle);

    }

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    private void setDatePickerDividerColor(DatePicker datePicker) {
        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);
        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = null;
            try {
                picker = (NumberPicker) mSpinners.getChildAt(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (picker == null) {
                continue;
            }
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(getResources().getColor(R.color.common_color_yellow)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

}
