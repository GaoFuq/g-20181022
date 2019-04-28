package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2017/12/21.
 */

public class RespCheckLS extends TempResponse {

    /**
     * result : {"page":1,"pageLength":1,"pageSize":10,"size":1,"source":[{"meloContent":"领取主播我要发的红包","meloDirection":1,"meloId":319,"meloPrice":7,"meloTime":"2017-12-08 16:10:12"}]}
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
         * size : 1
         * source : [{"meloContent":"领取主播我要发的红包","meloDirection":1,"meloId":319,"meloPrice":7,"meloTime":"2017-12-08 16:10:12"}]
         */

        private String page;
        private int pageLength;
        private String pageSize;
        private int size;
        private List<SourceBean> source;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getPageLength() {
            return pageLength;
        }

        public void setPageLength(int pageLength) {
            this.pageLength = pageLength;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
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
             * meloContent : 领取主播我要发的红包
             * meloDirection : 1
             * meloId : 319
             * meloPrice : 7
             * meloTime : 2017-12-08 16:10:12
             */

            private String meloContent;
            private String meloDirection;
            private String meloId;
            private String meloPrice;
            private String meloTime;

            public String getMeloContent() {
                return meloContent;
            }

            public void setMeloContent(String meloContent) {
                this.meloContent = meloContent;
            }

            public String getMeloDirection() {
                return meloDirection;
            }

            public void setMeloDirection(String meloDirection) {
                this.meloDirection = meloDirection;
            }

            public String getMeloId() {
                return meloId;
            }

            public void setMeloId(String meloId) {
                this.meloId = meloId;
            }

            public String getMeloPrice() {
                return meloPrice;
            }

            public void setMeloPrice(String meloPrice) {
                this.meloPrice = meloPrice;
            }

            public String getMeloTime() {
                return meloTime;
            }

            public void setMeloTime(String meloTime) {
                this.meloTime = meloTime;
            }
        }
    }
}
