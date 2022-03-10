package com.fjgcxy.xyb.auto.clock.in.utils;

import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserXybAccountMapper;
import com.fjgcxy.xyb.auto.clock.in.model.constant.Constants;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUserXybAccount;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybLogin;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class XybAccountLoginManager {

    private final TbUserXybAccountMapper tbUserXybAccountMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final XybApi xybApi;

    public XybAccountLoginManager(TbUserXybAccountMapper tbUserXybAccountMapper, RedisTemplate<String, Object> redisTemplate, XybApi xybApi) {
        this.tbUserXybAccountMapper = tbUserXybAccountMapper;
        this.redisTemplate = redisTemplate;
        this.xybApi = xybApi;
    }


    public boolean isLogin(Integer accountId) {
        HashOperations<String, Integer, Object> hashOperations = redisTemplate.opsForHash();
        Object value = hashOperations.get(Constants.LOGIN_XYB_ACCOUNT, accountId);
        return value != null;
    }


    public VoXybLogin getSessionId(Integer accountId, boolean isReLogin) {
        HashOperations<String, Integer, Object> hashOperations = redisTemplate.opsForHash();
        VoXybLogin value = (VoXybLogin) hashOperations.get(Constants.LOGIN_XYB_ACCOUNT, accountId);
        if (value == null || isReLogin) {
            // 登录
            TbUserXybAccount tbUserXybAccount = tbUserXybAccountMapper.selectByPrimaryKey(accountId);
            if (tbUserXybAccount == null) {
                throw new BaseException("未找到校友邦账号信息.");
            }
            value = xybApi.loginXyb(tbUserXybAccount.getXybUsername(), tbUserXybAccount.getXybPassword());
            if (value == null) {
                throw new BaseException("校友邦账号登录失败");
            }
            hashOperations.put(Constants.LOGIN_XYB_ACCOUNT, accountId, value);
        }
        return value;
    }


}
