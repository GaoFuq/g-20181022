package com.code.clkj.menggong.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.code.clkj.menggong.R;
import com.code.clkj.menggong.action.TempAction;
import com.code.clkj.menggong.activity.comWeb.ActWeb;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.holder.NetworkImageHolderView1;
import com.code.clkj.menggong.response.RespGetAverPage;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempConfig.TempURIConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者: CoolTone
 * 描述: 聊天页面
 */
public class ConversationActivity extends FragmentActivity {
    private TextView mName;
    private ConvenientBanner  convenientBanner;
    private  String sId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        mName = (TextView) findViewById(R.id.name);

        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

         sId = getIntent().getData().getQueryParameter("targetId");   // targetId:单聊即对方ID，群聊即群组ID
        String sName = getIntent().getData().getQueryParameter("title");    // 获取昵称
     //   String conversation = getIntent().getData().getQueryParameter("conversation");    // 获取昵称

        if (!TextUtils.isEmpty(sName)) {
            mName.setText(sName);
        } else {
            // sId
            // TODO 拿到id 去请求自己服务端
        }
        content();
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
    }
    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.setFocusable(true);
        convenientBanner.requestFocus();
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (convenientBanner != null)
            convenientBanner.stopTurning();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (convenientBanner != null)
            convenientBanner.startTurning(2000);
    }
    private List<String> imageList;
    private RespGetAverPage adverData;//广告
    private void  content() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class)
                        .selectDisplayAds("2",sId),
                new TempRemoteApiFactory.OnCallBack<RespGetAverPage>() {
                    @Override
                    public void onSucceed(RespGetAverPage data) {
                        if (data.getFlag() == 1) {
                            adverData = data;
                            imageList = new ArrayList<>();
                            Observable.from(data.getResult()).map(new Func1<RespGetAverPage.ResultBean, String>() {
                                @Override
                                public String call(RespGetAverPage.ResultBean resultBean) {
                                    return TempURIConfig.makeImageUrl(resultBean.getAdvImage());
                                }

                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                                @Override
                                public void onCompleted() {
                                    initAd(convenientBanner,imageList);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(String s) {

                                    imageList.add(s);

                                }
                            });
                            convenientBanner.setFocusable(true);
                            convenientBanner.requestFocus();
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

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

                      /*  if (adverData == null) {
                            mPreI.getAverPage();
                        } else {*/
                            String image = data.get(position);
                            List<RespGetAverPage.ResultBean> dataEntityList = adverData.getResult();
                            for (int i = 0; i < dataEntityList.size(); i++) {
                                if (TempURIConfig.makeImageUrl(dataEntityList.get(i).getAdvImage()).equals(image)) {
                                    String advId = dataEntityList.get(i).getAdvId();//广告Id
                                    String advType = dataEntityList.get(i).getAdvType();//1 广告（广告详情接口） 2 活动(红包详情接口)
                                    String advCreateTime = dataEntityList.get(i).getAdvCreateTime();
                                    if (advType != null && advType.equals("1")) {

                                        ActWeb.startActivityIntent(ConversationActivity.this, "" , BaseUriConfig.ADVER_DETAIL
                                                + "advId="+ advId + "&"
                                                + "advType=" +advType);

                                    } else {
                                        ActWeb.startActivityIntent(ConversationActivity.this, " ", BaseUriConfig.HONGBAO_DETAIL
                                                + "museId=" + TempLoginConfig.sf_getSueid() + "&"
                                                + "musePassword=" + TempLoginConfig.sf_getPwd() + "&"
                                                + "advId="+ advId + "&"
                                                + "advType=" +advType);

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
}
