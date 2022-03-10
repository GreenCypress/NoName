package com.fjgcxy.xyb.auto.clock.in.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbUserXybAccount {
    private Integer id;

    private Integer userId;

    private String xybUsername;

    private String xybPassword;

    private Integer state;
}