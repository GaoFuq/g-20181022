package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2017/12/27.
 */

public class RespGetDynamicLikes extends TempResponse {


    /**
     * result : {"list":[{"museNickName":"闪电侠不爱笑","museId":231}],"size":1}
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
         * list : [{"museNickName":"闪电侠不爱笑","museId":231}]
         * size : 1
         */

        private String size;
        private List<ListBean> list;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * museNickName : 闪电侠不爱笑
             * museId : 231
             */

            private String museNickName;
            private String museId;

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }
        }
    }
}
