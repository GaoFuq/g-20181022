package com.code.clkj.menggong.action;

import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.response.RespActAddress;
import com.code.clkj.menggong.response.RespActAddressDetails;
import com.code.clkj.menggong.response.RespActPassWord;
import com.code.clkj.menggong.response.RespActPhone;
import com.code.clkj.menggong.response.RespActRegister;
import com.code.clkj.menggong.response.RespActgetMessagePage;
import com.code.clkj.menggong.response.RespActmyDisplayCode;
import com.code.clkj.menggong.response.RespActmySpreadPng;
import com.code.clkj.menggong.response.RespCahtRoom;
import com.code.clkj.menggong.response.RespCheckBalance;
import com.code.clkj.menggong.response.RespCheckLS;
import com.code.clkj.menggong.response.RespCommentList;
import com.code.clkj.menggong.response.RespConsumOrder;
import com.code.clkj.menggong.response.RespGetAchorApplyStatus;
import com.code.clkj.menggong.response.RespGetAlipayInfo;
import com.code.clkj.menggong.response.RespGetAverPage;
import com.code.clkj.menggong.response.RespGetDynamicLikes;
import com.code.clkj.menggong.response.RespGetHelpList;
import com.code.clkj.menggong.response.RespGetMuseOrderBuy;
import com.code.clkj.menggong.response.RespGetMuseToken;
import com.code.clkj.menggong.response.RespGetSearchByKey;
import com.code.clkj.menggong.response.RespGetUnreadMessage;
import com.code.clkj.menggong.response.RespGetWxpayInfo;
import com.code.clkj.menggong.response.RespGetynamicPage;
import com.code.clkj.menggong.response.RespGiftList;
import com.code.clkj.menggong.response.RespGiftPaihangh;
import com.code.clkj.menggong.response.RespGradRed;
import com.code.clkj.menggong.response.RespLiveUser;
import com.code.clkj.menggong.response.RespMyAttention;
import com.code.clkj.menggong.response.RespMyFriendsList;
import com.code.clkj.menggong.response.RespMyHomePage;
import com.code.clkj.menggong.response.RespMyuserList;
import com.code.clkj.menggong.response.RespNearLive;
import com.code.clkj.menggong.response.RespOtherHomePage;
import com.code.clkj.menggong.response.RespPayOrder;
import com.code.clkj.menggong.response.RespPersonalDataActivity;
import com.code.clkj.menggong.response.RespQueryAppOrderPayMentType;
import com.code.clkj.menggong.response.RespQueryAreaCity;
import com.code.clkj.menggong.response.RespQueryCanLivingRoom;
import com.code.clkj.menggong.response.RespQueryMallGoods;
import com.code.clkj.menggong.response.RespQueryMallGoodsDetail;
import com.code.clkj.menggong.response.RespQueryMgooCommentPage;
import com.code.clkj.menggong.response.RespQueryRoom;
import com.code.clkj.menggong.response.RespQueryRoomIndex;
import com.code.clkj.menggong.response.RespQueryRoomMoreList;
import com.code.clkj.menggong.response.RespReceiveGift;
import com.code.clkj.menggong.response.RespSaveDynamic;
import com.code.clkj.menggong.response.RespSaveDynamicLike;
import com.code.clkj.menggong.response.RespSaveEpurse;
import com.code.clkj.menggong.response.RespSaveEpurseOrder;
import com.code.clkj.menggong.response.RespSaveMallOrder;
import com.code.clkj.menggong.response.RespSendGift;
import com.code.clkj.menggong.response.RespShowBankCard;
import com.code.clkj.menggong.response.RespShowHistory;
import com.code.clkj.menggong.response.RespToConfirmOrder;
import com.code.clkj.menggong.response.RespTuguannumber;
import com.code.clkj.menggong.response.RespgetCheckList;
import com.code.clkj.menggong.response.RespgetGiftBalance;
import com.code.clkj.menggong.response.ResponseUploadUEImg;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;
import com.lf.tempcore.tempResponse.TempResponse;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
import rx.Observable;

/**
 * Created by Administrator on 2017-12-09.
 */

public interface TempAction {
    /**
     * 图片上传
     *
     * @param params 图片地址
     */
    @Multipart
    @POST(BaseUriConfig.BASE_URL + "common/file/uploadUEImg.do")
    Observable<ResponseUploadUEImg> uploadUEImg(@PartMap Map<String, RequestBody> params);


    /**
     * 用户注册
     *
     * @param museUserName     用户名
     * @param musePassword     密码
     * @param code             验证码
     * @param museRegisterCode 邀请码
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/system/appUserRegister.do")
    Observable<RespActRegister> appUserRegister(@Field("museUserName") String museUserName,
                                                @Field("musePassword") String musePassword,
                                                @Field("code") String code,
                                                @Field("museRegisterCode") String museRegisterCode,
                                                @Field("museImage") String museImage,
                                                @Field("museSex") String museSex

    );

    /**
     * 登录
     *
     * @param museUserName 账户
     * @param musePassword 密码
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/mall/userLogin.do")
    Observable<ResponseLoginInfo> userLogin(@Field("museUserName") String museUserName,
                                            @Field("musePassword") String musePassword);

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/system/registerCode.do")
    Observable<TempResponse> registerCode(@Field("phone") String phone);

    /**
     * 忘记密码
     *
     * @param code
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/account/doForget.do")
    Observable<TempResponse> doForget(@Field("code") String code,
                                      @Field("userName") String userName,
                                      @Field("password") String password);

    /**
     * 忘记密码获取验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/account/sendForgetCode.do")
    Observable<TempResponse> sendForgetCode(@Field("phone") String phone);

    /**
     * 退出登录
     *
     * @param museId
     * @param musePassword
     * @param museOnlieTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/updateLoginById.do")
    Observable<TempResponse> updateLoginById(@Field("museId") String museId,
                                             @Field("musePassword") String musePassword,
                                             @Field("museOnlieTag") String museOnlieTag);

    /**
     * 查询个人资料
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/system/queryMemberInfoById.do")
    Observable<RespPersonalDataActivity> queryMemberInfoById(@Field("museId") String museId,
                                                             @Field("musePassword") String musePassword,
                                                             @Field("museOnlineTag") String museOnlineTag);

    /**
     * 修改个人信息
     *
     * @param museId
     * @param musePassword
     * @param museOnlinTage
     * @param museNickName
     * @param museAddress
     * @param museBirthday
     * @param museSex
     * @param museSignature
     * @param museImage
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/system/updateMember.do")
    Observable<TempResponse> updateMember(@Field("museId") String museId,
                                          @Field("musePassword") String musePassword,
                                          @Field("museOnlinTage") String museOnlinTage,
                                          @Field("museNickName") String museNickName,
                                          @Field("museAddress") String museAddress,
                                          @Field("museBirthday") String museBirthday,
                                          @Field("museSex") String museSex,
                                          @Field("museSignature") String museSignature,
                                          @Field("museImage") String museImage);

    /**
     * 判断是否设置支付密码
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/isSetPayPwd.do")
    Observable<RespActPassWord> isSetPayPwd(@Field("museId") String museId,
                                            @Field("musePassword") String musePassword,
                                            @Field("museOnlineTag") String museOnlineTag);



    /**
     * 我的推广二维码
     *
     * @param museId
     * @param
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/memberSpread/mySpreadPng.do")
    Observable<RespActmySpreadPng> mySpreadPng(@Field("museId") String museId, @Field("musePassword") String musePassword,
                                               @Field("museOnlineTag") String museOnlineTag
                                              );
    /**
     * 获取我的推广码
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/memberSpread/myDisplayCode.do")
    Observable<RespActmyDisplayCode> myDisplayCode(@Field("museId") String museId,
                                                   @Field("musePassword") String musePassword,
                                                   @Field("museOnlineTag") String museOnlineTag);



    /**
     *
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/memberSpread/mySpread.do")
    Observable<RespTuguannumber> mySpread(@Field("museId") String museId,
                                          @Field("musePassword") String musePassword,
                                          @Field("museOnlineTag") String museOnlineTag);

    /**
     * 设置支付密码接口
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param musePayPwdOne
     * @param musePayPwdTwo
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/setUserPayPwd.do")
    Observable<TempResponse> setUserPayPwd(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("musePayPwdOne") String musePayPwdOne,
                                           @Field("musePayPwdTwo") String musePayPwdTwo);

    /**
     * 忘记支付密码
     *
     * @param museId
     * @param musePassword
     * @param museOnlinTag
     * @param password
     * @param code
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/forgetUserPayPwd.do")
    Observable<TempResponse> forgetUserPayPwd(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("museOnlinTag") String museOnlinTag,
                                              @Field("password") String password,
                                              @Field("code") String code,
                                              @Field("phone") String phone);

    /**
     * 用户忘记支付密码获取验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/sendForgetPayPwdCode.do")
    Observable<TempResponse> sendForgetPayPwdCode(@Field("phone") String phone);

    /**
     * 重置登录密码
     *
     * @param museId
     * @param musePassword
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/resetLoginPwd.do")
    Observable<TempResponse> resetLoginPwd(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("oldPwd") String oldPwd,
                                           @Field("newPwd") String newPwd);

    /**
     * 重置支付密码
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/resetPayPwd.do")
    Observable<TempResponse> resetPayPwd(@Field("museId") String museId,
                                         @Field("musePassword") String musePassword,
                                         @Field("oldPwd")String oldPwd,
                                         @Field("newPwd")String newPwd);

    /**
     * 获取收货地址列表
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/addressList.do")
    Observable<RespActAddress> addressList(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("page") int page);

    /**
     * 获取收货地址信息
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param msadId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/getAddress.do")
    Observable<RespActAddressDetails> getAddress(@Field("museId") String museId,
                                                 @Field("musePassword") String musePassword,
                                                 @Field("museOnlineTag") String museOnlineTag,
                                                 @Field("msadId") String msadId);

    /**
     * 市级/下辖区域
     *
     * @param AId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/area/queryAreaCity.do")
    Observable<RespQueryAreaCity> queryAreaCity(@Field("AId") String AId);


    /**
     * 修改收货地址
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param msadMobileNo
     * @param msadReceiverName
     * @param msadAddrName
     * @param msadAddr
     * @param msadId
     * @param msadIsDefault
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/updateAddress.do")
    Observable<TempResponse> updateAddress(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("msadMobileNo") String msadMobileNo,
                                           @Field("msadReceiverName") String msadReceiverName,
                                           @Field("msadAddrName") String msadAddrName,
                                           @Field("msadAddr") String msadAddr,
                                           @Field("msadId") String msadId,
                                           @Field("msadIsDefault") String msadIsDefault);

    /**
     * 添加收货地址
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param msadMobileNo
     * @param msadReceiverName
     * @param msadAddrName
     * @param msadAddr
     * @param msadIsDefault
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/saveAddress.do")
    Observable<TempResponse> saveAddress(@Field("museId") String museId,
                                         @Field("musePassword") String musePassword,
                                         @Field("museOnlineTag") String museOnlineTag,
                                         @Field("msadMobileNo") String msadMobileNo,
                                         @Field("msadReceiverName") String msadReceiverName,
                                         @Field("msadAddrName") String msadAddrName,
                                         @Field("msadAddr") String msadAddr,
                                         @Field("msadIsDefault") String msadIsDefault);

    /**
     * 删除收货地址
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param msadId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/deleteAddress.do")
    Observable<TempResponse> deleteAddress(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("msadId") String msadId);

    /**
     * 查询检测点
     *
     * @return
     */
    @POST(BaseUriConfig.BASE_URL + "app/public/museFollow/getCheckList.do")
    Observable<RespgetCheckList> getCheckList();

    /**
     * 添加检测预约
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param mucrCkId
     * @param mucrBeginTime
     * @param mucrEndTime
     * @param code
     * @param mucrPhone
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museFollow/saveCheckReserve.do")
    Observable<TempResponse> saveCheckReserve(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("museOnlineTag") String museOnlineTag,
                                              @Field("mucrCkId") String mucrCkId,
                                              @Field("mucrBeginTime") String mucrBeginTime,
                                              @Field("mucrEndTime") String mucrEndTime,
                                              @Field("code") String code,
                                              @Field("mucrPhone") String mucrPhone);

    /**
     * 消息中心列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/message/getMessagePage.do")
    Observable<RespActgetMessagePage> getMessagePage(@Field("museId") String museId,
                                                     @Field("musePassword") String musePassword, @Field("page") String page,
                                                     @Field("pagesize") String pagesize);


    /**
     * 常见问题
     *
     * @return
     */
    @POST(BaseUriConfig.BASE_URL + "app/public/system/getHelpList.do")
    Observable<RespGetHelpList> getHelpList();

    /**
     * 获取联系电话
     *
     * @return
     */
    @POST(BaseUriConfig.BASE_URL + "app/public/mall/contactInformation.do")
    Observable<RespActPhone> contactInformation();


    /**
     * 添加意见反馈
     *
     * @param museId
     * @param musePassword
     * @param museOnline
     * @param phone
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/system/saveFeedBack.do")
    Observable<TempResponse> saveFeedBack(@Field("museId") String museId,
                                          @Field("musePassword") String musePassword,
                                          @Field("museOnline") String museOnline,
                                          @Field("phone") String phone,
                                          @Field("content") String content);

    /**
     * 查询用户是否申请主播
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/anchorApply/getAchorApplyStatus.do")
    Observable<RespGetAchorApplyStatus> getAchorApplyStatus(@Field("museId") String museId,
                                                            @Field("musePassword") String musePassword,
                                                            @Field("museOnlineTag") String museOnlineTag);

    /**
     * 用户添加主播认证
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param usceRealName
     * @param usceSex
     * @param usceAge
     * @param uscePhone
     * @param usceAddress
     * @param usceIdentityFace
     * @param usceIdentityBack
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/anchorApply/saveAchorApply.do")
    Observable<TempResponse> saveAchorApply(@Field("museId") String museId,
                                            @Field("musePassword") String musePassword,
                                            @Field("museOnlineTag") String museOnlineTag,
                                            @Field("usceRealName") String usceRealName,
                                            @Field("usceSex") String usceSex,
                                            @Field("usceAge") String usceAge,
                                            @Field("uscePhone") String uscePhone,
                                            @Field("usceAddress") String usceAddress,
                                            @Field("usceIdentityFace") String usceIdentityFace,
                                            @Field("usceIdentityBack") String usceIdentityBack);

    /**
     * 获取主播认证信息
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param usceId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/anchorApply/getAchorApply.do")
    Observable<RespLiveUser> getAchorApply(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("usceId") String usceId);

    /**
     * 用户修改主播认证
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param usceRealName
     * @param usceSex
     * @param usceAge
     * @param uscePhone
     * @param usceAddress
     * @param usceIdentityFace
     * @param usceIdentityBack
     * @param usceId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/anchorApply/updateAchorApply.do")
    Observable<TempResponse> updateAchorApply(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("museOnlineTag") String museOnlineTag,
                                              @Field("usceRealName") String usceRealName,
                                              @Field("usceSex") String usceSex,
                                              @Field("usceAge") String usceAge,
                                              @Field("uscePhone") String uscePhone,
                                              @Field("usceAddress") String usceAddress,
                                              @Field("usceIdentityFace") String usceIdentityFace,
                                              @Field("usceIdentityBack") String usceIdentityBack,
                                              @Field("usceId") String usceId);

    /**
     * 查询我的浏览记录
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museHistory/getMuseHistory.do")
    Observable<RespShowHistory> getMuseHistory(@Field("museId") String museId,
                                               @Field("musePassword") String musePassword,
                                               @Field("museOnlineTag") String museOnlineTag,
                                               @Field("page") String page,
                                               @Field("pagesize") String pagesize);

    /**
     * 删除浏览记录
     *
     * @param museId
     * @param musePassword
     * @param muhyId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museHistory/deleteMuseHistory.do")
    Observable<TempResponse> deleteMuseHistory(@Field("museId") String museId,
                                               @Field("musePassword") String musePassword,
                                               @Field("muhyId") String muhyId);

    /**
     * 查询余额接口
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/getMuseEpurse.do")
    Observable<RespCheckBalance> getMuseEpurse(@Field("museId") String museId,
                                               @Field("musePassword") String musePassword);

    /**
     * 查询余额流水
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/getMuseEpurseLog.do")
    Observable<RespCheckLS> getMuseEpurseLog(@Field("museId") String museId,
                                             @Field("musePassword") String musePassword,
                                             @Field("page") String page);

    /**
     * 查询提现银行卡
     *
     * @param museId
     * @param musePassword
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/getMuseBrank.do")
    Observable<RespShowBankCard> getMuseBrank(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("page") String page);

    /**
     * 余额提现-添加银行卡
     *
     * @param museId
     * @param musePassword
     * @param mbanName
     * @param mbanCard
     * @param mbanMemberName
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/saveMuseBrank.do")
    Observable<TempResponse> saveMuseBrank(@Field("museId") String museId,
                                           @Field("musePassword") String musePassword,
                                           @Field("mbanName") String mbanName,
                                           @Field("mbanCard") String mbanCard,
                                           @Field("mbanMemberName") String mbanMemberName);

    /**
     * 添加余额提现申请
     *
     * @param museId
     * @param musePassword
     * @param mbanId
     * @param price
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/user/saveWithdraw.do")
    Observable<TempResponse> saveWithdraw(@Field("museId") String museId,
                                          @Field("musePassword") String musePassword,
                                          @Field("mbanId") String mbanId,
                                          @Field("price") String price);

    /**
     * 收到的礼物
     *
     * @param museId
     * @param musePassword
     * @param museOnlieTag
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/giftRecord/getGiftRecordIn.do")
    Observable<RespReceiveGift> getGiftRecordIn(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword,
                                                @Field("museOnlieTag") String museOnlieTag,
                                                @Field("page") String page);

    /**
     * 送出的礼物
     *
     * @param museId
     * @param musePassword
     * @param museOnlieTag
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/giftRecord/getGiftRecordOut.do")
    Observable<RespSendGift> getGiftRecordOut(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("museOnlieTag") String museOnlieTag,
                                              @Field("page") String page);


    /**
     * 礼物结算
     *
     * @param museId
     * @param musePassword
     * @param museOnlieTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/giftRecord/getGiftBalance.do ")
    Observable<RespgetGiftBalance> getGiftBalance(@Field("museId") String museId,
                                                  @Field("musePassword") String musePassword,
                                                  @Field("museOnlieTag") String museOnlieTag);


    /**
     * 礼物金额结算
     *
     * @param museId
     * @param musePassword
     * @param price
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/giftRecord/saveEpurse.do ")
    Observable<RespSaveEpurse> saveEpurse(@Field("museId") String museId,
                                          @Field("musePassword") String musePassword,
                                          @Field("price") String price);

    /**
     * 查询我的好友
     *
     * @param museId
     * @param musePassword
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museFollow/getMuseFriend.do")
    Observable<RespMyFriendsList> getMuseFriend(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword,
                                                @Field("page") String page,
                                                @Field("pageSize") String pageSize);


    /**
     * 查询我的好友
     *
     * @param museId
     * @param musePassword
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/memberSpread/mySpreadList.do")
    Observable<RespMyuserList> mySpreadList(@Field("museId") String museId,
                                            @Field("musePassword") String musePassword,
                                            @Field("page") String page,
                                            @Field("pageSize") String pageSize);

    /**
     * 朋友圈---发动态
     *
     * @param museId
     * @param musePassword
     * @param dyContent
     * @param dyImage
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/dynamic/saveDynamic.do ")
    Observable<RespSaveDynamic> saveDynamic(@Field("museId") String museId,
                                            @Field("musePassword") String musePassword,
                                            @Field("dyContent") String dyContent,
                                            @Field("dyImage") String dyImage);

    /**
     * 朋友圈
     *
     * @param museId
     * @param frMuseId
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/dynamic/getDynamicPage.do ")
    Observable<RespGetynamicPage> getDynamicPage(@Field("museId") String museId,
                                                 @Field("frMuseId") String frMuseId,
                                                 @Field("page ") String page, @Field("pageSize") String pageSize);

    /**
     * 动态点赞
     *
     * @param museId
     * @param musePassword
     * @param dyId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/dynamic/saveDynamicLike.do")
    Observable<RespSaveDynamicLike> saveDynamicLike(@Field("museId") String museId,
                                                    @Field("musePassword") String musePassword,
                                                    @Field("dyId") String dyId);


    /**
     * 获取点赞人信息
     *
     * @param dyId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/dynamic/getDynamicLikes.do")
    Observable<RespGetDynamicLikes> getDynamicLikes(@Field("dyId") String dyId,
                                                    @Field("position") int position);

    /**
     * 动态的评论列表
     *
     * @param dyId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/dynamic/getDynamicComment.do")
    Observable<RespCommentList> getDynamicComment(@Field("dyId") String dyId,
                                                  @Field("position") int position);


    /**
     * 添加动态评论内容(增)
     *
     * @param museId
     * @param musePassword
     * @param dyId
     * @param content
     * @param type
     * @param rpMuseId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/dynamic/saveDynamicComment.do")
    Observable<TempResponse> saveDynamicComment(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword,
                                                @Field("dyId") String dyId,
                                                @Field("content") String content,
                                                @Field("type") String type,
                                                @Field("rpMuseId") String rpMuseId
                                                );
//    @Field("position") int position


    /**
     * 获取可以直播的流
     *
     * @param museId
     * @param musePassword
     * @param lat
     * @param lng
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/live/queryCanLivingRoom.do")
    Observable<RespQueryCanLivingRoom> queryCanLivingRoom(@Field("museId") String museId,
                                                          @Field("musePassword") String musePassword,
                                                          @Field("lat") String lat,
                                                          @Field("lng") String lng);


    /**
     * 直播间详情
     *
     * @param roomId
     * @param museId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryRoom.do")
    Observable<RespQueryRoom> queryRoom(@Field("roomId") String roomId,
                                        @Field("museId") String museId);

    /**
     * 直播首页
     *
     * @return
     */
//    @FormUrlEncoded
//    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryRoomIndex.do")
//    Observable<RespQueryRoomIndex> queryRoomIndex();
    @GET(BaseUriConfig.BASE_URL + "app/public/room/queryRoomIndex.do")
    Observable<RespQueryRoomIndex> queryRoomIndex();

    /**
     * 附近直播（改）
     *
     * @param startAge
     * @param endAge
     * @param sex
     * @param lat
     * @param lng
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryRoomList.do")
    Observable<RespNearLive> queryRoomList(@Field("startAge") String startAge,
                                           @Field("endAge") String endAge,
                                           @Field("sex") String sex,
                                           @Field("lat") String lat,
                                           @Field("lng") String lng,
                                           @Field("page") String page,
                                           @Field("pagesize") String pagesize);


    /**
     *
     * 聊天室列表
     * @param lat
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/seletChat.do")
    Observable<RespCahtRoom> seletChat(@Field("lon") String lon,
                                       @Field("lat") String lat,
                                       @Field("page") String page,
                                       @Field("pagesize") String pagesize
                                       );


    /**
     * 所有礼物
     *
     * @return
     */
    @POST(BaseUriConfig.BASE_URL + "/app/public/room/queryGiftList.do")
    Observable<RespGiftList> queryGiftList();

    /**
     * 我的关注
     *
     * @param museId
     * @param musePassword
     * @param museOnlineTag
     * @param lng
     * @param lat
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museFollow/getMuseFollow.do")
    Observable<RespMyAttention> getMuseFollow(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword,
                                              @Field("museOnlineTag") String museOnlineTag,
                                              @Field("lng") String lng,
                                              @Field("lat") String lat,
                                              @Field("page ") String page, @Field("pageSize") String pageSize);

    /**
     * 添加/取消 我的关注
     *
     * @param museId
     * @param musePassword
     * @param museOlineTag
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museFollow/saveMuseFollow.do")
    Observable<TempResponse> saveMuseFollow(@Field("museId") String museId,
                                            @Field("musePassword") String musePassword,
                                            @Field("museOlineTag") String museOlineTag,
                                            @Field("folMuseId") String folMuseId);

    /**
     * 主播商品
     *
     * @param museId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryMallGoods.do")
    Observable<RespQueryMallGoods> queryMallGoods(@Field("museId") String museId,
                                                  @Field("currentPage") String currentPage,
                                                  @Field("pageSize") String pageSize);


    //app/public/room/queryMallGoodsDetail.do?mgooId=1

    /**
     * 商品详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryMallGoodsDetail.do")
    Observable<RespQueryMallGoodsDetail> queryMallGoodsDetail(@Field("mgooId") String mgooId);


    //app/public/advertise/getAverPage.do

    /**
     * 查找广告列表
     *
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/advertise/getAverPage.do")
    Observable<RespGetAverPage> getAverPage();


    /**
     * 商品评论列表（增）
     *
     * @param
     * @param
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/advertise/selectDisplayAds.do")
    Observable<RespGetAverPage> selectDisplayAds(@Field("type") String type,
                                                              @Field("roomId") String roomId);

    /**
     * 商品评论列表（增）
     *
     * @param mgooId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryMgooCommentPage.do")
    Observable<RespQueryMgooCommentPage> queryMgooCommentPage(@Field("mgooId") String mgooId,
                                                              @Field("currentPage") String currentPage,
                                                              @Field("pageSize") String pageSize);

    /**
     * 到订单页
     *
     * @param museId
     * @param musePassword
     * @param mgooId
     * @param modeNum
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/toConfirmOrder.do")
    Observable<RespToConfirmOrder> toConfirmOrder(@Field("museId") String museId,
                                                  @Field("musePassword") String musePassword,
                                                  @Field("mgooId") String mgooId,
                                                  @Field("modeNum") String modeNum);

    /**
     * 我的主页（增）
     *
     * @param museId
     * @param musePassword
     * @param page
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/dynamic/getMuseDynamicPage.do")
    Observable<RespMyHomePage> getMuseDynamicPage(@Field("museId") String museId,
                                                  @Field("musePassword") String musePassword,
                                                  @Field("page") String page,
                                                  @Field("pagesize") String pagesize);

    /**
     * 删除我的动态
     *
     * @param museId
     * @param musePassword
     * @param dyId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "/app/private/dynamic/deleteMuseDynamic.do")
    Observable<TempResponse> deleteMuseDynamic(@Field("museId") String museId,
                                               @Field("musePassword") String musePassword,
                                               @Field("dyId") String dyId);

    /**
     * 查看其他人主页（增）
     *
     * @param museId
     * @param musePassword
     * @param frMuseId
     * @param page
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/dynamic/getOtherDynamicPage.do")
    Observable<RespOtherHomePage> getOtherDynamicPage(@Field("museId") String museId,
                                                      @Field("musePassword") String musePassword,
                                                      @Field("frMuseId") String frMuseId,
                                                      @Field("page") String page,
                                                      @Field("pagesize") String pagesize);

    /**
     * 获取Token
     *
     * @param museId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/system/getMuseToken.do")
    Observable<RespGetMuseToken> getMuseToken(@Field("museId") String museId);


    /**
     * 购买的订单 --- 我的订单
     *
     * @param museId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museOrder/getMuseOrderBuy.do")
    Observable<RespGetMuseOrderBuy> getMuseOrderBuy(@Field("museId") String museId,
                                                    @Field("musePassword") String musePassword,
                                                    @Field("museOlineTag") String museOlineTag,
                                                    @Field("currentPage") String currentPage,
                                                    @Field("pageSize") String pageSize);

    /**
     * 确认订单接口
     *
     * @param museId
     * @param musePassword
     * @param mgooId
     * @param modeNum
     * @param maorAddressId
     * @param mordRemark
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/saveMallOrder.do")
    Observable<RespSaveMallOrder> saveMallOrder(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword,
                                                @Field("mgooId") String mgooId,
                                                @Field("modeNum") String modeNum,
                                                @Field("maorAddressId") String maorAddressId,
                                                @Field("mordRemark") String mordRemark);

    /**
     * 支付订单(改)
     *
     * @param museId
     * @param musePassword
     * @param payment
     * @param tradeNo
     * @param payTime
     * @param outTradeNo
     * @param payPwd
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/payOrder.do")
    Observable<RespPayOrder> payOrder(@Field("museId") String museId,
                                      @Field("musePassword") String musePassword,
                                      @Field("payment") String payment,
                                      @Field("tradeNo") String tradeNo,
                                      @Field("payTime") String payTime,
                                      @Field("outTradeNo") String outTradeNo,
                                      @Field("payPwd") String payPwd);

    /**
     * 获取支付方式
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/queryAppOrderPayMentType.do")
    Observable<RespQueryAppOrderPayMentType> queryAppOrderPayMentType(@Field("museId") String museId,
                                                                      @Field("musePassword") String musePassword);


    /**
     * 获取支付宝配置接口
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/getAlipayInfo.do")
    Observable<RespGetAlipayInfo> getAlipayInfo(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword);


    /**
     * 获取微信支付配置
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/getWxpayInfo.do")
    Observable<RespGetWxpayInfo> getWxpayInfo(@Field("museId") String museId,
                                              @Field("musePassword") String musePassword);

    /**
     * 修改订单状态（增）
     *
     * @param museId
     * @param musePassword
     * @param mordId
     * @param status
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/museOrder/saveOrderStatus.do")
    Observable<TempResponse> saveOrderStatus(@Field("museId") String museId,
                                             @Field("musePassword") String musePassword,
                                             @Field("mordId") String mordId,
                                             @Field("status") String status,
                                             @Field("type") String type);

    /**
     * 直播首页_更多
     *
     * @param type
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/room/queryRoomMoreList.do")
    Observable<RespQueryRoomMoreList> queryRoomMoreList(@Field("type") String type,
                                                        @Field("currentPage") String currentPage,
                                                        @Field("pageSize") String pageSize);

    /**
     * 首页搜索
     *
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/public/live/getSearchByKey.do")
    Observable<RespGetSearchByKey> getSearchByKey(@Field("key") String key, @Field("currentPage") String currentPage,
                                                  @Field("pageSize") String pageSize);

    /**
     * 生成消费订单（增）
     *
     * @param museId
     * @param musePassword
     * @param price
     * @param roomId
     * @param giftId
     * @param type
     * @param number
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/epurse/saveConsumOrder.do")
    Observable<RespConsumOrder> saveConsumOrder(@Field("museId") String museId,
                                                @Field("musePassword") String musePassword,
                                                @Field("price") String price,
                                                @Field("roomId") String roomId,
                                                @Field("giftId") String giftId,
                                                @Field("type") String type,
                                                @Field("number") String number);

    /**
     * 用户查询未读消息条数(增)
     *
     * @param museId
     * @param musePassword
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/message/getUnreadMessage.do")
    Observable<RespGetUnreadMessage> getUnreadMessage(@Field("museId") String museId,
                                                      @Field("musePassword") String musePassword);

    /**
     * 抢红包接口
     *
     * @param museId
     * @param musePassword
     * @param redId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/live/gradRed.do")
    Observable<RespGradRed> gradRed(@Field("museId") String museId,
                                    @Field("musePassword") String musePassword,
                                    @Field("redId") String redId);

    /**
     * 生成订单
     * @param museId
     * @param musePassword
     * @param price
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/epurse/saveEpurseOrder.do")
    Observable<RespSaveEpurseOrder> saveEpurseOrder(@Field("museId")String museId,
                                                    @Field("musePassword")String musePassword,
                                                    @Field("price")String price);

    /**
     * 用户评论订单（增）
     * @param museId
     * @param musePassword
     * @param mgcoOrderId
     * @param mgcoImage
     * @param mgcoGoodsContent
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/room/saveMgooComment.do")
    Observable<TempResponse>saveMgooComment(@Field("museId")String museId,
                                            @Field("musePassword")String musePassword,
                                            @Field("mgcoOrderId")String mgcoOrderId,
                                            @Field("mgcoImage")String mgcoImage,
                                            @Field("mgcoGoodsContent")String mgcoGoodsContent);

    /**
     * 礼物排行榜（增）
     * @param roomId
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/public/live/giftPaihangh.do")
    Observable<RespGiftPaihangh>giftPaihangh(@Field("roomId")String roomId);

    /**
     * 更新经纬度（增）
     * @param museId
     * @param musePassword
     * @param lng
     * @param lat
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/saveLatLng.do")
    Observable<TempResponse> saveLatLng(@Field("museId")String museId,
                                        @Field("musePassword")String musePassword,
                                        @Field("lng")String lng,
                                        @Field("lat")String lat);

}
