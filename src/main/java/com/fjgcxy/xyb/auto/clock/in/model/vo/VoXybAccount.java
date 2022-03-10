package com.fjgcxy.xyb.auto.clock.in.model.vo;

import lombok.Data;

@Data
public class VoXybAccount {

    private Integer id;

    private Integer userId;

    private String xybUsername;

    private String xybPassword;

    private Integer state;

    private VoXybLogin.DataDTO loginInfo;

}
