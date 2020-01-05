package com.xujie.mysecret.entity.message;

import com.xujie.mysecret.entity.Music;
import lombok.Data;

@Data
public class MusicMessage extends BaseMessage {
    //音乐
    private Music music;
}
