package com.fjgcxy.xyb.auto.clock.in.service.impl;

import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.mapper.TbUserXybAccountMapper;
import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUserXybAccount;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybAccount;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoXybLogin;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceXybAccount;
import com.fjgcxy.xyb.auto.clock.in.utils.BeanCopyUtils;
import com.fjgcxy.xyb.auto.clock.in.utils.XybAccountLoginManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
public class ServiceXybAccount implements IServiceXybAccount {


    private final TbUserXybAccountMapper tbUserXybAccountMapper;

    private final XybAccountLoginManager xybAccountLoginManager;

    public ServiceXybAccount(TbUserXybAccountMapper tbUserXybAccountMapper, XybAccountLoginManager xybAccountLoginManager) {
        this.tbUserXybAccountMapper = tbUserXybAccountMapper;
        this.xybAccountLoginManager = xybAccountLoginManager;
    }

    @Override
    @Transactional
    public VoXybAccount addAccount(Integer userId, String username, String password) {
        TbUserXybAccount tbUserXybAccount = tbUserXybAccountMapper.selectOneByUserIdAndXybUsername(userId, username);
        if (tbUserXybAccount != null) {
            throw new BaseException("该校友邦账号已经被添加.");
        }

        tbUserXybAccount = new TbUserXybAccount();
        tbUserXybAccount.setXybUsername(username);
        tbUserXybAccount.setXybPassword(password);
        tbUserXybAccount.setState(1);
        tbUserXybAccount.setUserId(userId);
        tbUserXybAccountMapper.insert(tbUserXybAccount);

        // 登录到校友邦
        VoXybLogin voXybLogin = xybAccountLoginManager.getSessionId(tbUserXybAccount.getId(), true);

        VoXybAccount copy = BeanCopyUtils.copy(tbUserXybAccount, VoXybAccount.class);
        assert copy != null;
        copy.setLoginInfo(voXybLogin.getData());
        return copy;
    }

    @Override
    public VoXybAccount editAccount(Integer userId, Integer accountId, String username, String password) {
        TbUserXybAccount tbUserXybAccount = tbUserXybAccountMapper.selectByPrimaryKey(accountId);
        if (tbUserXybAccount == null) {
            throw new BaseException("该校友邦账号不存在.");
        }
        if (!Objects.equals(tbUserXybAccount.getUserId(), userId)) {
            throw new BaseException("不是您的校友邦账号，无法编辑.");
        }
        tbUserXybAccount.setXybUsername(username);
        tbUserXybAccount.setXybPassword(password);
        tbUserXybAccountMapper.updateByPrimaryKey(tbUserXybAccount);

        // 登录到校友邦
        VoXybLogin voXybLogin = xybAccountLoginManager.getSessionId(tbUserXybAccount.getId(), false);

        VoXybAccount copy = BeanCopyUtils.copy(tbUserXybAccount, VoXybAccount.class);
        assert copy != null;
        copy.setLoginInfo(voXybLogin.getData());
        return copy;
    }

    @Override
    public VoXybAccount reLoginAccount(Integer userId, Integer accountId) {
        TbUserXybAccount tbUserXybAccount = tbUserXybAccountMapper.selectByPrimaryKey(accountId);
        if (tbUserXybAccount == null) {
            throw new BaseException("该校友邦账号不存在.");
        }
        // 登录到校友邦
        VoXybLogin voXybLogin = xybAccountLoginManager.getSessionId(tbUserXybAccount.getId(), true);
        VoXybAccount copy = BeanCopyUtils.copy(tbUserXybAccount, VoXybAccount.class);
        assert copy != null;
        copy.setLoginInfo(voXybLogin.getData());
        return copy;
    }
}
