package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VoXybClockIn {


    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    private Object data;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("mstv")
    private MstvDTO mstv;

    @NoArgsConstructor
    @Data
    public static class MstvDTO {
        @JsonProperty("t")
        private Integer t;
        @JsonProperty("m")
        private String m;
        @JsonProperty("s")
        private String s;
    }
}
