package com.autocar.intelligent.hardware.domain.enums;

/**
 * Created by Seven on 11/3/16 12:24
 * mail: lizhongpin@meituan.com
 * 业务异常
 */
public enum BizExceptionEnum {
    SUCCESS(200, "success"),
    PARAM_ERROR(101,"参数不正确"),
    UNKNOWN_ERROR(102, "未知异常"),
    REQUEST_PARAM_NOT_BLANK(103, "请求参数不能为空"),
    INT_PARAM_MUST_BIGGER_THAN_ZERO(104, "请求整型参数必须大于0"),;


    private Integer code;
    private String error;


    BizExceptionEnum(Integer code, String error) {
        this.code = code;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BizExceptionEnum{" +
                "code=" + code +
                ", error='" + error + '\'' +
                '}';
    }
}
