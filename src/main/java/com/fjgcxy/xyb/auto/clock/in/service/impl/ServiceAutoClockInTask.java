package com.fjgcxy.xyb.auto.clock.in.service.impl;

import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbClockInRecordMapper;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbClockInTaskMapper;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserMapper;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoAutoClockInXyb;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInRecord;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoAutoClockInTask;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybGetPlan;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybGetPlanPostInfo;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybProject;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceAutoClockInTask;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceMail;
import com.fjgcxy.xyb.auto.clock.in.task.ClockInRunnable;
import com.fjgcxy.xyb.auto.clock.in.utils.BeanCopyUtils;
import com.fjgcxy.xyb.auto.clock.in.utils.CronUtils;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import com.fjgcxy.xyb.auto.clock.in.utils.XybApi;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ServiceAutoClockInTask implements IServiceAutoClockInTask {

    private final TbClockInTaskMapper tbClockInTaskMapper;
    private final XybApi xybApi;
    private final XybAccountLoginManager xybAccountLoginManager;
    private final TbUserMapper tbUserMapper;
    private final IServiceMail serviceMail;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final TbClockInRecordMapper tbClockInRecordMapper;
    private final static Map<Integer, ScheduledFuture<?>> FUTURE_MAP = new ConcurrentHashMap<>();

    public ServiceAutoClockInTask(TbClockInTaskMapper tbClockInTaskMapper, XybApi xybApi, XybAccountLoginManager xybAccountLoginManager, TbUserMapper tbUserMapper, IServiceMail serviceMail, ThreadPoolTaskScheduler threadPoolTaskScheduler, TbClockInRecordMapper tbClockInRecordMapper) {
        this.tbClockInTaskMapper = tbClockInTaskMapper;
        this.xybApi = xybApi;
        this.xybAccountLoginManager = xybAccountLoginManager;
        this.tbUserMapper = tbUserMapper;
        this.serviceMail = serviceMail;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.tbClockInRecordMapper = tbClockInRecordMapper;
    }

    @Override
    @Transactional
    public List<VoAutoClockInTask> createTask(Integer user, DtoAutoClockInXyb dtoAutoClockInXyb) throws XybNoAuthException {
        TbClockInTask tbClockInTask = tbClockInTaskMapper.selectOneByXybAccountId(dtoAutoClockInXyb.getXybAccountId());
        if (tbClockInTask != null) {
            throw new BaseException("该账号的定时签到任务已存在.");
        }

        String sessionId = xybAccountLoginManager.getSessionId(dtoAutoClockInXyb.getXybAccountId(), false).getData().getSessionId();
        List<VoAutoClockInTask> result = new ArrayList<>();
        // 获取Project列表
        VoXybProject voXybProject = xybApi.queryXybProject(sessionId);
        for (VoXybProject.DataDTO datum : voXybProject.getData()) {
            // 获取planId
            VoXybGetPlan plan = xybApi.getPlan(String.valueOf(datum.getPlanId()), sessionId);
            if (plan == null) {
                throw new BaseException("获取实习任务失败.");
            }
            // 获取打卡地址
            VoXybGetPlanPostInfo planPostInfo = xybApi.getPlanPostInfo(String.valueOf(plan.getData().getClockVo().getTraineeId()), sessionId);
            if (planPostInfo == null) {
                throw new BaseException("获取打卡地址失败.");
            }

            tbClockInTask = new TbClockInTask();
            tbClockInTask.setUserId(user);
            tbClockInTask.setXybAccountId(dtoAutoClockInXyb.getXybAccountId());
            tbClockInTask.setLat(String.valueOf(planPostInfo.getData().getPostInfo().getLat()));
            tbClockInTask.setLon(String.valueOf(planPostInfo.getData().getPostInfo().getLng()));
            tbClockInTask.setDeviceName(dtoAutoClockInXyb.getDeviceName());
            tbClockInTask.setTraineeId(String.valueOf(plan.getData().getClockVo().getTraineeId()));
            tbClockInTask.setAddress(planPostInfo.getData().getPostInfo().getAddress());
            tbClockInTask.setClockInTimeCron(dtoAutoClockInXyb.getClockInTime());
            tbClockInTask.setClockInDate(dtoAutoClockInXyb.getStartDate());
            tbClockInTask.setState(0);
            tbClockInTask.setCreateTime(new Date());
            tbClockInTaskMapper.insert(tbClockInTask);

            // 添加到任务
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new ClockInRunnable(tbClockInTask, tbUserMapper, xybAccountLoginManager, serviceMail, xybApi, tbClockInRecordMapper),
                    new CronTrigger(CronUtils.getCron(dtoAutoClockInXyb.getClockInTime(), dtoAutoClockInXyb.getStartDate())));
            FUTURE_MAP.put(tbClockInTask.getId(), schedule);
            result.add(BeanCopyUtils.copy(tbClockInTask, VoAutoClockInTask.class));
        }
        return result;
    }

    @Override
    public void stopTask(Integer user, Integer taskId) {
        TbClockInTask tbClockInTask = tbClockInTaskMapper.selectByPrimaryKey(taskId);
        if (tbClockInTask == null) {
            throw new BaseException("任务不存在.");
        }
        if (!Objects.equals(tbClockInTask.getUserId(), user)) {
            throw new BaseException("不是您的签到任务.");
        }
        ScheduledFuture<?> scheduledFuture = FUTURE_MAP.get(taskId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            FUTURE_MAP.remove(taskId);
        }
    }

    @Override
    public void startTask(Integer user, Integer taskId) {
        TbClockInTask tbClockInTask = tbClockInTaskMapper.selectByPrimaryKey(taskId);
        if (tbClockInTask == null) {
            throw new BaseException("任务不存在.");
        }
        if (!Objects.equals(tbClockInTask.getUserId(), user)) {
            throw new BaseException("不是您的签到任务.");
        }
        if (FUTURE_MAP.get(taskId) != null) {
            throw new BaseException("任务已存在.");
        }
        // 添加到任务
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new ClockInRunnable(tbClockInTask, tbUserMapper, xybAccountLoginManager, serviceMail, xybApi, tbClockInRecordMapper),
                new CronTrigger(CronUtils.getCron(tbClockInTask.getClockInTimeCron(), tbClockInTask.getClockInDate())));
        FUTURE_MAP.put(tbClockInTask.getId(), schedule);
        log.info("[Task] 创建成功... Message: {}", tbClockInTask);
    }

    @Override
    public void deleteTask(Integer user, Integer taskId) {
        stopTask(user, taskId);
        tbClockInTaskMapper.deleteByPrimaryKey(taskId);
    }

    @Override
    public PageInfo<VoAutoClockInTask> query(int pageNum, int pageSize, Integer userId) {
        Page<TbClockInTask> tbClockInTasks = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> tbClockInTaskMapper.selectByUserId(userId));
        return tbClockInTasks.toPageInfo(item -> {
            VoAutoClockInTask res = BeanCopyUtils.copy(item, VoAutoClockInTask.class);
            ScheduledFuture<?> scheduledFuture = FUTURE_MAP.get(item.getId());
            assert res != null;
            if (scheduledFuture != null){
                log.info(String.valueOf(scheduledFuture.isCancelled()));
            }
            res.setState(scheduledFuture != null && !scheduledFuture.isCancelled() ? 1 : 0);
            List<TbClockInRecord> tbClockInRecords = tbClockInRecordMapper.selectByClockInTaskIdOrderByClockInTimeDesc(item.getId());
            res.setRecordList(tbClockInRecords.stream().map(item1 -> BeanCopyUtils.copy(item1, VoAutoClockInTask.ClockInRecord.class)).collect(Collectors.toList()));
            return res;
        });


    }
}
