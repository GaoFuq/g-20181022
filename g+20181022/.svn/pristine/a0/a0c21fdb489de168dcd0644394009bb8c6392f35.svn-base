package com.code.clkj.menggong.activity.comAnchorStore;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreDetail.PreActCommodityDetailsActivityI;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreDetail.PreActCommodityDetailsActivityImpl;
import com.code.clkj.menggong.activity.comAnchorStore.anchorStroreDetail.ViewActCommodityDetailsActivityI;
import com.code.clkj.menggong.activity.comOrder.ActOrder;
import com.code.clkj.menggong.adapter.GoodsDetailCommentImgAdapter;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.base.config.Constance;
import com.code.clkj.menggong.holder.NetworkImageHolderView1;
import com.code.clkj.menggong.response.RespQueryMallGoodsDetail;
import com.code.clkj.menggong.response.RespToConfirmOrder;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempWebComponment.TempWebHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/15.
 */

/**
 * 商品详情
 */
public class ActCommodityDetailsActivity extends TempActivity implements ViewActCommodityDetailsActivityI {
    //    @Bind(R.id.toolbar_top)
//    Toolbar toolbar_top;
    @Bind(R.id.buy_btn)
    Button buy_btn;
    @Bind(R.id.comment_img)
    ImageView comment_img;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    private Integer[] banners = {
            R.mipmap.cpxq_2
    };

    @Bind(R.id.store_goods_name)
    TextView store_goods_name;//商品名字
    @Bind(R.id.store_goods_price)
    TextView store_goods_price;//折扣价
    @Bind(R.id.store_goods_old_price)
    TextView store_goods_old_price;//原价

    @Bind(R.id.store_goods_num)
    TextView store_goods_num;//销售数量

    @Bind(R.id.comment_total_count)
    TextView comment_total_count;//评论总数

    @Bind(R.id.comment_preson_head_img)
    ImageView comment_preson_head_img;//评论人头像

    @Bind(R.id.comment_preson_nickname)
    TextView comment_preson_nickname;//评论人昵称

    @Bind(R.id.comment_date)
    TextView comment_date;//评论时间

    @Bind(R.id.comment_content)
    TextView comment_content;//评论内容

    @Bind(R.id.act_web)
    WebView act_web;

    @Bind(R.id.comment_img_rcy)
    RecyclerView comment_img_rcy;//评论图片

    @Bind(R.id.comment_ly)
    LinearLayout comment_ly;

    @Bind(R.id.good_number_subtract)
    ImageView good_number_subtract;//减少商品数量

    @Bind(R.id.goods_number_add)
    ImageView goods_number_add;//增加商品数量

    @Bind(R.id.comment_detail_more)
    LinearLayout comment_detail_more;//查看更多评价

    @Bind(R.id.goods_count)
    TextView goods_count;//商品数量
    int count = 0;
    private GoodsDetailCommentImgAdapter mAdapter;
    private TempWebHelper mHelper;
    private PreActCommodityDetailsActivityI mPreI;
    List<RespQueryMallGoodsDetail.ResultBean.GoodsImageBean> list;
    private RespQueryMallGoodsDetail mData;
    String goodId;//商品id
    String commentTotalNumber;//评论总数
    private List<String> imageList;
    private String MaxNum;
    private int goodCount;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_commodity_details_layout);
    }

    @Override
    protected void findViews() {

    }


//        initAd(new ArrayList<int>());
//        initAd(new ArrayList<RespQueryMallGoodsDetail.ResultBean.GoodsImageBean>());

    @Override
    protected void setListeners() {
//        Toolbar toolbar_top = (Toolbar) findViewById(R.id.toolbar_top);
//        if (toolbar_top != null) {
//
//            toolbar_top.setNavigationIcon(R.mipmap.back);
//        }
        mAdapter = new GoodsDetailCommentImgAdapter(this);
        comment_img_rcy.setAdapter(mAdapter);

    }

    @Override
    protected void bindValues() {
        goodId = getIntent().getStringExtra("goodId");
        mPreI = new PreActCommodityDetailsActivityImpl(this);
        mPreI.queryMallGoodsDetail(goodId);

        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
//        initAd(new ArrayList<Integer>());

        addWed();//加载web页面
    }

    private void addWed() {
        String url = (BaseUriConfig.GOODS_DETAIL + "mgooId =" + goodId).replaceAll(" ","");//url地址
        if (mHelper == null) {
            mHelper = new TempWebHelper(act_web, getTempContext());
            Debug.error("-------------url-------" + url);
            if (url.startsWith("http://") || url.startsWith("https://")) {
                mHelper.loadWeb(url);
            } else {
                mHelper.loadWeb("http://" + url);
            }
//            mHelper.loadWeb(url);
        }
    }


    @OnClick({R.id.comment_detail_more, R.id.buy_btn, R.id.good_number_subtract, R.id.goods_number_add})
    @Override
    protected void OnViewClicked(View v) {
//        goodCount = TempDataUtils.string2Int(goods_count.getText().toString());
        switch (v.getId()) {
            case R.id.comment_detail_more:
                Intent intent = new Intent(this, ActCommentActivity.class);

                intent.putExtra("commentTotalNumber", commentTotalNumber);
                intent.putExtra("goodId", goodId);
                startActivity(intent);
                break;

            case R.id.good_number_subtract://减少商品数量
                if (goodCount == 0) {
                    return;
                }
                goodCount--;
                goods_count.setText(String.valueOf(goodCount));
                Log.i("goodCount", goodCount + "");
                break;

            case R.id.goods_number_add://增加商品数量
                goodCount++;
                goods_count.setText(String.valueOf(goodCount));
                Log.i("goodCount", goodCount + "");
                break;

            case R.id.buy_btn:
//                mPreI.toConfirmOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), goodId, goods_count);
                // // TODO: 2018/1/6 商品数量暂时写死 
//                mPreI.toConfirmOrder(TempLoginConfig.sf_getSueid(), TempLoginConfig.sf_getPwd(), goodId, String.valueOf(goodCount));
                if (goodCount != 0) {
                    Intent orderIntent = new Intent(this, ActOrder.class);
                    orderIntent.putExtra(Constance.GOODID, goodId);
                    orderIntent.putExtra(Constance.GOODCOUNT, String.valueOf(goodCount));
                    startActivity(orderIntent);
                    break;
                } else {
                    showToast("请输入数量");
                }
        }
    }
//                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//        //设置翻页的效果，不需要翻页效果可用不设
//        ;
//
//    }


//    /**
//     * 初始化广告滚动资源
//     * @param banners
//     */
//    private void initAd(final List<Integer> banners) {
//        Collections.addAll(banners, this.banners);
//        // 网络加载例子
//        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
//            @Override
//            public NetworkImageHolderView createHolder() {
//                return new NetworkImageHolderView();
//            }
//        }, banners) //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.mipmap.zye_3, R.mipmap.zye_2})
//
//                .setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                       /* if (NullUtils.isEmpty(banners.get(position).getLikjLink())) {
//                            return;
//                        }
//                        if (banners.get(position).getLikjLink().equals("2")) {
//                            Intent intent = new Intent();
//                            intent.setAction("android.intent.action.VIEW");
//                            Uri content_url = Uri.parse(banners.get(position).getLikjValue());
//                            intent.setData(content_url);
//                            startActivity(intent);
//                            return;
//                        }
//                        if (NullUtils.isEmpty(banners.get(position).getLikjType())) {
//                            return;
//                        }
//                        switch (banners.get(position).getLikjType()) {//1.图片广 告2.关联店铺，3.关联商品
//                            case "1":
//                                break;
//                            case "2":
//                                String shopId = banners.get(position).getLikjValue();
//                                if (TempLoginConfig.sf_getLoginState())
//                                    ActShopDetails.startActivityIntent(getActivity(), shopId);
//                                else startActivity(new Intent(getActivity(), ActLogin.class));
//                                break;
//                            case "3":
//                                String goodId = banners.get(position).getLikjValue();
//                                ActGoodDetail.startActivityIntent(getActivity(), goodId, null);//需要返回起送价
//                                break;
//                            case "4":
//                                break;
//                        }*/
//                    }
//                })
//                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//        //设置翻页的效果，不需要翻页效果可用不设
//        ;
//
//    }

    private void initAd(final ConvenientBanner player, final List<String> data) {
//        initImageLoader();
        // 网络加载例子
        player.setPages(new CBViewHolderCreator<NetworkImageHolderView1>() {
            @Override
            public NetworkImageHolderView1 createHolder() {
                return new NetworkImageHolderView1();
            }
        }, data) //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.zye_3, R.mipmap.zye_2})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        if (mData == null) {
                            mPreI.queryMallGoodsDetail(goodId);
                        } else {
                            String image = data.get(position);
                            List<RespQueryMallGoodsDetail.ResultBean.GoodsImageBean> dataEntityList = mData.getResult().getGoodsImage();
                            for (int i = 0; i < dataEntityList.size(); i++) {
                                if (TempURIConfig.makeImageUrl(dataEntityList.get(i).getImg()).equals(image)) {
//                                    String advId = dataEntityList.get(i).getAdvId();//广告Id
//                                    String advType = dataEntityList.get(i).getAdvType();//1 广告（广告详情接口） 2 活动(红包详情接口)
//                                    String advCreateTime = dataEntityList.get(i).getAdvCreateTime();
//                                    if (advType != null && advType.equals("1")) {
//
//                                        ActWeb.startActivityIntent(getContext(), "" , BaseUriConfig.ADVER_DETAIL
//                                                + "advId="+ advId + "&"
//                                                + "advType=" +advType);
//
//                                    } else {
//                                        ActWeb.startActivityIntent(getContext(), " ", BaseUriConfig.HONGBAO_DETAIL
//                                                + "museId=" + TempLoginConfig.sf_getSueid() + "&"
//                                                + "musePassword=" + TempLoginConfig.sf_getPwd() + "&"
//                                                + "advId="+ advId + "&"
//                                                + "advType=" +advType);
//
//                                    }
                                }
                            }
                        }

                    }
                })
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
        //设置翻页的效果，不需要翻页效果可用不设
        ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
    }


    @Override
    public void onLoadFinish() {

    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {

    }

    @Override
    public void onLoadDataSuccess() {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void queryMallGoodsDetailSuccess(RespQueryMallGoodsDetail data) {
        list = data.getResult().getGoodsImage();
//        MaxNum = data.getResult()
        if (data.getResult().getComment().getCommentTotal()!=null)
        commentTotalNumber = data.getResult().getComment().getCommentTotal();

        if (!TextUtils.isEmpty(data.getResult().getGoods().getMgooName())) {
            store_goods_name.setText(data.getResult().getGoods().getMgooName());
        }
        if (!TextUtils.isEmpty(data.getResult().getGoods().getDiscountPrice())) {
            store_goods_price.setText("￥" + data.getResult().getGoods().getDiscountPrice());
        }
        if (!TextUtils.isEmpty(data.getResult().getGoods().getMgooPrice())) {
            store_goods_old_price.setText("￥" + data.getResult().getGoods().getMgooPrice());
            store_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线

        }
        if (!TextUtils.isEmpty(data.getResult().getGoods().getMgooStockNum())) {
            store_goods_num.setText("已售" + data.getResult().getGoods().getMgooStockNum());
        }
        if (!TextUtils.isEmpty(data.getResult().getComment().getCommentTotal() + "")) {
            comment_total_count.setText("评价" + "(" + data.getResult().getComment().getCommentTotal() + ")");
//            if ( data.getResult().getComment().getCommentTotal()=="0") {
//                comment_ly.setVisibility(View.GONE);
//            }
        }
        if (!TextUtils.isEmpty(data.getResult().getComment().getComment().getMuseNickName())) {
            comment_preson_nickname.setText(data.getResult().getComment().getComment().getMuseNickName());
        }
        if (!TextUtils.isEmpty(data.getResult().getComment().getComment().getMuseId())) {

        }
        if (!TextUtils.isEmpty(data.getResult().getComment().getComment().getContent())) {
            comment_content.setText(data.getResult().getComment().getComment().getContent());
        }
        if (data.getResult().getComment().getComment().getMuseImage() != null) {
            Glide.with(ActCommodityDetailsActivity.this)
                    .load(TempURIConfig.makeImageUrl(data.getResult().getComment().getComment().getMuseImage()))
                    .into(comment_preson_head_img);
        }
        //评论时间
        if (!TextUtils.isEmpty(data.getResult().getComment().getComment().getCreatTime())) {
            comment_content.setText(data.getResult().getComment().getComment().getCreatTime());
        }
        this.mData = data;
        imageList = new ArrayList<>();
        Observable.from(data.getResult().getGoodsImage()).map(new Func1<RespQueryMallGoodsDetail.ResultBean.GoodsImageBean, String>() {
            @Override
            public String call(RespQueryMallGoodsDetail.ResultBean.GoodsImageBean goodsImageBean) {
                return TempURIConfig.makeImageUrl(goodsImageBean.getImg());
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                initAd(convenientBanner, imageList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                imageList.add(s);

            }
        });
    }

    @Override
    public void toConfirmOrderSuccess(RespToConfirmOrder data) {


    }

    @Override
    public void dismissPro() {

    }
}
