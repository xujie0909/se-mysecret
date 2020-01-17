package com.xujie.mysecret.entity.wechat.message;

import com.xujie.mysecret.entity.wechat.Voice;
import lombok.Data;

@Data
public class VoiceMessage extends BaseMessage {
    private Voice voice;
}
