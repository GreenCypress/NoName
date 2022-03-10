package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VoXybGetPlanPostInfo {

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
    public static class DataDTO {
        @JsonProperty("clockRuleType")
        private Integer clockRuleType;
        @JsonProperty("openEpidemicSituation")
        private Boolean openEpidemicSituation;
        @JsonProperty("clockInfo")
        private ClockInfoDTO clockInfo;
        @JsonProperty("postInfo")
        private PostInfoDTO postInfo;
        @JsonProperty("healthCodeStatus")
        private Boolean healthCodeStatus;
        @JsonProperty("healthCodeImg")
        private Boolean healthCodeImg;
        @JsonProperty("reported")
        private Boolean reported;
        @JsonProperty("canSign")
        private Boolean canSign;
        @JsonProperty("locationRiskLevel")
        private Boolean locationRiskLevel;

        @NoArgsConstructor
        @Data
        public static class ClockInfoDTO {
            @JsonProperty("date")
            private String date;
            @JsonProperty("inAddress")
            private String inAddress;
            @JsonProperty("inStatus")
            private Integer inStatus;
            @JsonProperty("inStatusDesc")
            private String inStatusDesc;
            @JsonProperty("inTime")
            private String inTime;
            @JsonProperty("outAddress")
            private String outAddress;
            @JsonProperty("outStatus")
            private Integer outStatus;
            @JsonProperty("outStatusDesc")
            private String outStatusDesc;
            @JsonProperty("outTime")
            private String outTime;
            @JsonProperty("status")
            private Integer status;
            @JsonProperty("week")
            private String week;
        }

        @NoArgsConstructor
        @Data
        public static class PostInfoDTO {
            @JsonProperty("address")
            private String address;
            @JsonProperty("clock")
            private Integer clock;
            @JsonProperty("compare")
            private Integer compare;
            @JsonProperty("distance")
            private Integer distance;
            @JsonProperty("lat")
            private Double lat;
            @JsonProperty("lng")
            private Double lng;
            @JsonProperty("state")
            private Integer state;
        }
    }

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
