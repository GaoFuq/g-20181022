package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2017/12/25.
 */

public class RespgetGiftBalance extends TempResponse{


    /**
     * result : {"balance":78}
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
         * balance : 78
         */

        private String balance;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
