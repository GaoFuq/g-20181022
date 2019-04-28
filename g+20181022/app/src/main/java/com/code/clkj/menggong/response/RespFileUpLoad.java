package com.code.clkj.menggong.response;


import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by longf on 2016/2/24.
 */
public class RespFileUpLoad extends TempResponse {

    /**
     * simaType : 1
     * simaId : 20156559
     * simaImagUri : http://192.168.0.7:8022/headersimg/member/201603/2015137936052422727981490.jpg
     * simaCreateDate : Mon Mar 14 19:03:49 CST 2016
     */

    private List<ResultEntity> result;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        private String simaType;
        private String simaId;
        private String simaImagUri;
        private String simaCreateDate;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "simaType='" + simaType + '\'' +
                    ", simaId='" + simaId + '\'' +
                    ", simaImagUri='" + simaImagUri + '\'' +
                    ", simaCreateDate='" + simaCreateDate + '\'' +
                    '}';
        }

        public void setSimaType(String simaType) {
            this.simaType = simaType;
        }

        public void setSimaId(String simaId) {
            this.simaId = simaId;
        }

        public void setSimaImagUri(String simaImagUri) {
            this.simaImagUri = simaImagUri;
        }

        public void setSimaCreateDate(String simaCreateDate) {
            this.simaCreateDate = simaCreateDate;
        }

        public String getSimaType() {
            return simaType;
        }

        public String getSimaId() {
            return simaId;
        }

        public String getSimaImagUri() {
            return simaImagUri;
        }

        public String getSimaCreateDate() {
            return simaCreateDate;
        }
    }

    @Override
    public String toString() {
        return "RespFileUpLoad{" +
                "result=" + result +
                "} " + super.toString();
    }
}
