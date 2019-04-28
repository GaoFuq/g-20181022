package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2018/1/5.
 */

public class RespQueryMallGoodsDetail extends TempResponse{


    /**
     * result : {"goods":{"discountPrice":32,"imageOne":"","mgooId":13,"mgooMuseId":209,"mgooName":"de","mgooPrice":32,"mgooStockNum":0},"comment":{"comment":{"museNickName":"歪歪","CreatTime":"2018-01-04 18:31:58","museId":209,"museImage":"0201801051750283681.png","content":"fdsafdsaf","imgList":[{"img":"a.jpg"}]},"commentTotal":4},"goodsImage":[{"img":"0201712251009166373.jpg"},{"img":"0201801041128039146.png"}]}
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
         * goods : {"discountPrice":32,"imageOne":"","mgooId":13,"mgooMuseId":209,"mgooName":"de","mgooPrice":32,"mgooStockNum":0}
         * comment : {"comment":{"museNickName":"歪歪","CreatTime":"2018-01-04 18:31:58","museId":209,"museImage":"0201801051750283681.png","content":"fdsafdsaf","imgList":[{"img":"a.jpg"}]},"commentTotal":4}
         * goodsImage : [{"img":"0201712251009166373.jpg"},{"img":"0201801041128039146.png"}]
         */

        private GoodsBean goods;
        private CommentBeanX comment;
        private List<GoodsImageBean> goodsImage;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public CommentBeanX getComment() {
            return comment;
        }

        public void setComment(CommentBeanX comment) {
            this.comment = comment;
        }

        public List<GoodsImageBean> getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(List<GoodsImageBean> goodsImage) {
            this.goodsImage = goodsImage;
        }

        public static class GoodsBean {
            /**
             * discountPrice : 32
             * imageOne : 
             * mgooId : 13
             * mgooMuseId : 209
             * mgooName : de
             * mgooPrice : 32
             * mgooStockNum : 0
             */

            private String discountPrice;
            private String imageOne;
            private String mgooId;
            private String mgooMuseId;
            private String mgooName;
            private String mgooPrice;
            private String mgooStockNum;

            public String getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(String discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getImageOne() {
                return imageOne;
            }

            public void setImageOne(String imageOne) {
                this.imageOne = imageOne;
            }

            public String getMgooId() {
                return mgooId;
            }

            public void setMgooId(String mgooId) {
                this.mgooId = mgooId;
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

            public String getMgooPrice() {
                return mgooPrice;
            }

            public void setMgooPrice(String mgooPrice) {
                this.mgooPrice = mgooPrice;
            }

            public String getMgooStockNum() {
                return mgooStockNum;
            }

            public void setMgooStockNum(String mgooStockNum) {
                this.mgooStockNum = mgooStockNum;
            }
        }

        public static class CommentBeanX {
            /**
             * comment : {"museNickName":"歪歪","CreatTime":"2018-01-04 18:31:58","museId":209,"museImage":"0201801051750283681.png","content":"fdsafdsaf","imgList":[{"img":"a.jpg"}]}
             * commentTotal : 4
             */

            private CommentBean comment;
            private String commentTotal;

            public CommentBean getComment() {
                return comment;
            }

            public void setComment(CommentBean comment) {
                this.comment = comment;
            }

            public String getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(String commentTotal) {
                this.commentTotal = commentTotal;
            }

            public static class CommentBean {
                /**
                 * museNickName : 歪歪
                 * CreatTime : 2018-01-04 18:31:58
                 * museId : 209
                 * museImage : 0201801051750283681.png
                 * content : fdsafdsaf
                 * imgList : [{"img":"a.jpg"}]
                 */

                private String museNickName;
                private String CreatTime;
                private String museId;
                private String museImage;
                private String content;
                private List<ImgListBean> imgList;

                public String getMuseNickName() {
                    return museNickName;
                }

                public void setMuseNickName(String museNickName) {
                    this.museNickName = museNickName;
                }

                public String getCreatTime() {
                    return CreatTime;
                }

                public void setCreatTime(String CreatTime) {
                    this.CreatTime = CreatTime;
                }

                public String getMuseId() {
                    return museId;
                }

                public void setMuseId(String museId) {
                    this.museId = museId;
                }

                public String getMuseImage() {
                    return museImage;
                }

                public void setMuseImage(String museImage) {
                    this.museImage = museImage;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public List<ImgListBean> getImgList() {
                    return imgList;
                }

                public void setImgList(List<ImgListBean> imgList) {
                    this.imgList = imgList;
                }

                public static class ImgListBean {
                    /**
                     * img : a.jpg
                     */

                    private String img;

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }
            }
        }

        public static class GoodsImageBean {
            /**
             * img : 0201712251009166373.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
