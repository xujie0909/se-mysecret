package com.xujie.mysecret.entity.message;

import com.xujie.mysecret.entity.Video;
import lombok.Data;

@Data
public class VideoMessage extends BaseMessage {
    private Video video;
}
