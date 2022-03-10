package com.fjgcxy.xyb.auto.clock.in.controller;

import com.fjgcxy.xyb.auto.clock.in.auth.anno.AuthLogin;
import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoAutoClockInXyb;
import com.fjgcxy.xyb.auto.clock.in.model.vo.BaseResponse;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoAutoClockInTask;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceAutoClockInTask;
import com.fjgcxy.xyb.auto.clock.in.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLParagraphElement;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(tags = "校友邦自动签到任务")
@RestController
public class ControllerAutoClockInTask {

    private final IServiceAutoClockInTask serviceAutoClockInTask;

    public ControllerAutoClockInTask(IServiceAutoClockInTask serviceAutoClockInTask) {
        this.serviceAutoClockInTask = serviceAutoClockInTask;
    }

    /**
     * 获取用户所有自动签到任务列表
     */
    @AuthLogin
    @ApiOperation("获取用户所有自动签到任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @PostMapping("/auto-clock-in/xyb/{pageNum}/{pageSize}")
    public BaseResponse<PageInfo<VoAutoClockInTask>> queryAutoClockList(@PathVariable int pageNum, @PathVariable int pageSize, HttpServletRequest request) {
        return BaseResponse.ok(serviceAutoClockInTask.query(pageNum, pageSize, TokenUtils.getUserId(request)));
    }

    /**
     * 创建 自动签到 任务
     *
     * @param dtoAutoClockInXyb 签到设置
     * @return 任务详情
     */
    @AuthLogin
    @ApiOperation("创建 自动签到 任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @PostMapping("/auto-clock-in/xyb/create")
    public BaseResponse<List<VoAutoClockInTask>> createAutoClockInXyb(
            HttpServletRequest request,
            @RequestBody DtoAutoClockInXyb dtoAutoClockInXyb) {
        try {
            return BaseResponse.ok(serviceAutoClockInTask.createTask(TokenUtils.getUserId(request), dtoAutoClockInXyb));
        } catch (XybNoAuthException e) {
            return BaseResponse.no("请重新登录校友邦.");
        }
    }


    /**
     * 暂停 自动签到 任务
     *
     * @param taskId 任务Id
     * @return 任务详情
     */
    @AuthLogin
    @ApiOperation("暂停 自动签到 任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @GetMapping("/auto-clock-in/xyb/suspended/{taskId}")
    public BaseResponse<VoAutoClockInTask> suspendedAutoClockInXyb(HttpServletRequest request, @PathVariable Integer taskId) {
        serviceAutoClockInTask.stopTask(TokenUtils.getUserId(request), taskId);
        return BaseResponse.ok();
    }

    /**
     * 暂停 自动签到 任务
     *
     * @param taskId 任务Id
     * @return 任务详情
     */
    @AuthLogin
    @ApiOperation("开始 自动签到 任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @GetMapping("/auto-clock-in/xyb/start/{taskId}")
    public BaseResponse<Void> startAutoClockInXyb(HttpServletRequest request, @PathVariable Integer taskId) {
        serviceAutoClockInTask.startTask(TokenUtils.getUserId(request), taskId);
        return BaseResponse.ok();
    }


    /**
     * 删除 自动签到 任务
     *
     * @param taskId 任务Id
     * @return 任务详情
     */
    @AuthLogin
    @ApiOperation("删除 自动签到 任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @DeleteMapping("/auto-clock-in/xyb/delete/{taskId}")
    public BaseResponse<Void> deleteAutoClockInXyb(HttpServletRequest request, @PathVariable Integer taskId) {
        serviceAutoClockInTask.deleteTask(TokenUtils.getUserId(request), taskId);
        return BaseResponse.ok();
    }


}
