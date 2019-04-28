package com.code.clkj.menggong.response;

import com.code.clkj.menggong.bean.Entity;
import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by KathLine on 2017/4/5.
 */

public class RoomComment extends TempResponse {


    private List<ResultEntity> result;

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public static class ResultEntity extends Entity {
        /**
         * rcomContent : fhaskfjhnsadkldf a
         * rcomCt : 2017-05-16 13:38:55
         * rcomId : 185
         * rcomMuseId : 22
         * rcomRoomId : 6
         */
        private String museNickName;
        private String rcomContent;
        private String rcomCt;
        private String rcomId;
        private String rcomMuseId;
        private String rcomRoomId;

        public String getMuseNickName() {
            return museNickName;
        }

        public void setMuseNickName(String museNickName) {
            this.museNickName = museNickName;
        }

        public String getRcomContent() {
            return rcomContent;
        }

        public void setRcomContent(String rcomContent) {
            this.rcomContent = rcomContent;
        }

        public String getRcomCt() {
            return rcomCt;
        }

        public void setRcomCt(String rcomCt) {
            this.rcomCt = rcomCt;
        }

        public String getRcomId() {
            return rcomId;
        }

        public void setRcomId(String rcomId) {
            this.rcomId = rcomId;
        }

        public String getRcomMuseId() {
            return rcomMuseId;
        }

        public void setRcomMuseId(String rcomMuseId) {
            this.rcomMuseId = rcomMuseId;
        }

        public String getRcomRoomId() {
            return rcomRoomId;
        }

        public void setRcomRoomId(String rcomRoomId) {
            this.rcomRoomId = rcomRoomId;
        }
    }
}
