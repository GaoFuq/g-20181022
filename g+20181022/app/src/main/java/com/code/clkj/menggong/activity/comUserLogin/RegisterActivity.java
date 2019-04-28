package com.code.clkj.menggong.activity.comUserLogin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.activity.comHome.ActHome;
import com.code.clkj.menggong.response.RespActRegister;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.widget.GlideImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fm.openinstall.OpenInstall;
import com.google.gson.Gson;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempModule.tempUtils.TempTimeUtil;
import com.lf.tempcore.tempModule.tempUtils.WeiboDialogUtils;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;
import com.lf.tempcore.tempResponse.TempResponse;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.rey.material.app.BottomSheetDialog;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 注册界面
 */
public class RegisterActivity extends TempActivity implements ViewActRegisterI {

    @Bind(R.id.mRegister)
    TextView mRegister;  //注册按钮
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.mSendVerificationCode)
    Button mSendVerificationCode;
    @Bind(R.id.phone_number)
    EditText phone_number;//手机号码
    @Bind(R.id.mVerificationCode)
    EditText mVerificationCode;//验证码
    @Bind(R.id.mNewPassdWord)
    EditText mNewPassdWord; //密码
    @Bind(R.id.mInviteID)
    EditText mInviteID; //邀请人ID\

    @Bind(R.id.act_person_info_head)
    SimpleDraweeView act_person_info_head; //邀请人ID\

    public static final int REQUEST_CODE_SELECT = 100;

    private TempRegexUtil tempRegexUtil = new TempRegexUtil();
    private static final String[] PERMISSION_STARTSPOT = new String[]{"android.permission.CAMERA"};
    private PreActRegisterI mPreI;
    private TempTimeUtil mTempTimeUtil;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 1;//允许选择图片最大数

    private  String    museImage;
    private String museSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register_layout);


    }
    private void initWidget() {
        selImageList = new ArrayList<>();
    }
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
       /* imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素*/
    }
    @Override
    protected void findViews() {
        initWidget();
        initImagePicker();
        initdata();
    }

    @Override
    protected void setListeners() {
        mPreI = new PreActRegisterImpl(this);
        mTempTimeUtil = new TempTimeUtil(60000, 1000, mSendVerificationCode);
        mTempTimeUtil.setTickString(getString(R.string.Send));
        mTempTimeUtil.setFinishString(getString(R.string.Retrieve_verification_code));
    }
    private BottomSheetDialog mDialog;
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

                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(RegisterActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                       // intent.putExtra("fromPhoto", "persion");//跳转相机不剪裁
                        startActivityForResult(intent, REQUEST_CODE_SELECT);

                    break;
                case R.id.pop_choose_pic_layout:

                    /*    if (PermissionUtils.hasSelfPermissions(ActPersonDetailActivity.this, PERMISSION_STARTSPOT)) {*/
                    //打开选择,本次允许选择的数量
                    //                    ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                    Intent intent2 = new Intent(RegisterActivity.this, ImageGridActivity.class);
                    //  intent1.putExtra("fromPhoto", "persion");
                    /* 如果需要进入选择的时候显示已经选中的图片，
                     * 详情请查看ImagePickerActivity
                     * */
                    startActivityForResult(intent2, REQUEST_CODE_SELECT);
               /*     } else {
                        if (!(checkPermission(Manifest.permission.CAMERA))) {
                            ActivityCompat.requestPermissions(ActPersonDetailActivity.this, new String[]{Manifest.permission.CAMERA}, ImageGridActivity.REQUEST_PERMISSION_CAMERA);
                        } else {
                            //打开选择,本次允许选择的数量
                            //                    ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                            Intent intent1 = new Intent(ActPersonDetailActivity.this, ImageGridActivity.class);
                            intent1.putExtra("fromPhoto", "persion");
                            *//* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * *//*
                            startActivityForResult(intent1, REQUEST_CODE_SELECT);
                           *//* new AlertDialog.Builder(ActPersonDetailActivity.this)
                                    .setTitle("Permission to apply")
                                    .setMessage("Allow Razer Pay to access to album")
                                    .setPositiveButton(R.string.imtrue, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions(ActPersonDetailActivity.this, PERMISSION_STARTSPOT, REQUEST_STARTSPOT);
                                        }
                                    })
                                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();*//*
                        }
*/

                  //  startActivityForResult(TempPKHelper.makeGalleryIntent(params), TempPKHelper.TEMP_REQUEST_CODE_GALLERY);
                    break;
                //选择图片结束
            }
        }
    };

    @Override
    protected void bindValues() {

        act_person_info_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });
        act_person_info_head.setImageResource(R.mipmap.yhu_1);
    }

    String phone;
    String password;
    String registercheck;
    String phonecode = "";
    String museRegisterCode;
    @Bind(R.id.zero_btn)
    TextView zero_btn;
    @Bind(R.id.zero_five_btn)
    TextView zero_five_btn;
    @Bind(R.id.one_btn)
    TextView one_btn;
    @Bind(R.id.all_btn)
    TextView all_btn;

    @Override
    @OnClick({R.id.mRegister, R.id.mSendVerificationCode,R.id.zero_btn,R.id.zero_five_btn,R.id.one_btn,R.id.all_btn})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mRegister:
                phone = phone_number.getText().toString();//手机号
                registercheck = mVerificationCode.getText().toString();//验证码
                password = mNewPassdWord.getText().toString();//密码
                museRegisterCode = mInviteID.getText().toString();//邀请码
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入电话号码");
                    return;
                }
                if (!tempRegexUtil.checkMobile(phone)) {
                    showToast("请输入正确的电话号码");
                    return;
                }
                if (TextUtils.isEmpty(registercheck)) {
                    showToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }

                mPreI.appUserRegister(phone, password, registercheck,museRegisterCode,museImage,museSex);
                break;
            case R.id.mSendVerificationCode:
                phonecode = phone_number.getText().toString();
                if (TextUtils.isEmpty(phonecode)) {
                    showToast("请输入手机号");
                    return;
                }
                mPreI.registerCode(phonecode);
                break;
            case R.id.zero_btn:
                zero_btn.setTextColor(Color.parseColor("#f5427f"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                museSex   = zero_btn.getText().toString();
                break;
            case R.id.zero_five_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#f5427f"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                museSex   = zero_five_btn.getText().toString();
                break;
            case R.id.one_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#f5427f"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                all_btn.setTextColor(Color.parseColor("#848484"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                museSex   = one_btn.getText().toString();
                break;
            case R.id.all_btn:
                zero_btn.setTextColor(Color.parseColor("#848484"));
                zero_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                zero_five_btn.setTextColor(Color.parseColor("#848484"));
                zero_five_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                one_btn.setTextColor(Color.parseColor("#848484"));
                one_btn.setBackgroundResource(R.drawable.shap_btn_gray);
                all_btn.setTextColor(Color.parseColor("#f5427f"));
                all_btn.setBackgroundResource(R.drawable.shap_btn_pink);
                museSex = "2";
                break;
        }

    }

    public void initdata() {
        toolbar_title.setText("注册");
        toolbar_title.setTextSize(18);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));
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
    public void appUserRegisterSuccess(RespActRegister data) {
        //用户注册成功后调用
        OpenInstall.reportRegister();
        mPreI.userLogin(phone, password);

    }

    @Override
    public void registerCodeSuccess(TempResponse data) {
        mTempTimeUtil.start();
    }

    @Override
    public void userLoginSuccess(ResponseLoginInfo data) {
        TempLoginConfig.sf_savePwd(password);
        startActivity(new Intent(this, ActHome.class));
        finish();
    }
    ArrayList<ImageItem> images = null;
    Map<String, RequestBody> map;
    Dialog mWeiboDialog;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    ImagePicker.getInstance().getImageLoader().fresscoImage(RegisterActivity.this, selImageList.get(0).path, act_person_info_head, 100, 100);
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                     mWeiboDialog = WeiboDialogUtils.createLoadingDialog(RegisterActivity.this, "正在上传...");

                     ArrayList<ImageItem>    filePath = new ArrayList<>();
                    ImageItem itemImg = new ImageItem();
                    itemImg.path =  selImageList.get(0).path;
                    filePath.add(itemImg);
                    //图片上传多张，返回结果还是第一张，后台有问题
                     map = new HashMap<>();
                    for (int i = 0; i < filePath.size(); i++) {
                        Debug.error("filePath" + i + " :" + filePath.get(i).path);
                        File currentFile = new File(filePath.get(i).path);
                        if (currentFile.exists()) {
                            RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), currentFile);
                            String[] arraypath = filePath.get(i).path.split("/");
                            String name = arraypath[arraypath.length - 1];
                            Debug.info("file name=" + name);
                            map.put("image\"; filename=\"" + name, fileBody);
                        } else {
                            showToast(filePath + "文件不存在");
                            }
                        }
                    }


                    TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).uploadUEImg(map), new TempRemoteApiFactory.OnCallBack<ResponseUploadUEImg>() {
                        @Override
                        public void onSucceed(ResponseUploadUEImg data) {
                            mWeiboDialog.dismiss();
                            if (data.getState().equals("SUCCESS")) {
                                Log.i("uploadUEImg", "onSucceed: " + new Gson().toJson(data));
                                 museImage   = data.getTitle();
                               /* if (mView != null) {
                                    mView.uploadUEImgSuccess(data);
                                }*/
                            } else {
                               /* if (mView != null) {
                                    mView.toast("操作失败，请重试！");
                                }*/
                            }
                        }

                        @Override
                        public void onCompleted() {
                          //  if (mView!=null) mView.disPro();
                            mWeiboDialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("uploadUEImg","onError:"+e.getMessage());
                            mWeiboDialog.dismiss();
                          /*  if (mView != null) {
                                mView.disPro();mView.showConntectError(ExceptionEngine.handleException(e));
                            }*/

                        }
                    });
                //
                  /*  filePath = new String[]{selImageList.get(0).path};
                 //   Logger.e("Head_Image:" + filePath[0]);
                    save_Image_Path = filePath[0];
                    for (int i = 0; i < filePath.length; i++) {
                        File image_file = new File(filePath[i]);
                        if (image_file.exists()) {
                            try {
                                File image_file01 = new Compressor(this).compressToFile(image_file);
                                MultipartBody.Builder builder = new MultipartBody.Builder()
                                        .setType(MultipartBody.FORM)//表单类型
                                        .addFormDataPart("avatar", "");
                                RequestBody imageBody = RequestBody.create(MediaType.parse("image/png"), image_file01);
                                String[] arraypath = filePath[i].split("/");
                                String name = arraypath[arraypath.length - 1];
                               // Logger.e("Head_Image:" + name);
                                builder.addFormDataPart("avatar_file", name, imageBody);//imgfile 后台接收图片流的参数名
                                List<MultipartBody.Part> parts = builder.build().parts();
                                requestPostHeadImage(parts);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            showToast(filePath + "文件不存在");
                        }
                    }*/
                }
            }
        }

}
