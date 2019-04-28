package com.code.clkj.menggong.base.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class Constance {



    public static final int  ACT_RESPEST_MAP_CODE = 10;
  /*  public static double mstoLat = 29.8265950000,
            mstoLng = 106.7376990000;
*/
    public static String messNum;

    public static  boolean ISSWITHCFRAGMENT= false;

    public static final String TEMP_KEY = "tem_key";
    public static final String TEMP_ID = "tem_key_id";
    public static final String TEMP_ADD_ADDRESS = "add_address";
    public static final String TEMP_EDIT_ADDRESS = "edit_address";
    public static final String TEMP_ID_ADDRESS = "id_address";

    //UI 绘制时的阀门 调用接口时去掉有关的数据
    public static int flag=0;
    /*临时分享*/
    public static String TEMP_SHARE_URL="";
    public static String TEMP_SHARE_TITLE="FOODMAMA";
    public static String TEMP_SHARE_CONTENT="FOODMAMA DETAIL";

    public static final String ADDR_ADD  = "add";

    public static  String CHANGTYPE  = "2";
//    功能模块
public static final String  ORDER_TYPE_SHOP_1 = "ORDER_TYPE_SHOP_1";//商城订单
    public static final String ORDER_TYPE_CAR_2 = "ORDER_TYPE_CAR_2";//车房订单
    public static final String KEY_SERVICE_NAME= "service_name";
    public static final String KEY_SERVICE_ICON= "service_icon";
    public static final String KEY_SERVICE_PRICE= "service_price";
    public static final String KEY_SERVICE_SIZE= "service_size";
    public static final String KEY_SERVICE_ID= "service_id";
    public static final String KEY_SERVICE_TIME= "service_time";
    public static final String KEY_SERVICE_MIN= "service_min";

    public static final String TEMP_TYPE_KEY = "temp_type_key";
    public static final String TEMP_IMAGE="temp_image";

    public static final String TYPE_HOUSE_CLEAR = "house_clear";
    public static final String TYPE_3C_CLEAR = "3c_clear";
    public static final String TYPE_HOME_FURNISHING_CLEAR = "home_furnishing_clear";
    public static final String TYPE_DEVELOPING_CLEAR = "developing_clear";
    public static final String TYPE_FAMILY_CLEAR = "family_clear";
    public static final String TYPE_RUN_SERVICE = "run_service";
    public static final String TYPE_EXPRESS_SERVICE = "express_service";
    public static final String TYPE_MORE_SERVICE = "more_service";
    public static final String TYPE_DAILY_CLEAR = "daily_clear";
    public static final String TYPE_GLASS_CLEAR = "glass_clear";
    public static final String TYPE_GAS_CLEAR = "gas_clear";
    public static final String TYPE_KETCHEN_CLEAR = "kitchen_clear";
    public static final String TYPE_BRASHROOM_CLEAR = "brashroom_clear";

    public static final String HOME_FAMILY_1 = "家庭保洁";
    public static final String HOME_3C_2 = "家电清洁";
    public static final String HOME_LIVE_3 = "家居保养";
    public static final String HOME_NEW_4 = "开荒保洁";
    public static final String HOME_HELP_5 = "家庭用工";
    public static final String HOME_CATS_6 = "叮咚喵喵";
    public static final String HOME_EXPRESS_7 = "叮咚速运";
    public static final String HOME_MORE_8 = "更多服务";

    public static final String HOME_DAILY_1 = "日常保洁";
 /*   public static final String HOME_GLASSES_2 = "玻璃清洗";*/
    public static final String HOME_MONTH_3 = "包月服务";
    public static final String HOME_GAS_4 = "蒸汽消毒";
    public static final String HOME_KITCHEN_5 = "厨房深度保洁";
    public static final String HOME_BASHROOM_6 = "卫生间深度保洁";

    public static final String KEY_SERVICE_DIFFERENT = "补差价";

    public static final String GOODSID = "GOODSID";

    public static final String MSTOID = "MSTOID";


    //充值金额
    public static final String KEY_RECHARGE_VALUE_BIG = "2000";
    public static final String KEY_RECHARGE_VALUE_MIDDLE = "1000";
    public static final String KEY_RECHARGE_VALUE_SMALL = "500";

    //订单模块
    // 订单类型
    public static final String KEY_ORDER_TYPE = "KEY_ORDER_TYPE";
    public static final String KEY_GOODS_OREDER = "KEY_GOODS_OREDER";
    public static final String KEY_GOODS_PRICE = "KEY_GOODS_PRICE";
    public static final String KEY_GOODS_TITLE = "KEY_GOODS_TITLE";
    public static final String KEY_GOODS_STATIC = "goods_static";
    public static final String KEY_GOODS_MOVE = "goods_move";
    public static final String KEY_GOODS_GOLDEN = "goods_golen";
    public static final String KEY_GOODS_YIN = "goods_yin";

    public static final String KEY_PAY_GOODS_TYPE = "pay_goods_type";
    public static final String KEY_PAY_GOODS_ID = "pay_goods_id";
    public static final String KEY_PAY_GOODS_NO = "pay_goods_no";
    public static final String KEY_PAY_GOODS_NAME = "pay_goods_name";
    public static final String KEY_PAY_GOODS_PRICE = "pay_goods_price";
    public static final String KEY_PAY_GOODS_NAME_VALUE = "电子钱包充值";
    public static final String KEY_PAY_SERVICE_NAME_VALUE = "服务";

    public static String KEY_PAY_SERVICE_NAME_DETELE = "";

//订单
    public static final String GOODID = "good_id";
    public static final String GOODCOUNT = "goodCount";
    //web 模块
    public static final String KEY_WEB_TITLE = "title";
    public static final String KEY_WEB_URL = "url";


    //支付方式模块
    public static final int KEY_ALIPAY_TYPE_ID = 1;
    public static final int KEY_WXPAY_TYPE_ID = 2;
    public static final int KEY_APPPAY_TYPE_ID = 3;

    //请求码
    public static final int KEY_REQUEST_CODE_MAP_1 = 1;
    public static final int KEY_REQUEST_CODE_COMMIT_1 = 11;
    public static final int KEY_REQUEST_CODE_ADDRESS_1 = 22;
    public static final int KEY_REQUEST_CODE_EMAIL = 44;
    public static final int KEY_REQUEST_CODE_PHONE = 55;
    public static final int KEY_REQUEST_CODE_ADD_ADDRESS = 66;
    public static final int REQUEST_PLACE_PICKER = 77;//google play Place Picker
    public static final int REQUEST_CODE_ADD_BANK = 88;
    public static final int KEY_REQUEST_CODE_LOGIN = 99;//请求登录
    public static final int PERMISSIONS_CODE = 111;//请求权限
    public static final int REQUEST_MESSAGE_DETAIL = 112;//消息详情
    public static final int REQUEST_COMMENT = 113;//品论
    public static final int ACT_HOME_MAP_CODE=114;//定位权限
    public static final int ACT_PERSONAL_DATA_CODE = 115;


    //返回码
    public static final int KEY_RESULT_CODE_MAP_1 = 1;
    public static final int KEY_RESULT_CODE_COMMIT_1 = 11;
    public static final int KEY_RESULT_CODE_ADDRESS_1 = 22;
    public static final int KEY_RESULT_CODE_ADDRESS_2 = 33;
    public static final int KEY_RESULT_CODE_EMAIL = 44;
    public static final int KEY_RESULT_CODE_PHONE = 55;
    public static final int KEY_RESULT_CODE_ADD_ADDRESS = 66;
    public static final int RESULT_CODE_ADD_BANK = 88;
    public static final int KEY_RESULT_CODE_LOGIN = 99;//登陆结果
    public static final int RESULT_MESSAGE_DETAIL = 112;//消息详情
    public static final int RESULT_COMMENT = 113;//品论

    //首页
    public static final String KEY_SERVICE_CODE_1 = "1";
    public static final String KEY_SERVICE_CODE_5 = "5";

    //view 刷新 和加载更多的状态
    public static final int KEY_RLV_INIT = 1;
    public static final int KEY_RLV_REFRESH = 1;
    public static final int KEY_RLV_LOADMORE = 2;

    //地址
    public static final String KEY_ADDRESS_ID = "address_id";
    public static final String KEY_ADDRESS_NAME = "address_name";
    public static final String KEY_ADDRESS_DETAIL = "address_detail";
    public static final String KEY_ADDRESS_PHONE = "address_phone";
    public static final String KEY_ADDRESS_ZONE = "address_zone";

    public static final String KEY_ADDRESS_CHOOSE_MARK = "10000";

    //订单
    public static final int KEY_ORDER_STATUS_WAIT_PAY_1 = 1;
    public static final int KEY_ORDER_STATUS_PAY_FINISH_2 = 2;
    public static final int KEY_ORDER_STATUS_EXPRESS_FINISH_3 = 3;
    public static final int KEY_ORDER_STATUS_RECEIVED_FINISH_4 = 4;
    public static final int KEY_ORDER_STATUS_QUIT_WAIT_PAY_5 = 5;
    public static final int KEY_ORDER_STATUS_DELECT_FINISH_PAY_6 = 6;

    public static final String KEYGOODSID="keygoodsid";

    public static final String KEYBOY="boy";
    public static final String KEYGRIL="gril";

    public static final String KEYNiu="KEYNiu";
    public static final String KEYBI="KEYBI";
    public static final String KEYFree="KEYFree";

    public static final String LOCATION_ACTION="com.lingkj.ngsy.location";

    public static final String KEY_OUTORDER="KEY_OUTORDER";

    public static final String KEY_ID="ID";
    public static final String KEY_MONEY="MONEY";

    public static final String KEY_MONEY1="MONEY1";
    public static final String KEY_MONEY2="MONEY2";

    public static final String KEY_ID_USER="ID_USER";

    public static final String USER_ID="USER_ID";
    public static final String USER_MONEY="USER_MONEY";
    public static final String USER_PAYPASSWORD="USER_PAYPASSWORD";

    public static final String KEY_NIUBI = "KEY_NIUBI";
    public static final String KEY_NIUBI_MONEY = "KEY_MONEY";

    public static final String KEY_GOODS_NAME = "KEY_GOODS_NAME";

    //订单
    public static final String KEY_IMAGE_URL="KEY_IMAGE_URL";
    public static final String KEY_TITLE="KEY_TITLE";
    public static final String KEY_NIU_MONEY="KEY_NIU_MONEY";

    public static final String TOWNID0="TOWNID0";
    public static final String TOWNID1="TOWNID1";
    public static final String TOWNID2="TOWNID2";
    public static final String TOWNID3="TOWNID3";

    public static final String CITYID0="CITYID0";
    public static final String CITYID1="CITYID1";
    public static final String CITYID2="CITYID2";
    public static final String CITYID3="CITYID3";


    public static final String NAME0="NAME0";
    public static final String NAME1="NAME1";
    public static final String NAME2="NAME2";
    public static final String NAME3="NAME3";
    public static final String NAME4="NAME4";
    public static final String NAME5="NAME5";
    public static final String NAME6="NAME6";
    public static final String NAME7="NAME7";

    public static final String NAME8="NAME8";
    public static final String NAME9="NAME9";


    public static final String MYMONEY = "MYMONEY";


    public static final int CHANGE= 1;


    public static   List<String> image1=new ArrayList<String>();
    public static  List<String> goodid=new ArrayList<String>();
    public static  List<String> goodModeid=new ArrayList<String>();

    public static  String MODEID="";















    public static String WX_APP_ID = "wxd441c22646a84813";
    public static String WX_ORDER_NO = "";




}
