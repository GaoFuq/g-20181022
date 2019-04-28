package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2018/1/2.
 */

public class RespCommentList extends TempResponse {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bycmContent : fdsafd
         * bycmId : 2
         * bysmType : 2
         * memberUser : {"museNickName":"G+_7777","museId":"175"}
         * memberUserReply : {"museNickName":"我要发1","museId":"174"}
         */

        private String bycmContent;
        private String bycmId;
        private String bysmType;
        private MemberUserBean memberUser;
        private MemberUserReplyBean memberUserReply;

        public String getBycmContent() {
            return bycmContent;
        }

        public void setBycmContent(String bycmContent) {
            this.bycmContent = bycmContent;
        }

        public String getBycmId() {
            return bycmId;
        }

        public void setBycmId(String bycmId) {
            this.bycmId = bycmId;
        }

        public String getBysmType() {
            return bysmType;
        }

        public void setBysmType(String bysmType) {
            this.bysmType = bysmType;
        }

        public MemberUserBean getMemberUser() {
            return memberUser;
        }

        public void setMemberUser(MemberUserBean memberUser) {
            this.memberUser = memberUser;
        }

        public MemberUserReplyBean getMemberUserReply() {
            return memberUserReply;
        }

        public void setMemberUserReply(MemberUserReplyBean memberUserReply) {
            this.memberUserReply = memberUserReply;
        }

        public static class MemberUserBean {
            /**
             * museNickName : G+_7777
             * museId : 175
             */

            private String museNickName;
            private String museId;

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }
        }

        public static class MemberUserReplyBean {
            /**
             * museNickName : 我要发1
             * museId : 174
             */

            private String museNickName;
            private String museId;

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }
        }
    }
}
