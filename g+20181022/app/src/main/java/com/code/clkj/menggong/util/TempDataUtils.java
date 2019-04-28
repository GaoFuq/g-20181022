package com.code.clkj.menggong.util;

import android.text.TextUtils;

import com.lf.tempcore.tempModule.tempUtils.NullUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by caoyang on 2017/7/22.
 */

public class TempDataUtils {

    /*//距离转化 distance 单位m hasTow:两种语言
    public static String displayDistance(String distance, boolean hasTow) {
        if (NullUtils.isEmpty(distance))
            return "0";
        String dw = TempLoginConfig.sf_getIsChinese() ? "千" : "K";
        try {
            BigDecimal bigDecimal = new BigDecimal(distance);
            BigDecimal decimal = new BigDecimal(1000);
            if (bigDecimal.compareTo(decimal) == 1) {
                String res;
                if (hasTow) {
                    res = bigDecimal.divide(decimal, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + dw;
                } else {
                    res = bigDecimal.divide(decimal, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "K";
                }
                return res;
            } else {
                return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            }
        } catch (Exception e) {
            return distance;
        }
    }
*/
    public static float string2Float(String average) {
        if (NullUtils.isEmpty(average)) {
            return 0.0f;
        }
        BigDecimal b = new BigDecimal(average);
        float d = b.floatValue();
        return d;
    }

    public static double string2Double(String average) {
        if (NullUtils.isEmpty(average)) {
            return 0.0d;
        }
        BigDecimal b = new BigDecimal(average);
        double d = b.doubleValue();
        return d;
    }

    public static String doubleAddDouble(String... vs) {
        BigDecimal d = new BigDecimal("0");
        for (String v : vs) {
            if (TextUtils.isEmpty(v))
                v = "0";
            BigDecimal b = new BigDecimal(v);
            d = d.add(b);
        }
        return subZeroAndDot(d.toString());
    }

    public static String doubleSubDouble(String v, String v1) {
        if (TextUtils.isEmpty(v))
            v = "0";
        if (v1.isEmpty())
            v1 = "0";
        BigDecimal d = new BigDecimal(v);
        BigDecimal b = new BigDecimal(v1);
        return subZeroAndDot(d.subtract(b).toString());
    }
    public static int compareTo(String v, String v1) {
        if (TextUtils.isEmpty(v))
            v = "0";
        if ((TextUtils.isEmpty(v1)))
            v1 = "0";
        BigDecimal d = new BigDecimal(v);
        BigDecimal b = new BigDecimal(v1);
        return d.compareTo(b);
    }



    public static int string2Int(String pageLength) {
        try {
            if (NullUtils.isEmpty(pageLength)) {
                return 0;
            }
            BigDecimal b = new BigDecimal(pageLength);
            int d = b.intValue();
            return d;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int string2Int(String pageLength,int NullInt) {
        try {
            if (NullUtils.isEmpty(pageLength)) {
                return NullInt;
            }
            BigDecimal b = new BigDecimal(pageLength);
            int d = b.intValue();
            return d;
        } catch (Exception ex) {
            ex.printStackTrace();
            return NullInt;
        }

    }

    public static long string2Long(String pageLength) {
        if (NullUtils.isEmpty(pageLength)) {
            return 0;
        }
        BigDecimal b = new BigDecimal(pageLength);
        long d = b.longValue();
        return d;
    }

    public static Integer string2Integer(String pageLength) {
        if (NullUtils.isEmpty(pageLength)) {
            return 0;
        }
        BigDecimal b = new BigDecimal(pageLength);
        Integer d = b.intValue();
        return d;
    }

    public static final String NULL_STR = "暂无";

    /**
     * 字符串 转化为不为空
     */
    public static String string2NotNull(String tsStr) {

        return NullUtils.isEmpty(tsStr) ? NULL_STR : tsStr;
    }



    public static Timestamp string2Timestamp(String tsStr) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        String tsStr = "2011-05-09 11:49:45";
        //注：String的类型必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错！！！
        try {
            ts = Timestamp.valueOf(tsStr);
//            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }


    /**
     * String[] -> List<String>
     */
    public static List<String> StringToList(String[] mStrings) {
        List<String> strings2 = new ArrayList<>();
        Collections.addAll(strings2, mStrings);
        return strings2;
    }

    /**
     * 营业时间组合
     *
     * @param start
     * @param end
     * @return
     */
    public static String getYYSJ(String start, String end) {
        String YYSHJ = "00:00-00:00";
        if (start.isEmpty()) {
            start = "00:00";
        }
        if (end.isEmpty()) {
            end = "00:00";
        }
        YYSHJ = start + "-" + end;
        return YYSHJ;
    }

    /**
     * 1:营业中 2：暂停营业
     */
    public static boolean isBusinessStatus(String businessStatus) {
        if (businessStatus.isEmpty()) {
            return false;
        }
        switch (businessStatus) {
            case "1":
                return true;
            case "2":
                return false;
            default:
                return false;
        }
    }

/*    public static int getMr(String msadType) {
        if (msadType.isEmpty()) {
            return R.string.Mr;
        }
        switch (msadType) {
            case "1":
                return R.string.Mr;
            case "2":
                return R.string.Ms;
            case "3":
                return R.string.company;
            default:
                return R.string.Mr;
        }
    }*/

    /*     1：待支付     2：已支付    3：配送员已接单
         4：商家接单 5：开始配送（前往商家）    6：配送员取货
         7：配送员送达    8： 用户收货  9:已评价
         10：用户未支付取消订单    11：用户已支付商家未接单申请退款
         12：商家拒单 13：配送员抢单后取消订单*/
    public static String getMaorStatus(String maorStatus) {
        if (maorStatus == null) {
            return "";
        }
        switch (maorStatus) {
            case "1":
                return "待支付";
            case "2":
                return "待接单";
            case "3":
                return "待收货";
            case "4":
                return "待收货";
            case "5":
                return "待收货";
            case "6":
                return "待收货";
            case "7":
                return "待收货";
            case "8":
                return "已完成";
            case "9":
                return "已评价";
            case "10":
                return "订单取消";
            case "11":
                return "退款中";
            case "12":
                return "商家拒单";
            case "13":
                return "配送员抢单后取消订单";
            case "14":
                return "暂无配送员接单";
            case "15":
                return "退款完成";

        }
        return maorStatus;
    }

    public static int getMaorStatus1(String maorStatus) {
        if (maorStatus == null) {
            return 0;
        }
        switch (maorStatus) {
            case "1":
                return 1;//取消订单,付款
            case "2":
                return 2;//申请退款
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "10":
            case "11":
            case "15":
            case "12":
            case "13":
            case "14":
                return 3;//
            case "8":
                return 4;//去评价。再次购买
            case "9":
                return 5;//已评价。再次购买


        }
        return 0;
    }

    // 	1.及时订单2.预定订单
    public static String getDistributionType(String distributionType) {
        if (distributionType == null) {
            return "";
        }
        switch (distributionType) {
            case "1":
                return "及时订单";
            case "2":
                return "预定订单";
        }
        return distributionType;
    }

    public static Timestamp min2Timestamp(String min) {
        int minint = string2Int(min);
        int time = minint * 60 * 1000;
        long psTime = System.currentTimeMillis() + time;
        Timestamp timestamp = new Timestamp(psTime);
        return timestamp;
    }

    public static String timestamp2String(Timestamp maorDistributionDay) {

        Timestamp current = new Timestamp(System.currentTimeMillis());
        Date currentdate = new Date(System.currentTimeMillis());
        Calendar currentcalendar = Calendar.getInstance();
        currentcalendar.setTime(currentdate);

        Date maorDistribution = new Date(maorDistributionDay.getTime());
        Calendar currentDistribution = Calendar.getInstance();
        currentDistribution.setTime(maorDistribution);

        if (maorDistributionDay.compareTo(current) != 1) {
            return String.format("%s(大约%02d:%02d)", "立即送出", maorDistributionDay.getHours(), maorDistributionDay.getMinutes());
        }
        String timestr = "";
        long curHour = currentcalendar.get(Calendar.HOUR_OF_DAY);
        long cha = maorDistributionDay.getTime() - System.currentTimeMillis();
        long day = (cha / (1000 * 60 * 60) + curHour) / 24;
        if (day == 0) {
            timestr += "立即送出";
        } else if (day == 1) {
            timestr += "明天";
        } else {
            timestr += "后天";
        }
        return String.format("%s(大约%02d:%02d)", timestr, maorDistributionDay.getHours(), maorDistributionDay.getMinutes());
    }

    //去除小数点后多余的零
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static boolean isCollection(String isCollection) {
        if (TextUtils.isEmpty(isCollection)) {
            return false;
        }
        switch (isCollection) {
            case "0":
                return false;
            case "1":
                return true;
            default:
                return false;
        }
    }

   /* public static String getMaorIds(List<ResponseAddOrder.ResultEntity.DataEntity> data) {
        String ids = "";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                ids += data.get(i).getMaorId() + "";
            } else {
                ids += data.get(i).getMaorId() + "-";
            }
        }
        return ids;
    }*/
}

