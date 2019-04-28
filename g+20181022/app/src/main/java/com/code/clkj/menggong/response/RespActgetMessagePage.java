package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-12-15.
 */

public class RespActgetMessagePage extends TempResponse {

    /**
     * result : {"source":[{"messIsFriend":0,"messId":6,"messType":0,"messCreateTime":"2018-09-05 10:01:13","messIsRead":0,"messContent":"123123","messTitle":"平台消息"},{"messIsFriend":0,"messId":5,"messType":0,"messCreateTime":"2018-08-31 16:25:48","messIsRead":0,"messContent":"12","messTitle":"平台消息"},{"messIsFriend":0,"messId":4,"messType":0,"messCreateTime":"2018-08-31 16:21:02","messIsRead":0,"messContent":"千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人","messTitle":"平台消息"},{"messIsFriend":0,"messId":395,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.92,于2018-01-25 16:24:02.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":396,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.96,于2018-01-25 16:24:17.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":397,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：5.0,于2018-01-25 16:26:21.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":398,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：555.0,于2018-01-25 16:27:24.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":399,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：233.0,于2018-01-25 16:29:33.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":400,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.91,于2018-01-26 09:58:34.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":401,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：3.0,于2018-01-26 10:01:46.0发送的红包到期返还","messTitle":"红包过期"}],"page":1,"pageSize":10,"size":22}
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        /**
         * source : [{"messIsFriend":0,"messId":6,"messType":0,"messCreateTime":"2018-09-05 10:01:13","messIsRead":0,"messContent":"123123","messTitle":"平台消息"},{"messIsFriend":0,"messId":5,"messType":0,"messCreateTime":"2018-08-31 16:25:48","messIsRead":0,"messContent":"12","messTitle":"平台消息"},{"messIsFriend":0,"messId":4,"messType":0,"messCreateTime":"2018-08-31 16:21:02","messIsRead":0,"messContent":"千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人千万人人人人人人人人人人人人人人人人人人人","messTitle":"平台消息"},{"messIsFriend":0,"messId":395,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.92,于2018-01-25 16:24:02.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":396,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.96,于2018-01-25 16:24:17.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":397,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：5.0,于2018-01-25 16:26:21.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":398,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：555.0,于2018-01-25 16:27:24.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":399,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：233.0,于2018-01-25 16:29:33.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":400,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：1.91,于2018-01-26 09:58:34.0发送的红包到期返还","messTitle":"红包过期"},{"messIsFriend":0,"messId":401,"messType":1,"messCreateTime":"2018-02-03 14:35:07","messIsRead":0,"messContent":"红包过期，返还红包余额：3.0,于2018-01-26 10:01:46.0发送的红包到期返还","messTitle":"红包过期"}]
         * page : 1
         * pageSize : 10
         * size : 22
         */

        private int page;
        private int pageSize;
        private int size;
        private List<SourceEntity> source;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<SourceEntity> getSource() {
            return source;
        }

        public void setSource(List<SourceEntity> source) {
            this.source = source;
        }

        public static class SourceEntity {
            /**
             * messIsFriend : 0
             * messId : 6
             * messType : 0
             * messCreateTime : 2018-09-05 10:01:13
             * messIsRead : 0
             * messContent : 123123
             * messTitle : 平台消息
             */

            private String messIsFriend;
            private String messId;
            private String messType;
            private String messCreateTime;
            private String messIsRead;
            private String messContent;
            private String messTitle;

            public String getMessIsFriend() {
                return messIsFriend;
            }

            public void setMessIsFriend(String messIsFriend) {
                this.messIsFriend = messIsFriend;
            }

            public String getMessId() {
                return messId;
            }

            public void setMessId(String messId) {
                this.messId = messId;
            }

            public String getMessType() {
                return messType;
            }

            public void setMessType(String messType) {
                this.messType = messType;
            }

            public String getMessCreateTime() {
                return messCreateTime;
            }

            public void setMessCreateTime(String messCreateTime) {
                this.messCreateTime = messCreateTime;
            }

            public String getMessIsRead() {
                return messIsRead;
            }

            public void setMessIsRead(String messIsRead) {
                this.messIsRead = messIsRead;
            }

            public String getMessContent() {
                return messContent;
            }

            public void setMessContent(String messContent) {
                this.messContent = messContent;
            }

            public String getMessTitle() {
                return messTitle;
            }

            public void setMessTitle(String messTitle) {
                this.messTitle = messTitle;
            }
        }
    }
}
