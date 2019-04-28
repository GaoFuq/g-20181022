package com.lf.tempcore.tempResponse;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ResponseLoginInfo extends TempResponse {
    /**
     * result : {"museAddress":"","museBirthday":"2017-11-07","museEpurse":0,"museId":174,"museImage":"0201711231619511292.png","museInviteCode":"","museNickName":"我要发","museOnlineTag":"268f8b8e-538d-4c1e-a385-4bd490c3da1b","museSex":0,"museSignature":"","museType":"","museUserName":"15788888888"}
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
         * museAddress :
         * museBirthday : 2017-11-07
         * museEpurse : 0
         * museId : 174
         * museImage : 0201711231619511292.png
         * museInviteCode :
         * museNickName : 我要发
         * museOnlineTag : 268f8b8e-538d-4c1e-a385-4bd490c3da1b
         * museSex : 0
         * museSignature :
         * museType :
         * museUserName : 15788888888
         */

        private String museAddress;
        private String museBirthday;
        private String museEpurse;
        private String museId;
        private String museImage;
        private String museInviteCode;
        private String museNickName;
        private String museOnlineTag;
        private String museSex;
        private String museSignature;
        private String museType;
        private String museUserName;

        public String getMuseAddress() {
            return museAddress;
        }

        public void setMuseAddress(String museAddress) {
            this.museAddress = museAddress;
        }

        public String getMuseBirthday() {
            return museBirthday;
        }

        public void setMuseBirthday(String museBirthday) {
            this.museBirthday = museBirthday;
        }

        public String getMuseEpurse() {
            return museEpurse;
        }

        public void setMuseEpurse(String museEpurse) {
            this.museEpurse = museEpurse;
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

        public String getMuseInviteCode() {
            return museInviteCode;
        }

        public void setMuseInviteCode(String museInviteCode) {
            this.museInviteCode = museInviteCode;
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

        public String getMuseSex() {
            return museSex;
        }

        public void setMuseSex(String museSex) {
            this.museSex = museSex;
        }

        public String getMuseSignature() {
            return museSignature;
        }

        public void setMuseSignature(String museSignature) {
            this.museSignature = museSignature;
        }

        public String getMuseType() {
            return museType;
        }

        public void setMuseType(String museType) {
            this.museType = museType;
        }

        public String getMuseUserName() {
            return museUserName;
        }

        public void setMuseUserName(String museUserName) {
            this.museUserName = museUserName;
        }
    }

    //    /**
//     * result : {"museId":"20","museUserName":"17726683870","musePwd":"e10adc3949ba59abbe56e057f20f883e","museNickName":null,"museImage":null,"museOnlineTag":"11583a01-d020-4ce8-8c0a-e9f4f5f049ae","museType":"1","museIsOnline":"1"}
//     */
//
//    private ResultEntity result;
//
//    public ResultEntity getResult() {
//        return result;
//    }
//
//    public void setResult(ResultEntity result) {
//        this.result = result;
//    }
//
//    public static class ResultEntity {
//        /**
//         * museId : 20
//         * museUserName : 17726683870
//         * musePwd : e10adc3949ba59abbe56e057f20f883e
//         * museNickName : null
//         * museImage : null
//         * museOnlineTag : 11583a01-d020-4ce8-8c0a-e9f4f5f049ae
//         * museType : 1
//         * museIsOnline : 1
//         */
//
//        private String museId;
//        private String museUserName;
//        private String musePwd;
//        private String museNickName;
//        private String museImage;
//        private String museOnlineTag;
//        private String museType;
//        private String museIsOnline;
//        private String musePayUserId;
//        private String logs;
//        private String  museIsOrg;
//        private String  museOrgId;
//
//        public String getMuseId() {
//            return museId;
//        }
//
//        public void setMuseId(String museId) {
//            this.museId = museId;
//        }
//
//        public String getMuseUserName() {
//            return museUserName;
//        }
//
//        public void setMuseUserName(String museUserName) {
//            this.museUserName = museUserName;
//        }
//
//        public String getMusePwd() {
//            return musePwd;
//        }
//
//        public void setMusePwd(String musePwd) {
//            this.musePwd = musePwd;
//        }
//
//        public String getMuseNickName() {
//            return museNickName;
//        }
//
//        public void setMuseNickName(String museNickName) {
//            this.museNickName = museNickName;
//        }
//
//        public String getMuseImage() {
//            return museImage;
//        }
//
//        public void setMuseImage(String museImage) {
//            this.museImage = museImage;
//        }
//
//        public String getMuseOnlineTag() {
//            return museOnlineTag;
//        }
//
//        public void setMuseOnlineTag(String museOnlineTag) {
//            this.museOnlineTag = museOnlineTag;
//        }
//
//        public String getMuseType() {
//            return museType;
//        }
//
//        public void setMuseType(String museType) {
//            this.museType = museType;
//        }
//
//        public String getMuseIsOnline() {
//            return museIsOnline;
//        }
//
//        public void setMuseIsOnline(String museIsOnline) {
//            this.museIsOnline = museIsOnline;
//        }
//
//        public String getMusePayUserId() {
//            return musePayUserId;
//        }
//
//        public void setMusePayUserId(String musePayUserId) {
//            this.musePayUserId = musePayUserId;
//        }
//
//        public String getLogs() {
//            return logs;
//        }
//
//        public void setLogs(String logs) {
//            this.logs = logs;
//        }
//
//        public String getMuseIsOrg() {
//            return museIsOrg;
//        }
//
//        public void setMuseIsOrg(String museIsOrg) {
//            this.museIsOrg = museIsOrg;
//        }
//
//        public String getMuseOrgId() {
//            return museOrgId;
//        }
//
//        public void setMuseOrgId(String museOrgId) {
//            this.museOrgId = museOrgId;
//        }
//    }


}
