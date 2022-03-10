package com.fjgcxy.xyb.auto.clock.in.model.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbUser {
    private Integer id;

    private String username;

    private String password;

    private Integer state;

    private Date expireDate;

    private Date createTime;

    private String email;
}