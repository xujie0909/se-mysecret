package com.xujie.mysecret.entity.wechat;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeixinMessageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fromUserName;           // 发送方微信账号

    private String toUserName;             // 接收方微信账号

    private String weixinUserName;         // 微信用户名

    private String messageType;            // 消息类型
}
