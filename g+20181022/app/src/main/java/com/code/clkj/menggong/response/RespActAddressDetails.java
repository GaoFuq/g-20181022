package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by Administrator on 2017-12-13.
 */

public class RespActAddressDetails extends TempResponse {

    /**
     * result : {"address":"万州区","msadAddr":"20238,32218","msadAddrName":"大渡口","msadId":5,"msadIsDefault":1,"msadMobileNo":"12288888888","msadReceiverName":"麻辣香锅","msadUserId":181}
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
         * address : 万州区
         * msadAddr : 20238,32218
         * msadAddrName : 大渡口
         * msadId : 5
         * msadIsDefault : 1
         * msadMobileNo : 12288888888
         * msadReceiverName : 麻辣香锅
         * msadUserId : 181
         */

        private String address;
        private String msadAddr;
        private String msadAddrName;
        private String msadId;
        private String msadIsDefault;
        private String msadMobileNo;
        private String msadReceiverName;
        private String msadUserId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMsadAddr() {
            return msadAddr;
        }

        public void setMsadAddr(String msadAddr) {
            this.msadAddr = msadAddr;
        }

        public String getMsadAddrName() {
            return msadAddrName;
        }

        public void setMsadAddrName(String msadAddrName) {
            this.msadAddrName = msadAddrName;
        }

        public String getMsadId() {
            return msadId;
        }

        public void setMsadId(String msadId) {
            this.msadId = msadId;
        }

        public String getMsadIsDefault() {
            return msadIsDefault;
        }

        public void setMsadIsDefault(String msadIsDefault) {
            this.msadIsDefault = msadIsDefault;
        }

        public String getMsadMobileNo() {
            return msadMobileNo;
        }

        public void setMsadMobileNo(String msadMobileNo) {
            this.msadMobileNo = msadMobileNo;
        }

        public String getMsadReceiverName() {
            return msadReceiverName;
        }

        public void setMsadReceiverName(String msadReceiverName) {
            this.msadReceiverName = msadReceiverName;
        }

        public String getMsadUserId() {
            return msadUserId;
        }

        public void setMsadUserId(String msadUserId) {
            this.msadUserId = msadUserId;
        }
    }
}
