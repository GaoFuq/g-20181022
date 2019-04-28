package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2018/1/11.
 */

public class RespGetAlipayInfo extends TempResponse {


    /**
     * result : {"optyPublicKey":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcXId/JXiRsTW0XPKrDT/5enqCeo6IWryEYYCzQsn596jNBYeu34oiOZxeTqnSZue+2kjqoki9Hj8zFSrK3JKRp9ftQyBhnge29Jx6qzLjjOg/D7V1pucrvOl5xfGwpj4MgIhsLeqwCgiVjya9ylxdO039TyPQKcMrr1x3ezbXPwIDAQAB","optyNotifyUrl":"http://localhost:8080/jiaoyi/mallOrder/alipaynotify.do","optyMchId":"2017060507425498","optyAppKey":"r060u1skulsot2ite18k4kp8tfdm5478","optyPrivateKey":"MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCkF+hnB2dAgIqrVJpUpOgCphW9jPYOVBJsdWSMhZXR13iTspbI/3TIGf4hqHVQg1tn7jROZZcjOlrF/hBHbk6v465Xc+FJ0qKIeclX4pJxi5GNskKdZyEm5G03vcPdDFbP8/cP+o/AL64ENAVrCseC2NRb8UqbTv4xEBv4ONjaJRptDiV1d1HvotMC4PqSTET6CX6DYu3stNSF7zkymvPBkh7RhWmk7HCYkyNprHirrOFqWHmQVQz57QhPMUS9CsUrKPsZIv5080LTFbjHkve8giLU29tCjaFvMaYcCekJD53Xxx6gQ9YEVlF7U+7SYyvGvJVPqDHr2VWhgCKQ3APAgMBAAECggEAUH9e1q7/+wt/9nwB8Xm+FtAxeHw3HrrHa4g/GvHSoGEZ2yuky2h3WDIEqE+pe2hMCyiMtEKDF/CxARw4LT18NGJiRps2FOxSorOgZYRwKGgNo7CnZtF4fLHFtfQ58sc6nv3K1Ap8C/gTnjyPbq1FsDJpO45jy3KppQiqic/noiO4HIrqy6BJjc5poHTQjmHB+ADJ9EP+jJcKPDa6apxVCuLx5iT1fwhl8GY3LmZxguO6J7Mk9lOIH5YIXymcQIr3ryFE5rPdmBmgFHsvWzDCDfxj8ioarLTs0UrFam6Q4zqb1zBzE0niVUl+2Vu97YSLB091Az1wqJKsO0qU7tjlcQKBgQDGYCEuBJxysf7nkvsh+V5Zqd/lcB9F6GaYOv0F55gH6X2TLrOhgFYttGe95VmL380D39e5gOE97R0DeHcjBmpvebEOR61+Dm+np9cF+9zZLHBPzdViUtU0B3bbKn40f9L4pjLpSONFcLNEcgL+BVeD9+HxqjAJoQerZjEqmN/HNQKBgQCofYyOxu5x6I5qMHaylj0qTJq/CxCu2hIwzknZLj42/oJn0cFtxpWtW7d6Q+iJyjuOwaZXwjcn1P0yz+n9rL9OVuSzJ763+30iBGo0SfpKUlC/Rm6F/rM1Az/Ou9yQlcr8vhM12ALLcTh4yXUqANfQQME7CnT9ER45WYym2NVOswKBgFJcDMp3PaDcx3DLdBo51vMw9PTj5Qf4IcgoDW6LJXJ7fRhDNm7hwmW9VajgdJn+87YdFAo/OMSmEfqj5AqVmlDn3BuKpLwGMFMR+tLQK3O64HIxidsFeuDcDHuXm8geHbAACHkUOs9UqAjNsPJXs2rl2lekCcelpHKDvfvqdlXVAoGAAX0Sd9R0GNeIIpIJCmzfX3dLyrJLVHAixwGbj2qkLBI1jrtArTqa6ewsg80DyevaeNlnIdFacWSlVASYOaCEFCoaXSbVvYRgR6OoWIvqxStTAU2a/l4QsUA1GRt5yGE6aN85BjnBVJDMgeytt1BIl9NqoZ3nCLFVBgavgm3Z/JcCgYBjzpcZKIMi7GrP/o4UUcIToBXjVx+eGnyJoCbNrCsxs2y4aYQQmQE/kjgjskCSQmlgc0nKggt2oQb0Ua7+OT+e9SD5xT5Zuk6AuuihGh/CQal8OJnftE60qJNi4xenu8YKgY+9aoOAoaywjGJQG/PAstP/h7ddzC/sdhjdv4SRMQ==","optyAppId":"lgsy777@qq.com"}
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
         * optyPublicKey : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcXId/JXiRsTW0XPKrDT/5enqCeo6IWryEYYCzQsn596jNBYeu34oiOZxeTqnSZue+2kjqoki9Hj8zFSrK3JKRp9ftQyBhnge29Jx6qzLjjOg/D7V1pucrvOl5xfGwpj4MgIhsLeqwCgiVjya9ylxdO039TyPQKcMrr1x3ezbXPwIDAQAB
         * optyNotifyUrl : http://localhost:8080/jiaoyi/mallOrder/alipaynotify.do
         * optyMchId : 2017060507425498
         * optyAppKey : r060u1skulsot2ite18k4kp8tfdm5478
         * optyPrivateKey : MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCkF+hnB2dAgIqrVJpUpOgCphW9jPYOVBJsdWSMhZXR13iTspbI/3TIGf4hqHVQg1tn7jROZZcjOlrF/hBHbk6v465Xc+FJ0qKIeclX4pJxi5GNskKdZyEm5G03vcPdDFbP8/cP+o/AL64ENAVrCseC2NRb8UqbTv4xEBv4ONjaJRptDiV1d1HvotMC4PqSTET6CX6DYu3stNSF7zkymvPBkh7RhWmk7HCYkyNprHirrOFqWHmQVQz57QhPMUS9CsUrKPsZIv5080LTFbjHkve8giLU29tCjaFvMaYcCekJD53Xxx6gQ9YEVlF7U+7SYyvGvJVPqDHr2VWhgCKQ3APAgMBAAECggEAUH9e1q7/+wt/9nwB8Xm+FtAxeHw3HrrHa4g/GvHSoGEZ2yuky2h3WDIEqE+pe2hMCyiMtEKDF/CxARw4LT18NGJiRps2FOxSorOgZYRwKGgNo7CnZtF4fLHFtfQ58sc6nv3K1Ap8C/gTnjyPbq1FsDJpO45jy3KppQiqic/noiO4HIrqy6BJjc5poHTQjmHB+ADJ9EP+jJcKPDa6apxVCuLx5iT1fwhl8GY3LmZxguO6J7Mk9lOIH5YIXymcQIr3ryFE5rPdmBmgFHsvWzDCDfxj8ioarLTs0UrFam6Q4zqb1zBzE0niVUl+2Vu97YSLB091Az1wqJKsO0qU7tjlcQKBgQDGYCEuBJxysf7nkvsh+V5Zqd/lcB9F6GaYOv0F55gH6X2TLrOhgFYttGe95VmL380D39e5gOE97R0DeHcjBmpvebEOR61+Dm+np9cF+9zZLHBPzdViUtU0B3bbKn40f9L4pjLpSONFcLNEcgL+BVeD9+HxqjAJoQerZjEqmN/HNQKBgQCofYyOxu5x6I5qMHaylj0qTJq/CxCu2hIwzknZLj42/oJn0cFtxpWtW7d6Q+iJyjuOwaZXwjcn1P0yz+n9rL9OVuSzJ763+30iBGo0SfpKUlC/Rm6F/rM1Az/Ou9yQlcr8vhM12ALLcTh4yXUqANfQQME7CnT9ER45WYym2NVOswKBgFJcDMp3PaDcx3DLdBo51vMw9PTj5Qf4IcgoDW6LJXJ7fRhDNm7hwmW9VajgdJn+87YdFAo/OMSmEfqj5AqVmlDn3BuKpLwGMFMR+tLQK3O64HIxidsFeuDcDHuXm8geHbAACHkUOs9UqAjNsPJXs2rl2lekCcelpHKDvfvqdlXVAoGAAX0Sd9R0GNeIIpIJCmzfX3dLyrJLVHAixwGbj2qkLBI1jrtArTqa6ewsg80DyevaeNlnIdFacWSlVASYOaCEFCoaXSbVvYRgR6OoWIvqxStTAU2a/l4QsUA1GRt5yGE6aN85BjnBVJDMgeytt1BIl9NqoZ3nCLFVBgavgm3Z/JcCgYBjzpcZKIMi7GrP/o4UUcIToBXjVx+eGnyJoCbNrCsxs2y4aYQQmQE/kjgjskCSQmlgc0nKggt2oQb0Ua7+OT+e9SD5xT5Zuk6AuuihGh/CQal8OJnftE60qJNi4xenu8YKgY+9aoOAoaywjGJQG/PAstP/h7ddzC/sdhjdv4SRMQ==
         * optyAppId : lgsy777@qq.com
         */

        private String optyPublicKey;
        private String optyNotifyUrl;
        private String optyMchId;
        private String optyAppKey;
        private String optyPrivateKey;
        private String optyAppId;

        public String getOptyPublicKey() {
            return optyPublicKey;
        }

        public void setOptyPublicKey(String optyPublicKey) {
            this.optyPublicKey = optyPublicKey;
        }

        public String getOptyNotifyUrl() {
            return optyNotifyUrl;
        }

        public void setOptyNotifyUrl(String optyNotifyUrl) {
            this.optyNotifyUrl = optyNotifyUrl;
        }

        public String getOptyMchId() {
            return optyMchId;
        }

        public void setOptyMchId(String optyMchId) {
            this.optyMchId = optyMchId;
        }

        public String getOptyAppKey() {
            return optyAppKey;
        }

        public void setOptyAppKey(String optyAppKey) {
            this.optyAppKey = optyAppKey;
        }

        public String getOptyPrivateKey() {
            return optyPrivateKey;
        }

        public void setOptyPrivateKey(String optyPrivateKey) {
            this.optyPrivateKey = optyPrivateKey;
        }

        public String getOptyAppId() {
            return optyAppId;
        }

        public void setOptyAppId(String optyAppId) {
            this.optyAppId = optyAppId;
        }
    }
}
