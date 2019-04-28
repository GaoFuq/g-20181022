package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2018/1/15.
 */

public class RespGetUnreadMessage extends TempResponse {

    /**
     * result : {"num":0}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * num : 0
         */

        private String num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
