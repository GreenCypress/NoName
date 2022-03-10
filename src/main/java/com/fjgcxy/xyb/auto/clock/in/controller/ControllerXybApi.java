package com.fjgcxy.xyb.auto.clock.in.controller;


import com.fjgcxy.xyb.auto.clock.in.auth.anno.AuthLogin;
import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.vo.BaseResponse;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybProject;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import com.fjgcxy.xyb.auto.clock.in.utils.XybApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取校友邦实习API
 */
@Api(tags = "获取校友邦实习API")
@RestController
public class ControllerXybApi {

    private final XybApi xybApi;
    private final XybAccountLoginManager xybAccountLoginManager;

    public ControllerXybApi(XybApi xybApi, XybAccountLoginManager xybAccountLoginManager) {
        this.xybApi = xybApi;
        this.xybAccountLoginManager = xybAccountLoginManager;
    }

    /**
     * 获取 校友邦 实习信息列表
     *
     * @return
     */
    @AuthLogin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "登录Token", paramType = "header", dataTypeClass = String.class)
    })
    @ApiOperation("获取 校友邦 实习信息列表")
    @GetMapping("/trainee/xyb/list/{accountId}")
    public BaseResponse<VoXybProject> queryXybTraineeList(@PathVariable Integer accountId) {
        try {
            return BaseResponse.ok(xybApi.queryXybProject(xybAccountLoginManager.getSessionId(accountId, false).getData().getSessionId()));
        } catch (XybNoAuthException e) {
            return BaseResponse.no("请重新登录校友邦账号.");
        }
    }


}
