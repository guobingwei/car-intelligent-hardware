package com.autocar.intelligent.hardware.provider.response;

import com.alibaba.fastjson.JSONObject;
import com.autocar.intelligent.hardware.domain.enums.BizExceptionEnum;

import java.io.Serializable;

/**
 * Created by Seven on 11/3/16 12:04
 * mail: lizhongpin@meituan.com
 * 返回的数据格式
 */
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -2465140747749709626L;
    private boolean success;
    private int status;
    private String msg;
    private T data;

    public ApiResponse() {
    }


    private static ApiResponse build(Object data, boolean isSuccess, int code, String msg) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setSuccess(isSuccess);
        response.setStatus(code);
        response.setMsg(msg);
        return response;
    }

    public static ApiResponse buildSuccess() {
        return buildSuccess(null);
    }

    public static ApiResponse buildSuccess(Object data) {
        return buildSuccess(BizExceptionEnum.SUCCESS, data);
    }

    public static ApiResponse buildSuccess(BizExceptionEnum bizExceptionEnum, Object data) {
        return build(data, true, bizExceptionEnum.getCode(), bizExceptionEnum.getError());
    }

    public static ApiResponse buildFailure() {
        return build(null, false, BizExceptionEnum.FAIL.getCode(), BizExceptionEnum.FAIL.getError());
    }

    public static ApiResponse buildFailure(String errorMsg) {
        return build(null, false, BizExceptionEnum.FAIL.getCode(), errorMsg);
    }

    public static ApiResponse buildFailure(int errorCode, String errorMsg) {
        return build(null, false, errorCode, errorMsg);
    }
     public static ApiResponse buildFailure(BizExceptionEnum exceptionEnum) {
        return build(null, false, exceptionEnum.getCode(), exceptionEnum.getError());
    }


    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJsonString() {
        JSONObject jo = new JSONObject();
        jo.put("msg", this.getMsg());
        jo.put("status", this.getStatus());
        jo.put("data", this.getData());
        return  jo.toJSONString();
    }

}
