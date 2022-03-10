package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class VoXybLogin implements Serializable {


    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("mstv")
    private MstvDTO mstv;

    @NoArgsConstructor
    @Data
    public static class DataDTO implements Serializable {
        @JsonProperty("activate")
        private Boolean activate;
        @JsonProperty("sessionId")
        private String sessionId;
        @JsonProperty("needComplete")
        private Boolean needComplete;
        @JsonProperty("loginerId")
        private Integer loginerId;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("loginKey")
        private String loginKey;
    }

    @NoArgsConstructor
    @Data
    public static class MstvDTO implements Serializable {
        @JsonProperty("t")
        private Integer t;
        @JsonProperty("m")
        private String m;
        @JsonProperty("s")
        private String s;
    }
}
