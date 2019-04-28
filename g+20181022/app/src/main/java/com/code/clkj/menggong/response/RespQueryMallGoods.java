package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/4.
 */

public class RespQueryMallGoods extends TempResponse {


    /**
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
         * size : 9
         * source : [{"discountPrice":1451,"mgooId":28,"mgooImage":"020171206092244360.jpg","mgooMuseId":179,"mgooName":"dfrrgr","mgooStockNum":0},{"discountPrice":0.65,"mgooId":27,"mgooImage":"0201712041812339501.jpg","mgooMuseId":177,"mgooName":"规范","mgooStockNum":0},{"discountPrice":8.25,"mgooId":25,"mgooImage":"0201712041804331177.jpg","mgooMuseId":176,"mgooName":"防守打","mgooStockNum":0},{"discountPrice":100,"mgooId":24,"mgooImage":"0201712041800278496.jpg","mgooMuseId":176,"mgooName":"sfdsfd","mgooStockNum":0},{"discountPrice":2,"mgooId":3,"mgooImage":"0201709251743134313.jpg","mgooMuseId":174,"mgooName":"牛板筋1","mgooStockNum":0},{"discountPrice":123,"mgooId":5,"mgooImage":"0201709251753313746.jpg","mgooMuseId":175,"mgooName":"桃花醉","mgooStockNum":478},{"discountPrice":25,"mgooId":4,"mgooImage":"0201709251747172194.jpg","mgooMuseId":177,"mgooName":"叫花鸡","mgooStockNum":350},{"discountPrice":38,"mgooId":2,"mgooImage":"0201712041803168697.jpg","mgooMuseId":175,"mgooName":"牛肉","mgooStockNum":50},{"discountPrice":20,"mgooId":1,"mgooImage":"0201709261106544030.jpg","mgooMuseId":174,"mgooName":"风车车","mgooStockNum":10}]
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
             * discountPrice : 1451
             * mgooId : 28
             * mgooImage : 020171206092244360.jpg
             * mgooMuseId : 179
             * mgooName : dfrrgr
             * mgooStockNum : 0
             */

            private String discountPrice;
            private String mgooId;
            private String mgooImage;
            private String mgooMuseId;
            private String mgooName;
            private String mgooStockNum;

            public String getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(String discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getMgooId() {
                return mgooId;
            }

            public void setMgooId(String mgooId) {
                this.mgooId = mgooId;
            }

            public String getMgooImage() {
                return mgooImage;
            }

            public void setMgooImage(String mgooImage) {
                this.mgooImage = mgooImage;
            }

            public String getMgooMuseId() {
                return mgooMuseId;
            }

            public void setMgooMuseId(String mgooMuseId) {
                this.mgooMuseId = mgooMuseId;
            }

            public String getMgooName() {
                return mgooName;
            }

            public void setMgooName(String mgooName) {
                this.mgooName = mgooName;
            }

            public String getMgooStockNum() {
                return mgooStockNum;
            }

            public void setMgooStockNum(String mgooStockNum) {
                this.mgooStockNum = mgooStockNum;
            }
        }
    }
}
