package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2018/1/3.
 */

public class RespCahtRoom extends TempResponse {
    /**
     * result : {"beginPageNoOfIndexPageBar":1,"dispPageNum":5,"endPageNoOfIndexPageBar":1,"page":1,"pageLength":1,"pageSize":10,"searchCondObj":null,"size":2,"sortMode":"","sortName":"","source":[{"total":"0","id":14,"createTime":"2018-10-23 16:33:48","roomName":"重庆2","juli":8178,"comment":"简介"},{"total":"0","id":13,"createTime":"2018-10-23 11:27:58","roomName":"重庆","juli":8670,"comment":null}]}
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
         * beginPageNoOfIndexPageBar : 1
         * dispPageNum : 5
         * endPageNoOfIndexPageBar : 1
         * page : 1
         * pageLength : 1
         * pageSize : 10
         * searchCondObj : null
         * size : 2
         * sortMode :
         * sortName :
         * source : [{"total":"0","id":14,"createTime":"2018-10-23 16:33:48","roomName":"重庆2","juli":8178,"comment":"简介"},{"total":"0","id":13,"createTime":"2018-10-23 11:27:58","roomName":"重庆","juli":8670,"comment":null}]
         */

        private int beginPageNoOfIndexPageBar;
        private int dispPageNum;
        private int endPageNoOfIndexPageBar;
        private int page;
        private int pageLength;
        private int pageSize;
        private Object searchCondObj;
        private int size;
        private String sortMode;
        private String sortName;
        private List<SourceEntity> source;

        public int getBeginPageNoOfIndexPageBar() {
            return beginPageNoOfIndexPageBar;
        }

        public void setBeginPageNoOfIndexPageBar(int beginPageNoOfIndexPageBar) {
            this.beginPageNoOfIndexPageBar = beginPageNoOfIndexPageBar;
        }

        public int getDispPageNum() {
            return dispPageNum;
        }

        public void setDispPageNum(int dispPageNum) {
            this.dispPageNum = dispPageNum;
        }

        public int getEndPageNoOfIndexPageBar() {
            return endPageNoOfIndexPageBar;
        }

        public void setEndPageNoOfIndexPageBar(int endPageNoOfIndexPageBar) {
            this.endPageNoOfIndexPageBar = endPageNoOfIndexPageBar;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageLength() {
            return pageLength;
        }

        public void setPageLength(int pageLength) {
            this.pageLength = pageLength;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public Object getSearchCondObj() {
            return searchCondObj;
        }

        public void setSearchCondObj(Object searchCondObj) {
            this.searchCondObj = searchCondObj;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getSortMode() {
            return sortMode;
        }

        public void setSortMode(String sortMode) {
            this.sortMode = sortMode;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public List<SourceEntity> getSource() {
            return source;
        }

        public void setSource(List<SourceEntity> source) {
            this.source = source;
        }

        public static class SourceEntity {
            /**
             * total : 0
             * id : 14
             * createTime : 2018-10-23 16:33:48
             * roomName : 重庆2
             * juli : 8178
             * comment : 简介
             */

            private String total;
            private String id;
            private String createTime;
            private String roomName;
            private String juli;
            private String comment;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getJuli() {
                return juli;
            }

            public void setJuli(String juli) {
                this.juli = juli;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }




    /*
    *//**
     * result : {"beginPageNoOfIndexPageBar":1,"dispPageNum":5,"endPageNoOfIndexPageBar":1,"page":1,"pageLength":1,"pageSize":10,"searchCondObj":null,"size":2,"sortMode":"","sortName":"","source":[{"id":14,"createTime":"2018-10-23 16:33:48","lon":"29.5473265500","address":"重庆市","img":"0201810231633383448.jpg","userName":"重庆2","juli":7286074,"lat":"106.5460968018"},{"id":13,"createTime":"2018-10-23 11:27:58","lon":"29.5474012183","address":"重庆市","img":"0201810231127575784.jpg","userName":"重庆","juli":7286262,"lat":"106.5507316589"}]}
     *//*

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        *//**
         * beginPageNoOfIndexPageBar : 1
         * dispPageNum : 5
         * endPageNoOfIndexPageBar : 1
         * page : 1
         * pageLength : 1
         * pageSize : 10
         * searchCondObj : null
         * size : 2
         * sortMode :
         * sortName :
         * source : [{"id":14,"createTime":"2018-10-23 16:33:48","lon":"29.5473265500","address":"重庆市","img":"0201810231633383448.jpg","userName":"重庆2","juli":7286074,"lat":"106.5460968018"},{"id":13,"createTime":"2018-10-23 11:27:58","lon":"29.5474012183","address":"重庆市","img":"0201810231127575784.jpg","userName":"重庆","juli":7286262,"lat":"106.5507316589"}]
         *//*

        private int beginPageNoOfIndexPageBar;
        private int dispPageNum;
        private int endPageNoOfIndexPageBar;
        private int page;
        private int pageLength;
        private int pageSize;
        private String searchCondObj;
        private int size;
        private String sortMode;
        private String sortName;
        private List<SourceEntity> source;

        public int getBeginPageNoOfIndexPageBar() {
            return beginPageNoOfIndexPageBar;
        }

        public void setBeginPageNoOfIndexPageBar(int beginPageNoOfIndexPageBar) {
            this.beginPageNoOfIndexPageBar = beginPageNoOfIndexPageBar;
        }

        public int getDispPageNum() {
            return dispPageNum;
        }

        public void setDispPageNum(int dispPageNum) {
            this.dispPageNum = dispPageNum;
        }

        public int getEndPageNoOfIndexPageBar() {
            return endPageNoOfIndexPageBar;
        }

        public void setEndPageNoOfIndexPageBar(int endPageNoOfIndexPageBar) {
            this.endPageNoOfIndexPageBar = endPageNoOfIndexPageBar;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageLength() {
            return pageLength;
        }

        public void setPageLength(int pageLength) {
            this.pageLength = pageLength;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getSearchCondObj() {
            return searchCondObj;
        }

        public void setSearchCondObj(String searchCondObj) {
            this.searchCondObj = searchCondObj;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getSortMode() {
            return sortMode;
        }

        public void setSortMode(String sortMode) {
            this.sortMode = sortMode;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public List<SourceEntity> getSource() {
            return source;
        }

        public void setSource(List<SourceEntity> source) {
            this.source = source;
        }

        public static class SourceEntity {
            *//**
             * id : 14
             * createTime : 2018-10-23 16:33:48
             * lon : 29.5473265500
             * address : 重庆市
             * img : 0201810231633383448.jpg
             * userName : 重庆2
             * juli : 7286074
             * lat : 106.5460968018
             *//*

            private String id;
            private String createTime;
            private String lon;
            private String address;
            private String img;
            private String userName;
            private String juli;
            private String lat;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getJuli() {
                return juli;
            }

            public void setJuli(String juli) {
                this.juli = juli;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
        }
    }*/
}
