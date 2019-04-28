package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-12-13.
 */

public class RespQueryAreaCity extends TempResponse {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * AName : 万州区
         * AId : 32218
         */

        private String AName;
        private String AId;

        public String getAName() {
            return AName;
        }

        public void setAName(String AName) {
            this.AName = AName;
        }

        public String getAId() {
            return AId;
        }

        public void setAId(String AId) {
            this.AId = AId;
        }
    }
}
