package com.fjgcxy.xyb.auto.clock.in.task;

import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbClockInRecordMapper;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserMapper;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoMail;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInRecord;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUser;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybLogin;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceMail;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import com.fjgcxy.xyb.auto.clock.in.utils.XybApi;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ClockInRunnable implements Runnable {

    private TbClockInTask tbClockInTask;
    private TbUserMapper tbUserMapper;
    private XybAccountLoginManager xybAccountLoginManager;
    private IServiceMail serviceMail;
    private XybApi xybApi;
    private TbClockInRecordMapper tbClockInRecordMapper;

    public ClockInRunnable(TbClockInTask tbClockInTask, TbUserMapper tbUserMapper, XybAccountLoginManager xybAccountLoginManager, IServiceMail serviceMail, XybApi xybApi, TbClockInRecordMapper tbClockInRecordMapper) {
        this.tbClockInTask = tbClockInTask;
        this.tbUserMapper = tbUserMapper;
        this.xybAccountLoginManager = xybAccountLoginManager;
        this.serviceMail = serviceMail;
        this.xybApi = xybApi;
        this.tbClockInRecordMapper = tbClockInRecordMapper;
    }

    @Override
    public void run() {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(tbClockInTask.getUserId());

        // 打卡签到
        VoXybLogin sessionId = xybAccountLoginManager.getSessionId(tbClockInTask.getXybAccountId(), true);
        try {
            xybApi.clockInNew(sessionId.getData().getSessionId(), tbClockInTask);
            saveRecord(1);
            if (tbUser != null) {
                serviceMail.send(new DtoMail(tbUser.getEmail(), "[校友邦自动签到平台]签到成功", "签到成功，签到时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
            }
        } catch (XybNoAuthException e) {
//                               throw new BaseException("校友邦重新登录失败.");
            saveRecord(0);
            if (tbUser != null) {
                serviceMail.send(new DtoMail(tbUser.getEmail(), "[校友邦自动签到平台]签到失败通知", "签到失败!!! 请前往平台重新登录，签到时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
            }

        }
    }


    private void saveRecord(int state) {
        TbClockInRecord tbClockInRecord = new TbClockInRecord();
        tbClockInRecord.setClockInTaskId(tbClockInTask.getId());
        tbClockInRecord.setClockStatus(state);
        tbClockInRecord.setClockInTime(new Date());
        tbClockInRecordMapper.insert(tbClockInRecord);
    }
}
