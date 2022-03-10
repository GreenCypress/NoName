package com.fjgcxy.xyb.auto.clock.in.service;

import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoRegister;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoLogin;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoUserInfo;

public interface IServiceLogin {

    VoLogin login(String userName, String password, String verifyCode);

    VoLogin register(DtoRegister dtoRegister);

}
