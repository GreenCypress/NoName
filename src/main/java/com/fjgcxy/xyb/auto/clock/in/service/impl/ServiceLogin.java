package com.fjgcxy.xyb.auto.clock.in.service.impl;

import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserMapper;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserXybAccountMapper;
import com.fjgcxy.xyb.auto.clock.in.model.constant.Constants;
import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoRegister;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUser;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUserXybAccount;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoLogin;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoUserInfo;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceLogin;
import com.fjgcxy.xyb.auto.clock.in.utils.BeanCopyUtils;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceLogin implements IServiceLogin {

    private final TbUserMapper tbUserMapper;
    private final TbUserXybAccountMapper tbUserXybAccountMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final XybAccountLoginManager xybAccountLoginManager;

    public ServiceLogin(TbUserMapper tbUserMapper, TbUserXybAccountMapper tbUserXybAccountMapper, RedisTemplate<String, Object> redisTemplate, XybAccountLoginManager xybAccountLoginManager) {
        this.tbUserMapper = tbUserMapper;
        this.tbUserXybAccountMapper = tbUserXybAccountMapper;
        this.redisTemplate = redisTemplate;
        this.xybAccountLoginManager = xybAccountLoginManager;
    }


    @Override
    public VoLogin login(String userName, String password, String verifyCode) {
        TbUser tbUser = tbUserMapper.selectOneByUsername(userName);
        if (tbUser == null) {
            throw new BaseException("用户不存在.");
        }
        if (!tbUser.getPassword().equals(password)) {
            throw new BaseException("账号密码错误.");
        }
        String token = Constants.TOKEN_USER_ID_KEY + ":" + tbUser.getId() + ":" + UUID.randomUUID().toString().replace("-", "");
        VoLogin result = new VoLogin();
        result.setToken(token);
        result.setUserInfo(BeanCopyUtils.copy(tbUser, VoUserInfo.class));


        redisTemplate.delete(redisTemplate.keys(Constants.TOKEN_USER_ID_KEY + ":" + tbUser.getId() + ":*"));
        redisTemplate.opsForValue().set(token, result);

        return result;
    }

    @Override
    @Transactional
    public VoLogin register(DtoRegister dtoRegister) {
        TbUser tbUser = tbUserMapper.selectOneByUsername(dtoRegister.getUsername());
        if (tbUser != null) {
            throw new BaseException("该用户已存在.");
        }
        tbUser = new TbUser();
        tbUser.setCreateTime(new Date());
        tbUser.setState(1);
        tbUser.setUsername(dtoRegister.getUsername());
        tbUser.setPassword(dtoRegister.getPassword());
        tbUser.setEmail(dtoRegister.getEmail());
        tbUser.setExpireDate(new Date());
        tbUserMapper.insert(tbUser);
        return login(dtoRegister.getUsername(), dtoRegister.getPassword(), "");
    }
}
