package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2017/12/23.
 */

public class RespSendGift extends TempResponse {


    /**
     * result : {"source":[{"time":"2017-11-27 17:11:20","name":"送给我要发的挖掘机","image":"0201706141101095444.png"}],"page":1,"size":1}
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
         * source : [{"time":"2017-11-27 17:11:20","name":"送给我要发的挖掘机","image":"0201706141101095444.png"}]
         * page : 1
         * size : 1
         */

        private String page;
        private String size;
        private List<SourceBean> source;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
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
             * time : 2017-11-27 17:11:20
             * name : 送给我要发的挖掘机
             * image : 0201706141101095444.png
             */

            private String time;
            private String name;
            private String image;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
