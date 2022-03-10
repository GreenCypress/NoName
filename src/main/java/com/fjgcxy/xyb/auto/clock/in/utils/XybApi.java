package com.fjgcxy.xyb.auto.clock.in.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.exception.NoAuthException;
import com.fjgcxy.xyb.auto.clock.in.exception.XybNoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoAutoClockInXyb;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import com.fjgcxy.xyb.auto.clock.in.model.vo.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 校友邦 API
 */
@Slf4j
@Component
public class XybApi {

    private final OkHttpClient okHttpClient;

    private final static String API_URL_PROJECT = "https://xcx.xybsyw.com/student/progress/ProjectList.action";

    private final static String API_URL_LOGIN = "https://xcx.xybsyw.com/login/login.action";

    private final static String API_URL_CLOCK_IN = "https://xcx.xybsyw.com/student/clock/Post!updateClock.action";

    private final static String API_URL_CLOCK_IN_NEW = "https://xcx.xybsyw.com/student/clock/PostNew.action";

    private final static String API_URL_GET_PLAN = "https://xcx.xybsyw.com/student/clock/GetPlan!getDefault.action";

    private final static String API_URL_GET_PLAN_POST_INFO = "https://xcx.xybsyw.com/student/clock/GetPlan!detail.action";


    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public XybApi(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public VoXybProject queryXybProject(String jsessionid) throws XybNoAuthException {
        log.info(String.format("JSESSIONID=%s", jsessionid));
        Request request = new Request.Builder()
                .url(API_URL_PROJECT)
                .addHeader("Cookie", String.format("JSESSIONID=%s;", jsessionid))
                .post(new FormBody.Builder().build())
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            ResponseBody body = execute.body();
            if (body == null) {
                throw new BaseException("请求失败, Response Body is Null.");
            }
            VoXybProject voXybProject = OBJECT_MAPPER.readValue(body.bytes(), VoXybProject.class);
            log.info("校友邦 Project：{}", voXybProject.toString());
            if (voXybProject.getCode().equals("205")) {
                throw new XybNoAuthException();
            }
            return voXybProject;
        } catch (IOException e) {
            throw new BaseException("请求失败.");
        }
    }


    public VoXybLogin loginXyb(String username, String password) {
        FormBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))
                .add("openId", "null")
                .add("unionId", "null")
                .add("model", "iPhone X")
                .add("platform", "mac")
                .add("system", "macOs")
                .build();

        Request request = new Request.Builder()
                .url(API_URL_LOGIN)
                .post(formBody)
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            ResponseBody body = execute.body();
            if (body == null) {
                throw new BaseException("请求失败, Response Body is Null.");
            }
            VoXybLogin voXybLogin = OBJECT_MAPPER.readValue(body.bytes(), VoXybLogin.class);
            if (!voXybLogin.getCode().equals("200")) {
                throw new BaseException(voXybLogin.getMsg());
            }
            return voXybLogin;
        } catch (IOException e) {
            throw new BaseException("请求失败.");
        }
    }

    public VoXybClockInNew clockInNew(String jsessionid, TbClockInTask dtoAutoClockInXyb) throws XybNoAuthException {
        FormBody formBody = new FormBody.Builder()
                .add("traineeId", dtoAutoClockInXyb.getTraineeId())
                .add("adcode", "null")
                .add("lat", dtoAutoClockInXyb.getLat())
                .add("lng", dtoAutoClockInXyb.getLon())
                .add("address", dtoAutoClockInXyb.getAddress())
                .add("deviceName", dtoAutoClockInXyb.getDeviceName())
                .add("punchInStatus", "1")
                .add("clockStatus", "2")
                .add("imgUrl", "")
                .add("reason", "")
                .build();

        Request request = new Request.Builder()
                .url(API_URL_CLOCK_IN_NEW)
                .addHeader("Cookie", String.format("JSESSIONID=%s", jsessionid))
                .post(formBody)
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            ResponseBody body = execute.body();
            if (body == null) {
                throw new BaseException("请求失败, Response Body is Null.");
            }
            VoXybClockInNew voXybClockInNew = OBJECT_MAPPER.readValue(body.bytes(), VoXybClockInNew.class);
            log.info(voXybClockInNew.toString());
            if (voXybClockInNew.getCode().equals("205")) {
                throw new XybNoAuthException();
            }
            if (!voXybClockInNew.getCode().equals("200")) {
                throw new BaseException(voXybClockInNew.getMsg());
            }
            return voXybClockInNew;
        } catch (IOException e) {
            throw new BaseException("请求失败.");
        }
    }

    public VoXybGetPlan getPlan(String planId, String jsessionid) throws XybNoAuthException {
        FormBody formBody = new FormBody.Builder()
                .add("planId", planId)
                .build();
        Request request = new Request.Builder()
                .url(API_URL_GET_PLAN)
                .addHeader("Cookie", String.format("JSESSIONID=%s", jsessionid))
                .post(formBody)
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            ResponseBody body = execute.body();
            if (body == null) {
                throw new BaseException("请求失败, Response Body is Null.");
            }
            VoXybGetPlan voXybGetPlan = OBJECT_MAPPER.readValue(body.bytes(), VoXybGetPlan.class);
            log.info(voXybGetPlan.toString());
            if (voXybGetPlan.getCode().equals("205")) {
                throw new XybNoAuthException();
            }
            if (!voXybGetPlan.getCode().equals("200")) {
                throw new BaseException(voXybGetPlan.getMsg());
            }
            return voXybGetPlan;
        } catch (IOException e) {
            throw new BaseException("请求失败.");
        }
    }

    public VoXybGetPlanPostInfo getPlanPostInfo(String traineeId, String jsessionid) throws XybNoAuthException {
        FormBody formBody = new FormBody.Builder()
                .add("traineeId", traineeId)
                .build();
        Request request = new Request.Builder()
                .url(API_URL_GET_PLAN_POST_INFO)
                .addHeader("Cookie", String.format("JSESSIONID=%s", jsessionid))
                .post(formBody)
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            ResponseBody body = execute.body();
            if (body == null) {
                throw new BaseException("请求失败, Response Body is Null.");
            }
            VoXybGetPlanPostInfo voXybGetPlan = OBJECT_MAPPER.readValue(body.bytes(), VoXybGetPlanPostInfo.class);
            log.info(voXybGetPlan.toString());
            if (voXybGetPlan.getCode().equals("205")) {
                throw new XybNoAuthException();
            }
            if (!voXybGetPlan.getCode().equals("200")) {
                throw new BaseException(voXybGetPlan.getMsg());
            }
            return voXybGetPlan;
        } catch (IOException e) {
            throw new BaseException("请求失败.");
        }
    }


}
