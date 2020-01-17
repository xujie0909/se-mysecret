package com.xujie.mysecret.entity.wechat.message;

import com.xujie.mysecret.entity.wechat.Video;
import lombok.Data;

@Data
public class VideoMessage extends BaseMessage {
    private Video video;
}
