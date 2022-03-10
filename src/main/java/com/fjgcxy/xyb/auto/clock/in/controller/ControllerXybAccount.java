package com.fjgcxy.xyb.auto.clock.in.controller;


import com.fjgcxy.xyb.auto.clock.in.auth.anno.AuthLogin;
import com.fjgcxy.xyb.auto.clock.in.model.vo.BaseResponse;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybAccount;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceXybAccount;
import com.fjgcxy.xyb.auto.clock.in.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "校友邦 - 账号管理")
@RestController
public class ControllerXybAccount {

    private final IServiceXybAccount serviceXybAccount;

    public ControllerXybAccount(IServiceXybAccount serviceXybAccount) {
        this.serviceXybAccount = serviceXybAccount;
    }

    /**
     * 添加 校友邦 账号密码
     *
     * @param xybUsername 校友邦账号
     * @param xybPassword 校友邦密码
     * @return 校友邦账号密码信息
     */
    @AuthLogin
    @ApiOperation("添加校友邦账号密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @GetMapping("/xyb/account/add/{xybUsername}/{xybPassword}")
    public BaseResponse<VoXybAccount> addXybAccount(
            HttpServletRequest request,
            @PathVariable String xybUsername, @PathVariable String xybPassword) {
        return BaseResponse.ok(serviceXybAccount.addAccount(TokenUtils.getUserId(request), xybUsername, xybPassword));
    }


    /**
     * 编辑 校友邦 账号密码
     *
     * @param accountId   账号标识
     * @param xybUsername 校友邦账号
     * @param xybPassword 校友邦密码
     * @return 校友邦账号密码信息
     */
    @AuthLogin
    @ApiOperation("更新校友邦账号密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @PutMapping("/xyb/account/update/{accountId}/{xybUsername}/{xybPassword}")
    public BaseResponse<VoXybAccount> updateXybAccount(
            HttpServletRequest request,
            @PathVariable Integer accountId, @PathVariable String xybUsername, @PathVariable String xybPassword) {
        return BaseResponse.ok(serviceXybAccount.editAccount(TokenUtils.getUserId(request), accountId, xybUsername, xybPassword));
    }


    /**
     * 编辑 校友邦 账号密码
     *
     * @param accountId   账号标识
     * @return 校友邦账号密码信息
     */
    @AuthLogin
    @ApiOperation("重新登录校友邦账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @PutMapping("/xyb/account/re-login/{accountId}")
    public BaseResponse<VoXybAccount> reLoginXybAccount(
            HttpServletRequest request,
            @PathVariable Integer accountId) {
        return BaseResponse.ok(serviceXybAccount.reLoginAccount(TokenUtils.getUserId(request), accountId));
    }

}
