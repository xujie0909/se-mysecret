package com.xujie.mysecret.entity.wechat.message;

import lombok.Data;

@Data
public class BaseMessage {
    //接收方账号(收到的OpenID)
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间(整型)
    private long CreateTime;
    //消息类型(text/music/news)
    private String MsgType;
    //星标刚收到的消息
    private int FuncFlag;
}
