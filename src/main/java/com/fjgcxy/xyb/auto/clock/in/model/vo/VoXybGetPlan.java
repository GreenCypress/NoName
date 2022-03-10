package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VoXybGetPlan {


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
        @JsonProperty("clockVo")
        private ClockVoDTO clockVo;
        @JsonProperty("hasMore")
        private Boolean hasMore;
        @JsonProperty("clockType")
        private String clockType;

        @NoArgsConstructor
        @Data
        public static class ClockVoDTO {
            @JsonProperty("dateName")
            private Object dateName;
            @JsonProperty("endDate")
            private String endDate;
            @JsonProperty("moduleName")
            private String moduleName;
            @JsonProperty("planName")
            private String planName;
            @JsonProperty("projectName")
            private String projectName;
            @JsonProperty("startDate")
            private String startDate;
            @JsonProperty("traineeId")
            private Integer traineeId;
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
