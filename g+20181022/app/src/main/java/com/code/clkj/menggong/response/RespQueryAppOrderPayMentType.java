package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/11.
 */

public class RespQueryAppOrderPayMentType extends TempResponse {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * optyId : 1
         * optyImage : 020171219141317595.jpg
         * optyName : 支付宝支付
         */

        private String optyId;
        private String optyImage;
        private String optyName;

        public String getOptyId() {
            return optyId;
        }

        public void setOptyId(String optyId) {
            this.optyId = optyId;
        }

        public String getOptyImage() {
            return optyImage;
        }

        public void setOptyImage(String optyImage) {
            this.optyImage = optyImage;
        }

        public String getOptyName() {
            return optyName;
        }

        public void setOptyName(String optyName) {
            this.optyName = optyName;
        }
    }
}
