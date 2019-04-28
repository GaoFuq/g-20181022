package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/6.
 */

public class RespQueryMgooCommentPage extends TempResponse {


    /**
     * result : {"source":[{"content":"dsafdsafdsaf","mgcoContentTime":"2017-12-28 15:28:26","museImage":"020171221094337471.jpg","mgcoImage":"a.jpg,b.jpg","museId":209,"museNickName":"歪歪"}],"page":1,"pageLenght":1,"pageSize":10,"size":1}
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
         * source : [{"content":"dsafdsafdsaf","mgcoContentTime":"2017-12-28 15:28:26","museImage":"020171221094337471.jpg","mgcoImage":"a.jpg,b.jpg","museId":209,"museNickName":"歪歪"}]
         * page : 1
         * pageLenght : 1
         * pageSize : 10
         * size : 1
         */

        private String page;
        private String pageLenght;
        private String pageSize;
        private String size;
        private List<SourceBean> source;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageLenght() {
            return pageLenght;
        }

        public void setPageLenght(String pageLenght) {
            this.pageLenght = pageLenght;
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
             * content : dsafdsafdsaf
             * mgcoContentTime : 2017-12-28 15:28:26
             * museImage : 020171221094337471.jpg
             * mgcoImage : a.jpg,b.jpg
             * museId : 209
             * museNickName : 歪歪
             */

            private String content;
            private String mgcoContentTime;
            private String museImage;
            private String mgcoImage;
            private String museId;
            private String museNickName;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getMgcoContentTime() {
                return mgcoContentTime;
            }

            public void setMgcoContentTime(String mgcoContentTime) {
                this.mgcoContentTime = mgcoContentTime;
            }

            public String getMuseImage() {
                return museImage;
            }

            public void setMuseImage(String museImage) {
                this.museImage = museImage;
            }

            public String getMgcoImage() {
                return mgcoImage;
            }

            public void setMgcoImage(String mgcoImage) {
                this.mgcoImage = mgcoImage;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }
        }
    }
}
