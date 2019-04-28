package com.lf.PayAndShare;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by longf on 2016/7/27.
 */

public class TempShareConfiger {
    //各个平台的配置，建议放在全局Application或者程序入口
    public void initConfig()
    {
        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
       // PlatformConfig.setWeixin("wx9944b9cc8233d963", "2015A2328BD38BD11B53E993187E7986");
        PlatformConfig.setWeixin("wx148f868e44911c06", "2d27724b90167b33e69fafc225e1db42");
                               //   wx6f65e789b8c8d043
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
        PlatformConfig.setSinaWeibo("169085366", "75ba23a7083eaeb7f0f6147ae45b2fff","https://sns.whalecloud.com/sina2/callback");
     //  PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //易信
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        // QQ和Qzone appid appkey
          PlatformConfig.setQQZone("1106427408", "KEY7Rz6f4jJDDc4uLyl");
    //    PlatformConfig.setQQZone("1105555231", "JgdgUgSfQgqVfjAr");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");

    }
}
