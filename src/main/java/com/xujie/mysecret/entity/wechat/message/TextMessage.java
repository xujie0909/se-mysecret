package com.xujie.mysecret.entity.wechat.message;

import lombok.Data;

@Data
public class TextMessage extends BaseMessage {
    //回复的消息内容
    private String Content;
}
