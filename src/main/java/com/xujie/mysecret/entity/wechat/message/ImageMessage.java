package com.xujie.mysecret.entity.wechat.message;

import com.xujie.mysecret.entity.wechat.Image;
import lombok.Data;

@Data
public class ImageMessage extends BaseMessage {
    private Image image;
}
