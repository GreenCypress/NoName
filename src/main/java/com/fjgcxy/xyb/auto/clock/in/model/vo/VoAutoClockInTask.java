package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VoAutoClockInTask {

    private Integer id;

    private Integer userId;

    private Integer xybAccountId;

    private String lat;

    private String lon;

    private String deviceName;

    private String traineeId;

    private String address;

    private String clockInTimeCron;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private List<ClockInRecord> recordList;

    @Data
    public static class ClockInRecord {
        private Integer id;

        private Integer clockInTaskId;

        private Integer clockStatus;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date clockInTime;

    }

}
