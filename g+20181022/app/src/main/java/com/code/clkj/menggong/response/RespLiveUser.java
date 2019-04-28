package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by clkj on 2017/12/21.
 */

public class RespLiveUser extends TempResponse {

    /**
     * result : {"address":"大足县","museId":206,"usceAddress":"20238,32236","usceAge":12,"usceId":11,"usceIdentityBack":"0201712211524244366.jpg","usceIdentityFace":"0201712211524035410.jpg","uscePhone":"123","usceRealName":"许海","usceSex":1}
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
         * address : 大足县
         * museId : 206
         * usceAddress : 20238,32236
         * usceAge : 12
         * usceId : 11
         * usceIdentityBack : 0201712211524244366.jpg
         * usceIdentityFace : 0201712211524035410.jpg
         * uscePhone : 123
         * usceRealName : 许海
         * usceSex : 1
         */

        private String address;
        private String museId;
        private String usceAddress;
        private String usceAge;
        private String usceId;
        private String usceIdentityBack;
        private String usceIdentityFace;
        private String uscePhone;
        private String usceRealName;
        private String usceSex;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMuseId() {
            return museId;
        }

        public void setMuseId(String museId) {
            this.museId = museId;
        }

        public String getUsceAddress() {
            return usceAddress;
        }

        public void setUsceAddress(String usceAddress) {
            this.usceAddress = usceAddress;
        }

        public String getUsceAge() {
            return usceAge;
        }

        public void setUsceAge(String usceAge) {
            this.usceAge = usceAge;
        }

        public String getUsceId() {
            return usceId;
        }

        public void setUsceId(String usceId) {
            this.usceId = usceId;
        }

        public String getUsceIdentityBack() {
            return usceIdentityBack;
        }

        public void setUsceIdentityBack(String usceIdentityBack) {
            this.usceIdentityBack = usceIdentityBack;
        }

        public String getUsceIdentityFace() {
            return usceIdentityFace;
        }

        public void setUsceIdentityFace(String usceIdentityFace) {
            this.usceIdentityFace = usceIdentityFace;
        }

        public String getUscePhone() {
            return uscePhone;
        }

        public void setUscePhone(String uscePhone) {
            this.uscePhone = uscePhone;
        }

        public String getUsceRealName() {
            return usceRealName;
        }

        public void setUsceRealName(String usceRealName) {
            this.usceRealName = usceRealName;
        }

        public String getUsceSex() {
            return usceSex;
        }

        public void setUsceSex(String usceSex) {
            this.usceSex = usceSex;
        }
    }
}
