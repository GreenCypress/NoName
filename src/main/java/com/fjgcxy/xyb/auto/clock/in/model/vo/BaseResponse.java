package com.fjgcxy.xyb.auto.clock.in.model.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -6862338098267614951L;

    private int code;
    private String msg;
    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <V> BaseResponse<V> ok(String message, V data) {
        return new BaseResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <V> BaseResponse<V> ok() {
        return new BaseResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getTag(), null);
    }

    public static <V> BaseResponse<V> ok(V data) {
        return new BaseResponse<V>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getTag(), data);
    }


    public static <V> BaseResponse<V> no(String message) {
        return new BaseResponse<>(ResponseCode.FAIL.getCode(), message, null);
    }

    public static <V> BaseResponse<V> no(String message, V data) {
        return new BaseResponse<>(ResponseCode.FAIL.getCode(), message, data);
    }

    public static <V> BaseResponse<V> no() {
        return new BaseResponse<>(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getTag(), null);
    }

    public static <V> BaseResponse<V> noAuth() {
        return new BaseResponse<>(ResponseCode.NO_AUTH.getCode(), ResponseCode.NO_AUTH.getTag(), null);
    }

    public static <V> BaseResponse<V> noPermission() {
        return new BaseResponse<>(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getTag(), null);
    }

    public static <V> BaseResponse<V> no(ResponseCode code) {
        return new BaseResponse<>(code.getCode(), code.getTag(), null);
    }

}
