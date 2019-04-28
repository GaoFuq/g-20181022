package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2017/12/21.
 */

public class RespShowHistory extends TempResponse {

    /**
     * result : {"source":[{"roomName":"哎呀我去","watchNum":0,"image":null,"muhyId":27,"roomId":1}],"page":1,"pageSize":10,"size":1}
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
         * source : [{"roomName":"哎呀我去","watchNum":0,"image":null,"muhyId":27,"roomId":1}]
         * page : 1
         * pageSize : 10
         * size : 1
         */

        private String page;
        private String pageSize;
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
             * roomName : 哎呀我去
             * watchNum : 0
             * image : null
             * muhyId : 27
             * roomId : 1
             */

            private String roomName;
            private String watchNum;
            private String image;
            private String muhyId;
            private String roomId;

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getWatchNum() {
                return watchNum;
            }

            public void setWatchNum(String watchNum) {
                this.watchNum = watchNum;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMuhyId() {
                return muhyId;
            }

            public void setMuhyId(String muhyId) {
                this.muhyId = muhyId;
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
