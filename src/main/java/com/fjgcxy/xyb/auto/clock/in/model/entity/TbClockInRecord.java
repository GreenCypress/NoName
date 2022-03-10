package com.fjgcxy.xyb.auto.clock.in.model.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbClockInRecord {
    private Integer id;

    private Integer clockInTaskId;

    private Integer clockStatus;

    private Date clockInTime;
}