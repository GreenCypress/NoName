package com.fjgcxy.xyb.auto.clock.in.controller;

import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoRegister;
import com.fjgcxy.xyb.auto.clock.in.model.vo.BaseResponse;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoLogin;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoUserInfo;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "平台登录")
@RestController
public class ControllerLogin {


    private final IServiceLogin serviceLogin;

    public ControllerLogin(IServiceLogin serviceLogin) {
        this.serviceLogin = serviceLogin;
    }

    /**
     * 登录
     *
     * @param username   用户名
     * @param password   密码
     * @param verifyCode 验证码
     * @return 用户资料
     */
    @ApiOperation("登录")
    @GetMapping("/user/login/{username}/{password}/{verifyCode}")
    public BaseResponse<VoLogin> login(@PathVariable String username, @PathVariable String password, @PathVariable String verifyCode) {
        return BaseResponse.ok(serviceLogin.login(username, password, verifyCode));
    }


    /**
     * 注册
     *
     * @param dtoRegister   注册资料
     * @return 用户资料
     */
    @ApiOperation("注册")
    @PostMapping("/user/register")
    public BaseResponse<VoLogin> register(@RequestBody DtoRegister dtoRegister) {
        return BaseResponse.ok(serviceLogin.register(dtoRegister));
    }



}
