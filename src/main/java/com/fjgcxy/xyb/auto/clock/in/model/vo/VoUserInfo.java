package com.fjgcxy.xyb.auto.clock.in.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VoUserInfo {
    private Integer id;

    private String username;

    private String password;

    private Integer state;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date expireDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String email;

}
