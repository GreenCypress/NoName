package com.fjgcxy.xyb.auto.clock.in.model.vo;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "成功"),
    FAIL(500, "服务器出错啦 ~"),
    NO_PERMISSION(401, "该操作没有权限"),
    NO_AUTH(402, "登录态过期, 请重新登录"),
    TIME_OUT(510,"服务器繁忙, 请稍后再试"),
    PARAM_ERROR(1001, "参数错误");

    private int code;
    private String tag;

    ResponseCode(int code, String tag) {
        this.code = code;
        this.tag = tag;
    }
}
