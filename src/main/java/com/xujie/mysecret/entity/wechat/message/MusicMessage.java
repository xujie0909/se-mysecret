package com.xujie.mysecret.entity.wechat.message;

import com.xujie.mysecret.entity.wechat.Music;
import lombok.Data;

@Data
public class MusicMessage extends BaseMessage {
    //音乐
    private Music music;
}
