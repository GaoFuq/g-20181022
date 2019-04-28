package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-12-15.
 */

public class RespGetHelpList extends TempResponse {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * helpContext : retreat1
         * helpId : 23
         * helpTitle : 儿童热特
         */

        private String helpContext;
        private String helpId;
        private String helpTitle;

        public String getHelpContext() {
            return helpContext;
        }

        public void setHelpContext(String helpContext) {
            this.helpContext = helpContext;
        }

        public String getHelpId() {
            return helpId;
        }

        public void setHelpId(String helpId) {
            this.helpId = helpId;
        }

        public String getHelpTitle() {
            return helpTitle;
        }

        public void setHelpTitle(String helpTitle) {
            this.helpTitle = helpTitle;
        }
    }
}
