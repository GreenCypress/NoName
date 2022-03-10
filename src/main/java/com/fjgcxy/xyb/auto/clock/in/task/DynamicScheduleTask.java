package com.fjgcxy.xyb.auto.clock.in.task;

import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbClockInTaskMapper;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserMapper;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoMail;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUser;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybLogin;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceMail;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import com.fjgcxy.xyb.auto.clock.in.utils.XybApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
@Configuration
@EnableScheduling
public class DynamicScheduleTask {


    private final TbClockInTaskMapper tbClockInTaskMapper;
    private final XybApi xybApi;
    private final XybAccountLoginManager xybAccountLoginManager;
    private final TbUserMapper tbUserMapper;
    private final IServiceMail serviceMail;

    public DynamicScheduleTask(TbClockInTaskMapper tbClockInTaskMapper, XybApi xybApi, XybAccountLoginManager xybAccountLoginManager, TbUserMapper tbUserMapper, IServiceMail serviceMail) {
        this.tbClockInTaskMapper = tbClockInTaskMapper;
        this.xybApi = xybApi;
        this.xybAccountLoginManager = xybAccountLoginManager;
        this.tbUserMapper = tbUserMapper;
        this.serviceMail = serviceMail;
    }


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(200);
        taskExecutor.setThreadNamePrefix("ClockIn-ThreadPool-");
        return taskExecutor;
    }






}
