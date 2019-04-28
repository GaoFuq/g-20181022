package com.lf.tempcore.tempResponse;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ResponseLoginInfoDisanfang extends TempResponse {


    /**
     * result : {"address":"","isCertification":0,"museId":95,"museImage":"","museIntroduction":"","museNickName":"","museOnlineTag":"9fb6ffad-a748-4b8f-a605-dc3c0027ab8d","musePassword":"123456","musePayPwd":"","musePhone":"","museQqAcount":"","museQqAppid":"","museRecoId":0,"museStatus":1,"museUserName":"","museWbAcount":"","museWecharOpenid":"oq2phxC51Vf_3Np3Svr8QIX1jLtI","museWechatAcount":""}
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
         * address :
         * isCertification : 0
         * museId : 95
         * museImage :
         * museIntroduction :
         * museNickName :
         * museOnlineTag : 9fb6ffad-a748-4b8f-a605-dc3c0027ab8d
         * musePassword : 123456
         * musePayPwd :
         * musePhone :
         * museQqAcount :
         * museQqAppid :
         * museRecoId : 0
         * museStatus : 1
         * museUserName :
         * museWbAcount :
         * museWecharOpenid : oq2phxC51Vf_3Np3Svr8QIX1jLtI
         * museWechatAcount :
         */


   /*     private String museId;
        private String museUserName;
        private String musePwd;
        private String museNickName;
        private String museImage;
        private String museOnlineTag;
        private String museType;
        private String museIsOnline;
        private String musePayUserId;*/

        private String address;
        private String isCertification;
        private String museId;
        private String museImage;
        private String museIntroduction;
        private String museNickName;
        private String museOnlineTag;
        private String musePassword;
        private String musePayPwd;
        private String musePhone;
        private String museQqAcount;
        private String museQqAppid;
        private String museRecoId;
        private String museStatus;
        private String museUserName;
        private String museWbAcount;
        private String museWecharOpenid;
        private String museWechatAcount;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsCertification() {
            return isCertification;
        }

        public void setIsCertification(String isCertification) {
            this.isCertification = isCertification;
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

        public String getMuseIntroduction() {
            return museIntroduction;
        }

        public void setMuseIntroduction(String museIntroduction) {
            this.museIntroduction = museIntroduction;
        }

        public String getMuseNickName() {
            return museNickName;
        }

        public void setMuseNickName(String museNickName) {
            this.museNickName = museNickName;
        }

        public String getMuseOnlineTag() {
            return museOnlineTag;
        }

        public void setMuseOnlineTag(String museOnlineTag) {
            this.museOnlineTag = museOnlineTag;
        }

        public String getMusePassword() {
            return musePassword;
        }

        public void setMusePassword(String musePassword) {
            this.musePassword = musePassword;
        }

        public String getMusePayPwd() {
            return musePayPwd;
        }

        public void setMusePayPwd(String musePayPwd) {
            this.musePayPwd = musePayPwd;
        }

        public String getMusePhone() {
            return musePhone;
        }

        public void setMusePhone(String musePhone) {
            this.musePhone = musePhone;
        }

        public String getMuseQqAcount() {
            return museQqAcount;
        }

        public void setMuseQqAcount(String museQqAcount) {
            this.museQqAcount = museQqAcount;
        }

        public String getMuseQqAppid() {
            return museQqAppid;
        }

        public void setMuseQqAppid(String museQqAppid) {
            this.museQqAppid = museQqAppid;
        }

        public String getMuseRecoId() {
            return museRecoId;
        }

        public void setMuseRecoId(String museRecoId) {
            this.museRecoId = museRecoId;
        }

        public String getMuseStatus() {
            return museStatus;
        }

        public void setMuseStatus(String museStatus) {
            this.museStatus = museStatus;
        }

        public String getMuseUserName() {
            return museUserName;
        }

        public void setMuseUserName(String museUserName) {
            this.museUserName = museUserName;
        }

        public String getMuseWbAcount() {
            return museWbAcount;
        }

        public void setMuseWbAcount(String museWbAcount) {
            this.museWbAcount = museWbAcount;
        }

        public String getMuseWecharOpenid() {
            return museWecharOpenid;
        }

        public void setMuseWecharOpenid(String museWecharOpenid) {
            this.museWecharOpenid = museWecharOpenid;
        }

        public String getMuseWechatAcount() {
            return museWechatAcount;
        }

        public void setMuseWechatAcount(String museWechatAcount) {
            this.museWechatAcount = museWechatAcount;
        }
    }
}
