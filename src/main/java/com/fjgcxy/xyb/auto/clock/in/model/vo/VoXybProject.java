package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
public class VoXybProject {

    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("mstv")
    private MstvDTO mstv;

    @NoArgsConstructor
    @Data
    @ToString
    public static class MstvDTO {
        @JsonProperty("t")
        private Integer t;
        @JsonProperty("m")
        private String m;
        @JsonProperty("s")
        private String s;
    }

    @NoArgsConstructor
    @Data
    @ToString
    public static class DataDTO {
        @JsonProperty("cenProjectFlag")
        private Boolean cenProjectFlag;
        @JsonProperty("douProjectFlag")
        private Boolean douProjectFlag;
        @JsonProperty("indProjectFlag")
        private Boolean indProjectFlag;
        @JsonProperty("moduleId")
        private Integer moduleId;
        @JsonProperty("planId")
        private Integer planId;
        @JsonProperty("planName")
        private String planName;
        @JsonProperty("practiceEnd")
        private Boolean practiceEnd;
        @JsonProperty("projectList")
        private List<ProjectListDTO> projectList;
        @JsonProperty("projectRuleId")
        private Integer projectRuleId;
        @JsonProperty("subscribeAuditFlag")
        private Boolean subscribeAuditFlag;

        @NoArgsConstructor
        @Data
        @ToString
        public static class ProjectListDTO {
            @JsonProperty("dates")
            private List<DatesDTO> dates;
            @JsonProperty("moduleId")
            private Integer moduleId;
            @JsonProperty("moduleName")
            private String moduleName;
            @JsonProperty("projectId")
            private Integer projectId;
            @JsonProperty("projectName")
            private String projectName;
            @JsonProperty("remove")
            private Boolean remove;

            @NoArgsConstructor
            @Data
            @ToString
            public static class DatesDTO {
                @JsonProperty("dateName")
                private Object dateName;
                @JsonProperty("endDate")
                private String endDate;
                @JsonProperty("remove")
                private Boolean remove;
                @JsonProperty("startDate")
                private String startDate;
            }
        }
    }
}
