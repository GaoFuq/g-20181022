package com.code.clkj.menggong.activity.comDynamics;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.adapter.ImagePickerAdapter;
import com.code.clkj.menggong.response.RespSaveDynamic;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.code.clkj.menggong.widget.GlideImageLoader;
import com.code.clkj.menggong.widget.SelectDialog;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempModule.tempUtils.WeiboDialogUtils;
import com.lf.tempcore.tempUtil.QuickClickUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.lzy.imagepicker.ImagePicker.REQUEST_CODE_PREVIEW;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ActDynamicsActivity extends TempActivity {
    public static final int IMAGE_ITEM_ADD = -1;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.content)
    EditText content;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private ImagePickerAdapter adapter;
    private int maxImgCount = 6;               //允许选择图片最大数
    public static final int REQUEST_CODE_SELECT = 100;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_publishing_dynamics_layout);
        initWidget();
        initImagePicker();
    }

    @Override
    protected void findViews() {
        initdata();

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }


    String mContent;

    @OnClick({R.id.publish_btn})
    protected void OnViewClicked(View v) {
        if (QuickClickUtils.checkClick()) return;
        switch (v.getId()) {
            case R.id.publish_btn:
                mContent = content.getText().toString().toString();
                if (TextUtils.isEmpty(mContent)) {
                    showToast("发布内容不能为空");
                    return;
                }
                uploadUEImg();

                // finish();
                break;

        }
    }

    public void initdata() {
        toolbar_title.setText("发布动态");
        toolbar_title.setTextSize(15);
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));


    }

    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(ActDynamicsActivity.this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(new ImagePickerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case IMAGE_ITEM_ADD:
                        List<String> names = new ArrayList<>();
                        names.add("拍照");
                        names.add("相册");
                        showDialog(new SelectDialog.SelectDialogListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0: // 直接调起相机
                                        /**
                                         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                         *
                                         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                         *
                                         * 如果实在有所需要，请直接下载源码引用。
                                         */
                                        //打开选择,本次允许选择的数量
                                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                        Intent intent = new Intent(ActDynamicsActivity.this, ImageGridActivity.class);
                                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                                        break;
                                    case 1:
                                        //打开选择,本次允许选择的数量
                                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                        Intent intent1 = new Intent(ActDynamicsActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                        break;
                                    default:
                                        break;
                                }

                            }
                        }, names);


                        break;
                    default:
                        //打开预览
                        Intent intentPreview = new Intent(ActDynamicsActivity.this, ImagePreviewDelActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                        startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                        break;
                }

            }


            @Override
            public void ondeteleChange(ArrayList<ImageItem> mimages) {
                if (images != null) {
                    for (int i = 0; i < mimages.size(); i++) {
                        if (TextUtils.isEmpty(mimages.get(i).path)) {
                            mimages.remove(i);
                        }
                    }
                    images = mimages;
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(ActDynamicsActivity.this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
       /* else {
            TempPKHelper.onResult(this, requestCode, resultCode, data);
        }*/


    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(ActDynamicsActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!ActDynamicsActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    private String mocoImage = "";
    private Dialog mWeiboDialog;
    ArrayList<ImageItem> images = new ArrayList<>();

    public void uploadUEImg() {
        if ( images.size()==0) {
            showToast("请选择图片！");
            return;
        }
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(this, "加载中...");
        //  showProgressDialog(false);
        Map<String, RequestBody> bodyMap = new HashMap<>();
        if (images.size() > 0) {
            for (int i = 0; i < images.size(); i++) {
                File file = new File(images.get(i).path);
                if (file.exists()) {
                    bodyMap.put("file" + i + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
                }
            }
        }
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi1(TempAction.class, 30).uploadUEImg(bodyMap), new TempRemoteApiFactory.OnCallBack<ResponseUploadUEImg>() {
            @Override
            public void onSucceed(ResponseUploadUEImg data) {
                if (data.getState().equals("SUCCESS")) {
                    dismissProgressDialog();
                    mocoImage = data.getTitle();
                    intent();
                } else {
                    // Toast.makeText(ActNewTwoStore.this,"操作失败，请重试！", Toast.LENGTH_SHORT).show();
                }

            }

            private void intent() {
                TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.
                                createRemoteApi(TempAction.class)
                                .saveDynamic(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), mContent, mocoImage),
                        new TempRemoteApiFactory.OnCallBack<RespSaveDynamic>() {
                            @Override
                            public void onSucceed(RespSaveDynamic data) {
                                if (data.getFlag() == 1) {
                                    finish();
                           /* if (mViewI !=null){
                                mViewI.saveFeedBackSucess(data);
                            }*/
                                } else {/*if (mViewI !=null){
                                mViewI.toast(data.getMsg());
                            }*/
                                }
                            }

                            @Override
                            public void onCompleted() {
                                if (mWeiboDialog != null) {
                                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                                }
                                // if (mViewI !=null) mViewI.dismissPro();
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (mWeiboDialog != null) {
                                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                                }
                       /* if (mViewI !=null){
                            mViewI.dismissPro();
                            mViewI.toast(ExceptionEngine.handleException(e).message);
                        }*/
                            }
                        });
            }

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }
        });

    }
}
