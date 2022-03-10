package com.fjgcxy.xyb.auto.clock.in.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.support.CronTrigger;

public class CronUtils {


    public static String getCron(String time, String date) {
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        String sec = time.split(":")[2];

        if (StringUtils.isBlank(date)) {
            return sec + " " + min + " " + hour + " * * ?";
        } else {
            String day = date.split("-")[2];
            String month = date.split("-")[1];
            String year = date.split("-")[0];
            return sec + " " + min + " " + hour + " " + day + "/1 " + month + " ?";
        }
    }

}
