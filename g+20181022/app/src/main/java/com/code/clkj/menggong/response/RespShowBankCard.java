package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2017/12/22.
 */

public class RespShowBankCard extends TempResponse {

    /**
     * result : {"page":1,"pageLength":1,"pageSize":10,"size":3,"source":[{"mbanCard":"2588888886665588888","mbanCreateTime":"2017-08-03 16:24:10","mbanId":6,"mbanMemberName":"凸轮","mbanMuseId":174,"mbanName":"华夏银行","mbanStatus":0},{"mbanCard":"4444444444444444444","mbanCreateTime":"2017-08-03 15:32:59","mbanId":5,"mbanMemberName":"dddd","mbanMuseId":174,"mbanName":"tt","mbanStatus":0},{"mbanCard":"235642185","mbanCreateTime":"2017-08-02 15:09:07","mbanId":3,"mbanMemberName":"张三","mbanMuseId":174,"mbanName":"农业银行","mbanStatus":0}]}
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
         * page : 1
         * pageLength : 1
         * pageSize : 10
         * size : 3
         * source : [{"mbanCard":"2588888886665588888","mbanCreateTime":"2017-08-03 16:24:10","mbanId":6,"mbanMemberName":"凸轮","mbanMuseId":174,"mbanName":"华夏银行","mbanStatus":0},{"mbanCard":"4444444444444444444","mbanCreateTime":"2017-08-03 15:32:59","mbanId":5,"mbanMemberName":"dddd","mbanMuseId":174,"mbanName":"tt","mbanStatus":0},{"mbanCard":"235642185","mbanCreateTime":"2017-08-02 15:09:07","mbanId":3,"mbanMemberName":"张三","mbanMuseId":174,"mbanName":"农业银行","mbanStatus":0}]
         */

        private String page;
        private String pageLength;
        private String pageSize;
        private String size;
        private List<SourceBean> source;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageLength() {
            return pageLength;
        }

        public void setPageLength(String pageLength) {
            this.pageLength = pageLength;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<SourceBean> getSource() {
            return source;
        }

        public void setSource(List<SourceBean> source) {
            this.source = source;
        }

        public static class SourceBean {
            /**
             * mbanCard : 2588888886665588888
             * mbanCreateTime : 2017-08-03 16:24:10
             * mbanId : 6
             * mbanMemberName : 凸轮
             * mbanMuseId : 174
             * mbanName : 华夏银行
             * mbanStatus : 0
             */

            private String mbanCard;
            private String mbanCreateTime;
            private String mbanId;
            private String mbanMemberName;
            private String mbanMuseId;
            private String mbanName;
            private String mbanStatus;

            public String getMbanCard() {
                return mbanCard;
            }

            public void setMbanCard(String mbanCard) {
                this.mbanCard = mbanCard;
            }

            public String getMbanCreateTime() {
                return mbanCreateTime;
            }

            public void setMbanCreateTime(String mbanCreateTime) {
                this.mbanCreateTime = mbanCreateTime;
            }

            public String getMbanId() {
                return mbanId;
            }

            public void setMbanId(String mbanId) {
                this.mbanId = mbanId;
            }

            public String getMbanMemberName() {
                return mbanMemberName;
            }

            public void setMbanMemberName(String mbanMemberName) {
                this.mbanMemberName = mbanMemberName;
            }

            public String getMbanMuseId() {
                return mbanMuseId;
            }

            public void setMbanMuseId(String mbanMuseId) {
                this.mbanMuseId = mbanMuseId;
            }

            public String getMbanName() {
                return mbanName;
            }

            public void setMbanName(String mbanName) {
                this.mbanName = mbanName;
            }

            public String getMbanStatus() {
                return mbanStatus;
            }

            public void setMbanStatus(String mbanStatus) {
                this.mbanStatus = mbanStatus;
            }
        }
    }
}
