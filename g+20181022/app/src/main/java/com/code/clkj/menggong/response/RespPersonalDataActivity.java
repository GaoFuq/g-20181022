package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**个人资料查询
 * Created by Administrator on 2017-12-12.
 */

public class RespPersonalDataActivity extends TempResponse {

    /**
     * result : {"address":" 万州区","museAddress":"20238,32218","museBirthday":"2017-12-21","museCommentToken":"","museEpurse":"20","museId":201,"museImage":"0201712211319527796.png","museInviteCode":"","museIsOnline":1,"museNickName":"丑","museOnlineTag":"d87a0f74-4cd6-481a-9852-d847964e277a","museSex":0,"museSignature":"这样不好","museType":1,"museUserName":"15213162622"}
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
         * address :  万州区
         * museAddress : 20238,32218
         * museBirthday : 2017-12-21
         * museCommentToken : 
         * museEpurse : 20
         * museId : 201
         * museImage : 0201712211319527796.png
         * museInviteCode : 
         * museIsOnline : 1
         * museNickName : 丑
         * museOnlineTag : d87a0f74-4cd6-481a-9852-d847964e277a
         * museSex : 0
         * museSignature : 这样不好
         * museType : 1
         * museUserName : 15213162622
         */

        private String address;
        private String museAddress;
        private String museBirthday;
        private String museCommentToken;
        private String museEpurse;
        private String museId;
        private String museImage;
        private String museInviteCode;
        private String museIsOnline;
        private String museNickName;
        private String museOnlineTag;
        private String museSex;
        private String museSignature;
        private String museType;
        private String museUserName;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getMuseCommentToken() {
            return museCommentToken;
        }

        public void setMuseCommentToken(String museCommentToken) {
            this.museCommentToken = museCommentToken;
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

        public String getMuseIsOnline() {
            return museIsOnline;
        }

        public void setMuseIsOnline(String museIsOnline) {
            this.museIsOnline = museIsOnline;
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
}
