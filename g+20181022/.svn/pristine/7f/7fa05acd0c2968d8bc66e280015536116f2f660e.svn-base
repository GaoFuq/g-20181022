package com.code.clkj.menggong.throwable;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.HttpException;


/**
 * 作者:caoyang
 * 创建时间:2017/7/14 15:39
 * 描述:接口 异常
 */

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, ERROR.SOCKET_TIMEOUT);
            ex.message = "链接超时";
            return ex;
        } else if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            /*ex = new ApiException(e, ERROR.HTTP_ERROR);*/
            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "未授权访问数据";
                    break;
                case FORBIDDEN:
                    ex.message = "服务器拒绝请求";
                    break;
                case NOT_FOUND:
                    ex.message = "服务器找不到请求的网页";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case BAD_GATEWAY:
                    ex.message = "错误网关";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务不可用";
                default:
                    ex.message = "网络错误";  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";  //均视为网络错误
            return ex;
        } else {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.message = "未知错误";          //未知错误
            return ex;
        }
    }


    public static class ApiException extends Exception {
        public int code;
        public String message;

        @Override
        public String toString() {
            return "ApiException{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    "} " + super.toString();
        }

        public ApiException(Throwable throwable, int code) {
            super(throwable);
            this.code = code;

        }
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }

    /**
     * 约定异常
     */

    public class ERROR {

        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         *
         */
        public static final int  SOCKET_TIMEOUT=1003;
       /* *//**
         * 协议出错
         *//*
        public static final int HTTP_ERROR = 1003;*/
    }
}
