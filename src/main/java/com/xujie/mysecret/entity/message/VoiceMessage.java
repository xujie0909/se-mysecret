package com.xujie.mysecret.entity.message;

import com.xujie.mysecret.entity.Voice;
import lombok.Data;

@Data
public class VoiceMessage extends BaseMessage {
    private Voice voice;
}
