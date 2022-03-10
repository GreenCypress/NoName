package com.fjgcxy.xyb.auto.clock.in.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMail {

    /**
     * 接受邮箱账户
     */
    private String mail;

    /**
     * 邮箱标题
     */
    private String title;

    /**
     * 要发送的内容
     */
    private String content;

}
