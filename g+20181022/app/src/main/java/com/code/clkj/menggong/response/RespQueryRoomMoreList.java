package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/13.
 */

public class RespQueryRoomMoreList extends TempResponse {


    /**
     * result : {"page":1,"pageLength":1,"pageSize":10,"size":2,"source":[{"roomId":25,"roomImage":"0201709071645496835.png","roomName":"ces","roomStatus":0,"roomWatchNum":1104},{"roomId":1,"roomImage":"","roomName":"哎呀我去","roomStatus":0,"roomWatchNum":36}]}
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
         * size : 2
         * source : [{"roomId":25,"roomImage":"0201709071645496835.png","roomName":"ces","roomStatus":0,"roomWatchNum":1104},{"roomId":1,"roomImage":"","roomName":"哎呀我去","roomStatus":0,"roomWatchNum":36}]
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
             * roomId : 25
             * roomImage : 0201709071645496835.png
             * roomName : ces
             * roomStatus : 0
             * roomWatchNum : 1104
             */

            private String roomId;
            private String roomImage;
            private String roomName;
            private String roomStatus;
            private String roomWatchNum;

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }

            public String getRoomImage() {
                return roomImage;
            }

            public void setRoomImage(String roomImage) {
                this.roomImage = roomImage;
            }

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(String roomStatus) {
                this.roomStatus = roomStatus;
            }

            public String getRoomWatchNum() {
                return roomWatchNum;
            }

            public void setRoomWatchNum(String roomWatchNum) {
                this.roomWatchNum = roomWatchNum;
            }
        }
    }
}
