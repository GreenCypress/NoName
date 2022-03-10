package com.fjgcxy.xyb.auto.clock.in.init;

import com.fjgcxy.xyb.auto.clock.in.mapper.TbClockInTaskMapper;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceAutoClockInTask;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class InitTask implements ServletContextListener {

    private final TbClockInTaskMapper tbClockInTaskMapper;
    private final IServiceAutoClockInTask serviceAutoClockInTask;

    public InitTask(TbClockInTaskMapper tbClockInTaskMapper, IServiceAutoClockInTask serviceAutoClockInTask) {
        this.tbClockInTaskMapper = tbClockInTaskMapper;
        this.serviceAutoClockInTask = serviceAutoClockInTask;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<TbClockInTask> tbClockInTasks = tbClockInTaskMapper.selectByState(0);

        for (TbClockInTask tbClockInTask : tbClockInTasks) {
            //
            serviceAutoClockInTask.startTask(tbClockInTask.getUserId(), tbClockInTask.getId());
        }

//        System.out.println("init");
        //在这里做数据初始化操作
    }
}
