package com.fjgcxy.xyb.auto.clock.in.model.entity;

import java.util.Date;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TbClockInTask {
    private Integer id;

    private Integer userId;

    private Integer xybAccountId;

    private String lat;

    private String lon;

    private String deviceName;

    private String traineeId;

    private String address;

    private String clockInTimeCron;

    private String clockInDate;

    private Integer state;

    private Date createTime;
}