package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-12-14.
 */

public class RespMyAttention extends TempResponse {

    /**
     * result : {"size":2,"pageSize":10,"page":1,"source":[{"museNickName":"DD","roomStatus":0,"museId":223,"distance":0,"museImage":"020171225100355261.png","folId":1},{"museNickName":"闪电侠不爱笑","roomStatus":0,"museId":231,"distance":0,"museImage":"0201801021542038536.png","folId":3}]}
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
         * size : 2
         * pageSize : 10
         * page : 1
         * source : [{"museNickName":"DD","roomStatus":0,"museId":223,"distance":0,"museImage":"020171225100355261.png","folId":1},{"museNickName":"闪电侠不爱笑","roomStatus":0,"museId":231,"distance":0,"museImage":"0201801021542038536.png","folId":3}]
         */

        private String size;
        private String pageSize;
        private String page;
        private List<SourceBean> source;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<SourceBean> getSource() {
            return source;
        }

        public void setSource(List<SourceBean> source) {
            this.source = source;
        }

        public static class SourceBean {
            /**
             * museNickName : DD
             * roomStatus : 0
             * museId : 223
             * distance : 0
             * museImage : 020171225100355261.png
             * folId : 1
             */

            private String museNickName;
            private String roomStatus;
            private String museId;
            private String distance;
            private String museImage;
            private String folId;

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }

            public String getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(String roomStatus) {
                this.roomStatus = roomStatus;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getMuseImage() {
                return museImage;
            }

            public void setMuseImage(String museImage) {
                this.museImage = museImage;
            }

            public String getFolId() {
                return folId;
            }

            public void setFolId(String folId) {
                this.folId = folId;
            }
        }
    }
}
