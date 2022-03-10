package com.fjgcxy.xyb.auto.clock.in.service;

import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoAutoClockInXyb;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoAutoClockInTask;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IServiceAutoClockInTask {


    List<VoAutoClockInTask> createTask(Integer user, DtoAutoClockInXyb dtoAutoClockInXyb) throws XybNoAuthException;


    void stopTask(Integer user, Integer taskId);


    void startTask(Integer user, Integer taskId);


    void deleteTask(Integer user, Integer taskId);

    PageInfo<VoAutoClockInTask> query(int pageNum, int pageSize, Integer userId);
}
