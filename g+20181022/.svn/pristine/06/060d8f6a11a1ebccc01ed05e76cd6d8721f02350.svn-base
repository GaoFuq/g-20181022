package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2017/12/25.
 */

public class RespMyFriendsList extends TempResponse {


    /**
     * result : {"source":[{"museImage":"0201712191426554052.jpg","address":" 重庆市 万州区","roomStatus":0,"museSex":0.5,"museNickName":"订单","museId":202},{"museImage":"0201712211906247120.jpg","address":" 重庆市 梁平县","roomStatus":0,"museSex":1,"museNickName":"宇宙第一帅","museId":215}],"page":1,"pageSize":10,"pageLength":1,"size":2}
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
         * source : [{"museImage":"0201712191426554052.jpg","address":" 重庆市 万州区","roomStatus":0,"museSex":0.5,"museNickName":"订单","museId":202},{"museImage":"0201712211906247120.jpg","address":" 重庆市 梁平县","roomStatus":0,"museSex":1,"museNickName":"宇宙第一帅","museId":215}]
         * page : 1
         * pageSize : 10
         * pageLength : 1
         * size : 2
         */

        private String page;
        private String pageSize;
        private String pageLength;
        private String size;
        private List<SourceBean> source;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageLength() {
            return pageLength;
        }

        public void setPageLength(String pageLength) {
            this.pageLength = pageLength;
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
             * museImage : 0201712191426554052.jpg
             * address :  重庆市 万州区
             * roomStatus : 0
             * museSex : 0.5
             * museNickName : 订单
             * museId : 202
             */

            private String museImage;
            private String address;
            private String roomStatus;
            private String museSex;
            private String museNickName;
            private String museId;

            public String getMuseImage() {
                return museImage;
            }

            public void setMuseImage(String museImage) {
                this.museImage = museImage;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(String roomStatus) {
                this.roomStatus = roomStatus;
            }

            public String getMuseSex() {
                return museSex;
            }

            public void setMuseSex(String museSex) {
                this.museSex = museSex;
            }

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
