package com.lf.tempcore.tempConfig;

//import com.snappydb.DB;
//import com.snappydb.DBFactory;
//import com.snappydb.SnappydbException;


import android.content.Context;
import android.content.SharedPreferences;

import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;

/**
 * Created by Administrator on 2015/12/21.
 */
public class TempLoginConfig {
    public final static String TAG = "SFLoginConfig";

    /**
     * museAddress : 年龄
     * museBirthday : 生日
     * museEpurse : 余额
     * museId : 用户id
     * museImage : 用户头像
     * museInviteCode : 用户邀请码
     * museSignature : 签名
     * museNickName : 昵称
     * museOnlineTag : 在线标识
     * museSex : 用户性别
     * museType :	类型（ 0 个人会员 1 直播）
     * museUserName : 登录手机号
     *
     */

    /**
     * 保存登陆信息的xml名字
     */
    public final static String LOGIN_TABLE_NAME = "login_info";

    public final static String LOGIN_ADDRESS = "museAddress";
    public final static String LOGIN_BIRTHDAY = "museBirthday";
    public final static String LOGIN_EPURSE = "museEpurse";
    public final static String LOGIN_ID = "museId";
    public final static String LOGIN_IMAGE = "museImage";
    public final static String LOGIN_INVITECODE = "museInviteCode";
    public final static String LOGIN_NICK_NAME = "museNickName";
    public final static String LOGIN_ONLINE_TAG = "museOnlineTag";
    public final static String LOGIN_SEX = "museSex";
    public final static String LOGIN_SIGNATURE = "museSignature";
    public final static String LOGIN_TYPE = "museType";
    public final static String LOGIN_PHONE = "museUserName";

    /**
     * 是否设置支付密码
     */
    public final static String PAYWORD_RESULT = "pay_result_state";

    /**
     * 登录状态
     */
    public final static String LOGIN_REMEMBER_STATE = "login_state";

    public final static String LOGIN_PWD="login_pwd";
    /**
     *   审核状态
     */
    public final static String LOGIN_AUDITING="login_auditing";
    /**
     *  自动登录状态
     */
    public final static String LOGIN_AUTO_STATE = "login_auto_state";



    //清空数据
    private static void sf_clearByName(String name) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //清空 登录数据
    public static void sf_clearLoginInfo() {
        sf_clearByName(LOGIN_TABLE_NAME);
    }

    //    保存用户登录状态
    public static void sf_saveLoginState(boolean state) {
        sf_saveBooleanInfo(LOGIN_REMEMBER_STATE, state);
    }

    /**
     * 获取用户登录状态
      * @return
     */
    public static boolean sf_getLoginState() {
        return sf_getBooleanInfo(LOGIN_REMEMBER_STATE, false);
    }
    //    保存用户登录状态
    public static void sf_saveAutoLoginState(boolean state) {
        sf_saveBooleanInfo(LOGIN_AUTO_STATE, state);
    }

    //    获取用户登录状态
    public static boolean sf_getAutoLoginState() {
        return sf_getBooleanInfo(LOGIN_AUTO_STATE, false);
    }

    //保存用户设置支付密码状态
    public static void sf_savePayState(boolean state) {
        sf_saveBooleanInfo(PAYWORD_RESULT, state);
    }

    //获取用户设置支付密码状态
    public static boolean sf_getPayState() {
        return sf_getBooleanInfo(PAYWORD_RESULT, false);
    }


    //    保存用户登录状态
    public static void sf_saveLoginInfo(ResponseLoginInfo data) {
        sf_saveString(LOGIN_ID, data.getResult().getMuseId());
        sf_saveString(LOGIN_IMAGE, data.getResult().getMuseImage());
        sf_saveString(LOGIN_NICK_NAME,data.getResult().getMuseNickName());
        sf_saveString(LOGIN_ONLINE_TAG,data.getResult().getMuseOnlineTag());
        sf_saveString(LOGIN_SEX,data.getResult().getMuseSex());
        sf_saveString(LOGIN_PHONE,data.getResult().getMuseUserName());
        sf_saveString(LOGIN_TYPE,data.getResult().getMuseType());
        sf_saveString(LOGIN_EPURSE,data.getResult().getMuseEpurse());
        sf_saveString(LOGIN_BIRTHDAY,data.getResult().getMuseBirthday());
    }


    //    *保存用户昵称
    public static void sf_saveNickName(String nickName) {
        sf_saveString(LOGIN_NICK_NAME, nickName);
    }

    //    *保存用户图片
    public static void sf_saveLoginImage(String image) {
        sf_saveString(LOGIN_IMAGE, image);
    }

    //    *获取用户id
    public static String sf_getSueid() {
        return sf_getString(LOGIN_ID,null);
    }
    public static String sf_getOnlineTag() {
        return sf_getString(LOGIN_ONLINE_TAG);
    }
    public static String sf_getPwd() {
        return sf_getString(LOGIN_PWD);
    }
    public static void sf_savePwd(String pwd) {
        sf_saveString(LOGIN_PWD,pwd);
    }

    public static String sf_getBirthday(){
        return sf_getString(LOGIN_BIRTHDAY);
    }
    public static String sf_getPhone() {
        return sf_getString(LOGIN_PHONE);
    }
//    public static void sf_savePhone(String phone) {
//        sf_saveString(LOGIN_PHONE,phone);
//    }
    public static String sf_getImage(){
        return sf_getString(LOGIN_IMAGE);
    }
    public static void sf_saveAuditing(String auditing) {
        sf_saveString(LOGIN_AUDITING,auditing);
    }

    public static String sf_getMuseEpurse(){
        return sf_getString(LOGIN_EPURSE);
    }

    public static String sf_getAuditing() {// 	如果用户没有申请成为商家值为-1(0：审核中
        // 1：审核通过切已经完善信息，2:审核不通过，3：已删除,4.店铺信息未完善 )
        return sf_getString(LOGIN_AUDITING);
    }
    public static String sf_getNickName() {// 	如果用户没有申请成为商家值为-1(0：审核中
        // 1：审核通过切已经完善信息，2:审核不通过，3：已删除,4.店铺信息未完善 )
        return sf_getString(LOGIN_NICK_NAME);
    }



    //保存Boolean 到SharedPreference
    private static void sf_saveBooleanInfo(String key, boolean val) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean(key, val);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean sf_getBooleanInfo(String key, boolean deVal) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, key + "=" + sf.getBoolean(key, deVal));
            return sf.getBoolean(key, deVal);
        } catch (Exception e) {

        }
        return deVal;
    }

    public final static String LOCATION_STATUS = "location_status";//定位状态
    public final static String LOCATION_INFO = "location_info";//定位信息
    public final static String LOCATION_CITY_ID = "location_city_id";//城市id
    public final static String LOCATION_LAT = "location_lat";//经纬度
    public final static String LOCATION_LNG = "location_lng";//经纬度
    public final static String LOCATION_ADRESS = "location_adrdd";//定位信息
    public final static String LOCATION_NEWADRESS = "location_nw";//地址信息

    public final static String LOCATION_TIME = "location_time";//时间信息

    public final static String LOCATION_GGT= "guyangtime";//经纬度

    /**
     * 保存定位状态
     *
     * @param lag_lng index 0纬度，index1 经度
     */
    public static void sf_saveLocation_lat_lng(String[] lag_lng) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_LAT, lag_lng[0]);
            editor.putString(LOCATION_LNG, lag_lng[1]);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String sf_getlag(){
        return sf_getLocation(LOCATION_LAT);
    }

    public static String sf_getlng(){
        return sf_getLocation(LOCATION_LNG);
    }
    public static void sf_saveLocation_lat_lngadress(String adress) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_ADRESS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_NEWADRESS, adress);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sf_saveLocation_lat(String adress) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_ADRESS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_LAT, adress);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sf_saveLocation_long(String adress) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_ADRESS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_LNG, adress);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String sf_getLocation(String key){
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            return sf.getString(key, "");
        } catch (Exception e) {

        }
        return "";
    }
    public static String sf_getLocation_lng(){
        return sf_getString(LOCATION_LNG);
    }
//    -------------------------------------------------------------------
    private static void sf_saveString(String key, String val) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(key, val);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sf_getString(String key,String val) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, key + "=" + sf.getString(key, val));
            return sf.getString(key, val);
        } catch (Exception e) {

        }
        return val;
    }
    private static String sf_getString(String key) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, key + "=" + sf.getString(key, ""));
            return sf.getString(key, "");
        } catch (Exception e) {

        }
        return "";
    }
    public final static String LEIXIN_WEIXIN = "pay_weixin";
    //    获取微信支付
    public static String sf_getPayWeixin() {
        return sf_getString(LEIXIN_WEIXIN);
    }

    //    保存微信支付
    public static void sf_savePayWeixin(String id) {
        sf_saveString(LEIXIN_WEIXIN, id);
    }
    public static class DataUtils {
        public static  boolean changeIsEighteen(String str){ //0.未满1.已满
            if (str==null){
                return false;
            }
            switch (str){
                case "0":
                    return false;
                case "1":
                    return true;
                default:
                    return false;
            }
        }

        public static  boolean changeIsMuslim(String str){ //0：是，1：否
            if (str==null){
                return false;
            }
            switch (str){
                case "0":
                    return true;
                case "1":
                    return false;
                default:
                    return false;
            }
        }
        public static  boolean changeIsChinese(String str){//1:中文 2：英语
            if (str==null){
                return false;
            }
            switch (str){
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    return true;
            }
        }
    }
//

 /*  *//* public final static String LOGIN_TABLE_USEID = "login_userid";//保存登陆信息的xml通联支付userid
    public final static String KET_LOCATION_CODE = "loc_code";//地区编号
    public final static String KET_LOCATION_CODE_VALUE = "loc_code_value";//地区编号值
    public final static String LOGIN_AUTO_STATUS;//默认自动登录
    //    public final static String LOGIN_USER_INFO= "user_info";//用户信息
//
//    public final static String PHONE = "musePhone";//保存登陆信息的xml名字
//    public final static String LOGIN_USERACOUNT;  // 1
    public final static String LOGIN_USERNAME;
  public final static String LOGIN_PASSWORD;

   // musePayUserId

    public final static String LOGIN_MUSEPAYUSERIDP;//昵称 1

    public final static String LOGIN_NICKNAME;//昵称 1
    //    public final static String LOGIN_SCORE;//积分        1
//    public final static String LOGIN_SEX;//积分     1 1
    public final static String LOGIN_ONLINEKEY;//key  1
    public final static String LOGIN_BIRTHDAY;//生日 1
    //    public final static String LOGIN_DATELINE;//用户登陆时间
    public final static String LOGIN_HEAD;//头像路径
    //    public final static String LOGIN_GRADESORT;//vip
    public final static String LOGIN_REMEMBER_STATE;//登录状态
    //    public final static String COOKIE;
//    public final static String LOGIN_COMPANY;
//    public final static String LOGIN_FANNUM;
//    public final static String LOGIN_ORDERNUM;
//    public final static String LOGIN_ASKCODE; //邀请码
    public final static String LOGIN_LOGTYPE; //用户登录类型 用户类型（1：普通会员 2：联盟商家 3：合作商家）
    public final static String LOGIN_SUSEID; //用户ID
    public final static String LOGIN_PHONE; //用户名
    public final static String LOGIN_ISREAL; //是否实名
    public final static String IsChinese = "IsChinese";//是否是中文

    //    public final static String LOGIN_DELETE; //是否启用 （1 - 启用 ，0 - 停用）
//
////
    static {
//        LOGIN_USERACOUNT = "userAcount";
        LOGIN_USERNAME = "museUserName";
        LOGIN_PASSWORD = "musePwd";
        LOGIN_NICKNAME = "nickname";
        LOGIN_MUSEPAYUSERIDP="musePayUserId";
//        LOGIN_SEX = "sex";
        LOGIN_BIRTHDAY = "museBirthday";
//        LOGIN_SCORE = "score";
        LOGIN_ONLINEKEY = "museOnlineTag";
//        LOGIN_DATELINE = "dateline";
        LOGIN_HEAD = "museImage";
        LOGIN_REMEMBER_STATE = "remember_state";
//        COOKIE = "cookie";
//        LOGIN_COMPANY = "company";
//        LOGIN_FANNUM = "fanNum";
//        LOGIN_ORDERNUM = "orderNum";
//        LOGIN_GRADESORT = "gradesort";
//        LOGIN_ASKCODE = "invitationcode";
        LOGIN_LOGTYPE = "museType";
        LOGIN_SUSEID = "museId";
        LOGIN_PHONE = "musePhone";
        LOGIN_AUTO_STATUS = "is_auto_login";
        LOGIN_ISREAL = "museIsReal";
//        LOGIN_DELETE = "delete";
//        snappyDB  = MainApplication.getInstance().initSnappydb();
//
    }
/*//*//*

    // 保存用户是否自动登录状态
    public static void sf_saveAutoStatus(boolean state) {
//        try {
//
//            snappyDB.putBoolean(LOGIN_REMEMBER_STATE, state);
//            Debug.debug(TAG, "保存LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean(LOGIN_AUTO_STATUS, state);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    获取用户是否自动登录状态
    public static boolean sf_getAutoLoginState() {
//        try {
//            Debug.debug(TAG, "获取LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//           return snappyDB.getBoolean(LOGIN_REMEMBER_STATE);
//
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOGIN_REMEMBER_STATE=" + sf.getBoolean(LOGIN_AUTO_STATUS, true));//默认自动登录
            return sf.getBoolean(LOGIN_AUTO_STATUS, true);
        } catch (Exception e) {

        }
        return false;
    }


    public static void sf_saveQAPrice(String price) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("QA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("qa", price);
        editor.commit();
    }

    public static String sf_getQAPrice() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("QA", Context.MODE_PRIVATE);
        return sf.getString("qa", "0");

    }


    // 保存用户登录状态
    public static void sf_saveLoginState(boolean state) {
//        try {
//
//            snappyDB.putBoolean(LOGIN_REMEMBER_STATE, state);
//            Debug.debug(TAG, "保存LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean(LOGIN_REMEMBER_STATE, state);
        editor.commit();
    }

    public static void sf_saveBirthday(String state) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_BIRTHDAY, state);
        editor.commit();
    }

    public static void sf_saveLocCode(String code) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(KET_LOCATION_CODE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(KET_LOCATION_CODE_VALUE, code);
        editor.commit();
    }

    public static String sf_getLocCode() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(KET_LOCATION_CODE, Context.MODE_PRIVATE);
        return sf.getString(KET_LOCATION_CODE_VALUE, "");
    }


    // 保存用户登录状态
    public static void sf_savePassword(String pw) {
//        try {
//
//            snappyDB.putBoolean(LOGIN_REMEMBER_STATE, state);
//            Debug.debug(TAG, "保存LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_PASSWORD, pw);
        editor.commit();
    }






    // 保存用户登录状态
    public static void sf_saveUserid(String userid) {
//        try {
//
//            snappyDB.putBoolean(LOGIN_REMEMBER_STATE, state);
//            Debug.debug(TAG, "保存LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_USEID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_MUSEPAYUSERIDP, userid);
        editor.commit();
    }

    //    获取用户登录状态
    public static boolean sf_getLoginState() {
//        try {
//            Debug.debug(TAG, "获取LOGIN_REMEMBER_STATE=" + snappyDB.getBoolean(LOGIN_REMEMBER_STATE));
//           return snappyDB.getBoolean(LOGIN_REMEMBER_STATE);
//
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOGIN_REMEMBER_STATE=" + sf.getBoolean(LOGIN_REMEMBER_STATE, false));
            return sf.getBoolean(LOGIN_REMEMBER_STATE, false);
        } catch (Exception e) {

        }
        return false;

    }

    //    *保存用户名和密码
//     * @param username
//     * @param password
//
    public static void sf_saveUsernameAndPsw(String username, String password) {
//        try {
//            snappyDB.put(LOGIN_USERNAME, username);
//            snappyDB.put(LOGIN_PASSWORD, password);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_USERNAME, username);
        editor.putString(LOGIN_PASSWORD, password);
        Debug.debug(TAG, "LOGIN_USERNAME=" + username + "LOGIN_PASSWORD=" + password);
        editor.commit();
    }

    public static final String WX_NAME = "wx_name";
    public static final String WX_IMAGE = "wx_image";
    public static final String WX_OPENID = "wx_openid";
    public static final String WX_LOGIN = "wx_login";

    public static void sf_saveWXUser(String username, String image, String openId) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(WX_NAME, username);
        editor.putString(WX_IMAGE, image);
        editor.putString(WX_OPENID, openId);
        Debug.debug(TAG, "username=" + username + "image=" + image + "openId" + openId);
        editor.commit();
    }


    public static void sf_saveIsWXLogin(boolean isWx) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean(WX_LOGIN, isWx);
        editor.commit();
    }

    public static boolean geIsWXLogin() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        return sf.getBoolean(WX_LOGIN, false);
    }

    public static String getWxName() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        return sf.getString(WX_NAME, "");
    }

    public static String getWxImage() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        return sf.getString(WX_IMAGE, "");
    }

    public static String getWxOpenid() {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("WX", Context.MODE_PRIVATE);
        return sf.getString(WX_OPENID, "");
    }

    //    *保存用户名和密码
//     * @param username
//     * @param password
//
//    @Deprecated
//    public static void  db_saveUsernameAndPsw(String username,String password){
//        try {
//            snappyDB.put(LOGIN_USERNAME, username);
//            snappyDB.put(LOGIN_PASSWORD, password);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//    }
//    *
//     * 从DB获取用户名和密码
//     *
//     * @return
//     *
//
//    public String db_getUsername(){
//        try {
//           return snappyDB.get(LOGIN_USERNAME);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    *
//     * 获取输入密码
//     *
//     * @return
//     *
//
//    public String sf_getPsw(){
//        try {
//            return snappyDB.get(LOGIN_PASSWORD);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//    *
//     * 从本地sharedpreferences获取用户名和密码
//     * key SFLoginConfig.LOGIN_USERNAME or SFLoginConfig.LOGIN_PASSWORD
//     * @return
//     *
//
    public static Map<String, String> sf_getUsernameAndPwd() {
        HashMap<String, String> map = (HashMap<String, String>) readUsernameAndPwd();
        return readUsernameAndPwd();
    }

    //    *
//     * 从sharedpreferences读取用户名和密码
//     * @return
//
    private static Map<String, String> readUsernameAndPwd() {
        HashMap<String, String> map = new HashMap<String, String>();
//        try {
//            map.put(LOGIN_USERNAME,snappyDB.get(LOGIN_USERNAME)) ;
//            map.put(LOGIN_PASSWORD,snappyDB.get(LOGIN_PASSWORD)) ;
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
        SharedPreferences sharedPrefs = TempApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        map.put(LOGIN_USERNAME, sharedPrefs.getString(LOGIN_USERNAME, ""));
        map.put(LOGIN_PASSWORD, sharedPrefs.getString(LOGIN_PASSWORD, ""));
        Debug.debug(TAG, "LOGIN_USERNAME=" + sharedPrefs.getString(LOGIN_USERNAME, "") + "LOGIN_PASSWORD=" + sharedPrefs.getString(LOGIN_PASSWORD, ""));
        return map;
    }

    public static void sf_saveLoginInfo(ResponseLoginInfo LoginInfo) {

        try {

//        LOGIN_USERID = "userId";
//        LOGIN_USERNAME = "userName";
//        LOGIN_PASSWORD = "password";
//        LOGIN_NICKNAME = "nickName";
//        LOGIN_SEX = "sex";
//        LOGIN_BIRTHDAY = "birthday";
//        LOGIN_SCORE = "score";
//        LOGIN_ONLINEKEY = "onlineKey";
//        LOGIN_DATELINE = "dateline";
//        LOGIN_HEAD = "head";
//        LOGIN_REMEMBER_STATE = "remember_state";
//        COOKIE = "cookie";
            //{,"fanNum":"0",,"company":"","orderNum":"0",}}
//        try {
//            snappyDB.put(LOGIN_USERACOUNT, LoginInfo.getResult().getSuseLoginName()+"");//账号
////            snappyDB.put(LOGIN_PASSWORD, LoginInfo.getResult().getSusePassword()+"");//密码
//            snappyDB.put(LOGIN_LOGTYPE, LoginInfo.getResult().getSuseType() + "");//账户登录类型
//            snappyDB.put(LOGIN_SUSEID, LoginInfo.getResult().getSuseId() + "");//用户ID
//            Debug.info("Db保存用户信息成功");
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
            SharedPreferences sf = TempApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
//        editor.putString(LOGIN_USERACOUNT,LoginInfo.getResult().getSuseLoginName());//账号
           *//* editor.putString(LOGIN_PASSWORD, LoginInfo.getResult().getMusePwd());//密码*//*
            editor.putString(LOGIN_LOGTYPE, LoginInfo.getResult().getMuseType() + "");//账户登录类型
            editor.putString(LOGIN_SUSEID, LoginInfo.getResult().getMuseId() + "");//用户ID
            *//*editor.putString(LOGIN_MUSEPAYUSERIDP, LoginInfo.getResult().getMusePayUserId() + "");//用户ID*//*

//        editor.putString(LOGIN_SCORE,LoginInfo.getData().getScore());
           *//* editor.putString(LOGIN_PHONE, LoginInfo.getResult().getMuseUserName());*//*
            editor.putString(LOGIN_ONLINEKEY, LoginInfo.getResult().getMuseOnlineTag());
           *//* editor.putString(LOGIN_USERNAME, LoginInfo.getResult().getMuseUserName());*//*
            editor.putString(LOGIN_NICKNAME, LoginInfo.getResult().getMuseNickName());
            editor.putString(LOGIN_HEAD, LoginInfo.getResult().getMuseImage());
//        editor.putString(LOGIN_GRADESORT,LoginInfo.getData().getGradesort());
//        editor.putString(LOGIN_COMPANY,LoginInfo.getData().getCompany());
//        editor.putString(LOGIN_ASKCODE,LoginInfo.getData().getInvitationcode());
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sf_saveUserInfo(ResponsSaveUserInfo userInfo) {

        try {

            SharedPreferences sf = TempApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOGIN_LOGTYPE, userInfo.getResult().getMuseType() + "");//账户登录类型
            editor.putString(LOGIN_PHONE, userInfo.getResult().getMusePhone());
            editor.putString(LOGIN_USERNAME, userInfo.getResult().getMuseUserName());
            editor.putString(LOGIN_ONLINEKEY, userInfo.getResult().getMuseOnlineTag());
            editor.putString(LOGIN_HEAD, userInfo.getResult().getMuseImage());
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sf_saveIsReal(String isWx) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_ISREAL, isWx);
        editor.commit();
    }
    public static String sf_getMuseType() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            return sf.getString(TempLoginConfig.LOGIN_LOGTYPE, "");

        } catch (Exception e) {

        }
        return " ";
    }


    public static String sf_getIsReal() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            return "2";//TODO 客户要求暂时改为2
//            return sf.getString(TempLoginConfig.LOGIN_ISREAL,"0");//TODO 还原0

        } catch (Exception e) {

        }
//        return " ";//TODO 还原0
        return "2";//TODO 客户要求暂时改为2
    }

    //
//    *获取用户id
//     * @return
//
    public static String sf_getSueid() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            return sf.getString(TempLoginConfig.LOGIN_SUSEID, "");

        } catch (Exception e) {

        }
        return " ";
    }

    public static void sf_saveUserHead(String head) {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOGIN_HEAD, head);
            editor.commit();
        } catch (Exception e) {

        }


    }

    public static String sf_getUserHead() {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);

            return sf.getString(TempLoginConfig.LOGIN_HEAD, "");
        } catch (Exception e) {

        }
        return "";

    }

    public static String sf_getUserName() {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);

            return sf.getString(TempLoginConfig.LOGIN_USERNAME, "");
        } catch (Exception e) {

        }
        return "";

    }

    public static String sf_getNickName() {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);

            return sf.getString(TempLoginConfig.LOGIN_NICKNAME, "");
        } catch (Exception e) {

        }
        return "";

    }

    public static String sf_getBirthday() {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);

            return sf.getString(TempLoginConfig.LOGIN_BIRTHDAY, "");
        } catch (Exception e) {

        }
        return "1970-01-01";

    }

    public static String sf_getUserPhone() {

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);

            return sf.getString(TempLoginConfig.LOGIN_PHONE, "");
        } catch (Exception e) {

        }
        return "";

    }


    //
//    *获取用户帐号
//     * @return
//
//    public static String sf_getUserAcount(){
//        try {
//            Debug.debug(TAG, "获取UserAcount=" + snappyDB.get(LOGIN_USERACOUNT));
//            return snappyDB.get(LOGIN_USERACOUNT);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
////        Debug.debug(TAG, "LOGIN_USERID=" + sf.getString(LOGIN_USERACOUNT, ""));
//        return "";
//    }
//    *获取NickName
//     * @return
//
//    public static String sf_getNickName(){
//        try {
//            Debug.debug(TAG, "LOGIN_NICKNAME=" + snappyDB.get(LOGIN_NICKNAME));
//            return snappyDB.get(LOGIN_NICKNAME);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
//        return "";
//    }
//    *获取Score
//     * @return
//
//    public static String sf_getScore(){
//        try {
//            Debug.debug(TAG, "LOGIN_SCORE=" + snappyDB.get(LOGIN_SCORE));
//            return snappyDB.get(LOGIN_SCORE);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
////        Debug.debug(TAG, "LOGIN_SCORE=" + sf.getString(LOGIN_SCORE, ""));
////        return sf.getString(LOGIN_SCORE,"");
//        return "";
//    }
//    *获取Head
//     * @return
//
//    public static String sf_getHead(){
//        try {
//            Debug.debug(TAG, "LOGIN_HEAD=" + snappyDB.get(LOGIN_HEAD));
//            return snappyDB.get(LOGIN_HEAD);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
////        Debug.debug(TAG, "LOGIN_HEAD=" + sf.getString(LOGIN_HEAD, ""));
////        return sf.getString(LOGIN_HEAD,"");
//        return "";
//    }
//    *获取OnLineKey
//     * @return
//
    public static String sf_getOnLineKey() {
//        try {
//            Debug.debug(TAG, "LOGIN_ONLINEKEY=" + snappyDB.get(LOGIN_ONLINEKEY));
//            return snappyDB.get(LOGIN_ONLINEKEY);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }

        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOGIN_ONLINEKEY=" + sf.getString(LOGIN_ONLINEKEY, ""));
            return sf.getString(LOGIN_ONLINEKEY, "");
        } catch (Exception e) {

        }

        return "";
    }

    public static String sf_getPassWord() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOGIN_ONLINEKEY=" + sf.getString(LOGIN_PASSWORD, ""));
            return sf.getString(LOGIN_PASSWORD, "");
        } catch (Exception e) {

        }
        return "";
    }



    public static String sf_getUserid() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_USEID, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOGIN_ONLINEKEY=" + sf.getString(LOGIN_MUSEPAYUSERIDP, ""));
            return sf.getString(LOGIN_MUSEPAYUSERIDP, "");
        } catch (Exception e) {

        }
        return "";
    }


    *//**
     * 保存是否融云已经初始化
     *//*
    public static void saveRongIMIsInit(boolean isInit) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("RongIM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("isinit", isInit);
        editor.commit();
    }

    *//**
     * 获取是否融云已经初始化
     *//*
    public static boolean getRongIMIsInit() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("RongIM", Context.MODE_PRIVATE);
            return sf.getBoolean("isinit", false);
        } catch (Exception e) {

        }
        return false;
    }

    *//**
     * 保存是否融云已经连接
     *//*
    public static void saveRongIMIsConnect(boolean isInit) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("RongIM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("isConnect", isInit);
        editor.commit();
    }

    public static boolean getRongIMIsConnect() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("RongIM", Context.MODE_PRIVATE);
            return sf.getBoolean("isConnect", false);
        } catch (Exception e) {

        }
        return false;
    }

    *//**
     * 保存是否已设置极光推送别名
     *//*
    public static void setJpshAlias(String alias, boolean successs) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("JPush", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("alias", alias);
        editor.putBoolean("isSuccess", successs);
        editor.commit();
    }

    *//**
     * 获取是否已设置极光推送别名
     *//*
    public static boolean getJpshAlias() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences("JPush", Context.MODE_PRIVATE);
            return sf.getBoolean("isSuccess", false);
        } catch (Exception e) {

        }
        return false;
    }


    public final static String LOCATION_STATUS = "location_status";//定位状态
    public final static String LOCATION_INFO = "location_info";//定位信息
    public final static String LOCATION_CITY_ID = "location_city_id";//城市id
    public final static String LOCATION_LAT = "location_lat";//经纬度
    public final static String LOCATION_LNG = "location_lng";//经纬度
    public final static String LOCATION_ADRESS = "location_adrdd";//定位信息
    public final static String LOCATION_NEWADRESS = "location_nw";//经纬度

    *//**
     * 保存定位状态
     *
     * @param status
     *//*
    public static void sf_saveLocation_status(boolean status) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean(LOCATION_STATUS, status);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * 保存定位状态
     *
     * @param lag_lng index 0纬度，index1 经度
     *
     *//*
    public static void sf_saveLocation_lat_lng(String[] lag_lng) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_LAT, lag_lng[0]);
            editor.putString(LOCATION_LNG, lag_lng[1]);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sf_saveLocation_lat_lngadress(String adress) {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_ADRESS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_NEWADRESS, adress);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * 获取定位定制
     *
     * @return
     *//*
    public static String StringgetLocationLa() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_ADRESS, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOCATION_CITY_ID=" + sf.getString(LOCATION_NEWADRESS, ""));//地址
            return sf.getString(LOCATION_NEWADRESS, "");
        } catch (Exception e) {

        }
        return "";
    }

    *//**
     * @return index0 lat index1 long
     *//*
    public static String[] getLocationLatLng() {
        String[] data = new String[2];
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOCATION_LAT=" + sf.getString(LOCATION_LAT, "0"));//默认自动登录
            Debug.debug(TAG, "LOCATION_LNG=" + sf.getString(LOCATION_LNG, "0"));//默认自动登录

            data[0] = sf.getString(LOCATION_LAT, "");
            data[1] = sf.getString(LOCATION_LNG, "");
            return data;
        } catch (Exception e) {

        }
        return data;
    }

    *//**
     * 获取定位状态
     *
     * @return
     *//*
    public static boolean getLocationStatus() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOCATION_STATUS=" + sf.getBoolean(LOCATION_STATUS, false));//默认自动登录
            return sf.getBoolean(LOCATION_STATUS, false);
        } catch (Exception e) {

        }
        return false;
    }

    *//**
     * 保存定位城市id
     *
     * @param id
     *//*
    public static void sf_saveLocation_cityId(String id) {
        Debug.info(TAG, "保存城市id=" + id);
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(LOCATION_CITY_ID, id);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * 获取定位状态
     *
     * @return
     *//*
    public static String getLocationCityId() {
        try {
            SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOCATION_INFO, Context.MODE_PRIVATE);
            Debug.debug(TAG, "LOCATION_CITY_ID=" + sf.getString(LOCATION_CITY_ID, ""));//默认自动登录
            return sf.getString(LOCATION_CITY_ID, "");
        } catch (Exception e) {

        }
        return "";
    }


    public static void sf_savePhone(String phone) {
        SharedPreferences sf = TempApplication.getInstance().getApplicationContext().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(LOGIN_PHONE, phone);
        editor.commit();
    }
//    *获取Birthday
//     * @return
//
//    public static String sf_getBirthday(){
//        try {
//            Debug.debug(TAG, "LOGIN_BIRTHDAY=" + snappyDB.get(LOGIN_BIRTHDAY));
//            return snappyDB.get(LOGIN_BIRTHDAY);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
////        Debug.debug(TAG, "LOGIN_BIRTHDAY=" + sf.getString(LOGIN_BIRTHDAY, ""));
////        return sf.getString(LOGIN_BIRTHDAY,"");
//        return "";
//    }
//    *获取Sex
//     * @return
//
//    public static String sf_getSex(){
//        try {
//            Debug.debug(TAG, "LOGIN_SEX=" + snappyDB.get(LOGIN_SEX));
//            return snappyDB.get(LOGIN_SEX);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TABLE_NAME, Context.MODE_PRIVATE);
////        Debug.debug(TAG, "LOGIN_SEX=" + sf.getString(LOGIN_SEX, ""));
////        return sf.getString(LOGIN_SEX,"");
//        return "";
//    }
//
////    *获取SharedPreferences 数据信息
////     * @param login_info SharedPreferences常量名
////     * @return
////
////    public static String sf_getInfo(String login_info){
////        try {
////            Debug.debug(TAG, "login_info=" + getDb().get(login_info));
////            return getDb().get(login_info);
////        } catch (SnappydbException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
//    *
//     * 从本地获取登录数据
//     * @return
//
//    public static RespActLogin.ResultEntity sf_getLoginInfo(){
//        RespActLogin.ResultEntity userEntity = new RespActLogin.ResultEntity();
//        try {
//
//            Debug.debug(TAG, "login_info=" + snappyDB.get(LOGIN_USER_INFO));
//            userEntity =snappyDB.getObject(LOGIN_USER_INFO, RespActLogin.ResultEntity.class);
//            return userEntity;
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
////        RespActLogin.ResultEntity loginInfoData = new RespActLogin.ResultEntity();
////        loginInfoData = (RespActLogin.ResultEntity)getDataFromSharedPreferences(LOGIN_TABLE_NAME, loginInfoData);
//        return userEntity;
//    }
//
//    @Deprecated
//    private static Object getDataFromSharedPreferences(String name,Object data){
//        SharedPreferences sharedPrefs = MainApplication.getInstance()
//                .getApplicationContext()
//                .getSharedPreferences(name, Context.MODE_PRIVATE);
//        Field[] fields = data.getClass().getDeclaredFields();
//        for(int i = 0;i < fields.length;i++){
//            fields[i].setAccessible(true);
//            String fieldName = fields[i].getName();//获取属性名字
//            String type = fields[i].getType().toString();
//            try {
//                if(type.endsWith("boolean")){
//                    fields[i].set(data,sharedPrefs.getBoolean(fieldName,false));
//                }else if(type.endsWith("float")){
//                    fields[i].set(data,sharedPrefs.getFloat(fieldName, 0));
//                }else if(type.endsWith("int")){
//                    fields[i].set(data,sharedPrefs.getInt(fieldName, 0));
//                }else if(type.endsWith("long")){
//                    fields[i].set(data,sharedPrefs.getLong(fieldName,0));
//                }else if(type.endsWith("String")){
//                    String a = sharedPrefs.getString(fieldName, "");
//                    fields[i].set(data,a);
//                }
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                Debug.error("SFLoginConfig", "getDataFromSharedPreferences()...属性访问异常:" + e.toString());
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                Debug.error("SFLoginConfig", "getDataFromSharedPreferences()...属性访问异常:" + e.toString());
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//
//
//    *
//     *  记录登录方式  LOGIN_REMOTE = "1";  //异地登录
//     LOGIN_TONGCHENG = "0"; //同城登录
//     * @param logonTpye
//
//    public static void  saveLoginType(String logonTpye){
//        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TYPE, Context.MODE_PRIVATE);
//        sf.edit().putString(LOGIN_TYPE,logonTpye).commit();
//
//    }
//
//    *
//     *  获得登录方式  LOGIN_REMOTE = "1";  //异地登录
//     LOGIN_TONGCHENG = "0"; //同城登录
//     * @param
//
//    public static String  getLoginType(){
//        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(LOGIN_TYPE, Context.MODE_PRIVATE);
//
//        return sf.getString(LOGIN_TYPE, DBLoginConfig.LOGIN_REMOTE);
//
//    }
//
//    //设置标志 标注广播是否已经注册
//    public static boolean isRegisterBroadcast = false;
//    //保存网络状态是否可用
//    public static boolean isNetWork = false;
//
//    public static void  savePhoneNum(String phone){
//        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(PHONE, Context.MODE_PRIVATE);
//        sf.edit().putString(PHONE,phone).commit();
//
//    }
//
//    public static String  getPhoneNum(){
//        SharedPreferences sf = MainApplication.getInstance().getSharedPreferences(PHONE, Context.MODE_PRIVATE);
//
//        return sf.getString(PHONE, DBLoginConfig.LOGIN_REMOTE);
//
//    }
////    public static DB getDb(){
////      return  MainApplication.getInstance().getSnappydb();
////    }
//    public static void destory(){
//    if (snappyDB!=null){
//        try {
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//    }
//}*/
}