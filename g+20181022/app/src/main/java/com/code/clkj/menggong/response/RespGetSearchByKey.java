package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/13.
 */

public class RespGetSearchByKey extends TempResponse {


    /**
     * result : {"sourse":[{"roomName":"ces","roomImage":"0201709071645496835.png","roomStatus":0,"roomId":25},{"roomName":"ces","roomImage":"0201709071645496835.png","roomStatus":0,"roomId":25}],"page":1,"pageSize":10,"pageLength":1,"size":1}
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
         * sourse : [{"roomName":"ces","roomImage":"0201709071645496835.png","roomStatus":0,"roomId":25},{"roomName":"ces","roomImage":"0201709071645496835.png","roomStatus":0,"roomId":25}]
         * page : 1
         * pageSize : 10
         * pageLength : 1
         * size : 1
         */

        private String page;
        private String pageSize;
        private String pageLength;
        private String size;
        private List<SourseBean> sourse;

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

        public List<SourseBean> getSourse() {
            return sourse;
        }

        public void setSourse(List<SourseBean> sourse) {
            this.sourse = sourse;
        }

        public static class SourseBean {
            /**
             * roomName : ces
             * roomImage : 0201709071645496835.png
             * roomStatus : 0
             * roomId : 25
             */

            private String roomName;
            private String roomImage;
            private String roomStatus;
            private String roomId;

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getRoomImage() {
                return roomImage;
            }

            public void setRoomImage(String roomImage) {
                this.roomImage = roomImage;
            }

            public String getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(String roomStatus) {
                this.roomStatus = roomStatus;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
