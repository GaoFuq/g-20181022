package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2018/1/6.
 */

public class RespToConfirmOrder extends TempResponse {

    /**
     * result : {"modeNum":1,"price":22.077,"shipingAddress":{"msadId":"40","msadReceiverName":"H8~","msadMobileNo":"10086","msadIsDefault":"1","address":"重庆市潼南县驴踢了咯我就"},"mallGoods":{"mgooId":"1","mgooImage":"0201712191425141513.jpg","mgooName":"草莓","mgooPrice":"22.3","mgooStockNum":"0","discountPrice":"22.077"}}
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
         * modeNum : 1
         * price : 22.077
         * shipingAddress : {"msadId":"40","msadReceiverName":"H8~","msadMobileNo":"10086","msadIsDefault":"1","address":"重庆市潼南县驴踢了咯我就"}
         * mallGoods : {"mgooId":"1","mgooImage":"0201712191425141513.jpg","mgooName":"草莓","mgooPrice":"22.3","mgooStockNum":"0","discountPrice":"22.077"}
         */

        private String modeNum;
        private String price;
        private ShipingAddressBean shipingAddress;
        private MallGoodsBean mallGoods;

        public String getModeNum() {
            return modeNum;
        }

        public void setModeNum(String modeNum) {
            this.modeNum = modeNum;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public ShipingAddressBean getShipingAddress() {
            return shipingAddress;
        }

        public void setShipingAddress(ShipingAddressBean shipingAddress) {
            this.shipingAddress = shipingAddress;
        }

        public MallGoodsBean getMallGoods() {
            return mallGoods;
        }

        public void setMallGoods(MallGoodsBean mallGoods) {
            this.mallGoods = mallGoods;
        }

        public static class ShipingAddressBean {
            /**
             * msadId : 40
             * msadReceiverName : H8~
             * msadMobileNo : 10086
             * msadIsDefault : 1
             * address : 重庆市潼南县驴踢了咯我就
             */

            private String msadId;
            private String msadReceiverName;
            private String msadMobileNo;
            private String msadIsDefault;
            private String address;

            public String getMsadId() {
                return msadId;
            }

            public void setMsadId(String msadId) {
                this.msadId = msadId;
            }

            public String getMsadReceiverName() {
                return msadReceiverName;
            }

            public void setMsadReceiverName(String msadReceiverName) {
                this.msadReceiverName = msadReceiverName;
            }

            public String getMsadMobileNo() {
                return msadMobileNo;
            }

            public void setMsadMobileNo(String msadMobileNo) {
                this.msadMobileNo = msadMobileNo;
            }

            public String getMsadIsDefault() {
                return msadIsDefault;
            }

            public void setMsadIsDefault(String msadIsDefault) {
                this.msadIsDefault = msadIsDefault;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class MallGoodsBean {
            /**
             * mgooId : 1
             * mgooImage : 0201712191425141513.jpg
             * mgooName : 草莓
             * mgooPrice : 22.3
             * mgooStockNum : 0
             * discountPrice : 22.077
             */

            private String mgooId;
            private String mgooImage;
            private String mgooName;
            private String mgooPrice;
            private String mgooStockNum;
            private String discountPrice;

            public String getMgooId() {
                return mgooId;
            }

            public void setMgooId(String mgooId) {
                this.mgooId = mgooId;
            }

            public String getMgooImage() {
                return mgooImage;
            }

            public void setMgooImage(String mgooImage) {
                this.mgooImage = mgooImage;
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

            public String getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(String discountPrice) {
                this.discountPrice = discountPrice;
            }
        }
    }
}
