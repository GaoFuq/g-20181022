package com.code.clkj.menggong.activity.comLiveApply.comLiveApplyAgain;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.SuperViewHolder;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespLiveUser;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.NullUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHandler;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHelper;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKParams;
import com.lf.tempcore.tempResponse.TempResponse;
import com.lzy.imagepicker.bean.ImageItem;
import com.rey.material.app.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by clkj on 2017/12/21.
 */

public class ActLiveApplyAgainActivity extends TempActivity implements ViewLiveApplyI, TempPKHandler {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示title
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;

    @Bind(R.id.live_sex_edit)
    TextView live_sex_edit;//性别
    @Bind(R.id.live_name_edit)
    EditText live_name_edit;

    @Bind(R.id.body_address_area_text)
    TextView body_address_area_text;

    @Bind(R.id.live_age_edit)
    EditText live_age_edit;//年龄
    @Bind(R.id.live_phone_edit)
    EditText live_phone_edit;//电话
    @Bind(R.id.idcard01)
    ImageView idcard01;//身份证正面
    @Bind(R.id.idcard02)
    ImageView idcard02;//背面

    private List<RespQueryAreaCity.ResultBean> dataList;
    private PreActLiveApplyI mPreI;
    private TempPKParams params;
    private BottomSheetDialog mPopChooseAddress, mPopChooseSex, mDialog,mPopChooseEducation;
    private String mEduStr;
    private String AddressId, AddressId02, AddressContent02;
    private ArrayList<ImageItem> imageItems;
    private int selecte;
    private int choose;
    private String IDFront, IDBack;//上传返回的图片ID
    private int type;
    private String mRegion;//区域
    private String mSex;
    private String mId;
    private ListBaseAdapter<String> pop_adapter;

    private static final String CityId = "20238";//默认重庆市

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_apply_live_layout);
    }

    @Override
    protected void findViews() {
        mId = getIntent().getStringExtra("usceId");
        params = new TempPKParams();
        params.compress = true;
        initData();
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void bindValues() {
        mPreI = new PreLiveApplyImpl(this);
        mPreI.getAchorApply(mId);
        mPreI.queryAreaCity(CityId);
    }

    @Override
    @OnClick({R.id.address, R.id.choose_sex, R.id.idcard01, R.id.idcard02, R.id.live_submit_btn})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.address:
                initChooseRole01();
                break;
            case R.id.choose_sex:
                initChooseSex();
                break;
            case R.id.idcard01:
                type = 1;
                Log.i("状态", type + "");
                initDialog();
                break;
            case R.id.idcard02:
                type = 2;
                Log.i("状态", type + "");
                initDialog();
                break;
            case R.id.live_submit_btn:

                if (live_name_edit.getText().toString().equals("")) {
                    showToast("请输入真实姓名");
                    return;
                }
                if (!live_sex_edit.getText().equals("")) {
                    if (live_sex_edit.getText().toString().equals("男") && !live_sex_edit.getText().equals("")){
                        mSex="1";
                    }else{
                        mSex="0";

                    }
                } else {
                    showToast("请选择性别");
                    return;
                }

                if (live_age_edit.getText().toString().equals("")) {
                    showToast("请输入年龄");
                    return;
                }
                if (live_phone_edit.getText().toString().equals("")) {
                    showToast("请输入电话号码");
                    return;
                }


                if (NullUtils.isEmpty(AddressId)&&NullUtils.isNotEmpty(AddressId02)) {
                    mRegion = CityId + "," + AddressId02;
                } else if (NullUtils.isNotEmpty(AddressId)) {
                    mRegion = CityId + "," + AddressId;
                }else{
                    showToast("请选择地址");
                    return;
                }

                if (IDFront == null || IDFront.equals("")) {
                    showToast("请上传您的身份证件正面");
                    return;
                }
                if (IDBack == null || IDBack.equals("")) {
                    showToast("请上传您的身份证件背面");
                    return;
                }
                if (NullUtils.isNotEmpty(imageItems)) {
                    mPreI.uploadUEImg(imageItems);
                }
//                } else {
//                mPreI.saveAchorApply(live_name_edit.getText().toString(), mSex,live_age_edit.getText().toString(),
//                        live_phone_edit.getText().toString(), mRegion, IDFront, IDBack);
//                }
                mPreI.updateAchorApply(live_name_edit.getText().toString(), mSex, live_age_edit.getText().toString(),
                        live_phone_edit.getText().toString(), mRegion, IDFront, IDBack, mId);
                break;
        }
    }

    private void initData() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(getResources().getColor(R.color.toolbar_top_color));
        toolbar_title.setText("主播申请");
        toolbar_title.setTextSize(18);

        AddressContent02 = body_address_area_text.getText().toString();
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
    public void updateAchorApplySuccess(TempResponse data) {
        toast(data.getMsg());
        finish();

    }

    @Override
    public void queryAreaCitySuccee(RespQueryAreaCity data) {
        if (data.getResult() != null) {
            dataList = data.getResult();
        }

        if (AddressContent02 != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (AddressContent02.equals(dataList.get(i).getAName())) {
                    selecte = i;
                    AddressId02 = dataList.get(selecte).getAId();
                    Log.i("区02", AddressId02 + "," + selecte);
                }
            }
        }
    }

    @Override
    public void getAchorApplySuccess(RespLiveUser data) {

            live_name_edit.setText(data.getResult().getUsceRealName());
            String sex;
            sex = data.getResult().getUsceSex();
            if (sex.equals("1")) {
                live_sex_edit.setText("男");
            } else {
                live_sex_edit.setText("女");
            }
            live_age_edit.setText(data.getResult().getUsceAge());
            live_phone_edit.setText(data.getResult().getUscePhone());
            body_address_area_text.setText(data.getResult().getUsceAddress());
            idcard01.setImageURI(Uri.parse(BaseUriConfig.makeImageUrl(data.getResult().getUsceIdentityFace())));
            idcard02.setImageURI(Uri.parse(BaseUriConfig.makeImageUrl(data.getResult().getUsceIdentityBack())));

    }


    @Override
    public void uploadUEImgSuccess(ResponseUploadUEImg data) {
        if (type == 1) {
            IDFront = data.getTitle();
            Log.i("正面", IDFront);
        } else if (type == 2) {
            IDBack = data.getTitle();
            Log.i("背面", IDBack);
        }
    }

    /**
     * pop  选择 区
     */
//    private void initChooseRole() {
//        choose = 1;
//        Log.i("状态", choose + "");
//        if (mPopChooseAddress != null && mPopChooseAddress.isShowing()) {
//            mPopChooseAddress.dismiss();
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
//                }
//            }
//        });
//        mPopChooseAddress = new BottomSheetDialog(this);
//        mPopChooseAddress.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
//
//    }

    /**
     * pop  选择 性别
     */
    private void initChooseSex() {
        mPopChooseSex = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.pop_choose_sex_layout, null);
        TextView man_tv= (TextView) view.findViewById(R.id.man_tv);
        TextView woman_tv= (TextView) view.findViewById(R.id.woman_tv);
        man_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                live_sex_edit.setText("男");
                mPopChooseSex.dismiss();
            }
        });
        woman_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                live_sex_edit.setText("女");
                mPopChooseSex.dismiss();
            }
        });
        mPopChooseSex.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_choose_education_quit:
                    if (mPopChooseAddress != null && mPopChooseAddress.isShowing()) {
                        mPopChooseAddress.dismiss();
                    }
                    break;
                case R.id.pop_choose_education_ok:
                    if (choose == 1) {
                        Log.i("状态", choose + "");
                        if (mEduStr != null && !mEduStr.equals("")) {
                            body_address_area_text.setText(mEduStr);
                            if (mPopChooseAddress != null && mPopChooseAddress.isShowing()) {
                                mPopChooseAddress.dismiss();
                            }
                        } else {
                            showToast("请选择区");
                        }
                    }
                    break;
            }
        }
    };

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

    @Override
    public void onSucceed(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(uri);
        imagePipeline.evictFromDiskCache(uri);
        imagePipeline.evictFromCache(uri);
        if (type == 1) {
            idcard01.setImageURI(uri);
        } else if (type == 2) {
            idcard02.setImageURI(uri);
        }
        Debug.info("ActPersonalInfo", "Uri in path=" + uri.getPath());
        imageItems = new ArrayList<>();
        ImageItem itemImg = new ImageItem();
        itemImg.path = uri.getPath();
        imageItems.add(itemImg);

        mPreI.uploadUEImg(imageItems);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
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
