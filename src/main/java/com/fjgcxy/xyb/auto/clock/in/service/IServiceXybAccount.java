package com.fjgcxy.xyb.auto.clock.in.service;

import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybAccount;

public interface IServiceXybAccount {
    VoXybAccount addAccount(Integer userId, String username, String password);

    VoXybAccount editAccount(Integer userId, Integer accountId, String username, String password);

    VoXybAccount reLoginAccount(Integer userId, Integer accountId);

}
