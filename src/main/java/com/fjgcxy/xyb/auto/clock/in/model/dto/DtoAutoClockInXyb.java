package com.fjgcxy.xyb.auto.clock.in.model.dto;

import lombok.Data;

@Data
public class DtoAutoClockInXyb {

    /**
     * 校友邦账号id
     */
    private Integer xybAccountId;

//    /**
//     * 经纬度
//     */
//    private String lat;
//
//    /**
//     * 经纬度
//     */
//    private String lng;


//    /**
//     * 实习id
//     */
//    private String planId;

//
//    /**
//     * 打卡地址
//     */
//    private String address;


    /**
     * 设备名称
     */
    private String deviceName;


    /**
     * 每日打卡时间  格式 HH:mm:ss
     */
    private String clockInTime;



    /**
     * 开始的打卡的日期  yyyy-MM-dd
     */
    private String startDate;


}
